import java.io.*;
import java.util.*;

public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static String input,result;
    static ArrayList<String> dic = new ArrayList<>();
    static void input() {
        input = sc.nextLine();
        int N = sc.nextInt();
        for (int i=0; i<N; i++){
            dic.add(sc.next());
        }
    }
    static void pro() {
        //1. 해당 암호화된 문장을 해독, 원래 문자로 바꾼다.
        Loop: for (int i=0; i<26; i++){ //이동 수
            String temp = "";
            for (int j=0; j<input.length(); j++){
                int decode = input.charAt(j)-'a' - i;
                if (decode < 0) decode = 26 + decode;
                temp += (char) (decode+'a');
            }
//            System.out.println("이동 수 "+i+" try decoding.."+temp);
            for (String di : dic){
                if(temp.contains(di)) {
                    result = temp;
                    break Loop;
                }
            }

        }
        System.out.println(result);


        //2. 그 중에 사전에 있는 단어가 1개 이상이라면 해독한 그 문장을 출력

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
