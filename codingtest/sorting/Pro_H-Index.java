import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);

        for(int j=0; j<= citations[citations.length-1]; j++){
            int h=0;
            for(int i=0; i<citations.length; i++){
                if(citations[i] >= j){
                    h= citations.length - i;
                    break;
                }
            }
            System.out.println("인용수 "+ j +" 논문 수 "+ h);
            if(j <= h) {
                System.out.println("들어감 :: 인용수 "+ j +" 논문 수 "+ h);
                answer = j;
            }
            else break;
           
        }

        return answer;
    }
}