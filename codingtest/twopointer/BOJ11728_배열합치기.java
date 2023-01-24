import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, M;
    static int [] A, B;
    static HashSet<Integer> set = new HashSet<>();
    static void input() {
        N= sc.nextInt();
        M=sc.nextInt();
        A = new int[N];
        B= new int[M];
        for (int i=0; i<N; i++){
            A[i] =  sc.nextInt();
        }
        for (int i=0; i<M; i++){
            B[i] = sc.nextInt();
        }



//        A = Stream.of(a.split(" ")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);

    }
    static void pro() {
        int L = 0;
        int R = 0;
        while (L <N && R <M){
            if (A[L] <= B[R]){
                sb.append(A[L]).append(" ");
                L++;
            }else{
                sb.append(B[R]).append(" ");
                R++;
            }
        }
        for (int i=L ; i<N; i++){
            sb.append(A[i]).append(" ");
        }
        for (int i=R ; i<M; i++){
            sb.append(B[i]).append(" ");
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




