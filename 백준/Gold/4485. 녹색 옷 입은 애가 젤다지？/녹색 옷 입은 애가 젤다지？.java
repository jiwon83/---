import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int [][] rupi, dp;
    static int N;


    public static void main(String[] args) throws IOException {
        int t = 1;
        while (true){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            rupi = new int[N][N];
            dp = new int[N][N];
            for(int i = 0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    rupi[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i<N; i++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[0][0] = rupi[0][0];
            bfs(0,0);
//            dfs(0,0);
//            for (int i = 0; i<N; i++){
//                System.out.println(Arrays.toString(dp[i]));
//            }

            sb.append("Problem "+t+": "+dp[N-1][N-1]).append("\n");
            t++;
        }
        System.out.println(sb);
    }
    static void dfs(int x, int y){
//        System.out.println("x = " + x + " y = "+ y);
        for(int d= 0; d < 2; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(!inArea(nx, ny)) continue;
            if(dp[x][y] + rupi[nx][ny] < dp[nx][ny]){
                dp[nx][ny] = dp[x][y] + rupi[nx][ny];
                dfs(nx, ny);
            }
        }
    }
    static void bfs(int sx, int sy){
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(sx, sy));
        dp[sx][sy] = rupi[sx][sy];
        while (!q.isEmpty()){
            Point now = q.pollFirst();
            for (int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                if(!inArea(nx, ny)) continue;
                if(dp[now.x][now.y] + rupi[nx][ny] < dp[nx][ny]){
                    dp[nx][ny] = dp[now.x][now.y] + rupi[nx][ny];
                    q.addLast(new Point(nx, ny));
                }
            }
        }

    }
    static int [] dx = {0,1, 0, -1}, dy = {1,0, -1, 0};
    static boolean inArea(int x, int y){
        return x >= 0 && y >= 0 && x <N && y <N;
    }

}
