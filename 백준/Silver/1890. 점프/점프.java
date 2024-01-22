import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static StringTokenizer st;
   static StringBuilder sb = new StringBuilder();

   static int [][] map ;
   static long [][] memo ;
   static int N;

   static void input() throws IOException{
      N = Integer.parseInt(br.readLine());
      memo = new long[N][N];
      map = new int[N][N];
      for (int i = 0; i<N; i++){
         st = new StringTokenizer(br.readLine());
         for (int j  = 0; j <N; j++){
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }

   }
   static void sol(){
      memo[0][0] = 1;
      for (int i = 0; i<N; i++){
         for (int j = 0; j <N; j++){
            int jump = map[i][j];
            if (i == N-1 && j == N-1) break;
            if ( j + jump < N ) memo[i][j+jump] += memo[i][j];
            if( i + jump < N) memo[i+jump][j] += memo[i][j];
         }
      }
//      System.out.println(Arrays.deepToString(memo));
      System.out.println(memo[N-1][N-1]);
   }

   static class Info{
      int r, c;
      long cnt;
      public Info(int r, int c, long cnt){
         this.r = r;
         this.c = c;
         this.cnt = cnt;
      }
   }
   static int [][] dirs = {{0,1},{1,0}};
   static void bfs(){
      ArrayDeque<Info> q = new ArrayDeque<>();
      q.add(new Info(0,0,1));
      while (!q.isEmpty()){
         Info now = q.poll();
         int val = map[now.r][now.c];
         if (val == 0) continue;
         for (int d = 0; d <2; d++){
            int nx = now.r + dirs[d][0]*val;
            int ny = now.c + dirs[d][1]*val;
            if(inArea(nx,ny)){
               memo[nx][ny] += now.cnt;
               q.add(new Info(nx,ny,memo[nx][ny]));
            }
         }

      }

   }
   static boolean inArea(int r, int c){
      return r >=0 && c >= 0 && r < N && c < N;
   }
   public static void main(String[] args) throws IOException{
      input();
      sol();
   }
}