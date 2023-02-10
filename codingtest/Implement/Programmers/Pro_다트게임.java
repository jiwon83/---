import java.util.*;
class Solution {
    
    int cal (String num, char bonus){
        int num1 = Integer.parseInt(num);
        if(bonus=='S'){
            return (int) Math.pow(num1, 1);
        }else if(bonus=='D'){
            return (int) Math.pow(num1, 2);
        }else if(bonus=='T'){
            return (int) Math.pow(num1, 3);
        }//else if(bonus=='T') 
        return -1;
    }
    
    public int solution(String dartResult) {
        int answer = 0;
        int[] ans= new int[3];
        int ansIdx=0;
        
        //세트당 자르고
        String tmp="";
        for(int i=0; i< dartResult.length(); i++){
            char c = dartResult.charAt(i);
            
            if('0'<= c && c <= '9'){
                tmp+=c+"";
            }else if(c=='S' || c=='D' || c=='T'){
                ans[ansIdx++] =cal(tmp, c);
                tmp="";
            }else if(c=='*'){
                if(ansIdx-2 >= 0) ans[ansIdx-2] *=2;
                ans[ansIdx-1] *=2;
            }else if(c=='#'){
                ans[ansIdx-1] *= -1;
            }
            
        }
        
        for(int i : ans ){
            answer += i;
        }
        return answer;
    }
}