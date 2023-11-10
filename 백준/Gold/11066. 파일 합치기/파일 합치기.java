import java.util.*;
import java.io.*;

class Main
{
    static Main.FastReader scan = new Main.FastReader();
    static StringBuilder sb = new StringBuilder();

    static int K, Q;
    static int[] num;
    static int[][] Dy, sum;

    static void input(){
        K = scan.nextInt();
        num = new int[K + 1];
        sum = new int[K + 1][K + 1];
        Dy = new int[K + 1][K + 1];
        for (int i = 1; i <= K; i++){
            num[i] = scan.nextInt();
        }
    }

    static void preprocess(){
        // Calculate additional cost for merging adjacent files
        for (int i = 1; i <= K; i++) {
            for (int j = i; j <= K; j++) {
                if (i == j) {
                    sum[i][j] = num[i];
                } else {
                    sum[i][j] = sum[i][j - 1] + num[j];

                }
            }
        }
//        System.out.println("sum...");
//        for (int i=1; i<=K; i++) System.out.println(Arrays.toString(sum[i]));
    }

    static void pro() {
        preprocess();

        for (int len = 2; len <= K; len ++){
            for (int i = 1; i <= K - len + 1; i++){
                int j = i + len - 1;
                Dy[i][j]= Integer.MAX_VALUE;
                for (int k = i; k < j; k++){

                    int res = Dy[i][k] + Dy[k + 1][j];
                    Dy[i][j] = Math.min(Dy[i][j], res + sum[i][j]);
                }
            }
        }

//        for (int i=1; i<=K; i++)
//            System.out.println(Arrays.toString(Dy[i]));
        System.out.println(Dy[1][K]);
    }
    public static void main(String[] args) {
        Q = scan.nextInt();
        for (int rep = 1; rep<=Q;rep++) {
            input();
            pro();
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