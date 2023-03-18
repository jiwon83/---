import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        HashMap<String, Integer> user_idx_info = new HashMap<>();
        HashMap<String, Integer> reported = new LinkedHashMap<>();
        HashMap<String, HashSet<String>> reports = new HashMap<>();
        ArrayList<String> stop = new ArrayList<String>();
        
        //초기화
        for(int i=0; i<id_list.length; i++){
            reports.put(id_list[i], new HashSet<>());
            user_idx_info.put(id_list[i], i);
        }
        
        //유저별 신고한 목록
        for(String re : report){
            String [] temp = re.split(" "); //신고자 신고대상
            String me = temp[0]; String other = temp[1];
            reports.get(me).add(other); 
        }
        
        // for(Map.Entry<String, HashSet<String>> entry : reports.entrySet()){
        //     System.out.println(entry.getKey() + " : "+ Collections.unmodifiableSet(entry.getValue()));
        // }

        //신고 당한 횟수 계산
        for(Map.Entry<String, HashSet<String>> entry : reports.entrySet()){
            for(String reported_user : entry.getValue()){
                reported.put(reported_user , reported.getOrDefault(reported_user, 0) + 1);
            }
        }
        
        for(Map.Entry<String, Integer > entry : reported.entrySet()){
            if(entry.getValue() >= k){
                //정지 대상
                stop.add(entry.getKey());
            }
            // System.out.println(entry.getKey() + " : "+ entry.getValue());
        }
        
        //리포츠에서 자신이 신고한 사람들이 신고당한 수만큼 answer에 갱신
        for(Map.Entry<String, HashSet<String>> entry : reports.entrySet()){
            for(String s : entry.getValue()){
                if(stop.contains(s)) answer[user_idx_info.get(entry.getKey())]++;
            }
        }

        return answer;
    }
}