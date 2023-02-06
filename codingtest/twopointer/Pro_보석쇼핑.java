import java.util.*;
class Solution {
    public int ans;
    public int cnt=0;
    HashMap<String, Integer> map = new HashMap<>();
    
    public void add(String key){
        if(!map.containsKey(key)){
            map.put(key, 1);
            cnt++;
        }else{
            int value = map.get(key);
            map.put(key, value+1);
            
        }
        
    }
    public void remove(String key){
        if(map.get(key)!=null){
            int value = map.get(key);
            map.put(key, value-1);
            if(map.get(key)==0){
                cnt--;
                map.remove(key);
            }
        }
        
    }
    public int getN(String [] gems){
        Set<String> set = new HashSet<>();
        for(int i=0; i<gems.length; i++){
            set.add(gems[i]);
        }
        return set.size();
    }
    public int[] solution(String[] gems) {
        
        /* 초기화 */
        int[] answer = new int[2];
        ans = gems.length; //처음엔 최대길이
        answer[0] = 1;
        answer[1] = gems.length;
        //1. 종류의 수 계산
        
        int N = getN(gems);
        // System.out.println("모든 종류의 수 "+N);
        //2. two -pointer로 최소 길이 갱신
        for(int L=0, R=-1; L<gems.length; L++){
            
            while(cnt < N && R+1 < gems.length){
                add(gems[++R]); //cnt가 N보다 작다면 계속 오른쪽으로 이동. 
            }
            //그러면 마지막 R은 최초로 cnt가 N이 될 경우이다. 그래야 while문은 돌지 않기 때문.
            int len = (R) - L +1;
            if(ans > len && cnt == N){
                // System.out.println("최단거리 탄생! "+ "L = "+L +" R = "+(R)+" len = "+ len);
                ans = len;
                answer[0] = L+1; //번호는 index +1
                answer[1] = R+1;
                
            }
            remove(gems[L]);
        }
        
        return answer;
    }
}