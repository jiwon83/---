import java.util.*;
class Solution {
    final int MAX = Integer.MAX_VALUE;
    final int MIN = Integer.MIN_VALUE;
    public int [][][] dp;
    public int [] numbers;
    public int [] opers; //0= +, 1= -
    public int recur(int a, int b, int flag){ //a=start, b=end, val = max or min
        
        if(a==b || dp[flag][a][b] != MAX ){ //이미 구한 값이 있다면
            return dp[flag][a][b];
        }
        
        int result = flag==0 ? MIN: MAX; 
 
        if(flag==0){ //max
            for(int sp=a; sp<b; sp++){
                int oper = opers[sp];
                if(oper==0){//+
                    result = Math.max(result, recur(a,sp,0) + recur(sp+1,b,0));
                    
                }else{ //-
                    result = Math.max(result, recur(a,sp,0) - recur(sp+1,b,1));   
                }
            }
        }else{ //min
            for(int sp=a; sp<b; sp++){
                int oper = opers[sp];
                if(oper==0){//+
                    result = Math.min( result, recur(a,sp,1) + recur(sp+1,b,1));
                }else{ //-
                    result = Math.min( result, recur(a,sp,1) - recur(sp+1,b,0));
                }
            } 
        }
        dp[flag][a][b] = result;
        return result;
    }
    public int solution(String arr[]) {
        // int answer = -1;
        numbers = new int[arr.length/2+1];
        opers = new int[arr.length/2];
        dp = new int [2][numbers.length][numbers.length];
        //1. arr 분리 
        for(int i=0; i<arr.length; i++){
            if(i % 2==0 ) numbers[i/2] = Integer.parseInt(arr[i]);
            else {
                opers[i/2] = arr[i].equals("+")? 0 : 1;
            }
        }
 
        //1-2. dp 초기화
        for(int i=0; i<numbers.length; i++){
            for(int j=0; j<numbers.length; j++){
                dp[0][i][j] = MAX;
                dp[1][i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i=0; i<numbers.length; i++){
            dp[0][i][i] = numbers[i];
            dp[1][i][i] = numbers[i];
        }
        
        //2. return recur
        int answer =  recur(0, numbers.length-1, 0);

        return answer;
    }
}