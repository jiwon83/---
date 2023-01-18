import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        //1. lost==reveser 확인 -> 제거
        List<Integer> listLost = Arrays.stream(lost).boxed().collect(Collectors.toList());
        List<Integer> listReserve = Arrays.stream(reserve).boxed().collect(Collectors.toList());
        // System.out.println(Collections.unmodifiableList(listReserve));
        Collections.sort(listLost);
        
        Iterator<Integer> it = listLost.iterator();        
        while(it.hasNext()){
            Integer item = it.next();
            if(listReserve.remove(item)){
                it.remove();
            }
        }
        
        //2. reverse에서 lost제거 
        //2-2. lost를 돌면서 n-1값, n+1값 확인 -> 있다면 삭제
        List<Integer> willRemoved = new ArrayList<>();
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