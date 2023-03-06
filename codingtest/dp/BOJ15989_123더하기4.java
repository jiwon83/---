import java.util.*;
import java.io.*;
public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [] dp = new int[100001];

    static void preprocess(){
        for (int num = 1; num<=3; num++){
            dp[num] = dp[num]+1;
            for (int i=num+1; i<=100000; i++){
                dp[i] = dp[i] + dp[i-num];
            }
        }

    }
    static void pro() {
        preprocess();
        int T = sc.nextInt();
        for(int i=1; i<=T; i++){
            int n = sc.nextInt();
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb);
//        System.out.println(Arrays.toString(dp));
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
