import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time=0;
        int outTruckCnt=0;
        int cap = weight;
        int TruckCnt = truck_weights.length;
        int pos=0;
        Queue<Integer> que = new LinkedList<>();
        //모든 트럭이 나올 때까지 반복
        while(outTruckCnt< TruckCnt){
            time++;
            if(que.size()==bridge_length){
                int out = que.poll();
                if( out!= 0) {
                    outTruckCnt++;
                    cap+= out;
                }
            }
            //대기열을 넣을 수 있는 지 확인 
            if( pos < TruckCnt && truck_weights[pos] <= cap){
                que.add(truck_weights[pos]);
                cap -= truck_weights[pos++];
            }else {
                que.add(0);
            }
            
        }
        return time;
    }
}