package kakao_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
lv2 k진수에서 소수의 갯수 구하기 https://school.programmers.co.kr/learn/courses/30/lessons/92335
시도: X O
 */
public class Solution3 {
    public int solution(int n, int k) {
        int answer = -1;
        int count =0;
        //1.k 진수로 변환
        String num = TenToN(n,k);
        //2. split으로 0으로 구분해서 배열에 저장
        String [] arrNum = num.split("0");

        //3. 공백이 있다면 제거
        //3-1 공백 제거를 위한 리스트로 변환.
        List<String> list = new ArrayList<>(Arrays.asList(arrNum));
        //3-2. 공백 제거
        list.removeAll(Arrays.asList(""));

        for (String s: list){

            if(isPrime(Long.parseLong(s))){
//                System.out.println("s = 소수 입니다."+ s);
                count++;
            }

        }
        return count;
    }
    static String TenToN(int num, int n){
        return Integer.toString(num,n);
    }
    static public boolean isPrime(long num){
        if (num==1) return false;
        for (int i=2; i<=Math.sqrt(num); i++){
            if (num % i==0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution3 s = new Solution3();
        System.out.println(s.solution(437674,3));//110011
        System.out.println(s.solution(110011,10));
        System.out.println(s.solution(999999,10));

    }
}
