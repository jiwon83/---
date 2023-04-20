import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        int[] answer = {};
        // 배포일 계산 -> speeds변경
        int N = progresses.length;
        for(int i=0; i<N; i++){
            int day = (100 - progresses[i]) / speeds[i];
            if( (100 - progresses[i]) % speeds[i] != 0 ) day+= 1;  
            speeds[i] = day;
        }
        //배포 수 계산
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<N; i++){
            int count = 1;//배포기준
            int stand = i;
            while(i+1 <N && speeds[i+1] <= speeds[stand]){
                count++;
                i++;
            }
            list.add(count);
        }
        int [] res = new int[list.size()];
        int idx=0;
        for(int  i : list){
            res[idx++]= i;
        }
        return res;
    }
}