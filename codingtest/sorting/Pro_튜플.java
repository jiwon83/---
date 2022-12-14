package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
문자열 압축 https://school.programmers.co.kr/learn/courses/30/lessons/60057
 */
public class Pro_튜플 {
    /*
        Todo : 변수 선언
         */

    static StringBuilder sb = new StringBuilder();

    /* 필요한 함수들 */

    static public ArrayList<Integer> solution(String s) {
        int[] answer = {};

        ArrayList<Integer> result = new ArrayList<>();

        //1. { 전체 제거
        s = s.replaceAll("\\{","");
        //2. } 로 split
        String [] group = s.split("}");

        // grup 정렬 : 크기의 오름차순
        Arrays.sort(group, new Comparator<String>(){
            public int compare(String o1, String o2){
                return Integer.compare(o1.length(), o2.length());
            }
        });

        //부분 집합 앞의 , 를 제거
        for (int i=0; i< group.length; i++){

            if (group[i].charAt(0)==','){
                group[i] = group[i].substring(1);
            }
        }

        //분리된 부분 집합인 group을 순회
        for (int i=0; i< group.length; i++){

            String [] inGroup = group[i].split(","); //부분 집합을 , 로 split해 원소들을 나눈다.

            for (int j=0; j<inGroup.length; j++){
                if (!result.contains( Integer.valueOf( inGroup[j] ))){  //이전에 없던 수라면
                    result.add(Integer.valueOf(inGroup[j])); //추가.
                }
            }

        }
            return result;
//        answer = result.toArray();
//        return answer;
    }


    public static void main(String[] args) {
        System.out.println(solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"));

    }


}
