import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int [][] dp;
    static int [][] arr;
    static void input() throws IOException{

        N = scan.nextInt();
        arr = new int[N][3];
        dp = new int[N][3];
        for (int i=0; i<N; i++){
            for (int j=0; j<3 ; j++){
                arr[i][j] = scan.nextInt();
            }
        }
//        System.out.println(Arrays.deepToString(arr));
    }

    static int dp(int N) {
        //초기화
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];


        for (int i=1; i<N; i++){
            dp[i][0] = Math.min( dp[i-1][1],dp[i-1][2] ) + arr[i][0];
            dp[i][1] = Math.min( dp[i-1][0],dp[i-1][2] ) + arr[i][1];
            dp[i][2] = Math.min( dp[i-1][0],dp[i-1][1] ) + arr[i][2];
        }

//        for (int i=0; i<N; i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }

        return Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
    }

    public static void main(String[] args) throws IOException{
        input();
        System.out.println(dp(N));
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}