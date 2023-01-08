package twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1253_좋다 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, ans;
    static int [] A;

    static void input() {
        N = sc.nextInt();
        A = new int[N+1];
        for (int i=1; i<=N; i++){
            A[i] = sc.nextInt();
        }

    }
    static boolean check( int targetIdx, int target){
        // L과 R은 처음과 끝에서 시작.
        int L =1, R=N;
        //L, R은 1보다 크고 0보다 작아야한다.
        int sum;
        while (L < R){

            if (L == targetIdx) L++;
            else if (R == targetIdx) R--;
            else{
                sum = A[L]+ A[R];
                //만약 sum==target, return true
                if (sum == target) return true;
                    //만약, sum이 target보다 크면, R--
                else if( sum > target ) R--;
                    //만약,sum <target , L++
                else if ( sum < target ) L++;

            }
        }
        return false;
    }

    static void pro() {
        Arrays.sort(A,1, N+1); //정렬

        // 1. 각 수마다 twopointer로 확인
        for (int i=1; i<=N; i++){
            if(check( i, A[i])) ans++;
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
