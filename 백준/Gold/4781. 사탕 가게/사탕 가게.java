import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int N, K; //N = 사탕 갯수, K= 가진 돈
  static int [][] candy; ///[0] = 가격, [1] = 칼로리
  static int [] dp; // 돈 i에 대하여 최대 칼로리
  static boolean isFinish;

  static void input() throws IOException{
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = multi100(st.nextToken());
    if (N == 0 && K == 0 ){
      isFinish = true;
      return;
    }
    candy = new int[N+1][2];
    dp = new int[K+1];
    for (int i = 1; i<= N; i++){
      st = new StringTokenizer(br.readLine());
      candy[i][0] = Integer.parseInt(st.nextToken());
      candy[i][1] = multi100(st.nextToken());
    }
  }
  static int multi100(String s){
    String [] str = s.split("\\.");
    return Integer.parseInt(str[0])*100 + Integer.parseInt(str[1]);
  }

  static void sol(){
    for (int c = 1; c <= N; c++){
      int cost = candy[c][1];
      int kcal = candy[c][0];
      for (int i = cost; i <= K; i++){ //금액 i
        dp[i] = Math.max(dp[i], dp[i - cost] + kcal);
      }
    }
    sb.append(dp[K]).append("\n");
  }


  public static void main(String[] args) throws IOException {
    while (true){
      input();
      if (isFinish) break;
      sol();
    }
    System.out.println(sb);
  }

}