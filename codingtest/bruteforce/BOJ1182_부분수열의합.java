package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182_부분수열의합 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, S, ans;
    static int [] A, select;

    static void input() {
          N = sc.nextInt();
          S = sc.nextInt();
          A = new int[N+1];
          for (int i=1; i<=N; i++){
              A[i] = sc.nextInt();
          }
    }
    static void recur(int k, int leg){
        if (k==leg+1){
            //합이 0인지 확인
//            System.out.println(Arrays.toString(select));
            long sum=0;
            for (int i=1; i<=leg; i++){
                sum += A[select[i]];
            }
            if (sum ==S){
                ans++;
            }

        }
        else{
            for (int cand = select[k-1]+1; cand <= N; cand++){
                select[k]= cand;
                recur(k+1, leg);
                select[k] = 0;
            }
        }
    }
    static void pro() {
        ans=0;
        for (int i=1; i<=N; i++){ //자릿수
            select = new int[i+1];
            recur(1,i);

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

