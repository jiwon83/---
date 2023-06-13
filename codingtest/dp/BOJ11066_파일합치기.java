import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int K;
    static int [][] dp;
    static int [][] fileSize;
    static int [] a;
    static void input() throws IOException{
/*
2
4
40 30 30 50
15
1 21 3 4 5 35 5 4 3 5 98 21 14 17 32

 */
        K = scan.nextInt();
         a = new int[K+1];
         fileSize = new int [K+1][K+1];
         dp =  new int [K+1][K+1]; //필요한 최소 비용

        for (int i=1; i<=K; i++){
            a[i] = scan.nextInt();
        }

    }
    static void initFileSize(){
        for (int i=1; i<=K; i++){
            fileSize[i][i] = a[i];
            for (int j=i+1; j<=K; j++){
                dp[i][j] = Integer.MAX_VALUE;
                fileSize[i][j] = fileSize[i][j-1] + a[j];
            }
        }
//        System.out.println("-----fileSize-----");
//        System.out.println(Arrays.deepToString(fileSize));
   }
    public static void doDp(){
        //1.
        initFileSize();
        //2.
        for (int len = 2; len <=K; len++){
            for (int i=1; i + len -1<=K; i++){
                int j = i+len-1;
                for (int sp = i; sp<j; sp++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][sp] + dp[sp+1][j] + fileSize[i][j]);
                }

            }
        }
        System.out.println(dp[1][K]);
    }



    public static void main(String[] args) throws IOException{
        int T = scan.nextInt();
        for (int i=0; i<T; i++){
            input();
            doDp();
        }


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