import java.util.*;
import java.util.Map;
import java.util.HashMap;
class Solution {
    public String solution(String[] participant, String[] completion) {

        HashMap<String, Integer> map = new HashMap<>(); 

        for(String part : participant) map.put( part, map.getOrDefault(part, 0) + 1); 
        for(String comple: completion) map.put( comple, map.get(comple)-1 );
        // System.out.println(Collections.unmodifiableMap(map));
        
        // for(String key : map.keySet()){
        //     if(map.get(key)==1){
        //         return key;
        //     }
        // }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue()==1){
                return entry.getKey();
            }
        }
        return "";
    }
}