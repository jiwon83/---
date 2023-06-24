import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
class Main2
{
    static Queue<int[]> emptyQ = new LinkedList<>();
    static int [][] map;
    static int ans, N, M;
    static boolean [][] visit;
    static FastReader sc = new FastReader();
    static int [] dx = {0,0,-1,1}, dy = {-1,1,0,0};
    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        ans = 0;
        map = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j]= sc.nextInt();
                if(map[i][j] ==0) emptyQ.add(new int[]{i,j});
            }
        }
//        System.out.println("input empty queue  >> " + emptyQ.size());
    }
    static void selectOne(){
//        System.out.println("selectOne...");
        //선택
        while (!emptyQ.isEmpty()){  // O(N)
            int [] n = emptyQ.poll();
            map[n[0]][n[1]] = 1;
//            System.out.println(" select "+n[0]+" , "+n[1] );
            countMaxGroup();
            map[n[0]][n[1]] = 0;

        }

    }
    static void countMaxGroup(){ //O(N)
//        System.out.println("countMaxGroup..");
        visit = new boolean[N][M];
        //1인 모양들을 찾아서 1의 갯수 탐색
        for (int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visit[i][j] || map[i][j] == 0) continue;
                visit[i][j] = true;
//                System.out.println("dfs 시작 ( "+i+" , "+j+" )");
                ans = Math.max(ans, dfs(i,j));
//                System.out.println("ans 갱신 -> "+ ans);
            }
        }
    }
    static int dfs(int x, int y){
        int count =1;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if( inArea(nx, ny) && !visit[nx][ny] && map[nx][ny]==1){
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