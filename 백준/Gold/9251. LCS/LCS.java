import java.io.*;
import java.util.*;

public class Main {

    static FastReader sc = new FastReader();
    static String A,B;
    static int [][] dp;

    static void input() {
        A = sc.nextLine();
        B = sc.nextLine();
        dp = new int[A.length()][B.length()];
    }
    static void pro() {
        //초기화
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i)==B.charAt(0)){
                dp[i][0] = 1;
            } else if ( i!=0 && dp[i-1][0] != 0) {
                dp[i][0] = dp[i-1][0];
            }

        }
        for (int i = 0; i < B.length(); i++) {
            if (B.charAt(i)==A.charAt(0)){
                dp[0][i] = 1;
            } else if (i!=0 && dp[0][i-1] != 0) {
                dp[0][i] = dp[0][i-1];
            }

        }

        for (int i=1; i< A.length(); i++){
            for (int j = 1; j < B.length(); j++) {
                if (A.charAt(i)==B.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }

            }
        }
        System.out.println(dp[A.length()-1][B.length()-1]);

    }
    public static void main(String[] args) {
        input();
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