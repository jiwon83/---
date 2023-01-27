import java.util.*;
class Solution {
    int[] Rocks;
    int N;
    int Distance;
    
    public boolean isOK(int min_dist){
        //바위를 제거해야하는 횟수 <= N 여부를 구한다.
        int stand=0; 
        int removed=0;
        for(int i=0; i<Rocks.length; i++){
            if(Rocks[i]-stand < min_dist){
                //제거한다. 
                removed++;
            }else{
                //제거하지않고 기준을 옮겨준다.
                stand = Rocks[i];
            }
        }
        //마지막 지점과의 비교를 한다. 21~25 사이를 비교한다.
        if(Distance - stand < min_dist){
            //마지막 stand였던 지점도 제거
            removed++;
            //예외케이스 ) min_dist가 너무 커서 모든 돌을 제거하지 않았을 경우 제거할 수 없는 도착지점을 제거하는 경우 발생 => R을 Distance로 주면 해결. (stand=0, min_dist = Distance가 되더라도 Distance - stand(0) = Distance이니까 min_dist보다 작을 수가 없다.)
        }
        System.out.println("dist >> "+min_dist+" removed >> "+removed);
        return removed <= N;
    }
    
    
    public int solution(int distance, int[] rocks, int n) {
        N = n; Rocks = rocks;
        Distance = distance;
        int answer = 0;
        
        Arrays.sort(Rocks);
        
        //이분탐색으로 거리의 최솟값 탐색
        int L =1, R= Distance;
        while(L <= R){
            int mid = (L+R)/2;
            if(isOK(mid)){
                L = mid +1;
                System.out.println("is OK "+ mid);
                answer = mid;
            }else{
                System.out.println("is Not OK "+ mid);
                R = mid -1;
            }
        }        
        return answer;
    }
}