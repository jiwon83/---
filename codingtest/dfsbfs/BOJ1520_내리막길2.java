package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
골드 3 내리막길: https://www.acmicpc.net/problem/1520
시도: 시간초과,
 */
public class BOJ1520_내리막길2 {
    static int M,N, cnt;
    static boolean [][] visit;
    static int [][] graph,dp;
    static int [] dx ={-1,1,0,0}, dy={0,0,-1,1};

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static void input() {
        //M, N을 입력 받고 그래프 초기화
        M = sc.nextInt();
        N= sc.nextInt();
        visit= new boolean[M+1][N+1];
        graph= new int[M+1][N+1];
        dp =new int[M+1][N+1];

        for (int i=1; i<=M; i++){
            for (int j=1; j<=N; j++){
                graph[i][j] = sc.nextInt();
                dp[i][j] = -1; //dp 초기화
            }
        }

    }
    static void pro() {
        ;
        System.out.println(dfs(1,1));

        System.out.println(Arrays.deepToString(dp));

    }

    private static int dfs(int x, int y) {
//        visit[x][y]= true;
        if (x == M && y== N){ //목적지에 도착하면
            System.out.println(x +" , "+y+" 목적지 도착. return 1");
//            cnt++;
            return 1;
        }
        if (dp[x][y] != -1){ //이미 방문했던 곳이라면
            System.out.println(x +" , "+y+" 이미 방문했음."+ dp[x][y]+ " return");
            return dp[x][y];
        }
        dp[x][y] = 0; //처음 방문했을 때 0으로 만들어주고

        for (int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 < nx && 0 < ny && M >= nx && N >=ny && isLower(graph[x][y] , graph[nx][ny])){ //갈 수 있다면
                dp[x][y] += dfs(nx,ny); //재귀 호출로 return 된 값을 더해준다.
//                visit[nx][ny] = false;
            }
        }
        return dp[x][y];
    }

    private static boolean isLower(int from, int to ) {
        return from > to;
    }

    public static void main(String[] args) {
        input();
        pro();
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
