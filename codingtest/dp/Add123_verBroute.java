package dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
실버 3 / 1, 2, 3 더하기 https://www.acmicpc.net/problem/9095
완전 탐색 버전(원래 dp문제로 풀려고 했음.)
시도: O 다시 안풀어봐도 ok
 */
public class Add123_verBroute {
    static int count, T, N;
    static FastReader sc = new FastReader();
    static StringBuilder sb =  new StringBuilder();

    static void recur(int sum, int total){
        if (sum >= total){
            if (sum==total) count++;
        }else{
            for (int i=1; i<4; i++){
                recur(sum+i, total);
            }
        }
    }
    static void pro() {

        T = sc.nextInt();
        while (T-- >0){
            N = sc.nextInt();
            count=0;
            recur(0,N); //재귀함수로 합이 N이 되는 경우의 수를 count
            sb.append(count).append('\n');

        }
        System.out.println(sb);


    }
    public static void main(String[] args) {

        pro();
        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
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
