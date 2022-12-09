package bfsdfs;

import java.io.*;
import java.util.*;

public class BOJ1937_욕심쟁이판다 {

    static int N, res;//M = 세로, N= 가로
    static int[][] graph,dp;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static void input() {
        //M, N을 입력 받고 그래프 초기화
        N = sc.nextInt();
        graph = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = sc.nextInt();
                dp[i][j] = -1;
            }
        }

    }

    static void pro() {
        res = Integer.MIN_VALUE;
        for (int i=0; i<=N; i++){
            for (int j=0; j<=N; j++){
                res = Math.max(res,dfs(i, j));
            }
        }
        System.out.println(res);
//        System.out.println(Arrays.deepToString(dp));
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != -1){ //방문한 적 있다면
            return dp[x][y];
        }
//        dp[x][y]=0;
        boolean can=false;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 < nx && 0 < ny && N >= nx && N >= ny ) {
                if (isBigger(nx,ny, x, y)){
                    can = true;
                    dp[x][y] = Math.max( dp[x][y], dfs(nx,ny)+1 );


                }
            }
        }
        if (!can) return 1; //끝이라면

        return dp[x][y];
    }

    private static boolean isBigger(int nx, int ny, int x, int y) {
        return graph[nx][ny] > graph[x][y];
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

        String next() {
            while (st == null || !st.hasMoreTokens()) {  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();

            } catch (IOException e) {
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
