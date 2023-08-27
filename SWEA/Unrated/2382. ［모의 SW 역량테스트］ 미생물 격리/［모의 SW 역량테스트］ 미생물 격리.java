import java.util.*;
import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 미생물 분리
 * point, 미생물이 3개이상 겹칠 경우 우선순위를 두어 미생물이 큰 방향으로 변경하도록 하였는데
 * 이를 구현하기위해 1. 우선순위 반영 정렬, 2. 군집 내부에서 캥거루처럼 먹도록 표현
 * */
class Solution
{
    static class Group implements Comparable<Group>{
        int x, y, micro, d, num;
        List<Group> duplicate;
        public Group(int x, int y , int micro, int d){
            this.x  =x ;
            this.y =y;
            this.micro = micro;
            this.d= d;
            duplicate = new ArrayList<>();
        }

        @Override
        public int compareTo(Group o) {
            //일단 같은 좌표로 정렬 , 같은 좌표 중에 micro가 큰 순으로
            if (this.num == o.num){
                return o.micro - this.micro;
            }
            return this.num - o.num;
            /*
            if(this.x != o.x){
               return Integer.compare(this.x, o.x);
            }else if(this.y != o.y){
               return Integer.compare(this.y, o.y);
            }else if(this.micro != o.micro){
                return - (this.micro - o.micro); //micro 오름차순
            }
            return 0;
            */
        }

        @Override
        public String toString() {
            return "Group{" +
                    "x=" + x +
                    ", y=" + y +
                    ", micro=" + micro +
                    ", d=" + d ;
        }
    }
    static int N, M, K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [] dx ={0,-1,1,0,0}, dy = {0,0,0,-1,1};
    public static void main(String args[]) throws Exception
    {
        int T=toInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            ArrayList<Group> groupList = new ArrayList<>();
            N = toInt(st.nextToken()) ;
            M = toInt(st.nextToken()) ;
            K = toInt(st.nextToken());
            for(int i=1; i<=K; i++){
                st = new StringTokenizer(br.readLine());
                int x = toInt(st.nextToken());
                int y = toInt(st.nextToken());
                int micro = toInt(st.nextToken());
                int d = toInt(st.nextToken());
                groupList.add(new Group(x, y, micro, d));
            }

            for(int t = 1; t <= M; t++){
                //군집을 각각 이동시킨다.
                for(int i = 0; i< groupList.size(); i++){
                    Group g = groupList.get(i); //참조 포인터 g
                    g.x += dx[g.d];
                    g.y += dy[g.d];
                    g.num = g.x * N + g.y;
                    if(isRed(g.x, g.y, N)){ //만약 약품에 도착하면
                        g.micro = reduceMicro(g.micro); //미생물 수 감소
                        g.d = reverseDir(g.d);
                    }
                    if(g.micro == 0){
                        groupList.remove(i);
                        i--;
                    }
                }
                Collections.sort(groupList);

                //합치는 작업
                for(int i=0; i<groupList.size()-1; i++){
                    if(groupList.get(i).num == groupList.get(i+1).num){
                        //어짜피 앞이 가장 미생물이 많을 것.
                        groupList.get(i).micro += groupList.get(i+1).micro;
                        groupList.remove(i+1);
                        i--; //1~3이 같을 지도 모르기 때문에 다시 인덱스를 당겨준다.
                    }

                }
            }//for m
            int sum = 0;
            for(Group g : groupList) sum += g.micro;
            System.out.printf("#%d %d\n", test_case, sum);
        }
    }
    static int toInt(String s){
        return Integer.parseInt(s);
    }
    static int reverseDir(int curDir){
        if( (curDir & 1 ) == 1){
            return curDir + 1;
        }else{
            return curDir - 1;
        }
    }
    static int reduceMicro(int curMicro){
        return  curMicro / 2;
    }
    static boolean isRed(int x, int y, int N){
        return x == 0 || y == 0 || x == N-1 || y==N-1;
    }
}