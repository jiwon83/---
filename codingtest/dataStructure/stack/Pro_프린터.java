import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
      
        int priSeq=0; //현재 출력 순서
        int L = location; // 구하고자하는 값의 큐에서의 현재 위치
        int pos = priorities.length-1;//현재 최고 우선 순위의 배열의 index
        
        
        ArrayList<Integer> que = new ArrayList<>();
        for(int i : priorities ){
            que.add(i);
        }
        Arrays.sort(priorities);
        
        while(L >= 0){// -1이되면 종료
            int i = que.get(0);
            if(i == priorities[pos] ){ //최우선 순위라면
                priSeq++;
                if(L==0){ //지금 target을 뺄 것이라면
                    return priSeq;
                }else{
                    que.remove(0);
                    L--; //target 위치 변경
                    pos--; //최우선순위 이동
                }
            }else{
                que.remove(0); 
                que.add(i);
                L = (L==0)? que.size()-1 : L-1; //target 위치 변경
            }
        }

        return -1;
    }
}