import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        //while 
        int time = Integer.MAX_VALUE;
        while(time-- > 0){
            int move = -1;
            int takeD = cap, takeP =cap;
            //1. 배송할 거리를 구한다. 끝에서부터 0이 아닌 곳 탐색
            for(int i=deliveries.length-1; i>=0; i--){
                if(deliveries[i]!= 0 ){
                    move = i;
                    break;
                }
            }
            if( move == -1) return answer; //만약 배송할 거리가 -1 이라면 종료 
            
            //2. 배송한다. 끝에서 부터 cap만큼 deliveries 감소
            for(int i=deliveries.length-1; i>=0; i--){
                if(deliveries[i]==0) continue;
                if(takeP <= deliveries[i]){
                    takeP=0;
                    deliveries[i] -= takeP;
                }else{
                    takeP -= deliveries[i];
                    deliveries[i]=0;
                }
                if( takeP==0 ) break; //다 배송 전에 배달물이 떨어졌다면 그만
            }
            
        
            //3. 회수한다. 끝에서 부터 cap 만큼 pickups 감소 
            for(int i=pickups.length-1; i>=0; i--){
                if(takeD < pickups[i]){
                    takeD=0;
                    pickups[i] -= takeD;
                }else{
                    takeD -= pickups[i];
                    pickups[i]=0;
                }
                
                if( takeD==0 ) break; //다 배송 전에 배달물이 떨어졌다면 그만
            }
            System.out.println("takeD > "+takeD);
            System.out.println("이동거리 "+ move );
            System.out.println(Arrays.toString(deliveries));
            System.out.println(Arrays.toString(pickups));

            //4. 배송할 거리 * 2만큼 전체 이동거리를 증가시킨다. 
            answer += (move+1)*2;
        }
        
        return answer;
    }
}