import java.util.*;
class Solution {
    public int solution(int[] food_times, long k) {
        List<Integer> pointers = new ArrayList<>();
        for(int i=0; i<food_times.length; i++) pointers.add(i);
        
        int idx = 0;
        for(int i=1; i<= k; i++){ //k초동안 음식을 먹는다. 
    
            int pointer = pointers.get(idx);
            food_times[pointer]--;

            if(food_times[pointer]==0){
                pointers.remove(idx);
                if(pointers.size()==0) return -1;
                idx = idx % pointers.size(); //여기서 idx는 그대로 두어야함. remove됐기 때문에
            }else{
                idx= (idx + 1) % pointers.size(); //다음 pointer의 idx
            }
            

            
        }

        if(pointers.size()==0) return -1;
        else return pointers.get(idx)+1;
     
    }
    public int findNextPoint(int preIdx, int[] food_times){
         //System.out.println(Arrays.toString(food_times));
         //System.out.println("before "+preIdx);
        preIdx = (preIdx+1)%food_times.length;
        int cnt=0;
        while(food_times[preIdx] < 1){
            if(cnt == food_times.length ) return -1;
            preIdx = (preIdx+1)%food_times.length;
            cnt++;
            
        }
         //System.out.println("after "+preIdx);
        return preIdx;
    }
}