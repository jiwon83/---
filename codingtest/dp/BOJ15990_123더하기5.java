import java.util.*;
import java.io.*;
public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [][] dp = new int[100005][3]; //0=1로 끝나는 경우의 수, 1=2로 끝나는 경우의 수, 2= 3으로 끝나는 경우의 수
    static final int MOD= 1000000009;
    static void preprocess(){
        //0.초기화
        dp[1][0] = 1; dp[2][1]=1; dp[3][2]=1;
        dp[3][0]=1; dp[3][1]=1;
        //1. dp
        for(int n=4; n<=100000; n++){
            for (int i=1; i<=3; i++){ // i-1(+1), i-2(+2), i-3(+3) 을 각각 구해준다.
                for (int end=0; end<3; end++){ //i-1일 경우에는 1로 끝나는 경우는 해주면 안된다.
                    if (i-1==end) continue; //n-i의 i와 끝나는 수인 end가 같다면 같은수가 연속해서 사용되므로 이 경우는 제외한다.
                    dp[n][i-1] += dp[n-i][end];
                    dp[n][i-1] %= MOD;
                }
            }
        }

    }
    static void pro() {
        preprocess();
        int T = sc.nextInt();
        for(int i=1; i<=T; i++){
            int n = sc.nextInt();
            int ans=0;
            for (int id=0; id<3; id++){
                ans += dp[n][id];
                ans %= MOD;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
//        System.out.println(Arrays.deepToString(dp));
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
