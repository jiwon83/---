import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;

    static long [][] dp;
    static long [] nums;

    static void input() {
        N= sc.nextInt();
        nums = new long[N];
        dp =new long[N][3];
        for (int i=0; i<N; i++){
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);

        dp[0][0] = 1; dp[0][1]=1; dp[0][2] = nums[0];
        for(int i=1; i<N; i++){
            dp[i][0] = nums[i]==nums[i-1]? dp[i-1][0]+1 : 1;
            dp[i][1] = (dp[i-1][1] < dp[i][0])? dp[i][1] = dp[i][0]: dp[i-1][1]; //현재 연속 값이 최대 연속값보다 크다면
            dp[i][2] = (dp[i-1][1] < dp[i][0])? nums[i] : dp[i-1][2];

        }

//        System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[N-1][2]);

    }
    static void pro() {

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