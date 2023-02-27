import java.util.*;
class Solution {
    public int solution(int[] money) {
        int max_value=0;
   
        //dp [0][k] => 1번 출발, dp [1][k] => 2번 출발
        int [][] dp = new int [2][money.length];
        dp[0][0]=money[0]; dp[0][1] = money[1]; dp[0][2] = money[2] + dp[0][0];
        dp[1][1]= money[1]; dp[1][2] = money[2];
        max_value = Math.max(dp[0][1],dp[0][2]);
        
        for(int s=0; s<2; s++){
            for(int i=3; i<money.length; i++){
                if(s==0 && i==money.length-1) continue; //1번 출발에서는 마지막 방문 X
                dp[s][i] = money[i] + Math.max(dp[s][i-2], dp[s][i-3]);
                max_value = Math.max(max_value, dp[s][i]);
            }
        }
        
        //정렬 후 출력
//         Integer [] integerArray = Arrays.stream(dp).boxed().toArray(Integer[]::new);
//         Arrays.sort(integerArray, Collections.reverseOrder());
//         Integer [] integerDp2 = Arrays.stream(dp2).boxed().toArray(Integer[]::new);
//         Arrays.sort(integerDp2, Collections.reverseOrder());
        
//         return Math.max(integerArray[0], integerDp2[0]);
        return max_value;
    }
}