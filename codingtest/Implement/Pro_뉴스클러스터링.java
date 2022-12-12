package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Pro_뉴스클러스터링 {
    static ArrayList<String> union, intersection, str1Set, str2Set;

    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    /* 필요한 함수들 */

    public int solution(String str1, String str2) {
        int answer = 0;

        //1. 2씩 나눈다.
        str1Set = makeSet(str1);
        str2Set = makeSet(str2);

        //2. 합집합, 교집합을  구한다.
        union = new ArrayList<>();
        intersection = new ArrayList<>();
        getUionInter();

        //3. 자카드 유사도를 반환
        answer = getJacckad(Double.parseDouble(union.size() + ""), Double.parseDouble(intersection.size() + ""));

        return answer;
    }

    public ArrayList<String> makeSet(String a) {

        //1. 모두 소문자로 변환
        a= a.toLowerCase();
        ArrayList<String> result = new ArrayList<>();
        //if (str.matches(".*[0-9].*")) return true;
        String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";

        //2. 2씩 나눈다. 만약 특수 , 공백, 숫자는 버린다.
        for (int i = 0; i < a.length() - 1; i++) {
            String str = a.substring(i, i + 2);
            if (!Pattern.matches(pattern, str)) {
                continue;
            } else if (containsNum(str)) {//숫자라면 정규식 방법도 알아볼 것.
                continue;
            }
            result.add(str);

        }
        return result;
    }

    public boolean containsNum(String str) {
        boolean found = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
                found = true;
            }
        }
        return found;
    }


    //3. 합집합, 교집합을  구한다.
    public void getUionInter() {
        for (String a : str1Set) {
            if (str2Set.remove(a)) {
                intersection.add(a);

            }
            union.add(a);
            }
        union.addAll(str2Set);

    }

    //4. 자카드 유사도를 반환
    public int getJacckad(double union, double inter) {
        if (union == 0 && inter == 0) return 1 * 65536;
        double l = (inter / union) * 65536;
        return (int) l;
    }


    public static void main(String[] args) {
        Pro_뉴스클러스터링 s = new Pro_뉴스클러스터링();
        System.out.println(s.solution("FRANCE", "french"));
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
