
import java.util.*;
import java.io.*;

class Solution
{
    static class Group{
        int x, y, micro, d;
        List<Group> duplicate;
        public Group(int x, int y , int micro, int d){
            this.x  =x ;
            this.y =y;
            this.micro = micro;
            this.d= d;
            duplicate = new ArrayList<>();
        }

        public boolean isDup(){ // 다른 그룹을 먹었는 지 확인
            return duplicate.size() > 0;
        }
        public void addDup(Group g){ //이 그룹에 먹힌다.
            duplicate.add(g);
        }
        public void solveDup(){
            //현재까지의 그룹들 중 큰 것으로 방향 변경 및 미생물 수 합산
            duplicate.add(new Group(this.x, this.y, this.micro, this.d)); //자기 자신도 copy 넣는다.
            Collections.sort(duplicate, (o1, o2) -> o2.micro - o1.micro);
            Group win = duplicate.get(0);
            this.d = win.d; //방향 변경
            this.micro = 0; //미생물 수 합산
            for (Group g : duplicate) this.micro += g.micro;
            duplicate.clear();
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
            N  = toInt(st.nextToken()) ;
            M =toInt(st.nextToken()) ;
            K = toInt(st.nextToken());
            for(int i=1; i<=K; i++){
                st = new StringTokenizer(br.readLine());
                int x = toInt(st.nextToken());
                int y = toInt(st.nextToken());
                int micro = toInt(st.nextToken());
                int d = toInt(st.nextToken());
                groupList.add(new Group(x, y, micro, d));
            }

            int [][] curGroup;

            for(int t = 1; t <= M; t++){
                curGroup = new int[N][N]; //중복 여부를 위해 먼저온 그룹의 index를 저장할 배열
                for(int i =0; i<N; i++) Arrays.fill(curGroup[i], -1);

                //군집을 각각 이동시킨다.
                for(int i = 0; i< groupList.size(); i++){
                    Group g = groupList.get(i); //참조 포인터 g
                    g.x += dx[g.d];
                    g.y += dy[g.d];
                    if(isRed(g.x, g.y, N)){ //만약 약품에 도착하면
                        g.micro = reduceMicro(g.micro); //미생물 수 감소
                        g.d = reverseDir(g.d);
                    }
                    if(g.micro == 0){
                        groupList.remove(i);
                        i--;
                        continue;
                    }
                    int alreadyIndex = curGroup[g.x][g.y];
                    if( alreadyIndex != -1){ //만약 다른 그룹이 이미 도착했다면
                        groupList.get(alreadyIndex).addDup(g);
                        groupList.remove(i); //삭제
                        i--;
                    }else{
                        curGroup[g.x][g.y] = i; //현재 위치 기록
                    }
                }
                for (Group group : groupList){
                    if (group.isDup()){
                        group.solveDup();
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