package twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473_세용액 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [] A;
    static int N, v1,v2,v3;
    static long best_sum;

    static void input() {
        N= sc.nextInt();
        A= new int[N+1];
        for (int i=1; i<=N; i++){
            A[i] = sc.nextInt();
        }
    }
    static void pro() {
        best_sum = Long.MAX_VALUE;
        Arrays.sort(A, 1, N+1);
        for (int tarIdx = 1; tarIdx<= N-2; tarIdx++){

            int L = tarIdx +1 , R = N;
            int target = -A[tarIdx];
            while (L < R){
//                System.out.println("target= "+target+" / "+ A[tarIdx]+" L "+L+" R "+R);
                if ( Math.abs( A[L]+ A[R] + (long)A[tarIdx] ) < best_sum){
                    best_sum = Math.abs( A[L]+ A[R] + (long) A[tarIdx] );
                    v1 = A[tarIdx]; v2 = A[L]; v3 =  A[R];
                }
                if (A[L]+A[R] > target) R--;
                else if ( A[L]+A[R] <= target ) L++; //** else 붙여야만 한다.
            }
        }
        System.out.println(v1+" "+v2+" "+v3);

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




