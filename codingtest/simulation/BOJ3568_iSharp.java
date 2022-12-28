package simulation;
import java.io.*;
import java.util.*;
/**
 * 1. 맨뒤 ;제거
 * 2. 공백으로 split 첫번 째는 common
 * 3. 2번째 부터 for
 * 3-1. ,는 제거
 * 3-2. *, []. &가 있다면 그 뒤까지 거꾸로 뒤집어서 임시로 저장.
 * 3-3. 조합해서 sb.append()
 * 4. 출력
 * */
public class BOJ3568_iSharp {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static String common="";
    static String input;

    static void input() {
        input= sc.nextLine();
    }
    static void pro() {

        String [] strings = input.substring(0,input.length()-1).split(" ");
        common = strings[0];

        for (int i=1; i< strings.length; i++){

            strings[i] = strings[i].replaceAll(",","");

            int nextIdx =strings[i].length();

            for (int j=0; j<strings[i].length(); j++){
                String part = strings[i].charAt(j)+"";
                if (!part.matches("^[a-zA-Z]*$")){
                    nextIdx = j;
                    break;
                }
            }
            StringBuilder tail = new StringBuilder( strings[i].substring(nextIdx) );
            //*, []. &가 있다면 그 뒤까지 거꾸로 뒤집어서 임시로 저장.
            String tailstr = tail.reverse().toString();
            tailstr = tailstr.replaceAll("\\]\\[","\\[\\]");

            String name = strings[i].substring(0,nextIdx);
            sb.append(common).append(tailstr).append(" ").append(name).append(";").append("\n");


        }
    }
    public static void main(String[] args) {
        input();
        pro();
        System.out.println(sb);
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
