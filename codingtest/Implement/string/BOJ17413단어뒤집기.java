package implement.string;
import java.io.*;
import java.util.*;
/*
https://www.acmicpc.net/problem/17413
시간 단축을 위해 한 번 더 풀어보기
 */
public class BOJ17413단어뒤집기 {
    static StringBuilder wait = new StringBuilder();
    static StringBuilder result = new StringBuilder();
    static FastReader sc = new FastReader();
    static String S;

    static void input() {
        //string주어짐
        S = sc.nextLine();
    }
    static void pro() {
        for (int i=0; i<S.length(); i++){
            if (S.charAt(i)=='<'){
                result.append(reverseWords(wait.toString())); //이전은 모두 단어이므로
                wait.setLength(0); //wait 비우기
                result.append(S.charAt(i));

            } else if (S.charAt(i)=='>') {
                result.append(wait); //이전은 모두 태그이므로
                wait.setLength(0); //wait 비우기
                result.append(S.charAt(i));

            }else {
                wait.append(S.charAt(i));
            }
        }
        //<>를 거치지 못하고 통과한 것들은 모두 단어이므로
        result.append(reverseWords(wait.toString()));
        wait.setLength(0);

    }
    static String reverseWords(String input){
        String result="";
        String [] words = input.split(" ");
        Stack<String> stack = new Stack<>();

        for (int i=0; i<words.length; i++){
            for (int j=0; j<words[i].length(); j++){
                stack.push(words[i].charAt(j)+"");
            }
            for (int j=0; j<words[i].length(); j++){
                result += stack.pop();
            }
            if (words.length - i >1) result += " "; //공백 추가
        }

        return result;
    }
    public static void main(String[] args) {

        input();
        pro();
        System.out.println(result);
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
