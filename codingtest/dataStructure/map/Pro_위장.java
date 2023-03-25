import java.util.*;
class Solution {

    Map<String, Integer> map = new HashMap<>();

    public int solution(String[][] clothes) {
        
        for(int i=0; i< clothes.length; i++){
            String cat = clothes[i][1]; 
            map.put(cat, map.getOrDefault(cat,0) + 1);
        }

        int answer = 1;
        for(String key : map.keySet()){
            answer *= map.get(key)+1;
        }
   
        return answer-1;
    }
}