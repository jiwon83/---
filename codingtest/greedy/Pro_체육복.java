import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {

        List<Integer> listLost = Arrays.stream(lost).boxed().collect(Collectors.toList());
        List<Integer> listReserve = Arrays.stream(reserve).boxed().collect(Collectors.toList());
        // System.out.println(Collections.unmodifiableList(listLost));
        // System.out.println(Collections.unmodifiableList(listReserve));
        //1. lost==reveser 확인 -> 제거
        Collections.sort(listLost);
        List<Integer> willRemoved = new ArrayList<>();
        
        for( Integer element : listLost){
            if(listReserve.remove(element)){
                willRemoved.add(element);
            }
        }
        for(Integer item: willRemoved){
            listLost.remove(item);
        }
        willRemoved.clear();
        
        
        // reverse에서 lost제거 
        for(int i=0; i<listLost.size();i++){
            Integer element = listLost.get(i);
        
            Integer pre = element -1;
            Integer next = element +1;
            if(listReserve.remove(pre)){
                willRemoved.add(element);

            }else if(listReserve.remove(next)){
       
                willRemoved.add(element);
            }
 
        }
        for(Integer item: willRemoved){
            listLost.remove(item);
        }
        //3. n- lost.size();
        return n - listLost.size();
    }
}