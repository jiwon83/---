import java.io.*;
import java.util.*;

public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int L;
    static char [] A;

    static void input() {
        L = sc.nextInt();
        A = sc.nextLine().toCharArray();
        long sum=0;
        long pow = 1;
        for (int i=0; i<L ; i++){

            System.out.println("pow를 따로 계산 한 경우 : "+pow);
            System.out.println("pow를 Math.pow 한 경우  : "+Math.pow(31, i) % 1234567891); //-> 아예 너무 큰 숫자라 Math.pow가 다루지 못함.
            sum += ( ( A[i] - 'a' + 1 ) * pow ) % 1234567891;
            pow = pow * 31 % 1234567891;

        }
        System.out.println(sum% 1234567891);
    }

    public static void main(String[] args) {
        input();

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
