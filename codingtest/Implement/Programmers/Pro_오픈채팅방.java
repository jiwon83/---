import java.util.*;
class Solution {
    HashMap<String, String> hm = new HashMap<>();
    ArrayList<String> ans = new ArrayList<>();
    
    public String[] solution(String[] record) {
        String[] answer = {};
        for(String rec : record){ //"Enter uid1234 Muzi"
            if(rec.split(" ")[0].equals("Leave")) continue;
            
            String key = rec.split(" ")[1];
            String nick = rec.split(" ")[2];
            // System.out.println(key +" : "+nick);
            hm.put(key, nick);
        }
        for(String rec: record){
            String[] each = rec.split(" ");
            if(each[0].equals("Enter")){
                String msg = hm.get(each[1]) + "님이 들어왔습니다.";
                ans.add(msg);
            }else if(each[0].equals("Leave")){
                // String msg = hm.get(each[1].substring(3)) + "님이 나갔습니다.";
                 String msg = hm.get(each[1]) + "님이 나갔습니다.";
                ans.add(msg);
            }
        }
        answer = ans.toArray(new String[ans.size()]);
        return answer;
    }
}