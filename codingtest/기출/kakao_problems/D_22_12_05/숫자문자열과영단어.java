package programmers.D_22_12_05;
/*
lv1. https://school.programmers.co.kr/learn/courses/30/lessons/81301

더 간단한 코드 https://gist.github.com/buri-1029/1eb88cb289b8cdcf3d00f52109f565a9
 */

import java.io.*;
import java.util.*;

public class 숫자문자열과영단어 {

    static HashMap<String, Integer> vacaMap = new HashMap<>();
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static String string;

    /* 필요한 함수들 */

    public int solution(String s) {

        string = s;
        initVocaMap();
        pro();
        int answer = Integer.parseInt(sb.toString());
        //sb를 int로 변환
        return answer;
    }

    public void initVocaMap() {
        vacaMap.put("zero", 0);
        vacaMap.put("one", 1);
        vacaMap.put("two", 2);
        vacaMap.put("three", 3);
        vacaMap.put("four", 4);
        vacaMap.put("five", 5);
        vacaMap.put("six", 6);
        vacaMap.put("seven", 7);
        vacaMap.put("eight", 8);
        vacaMap.put("nine", 9);
    }

    public int isSameWithMap(int startIdx) { //string의 startIdx을 기준으로 맵에서 일치는 숫자는 applend + 그때의 문자열 길이를 반환
        //map을 순회하면서
        int length = 0;
        Iterator<String> iter = vacaMap.keySet().iterator();

        while (iter.hasNext()) {
            String key = iter.next();
            int value = vacaMap.get(key); //숫자

            //map의 key와 string이 같은지 확인
            if ( key.length() <= string.substring(startIdx).length() ){
                if (key.equals(string.substring(startIdx, startIdx + key.length()))) {
                    length = key.length();
//                    System.out.println("key:"+key+" value:"+value);
                    sb.append(value); //숫자를 넣어준다.
                    break;
                }
            }

        }
//        System.out.println("return "+ length);
        return length; //만약 못찾았다면 0을 반환.
    }


    public void pro() {
        for (int i = 0; i < string.length(); i++) {
//            System.out.println("-------- i = "+i+" ---------");
            if (Character.isDigit(string.charAt(i))) { //숫자라면
                sb.append(string.charAt(i));
            } else { //문자라면 모든 map들과 비교
                int lenOfWord = isSameWithMap(i);

                i += lenOfWord - 1;
//                System.out.println(lenOfWord+"만큼 이동, "+"바뀐 i는 "+ i  );
            }
        }
    }


    public static void main(String[] args) {
        숫자문자열과영단어 s = new 숫자문자열과영단어();
        System.out.println(s.solution( "one4seveneight"));
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
