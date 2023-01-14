import java.util.*;
class Solution {
    int [][] Puddles;
    int N, M;
    long [][] dp;
    long ans;
    int [][] d = {{1,0},{0,1}};
    
    long dfs(int x, int y){
        
        if(x==M && y==N) return 1;
        
        if(dp[x][y]!= -1) return dp[x][y];
        
        dp[x][y] =0;
        
        Loop1: for(int i=0; i<2; i++){
            int nx = x + d[i][0];
            int ny = y + d[i][1];
            
            if(nx < 1 || ny < 1 || nx > M || ny > N ) continue;
            for(int p = 0; p<Puddles.length; p++){
                if(nx ==Puddles[p][0] && ny ==Puddles[p][1]){
                    continue Loop1;
                }
            }
            System.out.println("이동>> "+ nx+ " "+ny);
            
            //물웅덩이도 아니고 범위에 벗어나지 않았다.
            dp[x][y] += dfs(nx,ny); 
   
        }//Loop1
        return dp[x][y];
    }
    
    public int solution(int m, int n, int[][] puddles) {
        N =n; M=m;
        dp = new long [M+1][N+1];
        for(int i=1; i<=M; i++){
            for(int j=1; j<=N; j++){
                dp[i][j] = -1;
            }
        }
        Puddles = puddles;
        
        ans = dfs(1,1);
        System.out.println(ans);
        System.out.println(Arrays.deepToString(dp));
        
        int answer = 0;
        return (int) (ans % 1000000007) ;
    }
}