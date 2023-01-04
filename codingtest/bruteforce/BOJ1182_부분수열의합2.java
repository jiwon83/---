package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182_부분수열의합2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, S, ans;
    static int [] A;

    static void input() {
          N = sc.nextInt();
          S = sc.nextInt();
          A = new int[N+1];
          for (int i=1; i<=N; i++){
              A[i] = sc.nextInt();
          }
    }
    static void recur(int k , int sum){
        if (k == N+1){
            if (sum ==S ) ans++;
        }
        else{
            recur(k+1, sum+A[k]);
            recur(k+1,sum);
        }
    }
    static void pro() {
        ans=0;
        recur(1,0);
        if (S==0){
            ans--;
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



