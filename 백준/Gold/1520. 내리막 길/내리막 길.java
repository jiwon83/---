import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 4 5
 50 45 37 32 30
 35 50 40 20 25
 30 30 25 17 28
 27 24 22 15 10
 */
class Main{
   static int N,M;
   static int [][] dp, map;
   static StringTokenizer st;
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

   static void input() throws IOException{
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      dp = new int[N][M];
      map = new int[N][M];
      for (int i = 0; i<N; i++){
         st = new StringTokenizer(br.readLine());
         Arrays.fill(dp[i],-1);
         for (int j = 0; j <M; j++){
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }
   }
   static int sol(){
      return dfs(0,0);
   }
   static int [] dx = {-1,1,0,0},dy = {0,0,-1,1};
   static int dfs(int x, int y){
      if (x == N-1 && y == M-1){
         return 1;
      }
      if(dp[x][y] != -1) return dp[x][y];
      dp[x][y] = 0;
      for (int d = 0; d <4; d++){
         int nx = x + dx[d];
         int ny = y + dy[d];
         if(!inArea(nx,ny)) continue;
         if(map[x][y] <= map[nx][ny]) continue;
         dp[x][y] += dfs(nx,ny);
      }
      return dp[x][y];
   }
   static boolean inArea(int x, int y){
      return x >=0 && y >=0 && x <N && y <M;
   }
   public static void main(String[] args) throws IOException{
      input();
      System.out.println(sol());

   }

}