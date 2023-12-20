import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int [] a;
  static int N;

  private static void input() throws IOException{
    N = Integer.parseInt(br.readLine());
    a = new int[N+1];
    for (int i = 1; i <=N; i++){
      a[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(a);
  }
  private static int sol(){
    int [][] dp = new int[N+1][N+1];
    int ans = 1;
    for (int i = 1; i<=N; i++){
      for (int j = i+1; j <=N; j++){
        int preVal = a[i] - (a[j] - a[i]);
        int preIdx = -1;
        for (int w = i-1; w >=0; w--){
          if(preVal == a[w]){
            preIdx = w;
            break;
          }
        }
        dp[i][j] = 2;
        if (preIdx == -1) continue;
        dp[i][j] = Math.max(dp[i][j], dp[preIdx][i] + 1);
        ans = Math.max(ans, dp[i][j]);
      }
    }
    return ans;
  }


  public static void main(String[] args) throws IOException {
    input();
    System.out.println(sol());
  }

}
