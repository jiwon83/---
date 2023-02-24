import java.util.*;
class Solution {
    
    int [] Numbers;
    int count=0;
    int Target;

    public void recur(int k, int sum){  
        if(k==Numbers.length){
            if( sum == Target ){
                count++;
            }
            return;
        }
        recur(k+1, sum - Numbers[k]);
        recur(k+1, sum + Numbers[k]);  
    }
    public int solution(int[] numbers, int target) {

        Numbers = numbers;
        Target = target;
        recur(0,0);
        return count;
    }
}