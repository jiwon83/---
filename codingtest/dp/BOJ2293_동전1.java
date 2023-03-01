import java.util.*;
import java.io.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [] dp;

    static void pro() {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int [] coins = new int[n];
        dp = new int[k+1];
        for (int i=0; i<n; i++){
            coins[i] = sc.nextInt();
        }
        /** wow,, 초기화를 안해도 가능하다..
         * 금액 m을 만들기 위한 경우의 수 점화식이 [이전까지의 동전으로 가능한 경우의수] + [m - 동전금액의 경우의 수]이기 때문이다.
         * 즉, 이전까지의 동전이 없어도 0으로 그냥 더하면 되고, 해당 코인이 4원이라면 5,6,7,8은 못 만드는게 당연하며 8월부터 dp[4] 인 1과 같기 떄문.
         * */
        //초기화 ver1
//        for(int i=coins[0]; i<=k; i++){
//            dp[i] = i % coins[0]==0 ? 1 : 0;
//        }
        //초기화 ver2
//        dp[coins[0]] = 1;

        for (int i=0; i<n; i++){ // 동전
            if(coins[i] <=k) dp[coins[i]]= dp[coins[i]] + 1;
            for (int m = coins[i]+1; m <= k; m++){
                dp[m] = dp[m] + dp[m-coins[i]];
            }
        }
        System.out.println(dp[k]);
    }
    public static void main(String[] args) {
        pro();
    }
    static class FastReader {
            BufferedReader br;
            StringTokenizer st;

            public FastReader() {
                br = new BufferedReader(new InputStreamReader(System.in));

            }
            String next(){
                while (st == null || !st.hasMoreTokens()){  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }

            int nextInt(){
                return Integer.parseInt(next());
            }
            long nextLong(){return Long.parseLong(next()); }

            double nextDouble(){return Double.parseDouble(next());}

            String nextLine(){
                String str ="";
                try {
                    str = br.readLine();

                }catch (IOException e){
                    e.printStackTrace();
                }
                return str;
            }
            void close() {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
}
