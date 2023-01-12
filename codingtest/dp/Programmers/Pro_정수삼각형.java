import java.util.*;
class Solution {
    
    ArrayList<Integer> [] dp;
    int depth;
    
    public int solution(int[][] triangle) {
        
        depth = triangle.length; // 정수 삼각형의 높이
        dp = new ArrayList [ depth + 1];
    
        //초기화.
        for(int i=0; i<= depth; i++){
            dp[i] = new ArrayList<>();
        }
        dp[0].add(0);
        
        //dp를 채워 넣는다.
        for(int n = 1; n <= depth; n++){ //1~5
            
            for(int idx = 0; idx < triangle[n-1].length; idx++ ){ //1= 0, 2=0,1 , 3=0,1,2 
                
                if(idx==0){
                    dp[n].add(dp[n-1].get(idx) + triangle[n-1][idx]);
                    
                }else if(idx == triangle[n-1].length-1){ //맨 끝이라면
                    dp[n].add( dp[n-1].get(idx - 1)+ triangle[n-1][idx] );
                    
                }else{
                    //점화식 
                    /*
                     dp[n][row] = MAX( dp[n-1]의 row -1 인덱스 값, dp[n-1]의 row 인덱스 값) + 주어진 삼각형의 해당 위치 값
                    */
                    dp[n].add( Math.max(dp[n-1].get(idx-1), dp[n-1].get(idx) ) + triangle[n-1][idx]);
                }
            }
        }
        
        // System.out.println(Arrays.toString(dp));
    
        //마지막 dp 인덱스 값 중 최대값을 return.    
        int answer = Collections.max(dp[dp.length-1]); //list에서 max값을 가져온다.
        
        
        return answer;
    }
}