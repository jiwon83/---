
import java.io.*;
import java.security.cert.PolicyNode;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static int [] A;
    static int [] sum;
    static int sumIdx=0;
    static void input() {
        N = sc.nextInt();
        A = new int[N];
        sum = new int[N*N];
        for (int i=0; i<N; i++){
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);
        //x+y를 구한다.
        for (int i=0; i<N; i++){
            for (int j=i; j<N; j++){
                sum[sumIdx++] = A[i] + A[j];
            }
        }

        Arrays.sort(sum, 0, sumIdx);

        Loop:for (int k=N-1; k>=0 ; k--){
            for (int z=0; z<N; z++){
                if(Arrays.binarySearch(sum, 0,sumIdx, A[k]-A[z]) < 0 ) continue;
                System.out.println(A[k]);
                break Loop;
            }
        }


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
