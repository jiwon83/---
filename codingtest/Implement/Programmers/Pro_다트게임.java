class Solution {
    String bonus = "SDT";
    String option = "*#";
    
    double cal (String num, char bonus){
        double num1 = Double.parseDouble(num);
        if(bonus=='S'){
            return Math.pow(num1, 1);
        }else if(bonus=='D'){
            return Math.pow(num1, 2);
        }else if(bonus=='T'){
            return Math.pow(num1, 3);
        }//else if(bonus=='T') 
        return -1;
    }
    
    public double solution(String dartResult) {
        double answer = 0;
        //세트당 자르고
       
        for(int i=0; i< dartResult.length(); i++){
            int j= i+1;
            while( j+1 < dartResult.length() 
                  && !Character.isDigit(dartResult.charAt(j+1)) ){
                j++;
            }
            //System.out.println(dartResult.substring(i,j+1));//ok
            //점수 보너스/ 옵션으로 나눈다.
            String temp = dartResult.substring(i,j+1);
            if(dartResult.charAt(j)=='#' || dartResult.charAt(j)=='*'){
                System.out.println("포함,,"+ temp);
                if(dartResult.charAt(j)=='#'){
                    answer += - cal(dartResult.substring(i,j-1), dartResult.charAt(j-1));
                }else{
                    answer += cal(dartResult.substring(i,j-1), dartResult.charAt(j-1));
                    answer *= 2;
                }
            }else{
                System.out.println("비포함,,"+ temp);
                answer += cal(dartResult.substring(i,j), dartResult.charAt(j));
                //뒤는 bonus 앞은 숫자
            }
            //옵션이 있다면 각각에 맞게 처리
            //없다면 점수와 보너스만 처리
            //anser에 갱신.
            
            i = j;
        }
        
        
        return answer;
    }
}