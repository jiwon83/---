import java.util.*;
import java.util.stream.Stream;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        
        ArrayList<Integer> ans = new ArrayList<>();
        String [] arr = today.split("\\.");
        System.out.println(Arrays.toString(arr));
        int todayToDay = Integer.parseInt(arr[0])*12*28
                            + Integer.parseInt(arr[1])*28 + Integer.parseInt(arr[2]);
        System.out.println(todayToDay);
        
        //1. 약관을 찾아서 유효기간을 측정
        for(int i=0; i<privacies.length; i++){
            
            String [] privacy = privacies[i].split(" ");
            // System.out.println(Arrays.toString(privacy));
            
            int term_month=0; //유효 가능 달
            for(int j=0; j< terms.length; j++){
                String [] term = terms[j].split(" ");
                if( privacy[1].equals(term[0]) ){
                    term_month = Integer.parseInt(term[1]);
                }
            } 
//             //유효 기간 측정 privacy[0] + 유효 가능 달
            System.out.println(privacy[0]+" 유효 가능 달 "+term_month);
            String [] temp = privacy[0].split("\\.");
            int endToday = Integer.parseInt(temp[0])*12*28 
                            + Integer.parseInt(temp[1])*28 + term_month*28
                                + Integer.parseInt(temp[2]);
            System.out.println(endToday);
            
// //             //2. 만약 폐기해야한다면 answer에 넣는다. 
            if(todayToDay - endToday >= 0) ans.add(i+1);
        }
  
        return ans.stream().mapToInt(Integer:: intValue).toArray();
    }
}