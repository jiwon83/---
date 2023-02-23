
import java.io.*;
import java.security.cert.PolicyNode;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int ans=0;
    static int N,M;
    static String S;
    static int [] memo;

    static void input() {
        N= sc.nextInt();
        M = sc.nextInt();
        S = sc.nextLine();
        memo = new int[M];
    }
    static void pro() {
        for (int i=1; i<S.length()-1; i++){
            if(S.charAt(i)=='O' && S.charAt(i+1)=='I' ){
                // OI의 누적 , memo를 갱신시킨다.
                memo[i+1] = memo[i-1]+1;
                if ( memo[i+1] >= N && S.charAt( i+1 - 2 * N )=='I'){ //현재 memmo값이 N보다 크다면, 앞의 I를 확인해서 만약 I라면
                    ans++;
                }
            }

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
