import java.util.*;
import java.util.stream.Stream;
class Solution {
    public HashMap<String, Integer> hm = new HashMap<>();
    
    public void initHm(){
        //'A' = 0 +'A', 'B' = 1+'A' ....
        for(int i=0; i<26; i++){
            String key = (char)(i + 'A') +"";
            Integer val = new Integer(i)+1;
            hm.put(key, val);
        }
    }
    
    public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> ans = new ArrayList<>();
        //1.A~Z 0~25까지 사전 초기화
        initHm();
        // System.out.println(hm);
        //2. 압축
        for(int L=0, R=-1; L < msg.length(); L++ ){
            System.out.println("L= "+L+" R= "+R);
            while(R+1 < msg.length() && hm.containsKey(msg.substring(L,R+1+1))){
                R++;
            }
            System.out.println("출력 "+hm.get(msg.substring(L,R+1)));
            ans.add(hm.get(msg.substring(L,R+1)));
            if(R+1 < msg.length()){
                hm.put( msg.substring(L,R+1+1), hm.size()+1);
                System.out.println("새로운 등록 ! "+msg.substring(L,R+1+1));
            }
            L=R;
        }
        answer = ans.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}