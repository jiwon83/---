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

   static StringTokenizer st;
   static StringBuilder sb = new StringBuilder();
   static long [][] cnt;

   static long getAnswer(int N){
      long answer = 0;
      for (long i : cnt[N]){
         answer += i;
      }
      return answer;
   }

   static void sol(){

      cnt = new long[65][10];

      Arrays.fill(cnt[1], 1);
      for (int i = 2; i <= 64; i++){
         for (int j = 0; j <10; j++){ //  j= lastNum
            for (int w = j; w <10; w++){
               cnt[i][w] += cnt[i-1][j];
            }
         }
      }
//      System.out.println(Arrays.deepToString(cnt));
   }

   static void input() throws IOException{
      int T = Integer.parseInt(br.readLine());
      while (T-- > 0) {
         int N = Integer.parseInt(br.readLine());
         sb.append(getAnswer(N)+"\n");
      }
      System.out.println(sb);
   }

   public static void main(String[] args) throws IOException {
      sol();
      input();
   }
}