import java.util.*;
class Solution {
    String [] select; //선택한 user_id를 담아둘 배열
    boolean [] visit; //중복 허용 방지
    int M,N;
    HashSet<String> set = new HashSet<>(); //순서가 달라도 동일한 경우의 수로 취급
    
    public void recur(int k, String[] user_id, String[] banned_id){
        if(k==M){
            
            //select를 정렬한 뒤, string으로 바꿔서 set에 저장.
            String [] copy = select.clone(); //deep copy 안하면 select가 변해서 다음 결과에 영향
            Arrays.sort(copy);
            String temp="";
            for(int i=0; i<M; i++){
                temp += copy[i];
            }
            set.add(temp);
        }else{
            
            String target = banned_id[k]; // "fr*do", "******"
            for(int cand= 0; cand < N; cand++){
                if(visit[cand]) continue;
                
                if(target.length() != user_id[cand].length() ) continue;
                
                boolean isSame=true; //모든 자릿수가 일치하는 지 
                for(int i=0; i<target.length(); i++){
                    if(target.charAt(i)=='*') continue;
                    if(target.charAt(i) != user_id[cand].charAt(i)){
                        isSame=false;
                        break;
                    }
                }
                if(isSame) {
                    
                    // System.out.println("k= "+k+" target>> "+ target);
                    select[k] = user_id[cand];
                    visit[cand] = true;
                    // System.out.println(Arrays.toString(select));
                    recur(k+1, user_id, banned_id);
                    select[k]="";
                    visit[cand] = false; // 다음 선택을 위해
                }
            }
        }
        
    }
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        M = banned_id.length;
        N = user_id.length;
        select = new String[M];
        visit = new boolean[N];
        
        recur(0,user_id, banned_id);
        return set.size();
    }
}