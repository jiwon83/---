import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
class Main2
{
    static class Node{
        int val, gId, gCnt; //gid = 1부터 유효
        public Node(int val, int gId, int gCnt){
            this.val = val;
            this.gId = gId;
            this.gCnt = gCnt;
        }
    }
    static Queue<int[]> emptyQ = new LinkedList<>();
    static Queue<int[]>  groupQ = new LinkedList<>();

    static Node [][] map;
    static int ans, N, M, groupNum;
    static boolean [][] visit;
    static FastReader sc = new FastReader();
    static int [] dx = {0,0,-1,1}, dy = {-1,1,0,0};
    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        ans = 0;
        map = new Node[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int a = sc.nextInt();
                map[i][j]= new Node( a ,0,0);
                if(map[i][j].val == 0) emptyQ.add(new int[]{i,j});
            }
        }
//        System.out.println("input empty queue  >> " + emptyQ.size());
    }

    static void makeGroup(){
        visit = new boolean[N][M];
        groupNum = 1;
        //1인 모양들을 찾아서 1의 갯수 탐색
        for (int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visit[i][j] || map[i][j].val == 0) continue;
                groupQ = new LinkedList<>();
                visit[i][j] = true;
//                System.out.println("dfs 시작 ( "+i+" , "+j+" )");
                int count = dfs(i,j);

                while (!groupQ.isEmpty()){ //그룹 정보 갱신
                    int [] a= groupQ.poll();
                    map[a[0]][a[1]].gId = groupNum;
                    map[a[0]][a[1]].gCnt = count;
                }
//                System.out.println("새로운 그룹 "+ groupNum+" count = "+ count);
                groupNum++;

            }
        }

    }
    static void selectOne(){
//        System.out.println("selectOne...");
        boolean [] visitG1 = new boolean[groupNum];//그대로 인덱스 사용

        while(!emptyQ.isEmpty()){

            int [] now = emptyQ.poll();
            int willCnt = 1;
//            boolean [] visitG1 = new boolean[groupNum]; **-> 이부분 때문에 시간 초과
            HashSet<Integer> visitG = new HashSet<>();
            for (int i=0; i<4;i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(!inArea(nx, ny)) continue;

                /** 선택지 1 HashSet으로 그룹 visit check */
//                if ( map[nx][ny].gId==0 || visitG.contains( map[nx][ny].gId)) continue;
//                visitG.add( map[nx][ny].gId);
                /** 선택지 2 boolean[][] 그룹 visit check */
                if(visitG1[map[nx][ny].gId] || map[nx][ny].gId==0 ) continue;
                visitG1[map[nx][ny].gId] = true;


                willCnt += map[nx][ny].gCnt;
            }
            /** 선택지 2 boolean[][] 그룹 visit false 처리 */
            for (int i=0; i<4;i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(!inArea(nx, ny)) continue;
                if(map[nx][ny].gId==0 ) continue;
                visitG1[map[nx][ny].gId] = false; //-> 이렇게 되면 false 처리가 문제
            }
//            System.out.println("willCoutn = " + willCnt );
            ans = Math.max(ans, willCnt);
        }

    }

    static int dfs(int x, int y){
        groupQ.add(new int[]{x,y});
        int count =1;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if( inArea(nx, ny) && !visit[nx][ny] && map[nx][ny].val==1){
                visit[nx][ny] = true;
                count += dfs(nx, ny);
            }
        }
        return count;
    }
    static boolean inArea(int x, int y){
        return x >=0 && y >=0 && x <N && y <M;
    }
    public static void main(String[] args) {

        input();
        makeGroup();
        selectOne(); //O(N^2)
        System.out.println(ans);
    }

    static class FastReader {
            BufferedReader br;
            StringTokenizer st;

            public FastReader() {
                br = new BufferedReader(new InputStreamReader(System.in));

            }
            String next(){
                while (st == null || !st.hasMoreTokens()){  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }

            int nextInt(){
                return Integer.parseInt(next());
            }
            long nextLong(){return Long.parseLong(next()); }

            double nextDouble(){return Double.parseDouble(next());}

            String nextLine(){
                String str ="";
                try {
                    str = br.readLine();

                }catch (IOException e){
                    e.printStackTrace();
                }
                return str;
            }
            void close() {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

}