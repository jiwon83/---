package kakao_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/*
프로그래머스 lv 2 뉴스클러스터링 https://school.programmers.co.kr/learn/courses/30/lessons/17677
시도 X O
 */
public class Solution2 {
    List<String> array1, array2; //다중 집합을 담을 리스트
    ArrayList<String> intersection, union; //교집합과 합집합

    public int solution(String str1, String str2) {
        int answer = 0;

        //배열들 초기화
        intersection = new ArrayList<>();
        union = new ArrayList<>();

        //1. 다중집합의 원소로 만든다.
        array1 = new ArrayList<>(makeOverlapUnion(str1));
        array2 = new ArrayList<>(makeOverlapUnion(str2));

        Collections.sort(array1);
        Collections.sort(array2);

        //2. 합집합과 교집합을 구한다.
        getUionIntersection();

        //3. 자키드 유사도 출력
        double result = getJacckad();
        return (int)result ;
    }

    private void getUionIntersection() {
        //오름차순 정렬을 해주어야

        for (String s : array1) {
            if (array2.remove(s)) {
                intersection.add(s);
            }
            union.add(s);
        }
        union.addAll(array2);
    }

    public boolean isInOtherString(String str) {
        if (str.matches(".*[0-9].*")) return true;

        //문자열에 공백 혹은 특수문자가 입력된 경우
        String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
        if (!Pattern.matches(pattern, str)) {
            return true;
        }
        return false;
    }


    // 두 글자씩 다중 집합을 만들기
    public List<String> makeOverlapUnion(String str) {
        ArrayList<String> array = new ArrayList<>();

        //우선 먼저 다 대문자로 만들어준다.
        str = str.toUpperCase();

        for (int i = 0; i < str.length() - 1; i++) {

            if (!isInOtherString(str.substring(i,i+2))){
                array.add(str.substring(i,i+2));
            }

        }

//        for (int i = 0; i < str.length() - 1; i++) {
//            char a = str.charAt(i);
//            char b = str.charAt(i + 1);
//            if (Character.isLetter(a) && Character.isLetter(b)) { //모두 문자가 맞다면
//                StringBuilder sb = new StringBuilder();
//                sb.append(a).append(b);
//                array.add(new String(sb));
//
//            }
//        }
        return array;
    }

    public double getJacckad() {
        double d =0;
        if (union.size()==0) d=1;
        else d = (double)intersection.size() / (double)union.size();
        d = d * 65536;
//        return Math.floor(d);
        return d;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
//        System.out.println(s.solution("FRANCE", "french"));
        System.out.println(s.solution("E=M*C^2", "e=m*c^2"));
    }
}
