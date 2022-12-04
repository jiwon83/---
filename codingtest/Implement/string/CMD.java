package implement.string;
import java.io.*;
import java.util.*;
/*
브론즈 1 명령 프롬프트 https://www.acmicpc.net/problem/1032
 */
public class CMD {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static String [] strings;

    static void input() {
          N = sc.nextInt();
          strings = new String[N];
          for (int i=0; i<N; i++) strings[i]= sc.nextLine();
    }
    static void pro() {

        for (int i=0; i < strings[0].length(); i++){
            //모든 문자열을 동시에 확인
            boolean uniform = true;
            for (int j =0; j <N-1; j++){
                if ( strings[j].charAt(i) != strings[j+1].charAt(i)) uniform = false;
            }
            if (uniform) { //i번째 문자열은 모두 같은 것임
                sb.append(strings[0].charAt(i));
            }else{
                sb.append("?");
            }
        }

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
