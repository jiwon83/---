import java.util.*;
class Solution {
    int R, C;
    List<String> keys = new ArrayList<>();
    List<boolean [] > uqKeys = new ArrayList<>();
    
    public boolean isUnique(String select, String[][] relation){
        //모든 튜플에 대하여 후보키에 해당하는 것만 String으로 변환후 set에 담는다.
        Set<String> set = new HashSet<>();
        for(int i=0; i<R; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<C; j++){
                if(select.charAt(j)=='0') continue;
                sb.append(relation[i][j]).append(" ");
            }
            set.add(sb.toString());
        }
        return R == set.size();
    }
    public boolean isMin(String select){
        int key = Integer.parseInt(select, 2);
        System.out.println("key> "+ key);
        for(String str : keys){
            int other = Integer.parseInt(str,2); 
            if((other & key ) == other ){
                return false;
            }
        }
        return true;
    }
    
    public void recur(int k, String[][] relation, String select){
        if(k==C){
   
            if(isUnique(select, relation) && isMin(select) ){// 
                keys.add(select);
            }
            return;
        }
        recur(k+1,relation, select+0);
        recur(k+1,relation, select+1);
    }
    public int solution(String[][] relation) {
        int answer = 0;
        R = relation.length; C = relation[0].length;
        //1. 후보키 조합 생성
        recur(0,relation,"");
        return keys.size();
    }
}