package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ1182_단어정렬 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static String [] strings ;
    static void input() {
          //N
        N= sc.nextInt();
        strings= new String[N];
        for (int i=0; i<N; i++){
            strings[i] = sc.next();
        }
    }
    static void pro() {
        //길이 > 사전순
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                if (o1.length() != o2.length()) return o1.length() - o2.length();
                return o1.compareTo(o2);

            }
        });
        List<String> result = deduplicateStringDistinct(strings);

        for (String s : result){
            sb.append(s).append("\n");
        }
        System.out.println(sb);

    }
    public static void main(String[] args) {
        input();
        pro();
    }
    /*
        String, Int Integer 배열, 리스트를 중복 제거, 반환값은 항상 List
         */
        //중복 제거 String [] arr -> List<String>
        public static List<String> deduplicateStringDistinct(String [] arr){
            List<String> list = new ArrayList<>(List.of(arr)); //배열을 리스트로 변환
            List<String> result = list.stream().distinct().collect(Collectors.toList()); //중복 제거
    //        System.out.println("result : " + result);
            return result;
            /* List<String> listtest = deduplicateStringDistinct(arr); */
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
