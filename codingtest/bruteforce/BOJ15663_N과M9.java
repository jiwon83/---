package bruteforce;
/*
 N 과 M (9) https://www.acmicpc.net/problem/15663
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15663_N과M9 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, M;
    static int [] selected, A;
    static boolean [] visit;

    static void input() {
        N = sc.nextInt();
        M = sc.nextInt();
        selected = new int[M+1];
        A = new int[N+1];
        visit = new boolean[N+1];
        for (int i=1; i<=N; i++){
            A[i] = sc.nextInt();
        }
    }
    static void recur(int k){

        if (k == M+1){
            for (int i=1;i<=M; i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");

        }else{
            int last_insert =0; //for문 안에서 중복되지 않도록
            for (int cand=1; cand<=N; cand++){


                if (visit[cand]) continue;
                if (last_insert == A[cand]) continue;


                selected[k] = A[cand];
                last_insert = A[cand]; visit[cand]=true;

                recur(k+1);

//            if (k==1) last_insert=0;
                selected[k]=0; visit[cand]= false;

            }
        }

    }
    static void pro() {
        Arrays.sort(A, 1, N+1);
        recur(1);
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
