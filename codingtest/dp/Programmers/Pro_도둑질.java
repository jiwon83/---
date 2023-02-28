import java.util.*;
class Solution {
    public int solution(int[] money) {
    
        //dp [0][k] => 1번 출발, dp [1][k] => 2번 출발
        int [][] dp = new int [2][money.length];
        dp[0][0]=money[0]; dp[0][1] = money[1]; dp[0][2] = money[2] + dp[0][0];
        dp[1][1]= money[1]; dp[1][2] = money[2];
        
        for(int s=0; s<2; s++){
            for(int i=3; i<money.length; i++){
                if(s==0 && i==money.length-1) continue; //1번 출발에서는 마지막 방문 X
                dp[s][i] = money[i] + Math.max(dp[s][i-2], dp[s][i-3]);
            }
        }
        int max_value1 = Math.max(dp[0][money.length-3], dp[0][money.length-2]);
        int max_value2 = Math.max(dp[1][money.length-2], dp[1][money.length-1]);
        return Math.max(max_value1, max_value2);
    }
}