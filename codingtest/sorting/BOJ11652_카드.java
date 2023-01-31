
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static long max_value;
    static int N;
    static long [] A;
//    static long [] cnt; 원래 의도는 인덱스가 0~ long의 최대 크기만큼 만드는 것이었으나, 그렇게는 불가능.
    static void input() {

        max_value = Long.MIN_VALUE;

        N = sc.nextInt();
        A = new long[N];

        for (int i=0; i<N; i++){
            A[i] = sc.nextLong();
        }
        Arrays.sort(A);
        int cnt=1; long pre=A[0]; int max_cnt = 0;
        for (int i=1; i<N; i++){
            //만약 이전과 숫자가 같다면 cnt++
            //그렇지 않다면 이전 cnt 를 max_count_value에 갱신 cnt 초기화, \
            if(A[i] == pre) cnt ++;
            else {
                if(max_cnt < cnt) {
                    max_cnt = cnt;  max_value = pre;
                }
                cnt =1;
            }
            pre=A[i];

        }
        //마지막 숫자 갱신
        if(max_cnt < cnt) {
            max_value = pre;
        }
        System.out.println(max_value);

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
