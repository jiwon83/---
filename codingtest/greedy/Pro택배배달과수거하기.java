import java.util.*;

//1. 배달과 pickup을 별도로 1번의 물류 창고에 가져올 때 마다 cap 만큼 짐을 부과(물류센터에 갔다옴)하고 거리 갱신( (i+1) * 2 )
        
//2. 만약 배달할 짐이 0 초과라면 물류센처에 가져와야함. 
        
//3. 물류센처에 갔다오면 - cap으로 배달 처리.
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int pickup_haveto = 0;
        int deliver_haveto = 0;
        for(int i=n-1; i>=0; i--){ //맨 끝부터 탐색
            
            int move =0;
            
            deliver_haveto += deliveries[i];
            pickup_haveto += pickups[i];
             
            while(deliver_haveto > 0 ||  pickup_haveto > 0){ //물류 센터를 2번이상 방문해야할 수도 있으므로 if(x), while(O)
                deliver_haveto -= cap;
                pickup_haveto -= cap;
                move++;
            }
            answer += (i+1)*2 * move; //물류센처에 갔다왔다면
        }//for
        return answer;
    }
}