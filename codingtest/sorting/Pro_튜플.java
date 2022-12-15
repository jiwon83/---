package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Pro_튜플 {


    static StringBuilder sb = new StringBuilder();
    static String [] groups;
    /* 필요한 함수들 */

    public static List<Integer> solution(String s) {

        //1. s를 나눈다.
        s = s.substring(2, s.length()-2).replace("},{", "-");
        groups= s.split("-");

        //2. 배열을 길이 순으로 정렬한다.
        Arrays.sort(groups, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        //3.튜플 배열을 순서대로 처리한다
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0; i< groups.length; i++){
            String [] temp = groups[i].split(",");
            for (int j=0; j< temp.length; j++){
                if (temp[j].length()==0) continue;
                if (list.indexOf(Integer.valueOf( temp[j] ) )==-1){
                    list.add( Integer.valueOf(temp[j]));
                }
            }
        }
        //중복확인한다.
        return list;
    }
    public int [] convert(List<Integer> list){
        int[] primitive = list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return primitive;
    }


    public static void main(String[] args) {
        System.out.println(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));

    }




}
