import java.util.*;
import java.io.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [][] dp = new int[41][2];

    static void pro() {
        int T =  sc.nextInt();
        for(int t=0; t<T; t++){
            int N = sc.nextInt();
            dp[0] = new int[]{1,0};
            dp[1] = new int[]{0,1};
            for (int i=2; i<=N; i++){
                if(dp[i][0] !=0 ) continue;
                dp[i] = new int [] {dp[i-1][0] + dp[i-2][0], dp[i-1][1] + dp[i-2][1]};
            }
            sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
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
