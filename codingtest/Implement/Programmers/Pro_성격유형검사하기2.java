import java.util.*;
class Solution {
    public int [] count = new int [9]; //RT CF JM AN
    public int [] amount = {0,3,2,1,0,1,2,3};
    public List<Character> alpha = Arrays.asList('.','R','T','C','F','J','M','A','N');
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        for(int i=0; i<survey.length; i++){
            
            int score = choices[i];
            if(score < 4){
                count [ alpha.indexOf(survey[i].charAt(0)) ] += amount[score] ;
            }else{
                count [ alpha.indexOf(survey[i].charAt(1)) ] += amount[score] ;
            }
            
        }//for
        //이제 점수를 체택
        for(int i=1; i<8; i+= 2){
            if( count[i] != count[i+1]){
                answer += count[i] > count[i+1] ? alpha.get(i)+"" : alpha.get(i+1)+"";
            }else{
                answer += alpha.get(i)+"";
            }
            
        }
        return answer;
    }
}