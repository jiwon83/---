import java.util.*;
class Solution {
    
    public int[] Answers;
    public int [] AnsCnt = new int[4];
    public ArrayList<Integer> Ans= new ArrayList<>();
    int cntMax=0;
    String [] students = {"","12345", "21232425","3311224455"};
    
    
    public ArrayList<Integer> solution(int[] answers) {
        Answers = answers; 
        
        //1. 각 수험생의 답을 정답의 길이 만큼 자른다. for
        for(int i=1; i<=3; i++){
        
            String stAns = getStudentAns(i);
            int count =0;
            
            //2. 정답이랑 비교해서 맞은 갯수를 구하고
            for(int j=0; j<Answers.length; j++){
                if(stAns.charAt(j)==(char)(Answers[j]+'0')){
                    count++;
                }
            }
            AnsCnt[i] = count;
        }
        //3. Max값을 update한다.
        for(int i=1; i<4; i++){
            cntMax = Math.max(cntMax,AnsCnt[i] );
        }
        for(int i=1; i<4; i++){
            if(AnsCnt[i] >= cntMax){
                Ans.add(i);
            }
            
        }
        Collections.sort(Ans);
        int[] ans = {};
        return Ans;
    }
    public String getStudentAns( int studentNum){
        String answer = ""; 
            //answers의 길이보다 크거나 같을 때까지
            while(answer.length() <= Answers.length){
                answer  += students[studentNum];
            }
        return answer;
    }
}