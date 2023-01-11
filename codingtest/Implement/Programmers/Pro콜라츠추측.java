package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro콜라츠추측 {

    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    /* 필요한 함수들 */

    public long solution(long num) {
        return pro(num);
    }
    public long pro(long num){
        long cnt=0;
        while (num >= 1){
            if (num ==1) return cnt;
            if (cnt >= 500) return -1;
            if ((num & 1) ==0){ //짝수이면
                num /= 2;
                cnt++;
            }else if ((num & 1) == 1){
                num = num*3+1;
                cnt++;
            }
        }
        return cnt;
    }


    public static void main(String[] args) {
        Pro콜라츠추측 s = new Pro콜라츠추측();
        System.out.println(s.solution(626331));
        sc.close();

    }

    //입출력 관련
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
