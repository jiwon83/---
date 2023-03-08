import java.util.*;
class Solution {
    int[] answer = new int[2];
    int [] select;
    int N;
    int[][] Users;
    int [] Emoticons;
    
    public void compare(int [] select){
        int total_earn=0;
        int total_register=0;
        
        for(int i=0; i<Users.length; i++){ //유저마다
            int sale = Users[i][0]; int money = Users[i][1];
            int purchase=0;
            for(int j=0; j<N; j++){ //이모티콘 하나씩   
                if(select[j] >= sale){ //기준 할인율 이상이라면
                    purchase += Emoticons[j] - Emoticons[j] *((double)select[j] /100);
                }
            }
 
            if(money <= purchase){
                total_register++;
            }else{
                total_earn += purchase;
            }
        }//for users
        // System.out.println("total_earn> "+ total_earn + " total_register> "+total_register);
        
        if(answer[0] < total_register){
            answer[0] = total_register;
            answer[1] = total_earn;
        }else if(answer[0] == total_register && answer[1] < total_earn){
            answer[1] = total_earn;
        }
    }
    public void recur(int k){
        if(k==N){
            //compare();
            compare(select);
            
        }else{
            for(int cand = 10; cand <=40; cand +=10){
                select[k] = cand;
                recur(k+1);
                select[k]=0;
            }
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        N = emoticons.length;
        select = new int[N];
        Emoticons = emoticons;
        Users = users;
        
        recur(0);
        return answer;
    }
}