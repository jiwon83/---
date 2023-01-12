import java.util.*;
class Solution {
    
    public int [][] dp;
    public int depth;
    
    public int solution(int[][] triangle) {
        
        //1. dp 초기화
        depth = triangle.length;
        dp = new int[depth][];
        for(int i=0; i<depth; i++){
            dp[i] = new int[i+1];
        }
        
        //2. dp를 채워감
        dp[0][0] = triangle[0][0];
        
        for(int n =1; n < depth; n++){
            for(int row = 0; row <= n; row++){
                
                int left = ( row-1 >= 0 )? dp[n-1][row-1] : -1 ;
                int right = ( row < dp[n-1].length )? dp[n-1][row] : -1 ;
                
                dp[n][row] = Math.max(left, right) + triangle[n][row];

            }
        }
        return Arrays.stream(dp[depth-1]).max().getAsInt();     
    
    }
}