package dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
실버 3 / 1, 2, 3 더하기 https://www.acmicpc.net/problem/9095
dp 버전
시도 : O
 */
public class Add123 {
    static int T, N;
    static int [] dy;
    static FastReader sc = new FastReader();
    static StringBuilder sb =  new StringBuilder();

    private static void dp() {
        dy[1] = 1;
        if (N >=2) dy[2] = 2; //유의
        if (N >=3) dy[3] = 4;

        for (int i=4; i<=N; i++) dy[i]= dy[i-1] + dy[i-2]+ dy[i-3];
    }
    static void pro() {

        T = sc.nextInt();
        while (T-- >0){
            N = sc.nextInt();
            dy = new int[N+1];
            dp();
            sb.append(dy[N]).append('\n');

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
