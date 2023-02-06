import java.util.*;
class Solution {
    public int [] count = new int [9]; //RT CF JM AN
    public List<Character> alpha = Arrays.asList('.','R','T','C','F','J','M','A','N');
    public String solution(String[] survey, int[] choices) {
        String answer = "";

        for(int i=0; i<survey.length; i++){
            String index = survey[i];
            int score = choices[i];
            switch(score){
                case 1: case 2: case 3:
                    //왼쪽
                    count [ alpha.indexOf(index.charAt(0)) ] += 3 - score + 1 ;
                    System.out.println(index.charAt(0)+" 에 += "+ (3 - score + 1));
                    break;
                case 4:
                    break;
                case 5: case 6: case 7:
                    //오른쪽
                    count [ alpha.indexOf(index.charAt(1)) ] += score - 4 ;
                    System.out.println(index.charAt(1)+" 에 += "+ (score - 4));
                    break;
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
