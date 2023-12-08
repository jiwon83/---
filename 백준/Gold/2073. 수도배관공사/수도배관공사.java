import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int D, P;
  static int [][] pipes;
  static int [] dp;

  static void input() throws IOException{
    st = new StringTokenizer(br.readLine());
    D = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());
    pipes = new int[P+1][2];
    dp = new int[D+1];
    for (int i = 1; i <= P; i++){
      st = new StringTokenizer(br.readLine());
      int Li = Integer.parseInt(st.nextToken());
      int Ci = Integer.parseInt(st.nextToken());
      pipes[i] = new int[]{Li, Ci};
    }
  }

  static void sol(){
    for (int i = 1; i <= P; i++){
      int Li = pipes[i][0];
      int Ci = pipes[i][1];
      for (int j = D; j >= Li; j--){
        if (dp[j-Li] == 0) continue;
        dp[j] = Math.max(dp[j],  Math.min(Ci, dp[j-Li]) );
      }
      dp[Li] = Math.max(dp[Li], Ci);
    }
    System.out.println(dp[D]);
  }

  public static void main(String[] args) throws IOException {
    input();
    sol();
  }

}