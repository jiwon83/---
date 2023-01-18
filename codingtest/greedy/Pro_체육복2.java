import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        int answer = n;
        int [] A = new int [n+1];
        
        for(int i=0; i< lost.length; i++){
            A[lost[i]]--; //없는 사람은 -1
        }
        for(int i=0; i< reserve.length; i++){
            A[reserve[i]]++; //있는 사람은 + 1
        }
        
        //이제 있는 -> 없는
        for(int i=1; i<=n; i++){
            if(A[i] == -1){
                
                if(A[i-1]==1){
                    A[i-1]--; A[i]++;
                }else if(i+1 <= n && A[i+1]==1){
                    A[i+1]--; A[i]++;
                }else{ // lost중 빌리지 못한 아이들만큼 --
                   answer--; 
                }
            }
        }
        return answer;
    }
}