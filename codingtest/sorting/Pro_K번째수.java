import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int [commands.length];
        ArrayList<Integer> ans =new ArrayList<>();
        
        for(int i=0; i<commands.length; i++){
            int fr = commands[i][0];
            int end = commands[i][1];
            int idx = commands[i][2];
            int [] temp = IntStream.range(fr-1, end).map(num -> array[num]).toArray();
            Arrays.sort(temp);
            answer[i] = temp[idx-1];
            // ans.add(temp[idx-1]);
            
        }
        // answer = ans.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}