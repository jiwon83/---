package kakao_problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/*
프로그래머스 lv 2 뉴스클러스터링 https://school.programmers.co.kr/learn/courses/30/lessons/17677
시도 X
 */
public class Solution2 {
    String[] overlap, union;
    String [] asso1, asso2;

    public int solution(String str1, String str2) {
        //배열들 초기화
        int answer = 0;
        overlap= new String[0];
        union = new String[0];
        asso1 = makeManySet(str1);
        asso2 = makeManySet(str2);
        makeOverlapUnion();
        return (int) getAnswer(); //**check
    }

    public boolean isInOtherString(String str){
        if ( str.matches(".*[0-9].*") ) return true;

        //문자열에 공백 혹은 특수문자가 입력된 경우
        String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
        if(!Pattern.matches(pattern, str)){
            return true;
//            System.out.println("공백 혹은 특수문자가 입력되었습니다.");
        }
        return false;
    }


    // 두 글자씩 다중 집합을 만들기
    public String [] makeManySet(String str){
        String [] arr = new String[str.length()-1]; //분할 하면 생기는 갯수 = str.length()-1
        for (int i=0; i<str.length()-1;i++){
            if (!isInOtherString( str.substring(i,i+2) )) { //특수문자, 공백, 숫자 없다면
                arr[i]= str.substring(i,i+2);
            }

        }
        return arr;
    }

    //교집합과 합집합 구하기
    public void makeOverlapUnion(){
        for (int i=0; i<asso1.length; i++){
            for (int j =0;j<asso2.length; j++){
                //대문자, 소문자 차이는 무시.
                if ( asso1[i].equalsIgnoreCase( asso2[j] ) ){
                    overlap[overlap.length] = asso1[i];

                }
                union[union.length] = asso1[i];
                union[union.length] = asso1[j];//check -?? union.length+1??
            }
        }
        //중복 제거 https://developer-talk.tistory.com/453
        List<String> newUnion = Arrays.asList(union)
                .stream()
                .distinct()
                .collect(Collectors.toList());
        List<String> newOverlap = Arrays.asList(overlap)
                .stream()
                .distinct()
                .collect(Collectors.toList());

//        System.out.println(Collections.unmodifiableList(newUnion));
//        System.out.println(Collections.unmodifiableList(newOverlap));

    }

    public double getAnswer() {
        double d = overlap.length / union.length;
        d = d * 65536;
        return Math.floor(d);
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        s.solution("FRANCE", "french");


    }
}
