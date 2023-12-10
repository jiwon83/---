
import java.util.*;
import java.io.*;
public class Main {
    static FastReader sc = new FastReader();
    static int N;
    static int [] A;
    static int[][] Dy;

    static void input(){
        N= sc.nextInt();
        A = new int[N+1];
        Dy = new int[N+1][2];
        for (int i=1; i<=N; i++ ){
            A[i] = sc.nextInt();
        }
    }
    static void preprocess() {

        Dy[1][0]=A[1];
        Dy[1][1]=0;
        if (N >=2){
            Dy[2][0]=A[2];
            Dy[2][1]=Dy[1][0]+ A[2];
        }
        for (int i=3; i<=N; i++){
            Dy[i][0] = Math.max(Dy[i-2][0], Dy[i-2][1] )+ A[i];
            Dy[i][1] = Dy[i-1][0]+ A[i];

        }

    }
    static void pro(){
        input();
        preprocess();

//        System.out.println(Arrays.deepToString(Dy));
        System.out.println(Math.max(Dy[N][0], Dy[N][1]));

    }

    public static void main(String[] args) {

        pro();
        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
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
