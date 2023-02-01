class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int union =0;
        int zeroCnt=0;
        for(int i=0; i<lottos.length; i++){ //0 1 2 3 4
            if(lottos[i]==0) zeroCnt++;
            for(int j=0; j< win_nums.length; j++){
                if(lottos[i] == win_nums[j]) {
                    union++; break;
                }
            }
        }
        answer[0] = (6 -( union+zeroCnt ) + 1) <=6 ? 6 - (union+zeroCnt) + 1: 6 ;
        answer[1] = 6 - union + 1 <=6 ? 6 - union + 1: 6 ;
        return answer;
    }
}