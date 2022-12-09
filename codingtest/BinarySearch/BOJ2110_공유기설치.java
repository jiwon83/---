package BinarySearch;

import java.io.*;
import java.util.*;

public class BOJ2110_공유기설치 {

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [] A;
    static int N, C; //N은 A배열의 갯수, C는 Determination을 결정할 변수(공유기의 갯수)
    static int ans;

    static void input() {
        N= sc.nextInt();
        C = sc.nextInt();
        A = new int[N+1];
        for (int i=1; i<=N; i++){
            A[i]= sc.nextInt();
        }
    }

    static boolean determination(int D) {
        int last = 1, cnt =1;
        for (int i = 2; i<=N; i++){ //2 3 4 5
            if (A[i] - A[last] >= D){
                last = i;
                cnt++;
            }
        }
//        System.out.println(" count = "+cnt);
        return cnt >= C;
    }

    static void lower_bound(int L, int R) {
        Arrays.sort(A, 1, N+1);
        //L,R 초기화
        while (L <= R){
            int mid = ( L + R ) / 2;
//            System.out.print("mid>"+mid);
            if (determination(mid)) {
                ans = mid;
                L = mid + 1;
            }
            else R = mid - 1;
        }
    }

    static void pro() {
        input();
        //L과 R지정
        int L = 1, R = A[N];
        lower_bound(L,R);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        pro();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();

            } catch (IOException e) {
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
