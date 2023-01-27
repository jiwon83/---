class Solution {
    int N;
    int [] Times;
    
    public boolean isPossible(long time){
        //주어진 시간동안 심사받을 수 있는 총 인원을 검사해서, 이 인원이 n보다 크면 true를 반환
        // 주어진 시간 동안 심사 받을 수 있는 총 인원 += for( 주어진 시간 / 걸리는 시간)
        long people = 0;
        for(int i=0; i<Times.length; i++){
            people += time / Times[i];
          
        }
        return people >= N;
        
    }
    public long solution(int n, int[] times) {
        N=n; Times = times;
        // Arrays.sort(times);
        long answer = 0;
        long L=1, R = 1000000000000000000L; //R은 가능한 최대 걸리는 시간 
        while(L<=R){
            long mid = (L + R) / 2;
            if(isPossible(mid)){
                answer = mid;
                R = mid -1;
            }else{
                L = mid + 1;
            }
        }
        return answer;
    }
}