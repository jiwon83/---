import java.util.*;
class Solution {
    static int[] res = { -1 };
    static int[] lion;
    static int max = -1000;
    public void dfs(int [] lion, int[] info, int k, int n, int now_arrow) {
        if(k == n) {
            // System.out.println(Arrays.toString(lion));
            int apeach_point = 0;
            int lion_point = 0;
            for(int i = 10; i>=0; i--) 
            {
                if(info[i] != 0 || lion[i] != 0) {
                    if(info[i] < lion[i]) 
                        lion_point += 10 - i;
                    else 
                        apeach_point += 10 - i;
                }
            }

            if(lion_point > apeach_point) {
                if(lion_point - apeach_point == max){
                    Loop: for(int i = 10; i>=0; i--){
                        // res 와 현재 lion 중에 누구라도 다른게 있다면 종료
                        if(lion[i] != res[i]){
                            if(lion[i] > res[i]){
                                // System.out.println("update (same) "+ Arrays.toString(res) + " "+ max);
                                res = lion.clone();
                            }
                            break Loop;
                        }
                    }
                }// 현재까지의 가장 큰 점수 차와 같을 경우
                if(lion_point - apeach_point > max)
                {
                    res = lion.clone();
                    max = lion_point - apeach_point;
                    // System.out.println("update "+ Arrays.toString(res) + " "+ max);
                }
            }
            return ;
        }//if 종료조건
       
        if( k==n-1){ //마지막 과녁이라면
            lion[k] = now_arrow;
            dfs(lion, info, k+1, n, 0);
        }else{
            if(now_arrow > info[k]){ //이번 과녁의 점수를 얻었을때
                lion[k] = info[k] + 1;
                dfs(lion, info, k+1, n, ( now_arrow - lion[k]) );
            }
            lion[k] = 0; //그렇지 않을 때
            dfs(lion, info, k+1, n, now_arrow );
        } 
    }
    
    public int[] solution(int n, int[] info) {
        test(new int[]{2,3,1,0,0,0,0,1,3,0,0}, new int[]{2,1,0,2,0,0,0,2,3,0,0});
        lion = new int[11];
        dfs(lion, info, 0, 11, n);
        return res;
    }
    public void test(int [] info, int [] lion){
        
        int apeach_point = 0;
            int lion_point = 0;
            for(int i = 10; i>=0; i--) 
            {
                if(info[i] != 0 || lion[i] != 0) {
                    if(info[i] < lion[i]) 
                        lion_point += 10 - i;
                    else 
                        apeach_point += 10 - i;
                }
            }
            if(lion_point >= apeach_point) {
                if(lion_point - apeach_point >= max)
                {
                    System.out.println(Arrays.toString(lion));
                    max = lion_point - apeach_point;
                }
            }
    }
}