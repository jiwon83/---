class Solution {
    String [] opers;
    int [] nums;
    int [][][] dp = new int [2][101][101]; //0= 최대, 1=최소
    
    public int recur(int type, int startIdx, int endIdx){ //최대값을 return
        if(startIdx == endIdx){
            return dp[0][startIdx][endIdx];
        }
        if(dp[type][startIdx][endIdx] != Integer.MAX_VALUE && dp[type][startIdx][endIdx] != Integer.MIN_VALUE){
            return dp[type][startIdx][endIdx];
        }
        if(type==0){ //max
            for(int oper = startIdx; oper < endIdx; oper++){
                int splitIdx = oper;
                if(opers[oper].equals("-")){
                    dp[type][startIdx][endIdx] 
                        = Math.max( dp[type][startIdx][endIdx] ,
                                   recur(0, startIdx, splitIdx) - recur(1, splitIdx+ 1, endIdx)
                                   );
                }
                if(opers[oper].equals("+")){
                    dp[type][startIdx][endIdx]
                        = Math.max (dp[type][startIdx][endIdx], 
                                    recur(0, startIdx, splitIdx) + recur(0, splitIdx+ 1, endIdx)
                                    );
                }
                
            }//for
        }else if(type==1){ //min
            for(int oper = startIdx; oper < endIdx; oper++){
                int splitIdx = oper;
                if(opers[oper].equals("-")){
                    dp[type][startIdx][endIdx] 
                        = Math.min( dp[type][startIdx][endIdx] , 
                                   recur(1, startIdx, splitIdx) - recur(0, splitIdx+ 1, endIdx)
                                   );
                }
                if(opers[oper].equals("+")){
                    dp[type][startIdx][endIdx] 
                        = Math.min(dp[type][startIdx][endIdx] ,
                                   recur(1, startIdx, splitIdx) + recur(1, splitIdx+ 1, endIdx)
                                   );
                }
                
            }//for
        }
        
        return dp[type][startIdx][endIdx];
        
    }
    public int solution(String arr[]) {
        /** nums & opers 분리*/
        nums = new int[arr.length/2+1];
        opers = new String [arr.length/2];
        for(int i=0; i<arr.length; i++){
            if(i % 2==0){
                nums[i/2]= Integer.parseInt( arr[i] );
            }else{
                opers[i/2] = arr[i];
            }
        }
        /* dp 배열 초기화 */
        for(int i=0; i<nums.length; i++){
            for(int j=0; j<nums.length; j++){
                dp[0][i][j] = Integer.MIN_VALUE;
                dp[1][i][j] = Integer.MAX_VALUE;
            }
        }
        /* 자기자신 값 넣어주기 dp 초기화 */
        for(int i=0; i< nums.length; i++){
            dp[0][i][i] = nums[i];
            dp[1][i][i] = nums[i];
        }
        
        return recur(0, 0, arr.length/2);
    }
}