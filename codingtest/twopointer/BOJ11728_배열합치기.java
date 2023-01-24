import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, M;
    static int [] A , B;
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static void input() {
          N = sc.nextInt();
          M = sc.nextInt();
          A = new int[N];
          B= new int[M];

          for (int i=0; i<N; i++){
              A[i] = sc.nextInt();

          }
          for (int i=0; i<M; i++){
              B[i] = sc.nextInt();
          }
    }
    static void pro() {
        int L =0, R=0;
        while (L < N && R <M){
            if (A[L] <= B[R]){
                sb.append(A[L++]).append(" ");
            }
            else if (A[L] > B[R]){
                sb.append(B[R++]).append(" ");
            }
        }
        while(L < N){
            sb.append(A[L++]).append(" ");
        }
        while(R < M){
            sb.append(B[R++]).append(" ");
        }

        System.out.println(sb);
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
