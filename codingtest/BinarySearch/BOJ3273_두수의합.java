package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3273_두수의합 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [] A;
    static int N, X, ans;

    static void input() {
        N= sc.nextInt();
        A= new int[N+1];
        for (int i=1; i<=N; i++){
            A[i] = sc.nextInt();
        }
        X = sc.nextInt();
    }
    static void pro() {
        ans=0;
        Arrays.sort(A, 1, N+1);
        System.out.println(Arrays.toString(A));
        for (int i=1; i<= N; i++){
            int tar = X - A[i];
            int L,R;
            if (tar < A[i] && i!=0) {
                L=0; R=i-1;
            }
            else {
                L=i+1; R=N;
            }
            while (L <= R){
                int mid = (L+R) / 2;
                if (A[mid]==tar) {
//                    System.out.println("A[i]: "+A[i]+" A[mid]: "+ A[mid]);
                    ans++;
                    break;
                }
                if(A[mid] < tar) L = mid+1;
                else R= mid-1;
            }

        }
        System.out.println(ans/2);

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




