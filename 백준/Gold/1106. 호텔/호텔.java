import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
   static class City{
      int cost, cnt;
      public City(int cost, int cnt){
         this.cost = cost;
         this.cnt = cnt;
      }
   }
   static int C, N;
   static int dp[];
   static City cities[];
   static final int MAX = 1000*100+1; // 최소 비용
   static StringTokenizer st;
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

   static void input() throws IOException{
      st = new StringTokenizer(br.readLine());
      C = Integer.parseInt(st.nextToken());
      N = Integer.parseInt(st.nextToken());
      cities = new City[N];
      dp = new int[C+1];
      for (int i = 0; i<N; i++){
         st = new StringTokenizer(br.readLine());
         int cost = Integer.parseInt(st.nextToken());
         int cnt = Integer.parseInt(st.nextToken());
         cities[i] = new City(cost, cnt);
      }
   }
   public static void main(String[] args) throws IOException{
      input();
      Arrays.fill(dp, MAX);
      for (int c = 0; c <N; c++){ // 도시마다
         for (int i = 1; i <=C; i++){ // 최소 유치 고객 수
            int cost = cities[c].cost; // 1번의 홍보 비용
            if (i - cities[c].cnt > 0) cost += dp[i-cities[c].cnt]; // 나머지 고객 수를 채우기 위한 최소 비용
            dp[i] = Math.min(dp[i], cost);
         }
      }
      System.out.println(dp[C]);
   }

}