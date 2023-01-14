import java.util.*;
class Solution {

    int [][] dp;
    
    public int solution(int M, int N, int[][] puddles) {
  
        dp = new int [M+1][N+1];
        for(int i=0; i< puddles.length; i++){
            dp[puddles[i][0]][puddles[i][1]] = -1;
        }
        
        //dp를 채워 넣는다.
        dp[1][1]=1;
        for(int i=1; i<=M; i++){
            for(int j=1; j<=N; j++){
                
                if( dp[i][j] == -1 ) continue;
                
                if( j + 1 <= N && dp[i][j+1] != -1){
                    int num = ( dp[i][j+1] + dp[i][j] ) % 1000000007 ;
                    dp[i][j+1] = num;
                } 
                // dp[i][j+1] += dp[i][j] % 1000000007 ; //right
                
                if( i + 1 <= M && dp[i+1][j] != -1){
                    int num = ( dp[i+1][j] + dp[i][j] ) % 1000000007 ;
                    dp[i+1][j] = num;
                } //dp[i+1][j] += dp[i][j] % 1000000007; //lower
            }
        }       
        return dp[M][N];
    }
}