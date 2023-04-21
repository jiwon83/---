import java.util.*;
import java.io.*;

class Solution
{
   public static void main(String args[]) throws Exception
   {
      //System.setIn(new FileInputStream("res/input.txt")); 
       /*
       (9*(5+3))
(5*10+2)
       */

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      for(int test_case = 1; test_case <= 10; test_case++)
      {
            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();
            System.out.println("#"+test_case+" "+recur(str));

      }
   }//main
   static Long recur(String exp){
       StringBuilder sb = new StringBuilder();
       
       for(int i=0; i<exp.length(); i++){
           char c = exp.charAt(i);
       		if(c=='('){
                int end = getEndIdx( i, exp);
                //System.out.println(exp+"의 괄호 제거 "+ exp.substring(i+1,end) );
                sb.append(recur(exp.substring(i+1,end)));
                i = end;
                          
            }else{
                sb.append(c);
            }
       }//for
       //System.out.println("계산할 식 "+ sb.toString());
       return calculate(sb.toString());  
   }
   static int getEndIdx(int sIdx, String exp){
       int index=0;
       for(int i=sIdx; i<exp.length(); i++){
           char c = exp.charAt(i);
           if(c=='('){
               index--;
           }
           if(c==')'){
               index++;
           }
           if(index==0){
               return i;
           }
       }
       return -1;
       
   }//getEndIdx
    static Long calculate(String exp){ //원소가 하나인 경우도 계산
        char [] prior = new char[]{'*','+'};
        //나누고
        ArrayList<Long> nums = new ArrayList<>();
        ArrayList<Character> opers = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        for(int i=0; i<exp.length(); i++){
            char c = exp.charAt(i);
            if(c=='*' || c=='+'){
                nums.add( Long.parseLong(buffer.toString()) );
                buffer.setLength(0);
                opers.add(c);
            }
            else{
            	buffer.append(c);   
            }
        }
        nums.add( Long.parseLong(buffer.toString()) );
        
        //우선순위별로 계산
        for(int i=0; i<prior.length; i++){
            for(int j=0; j<opers.size(); j++){
                if(prior[i] == opers.get(j)){
                    //계산
                    Long ans = cal( prior[i], nums.get(j), nums.get(j+1));
                    nums.add(j, ans );
                    nums.remove(j+1);
                    nums.remove(j+1);
                    opers.remove(j);
                    j--;
                }
            }
        }
        if(nums.size() != 0 ) return nums.get(0);
        else return -1L;
    }
    static Long cal(char oper, Long num1, Long num2){
        switch(oper){
            case '*':
                return num1 * num2;
            case '+':
                return num1+num2;
        }
        return -1L;
    }
  
    
}//solution