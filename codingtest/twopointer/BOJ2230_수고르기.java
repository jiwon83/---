import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, M;
    static long ans= Long.MAX_VALUE;
    static int [] A;
    static void input() {
        N = sc.nextInt(); M = sc.nextInt();
        A = new int [N];
        for (int i=0; i<N; i++){
            A[i] = sc.nextInt();
        }

    }
    static void pro() {
        Arrays.sort(A);
        for (int L=0, R=1; L<N; L++){
            while ( (long)A[R] - (long) A[L] < M && R+1 < N){
                R++;
            }
            //그럼 여기서의 R은 무조건 같거나, M보다 클 것이다. 그리고 그것은 가능한 최솟값일 것이다.
            if ( (long)A[R] - (long) A[L] >= M) ans = Math.min(ans, (long)A[R] - (long) A[L]);
        }
        System.out.println(ans);

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

