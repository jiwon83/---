import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static int answer;
   static int [] a;
   static int N;
   static StringTokenizer st;
   static int pivot = -1;

   static void sol(){
      int [][] dp = new int[N+1][N+1];
      for (int l = 2; l<= N; l++){
         for (int s = 1; s<=N; s++){
            if (s + l -1 > N) continue;
            int e = s + l -1;
            if (a[s] == a[e]){
               if(e - s > 1 ) dp[s][e] = dp[s+1][e-1];
            }
            else{
               dp[s][e] = Math.min(dp[s][e-1], dp[s+1][e])+1;
            }
         }
      }
      System.out.println(dp[1][N]);


   }

   static void input() throws IOException{
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      a = new int [N+1];
      for (int i = 1; i<= N; i++){
         a[i] = Integer.parseInt(st.nextToken());
      }

   }

   public static void main(String[] args) throws IOException {
      input();
      sol();
   }
}