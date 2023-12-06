import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 test case
 5 3
 1 1 1 2 2
 */
public class Main {
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static StringTokenizer st;


   static int sol(int N, int K, int [] a){
      int cnt = 0;
      int ans = 0;
       for (int R = 0, L = 0; L <= N; L++){
          if( (a[L] & 1 ) == 1) cnt--;
          while (R + 1 <= N && cnt <= K){
            if (cnt == K && (a[R+1] & 1) == 1 ) break;
             R++;

             if( (a[R] & 1) == 1 ) cnt++;
          }
          ans = Math.max(ans, R - L - cnt );
          if(R == N ) break;

       }
       return ans;
   }

   public static void main(String[] args) throws IOException {
      st = new StringTokenizer(br.readLine());
      int N =Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      int [] a = new int[N+1];
      st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= N; i++){
         a[i] = Integer.parseInt(st.nextToken());
      }
      System.out.println(sol(N, K, a));
   }

}
