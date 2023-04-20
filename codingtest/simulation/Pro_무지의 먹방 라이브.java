import java.util.*;
class Solution {
    
    public boolean isFinish(int [] food_times){
        for(int i=0; i<food_times.length; i++){
            if(food_times[i]!=0){
                return false;
            }
        }
        return true;
    }
    


    public int solution(int[] food_times, long k) {
        int answer = 0;
        int pos =0;
        while(k-- > 0){
            if(isFinish(food_times)) return -1;
            
            while(food_times[pos]==0){
                pos = (pos +1 ) % food_times.length;
            }
            // System.out.println(pos);
            food_times[pos]--;
            pos = (pos +1 ) % food_times.length;
            
        }
        //다음 위치를 return
        // System.out.println(Arrays.toString(food_times));
        if(isFinish(food_times)) return -1;
        while(food_times[pos]==0){
                pos = (pos +1 ) % food_times.length;
                // if(isFinish(food_times)) return -1;
            }
        return pos+1;
    }
}