import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        /*
        명함 당 큰 것과 작은 것을 구분하여,
        큰것 들 중 max, 작은 것들 중 max를 구한다.
        */
        int big=0; int smal =0;
        for(int [] cand : sizes){
            // System.out.println(Arrays.toString(cand));
            big = Math.max( big, Math.max(cand[0], cand[1]) );
            smal = Math.max( smal, Math.min(cand[0], cand[1]) );
        }
        return big * smal;
    }
}