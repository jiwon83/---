import java.util.*;
class Solution {
    Map<String, Integer> termMap = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        String [] todayArr = today.split("\\.");
        int Today = Integer.parseInt(todayArr[0])* 12*28 + Integer.parseInt(todayArr[1])* 28 + Integer.parseInt(todayArr[2]);
        
        for(String term : terms){
            String [] temp = term.split(" ");
            termMap.put(temp[0], Integer.parseInt(temp[1])* 28 );
        }
        for(int i=0; i< privacies.length; i++){
            String [] temp = privacies[i].split(" ");
            int add = termMap.get(temp[1]);
            String [] priArr = temp[0].split("\\.");
            int priDay = Integer.parseInt(priArr[0])* 12*28 + Integer.parseInt(priArr[1])* 28 + Integer.parseInt(priArr[2]);
            int deadDay = priDay + add;
            // System.out.println(deadDay);
            if(Today >= deadDay){
                // System.out.println("trash "+(i+1) );
                list.add(i+1);
            }
        }
        Collections.sort(list);
        int [] a = new int [list.size()];
        int idx=0;
        for(int i : list){
            a[idx++] = i;
        }

        return a;
    }
}