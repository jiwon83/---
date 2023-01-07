package twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1253_좋다_완탐풀이 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, ans;
    static int [] A, select;

    static void input() {
          N = sc.nextInt();
          A = new int[N+1];
          for (int i=1; i<=N; i++){
              A[i] = sc.nextInt();
          }
    }
    static boolean isFind;
    static void recur(int k, int num){
            if (k ==3){
                if (num == A[select[1]]+ A[select[2]]){
                    if (!isFind){
                        ans++;
                        System.out.println(A[select[1]] + A[select[2]]);
                        isFind = true;
                    }

                    return;
                }
            }else{
                for (int cand = select[k-1]+1; cand<= N; cand++){
                    select[k] = cand;
                    recur(k+1,num);
                }
            }
    }
    static void pro() {
        select = new int[3];
        ans =0;

        //1. 각 수마다 조합으로 2개를 뽑아서 가능하면 count ++;
        for (int i=1; i<=N; i++){

            recur(1, A[i]);
            isFind = false;
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
