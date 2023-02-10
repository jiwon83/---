import java.util.*;
class Solution {
    String[] answer = {};
    String[][] Tickets;
    ArrayList<String> answers;
    boolean [] visit; //티켓 사용에 대한 방문 처리
    
    public void dfs(int count, String node, String route){
        
        if( count == Tickets.length){ //이 문제의 경우, 노드를 방문하는게 아니라 티켓(간선)을 사용하는 것이 목적이다.  (조건 : 주어진 항공권은 모두 사용해야 합니다.)
            answers.add(route); //모든 가능한 경로들이 저장될 것이다.
            return;
        }
        for(int i=0; i< Tickets.length; i++ ){
            if(visit[i]) continue; 
            if(Tickets[i][0].equals(node)){
                //방문여부를 확인해서
          
                visit[i] = true;
                dfs(count+1, Tickets[i][1], route+" "+Tickets[i][1]);
                visit[i] = false;
        
            }
        }
    } 
    public String[] solution(String[][] tickets) {
        Tickets = tickets;
        visit = new boolean[tickets.length];
        answers = new ArrayList<>();
        int count=0;
       
        dfs(count, "ICN", "ICN");
        // System.out.println(Collections.unmodifiableList(answers));
        
        Collections.sort(answers); // 그 중에서 오름차순을 정답으로 체택.
        answer = answers.get(0).split(" ");
 
        return answer ;
    }
}