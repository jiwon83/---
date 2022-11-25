package kakao_problems;

import java.util.Arrays;
/*
programmers lv1. 비밀지도
 */
public class Solution1 {
    String [] map1,map2;
    int n;
    public String convertLengthStr(String input, int digit){

        //1 => 00001 / N=5
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        int num = digit - input.length(); //5-1
        while (num-- >0){
            sb.insert(0,'0');
        }

        return sb.toString();

    }

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        map1 = new String[n];
        map2 = new String[n];

        this.n= n;

        for (int i=0; i<arr1.length; i++){
            //여기서 숫자 남은 자리 0을 채우기 위해 작업

            String row1 = convertLengthStr(Integer.toBinaryString(arr1[i]),n);
            map1[i] = row1;
            String row2 = convertLengthStr(Integer.toBinaryString(arr2[i]),n);
            map2[i] = row2;

        }
//        System.out.println(Arrays.toString(map1));
//        System.out.println("----------------");
//        System.out.println(Arrays.toString(map2));

        for (int i=0; i< n; i++){

            StringBuilder sb = new StringBuilder();
            for (int j=0; j<map1.length; j++){
                if (map1[i].charAt(j) == '1' || map2[i].charAt(j) == '1'){
                    sb.append("#");
                }else{
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        String[] answer = s.solution(5, new int []{9,20,28,18,11}, new int[] {30,1,21,17,28});
        for (int i=0; i<answer.length; i++){
            System.out.println(answer[i]);
        }


    }
}
