import java.util.*;
import java.io.*;
public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [][] dp = new int[100001][4]; //0=1로 끝나는 경우의 수, 1=2로 끝나는 경우의 수, 2= 3으로 끝나는 경우의 수
    static final int MOD= 1000000009;
    static void preprocess(){
        //0.초기화
        dp[1][1] = 1; dp[2][0]=1; dp[2][2]=1;
        dp[3][1]=1; dp[3][3]=1;
        //1. dp
        for(int i=4; i<=100000; i++){
            for (int a=1; a<=3; a++){ // i-1(+1), i-2(+2), i-3(+3) 을 각각 구해준다.
                dp[i][a] += dp[i-a][0];
                dp[i][a] %= MOD;
                dp[i][0] += dp[i-a][a];
                dp[i][0] %= MOD;
            }
        }

    }
    static void pro() {
        preprocess();
        int T = sc.nextInt();
        for(int i=1; i<=T; i++){
            int n = sc.nextInt();
            int ans=0;
            for (int id=0; id<=3; id++){
                ans += dp[n][id];
                ans %= MOD;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
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
