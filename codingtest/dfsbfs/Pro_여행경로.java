import java.util.*;
class Solution {
    String[] answer = {};
    String[][] Tickets;
    boolean [] visit; //티켓 사용에 대한 방문 처리
    String answers="";
    public void dfs(String node, String route){
        //만약 더이상 갈 곳이 없고 모든 티켓을 사용 했다면 route를 return 한다.
        // answers += node + " ";
        boolean cango = false;
        route += " "+ node;
        //다음 노드를 탐색
        for(int i=0; i<Tickets.length; i++){
            if(Tickets[i][0].equals(node)){
                //방문여부를 확인해서
                if(visit [i]) continue;
                cango= true;
                
                String next = Tickets[i][1];
                visit[i] = true;
                dfs(next, route);
                // if(!answers.contains(next)){
                //     dfs(next);
                // }
            }
        }
        if(!cango){ // 더이상 갈 수 없다면
            boolean allVisit=true;
            for(boolean b : visit){
                if(!b) allVisit=false;
            }
            if(allVisit) answers = route;
           
        }
    } 
    public String[] solution(String[][] tickets) {
        Tickets = tickets;
        visit = new boolean[tickets.length];
        
        //1. 이중 배열 정렬 -> 뒤에 것만 정렬하면 될듯. ok
        Arrays.sort( Tickets, new Comparator<String []>(){
            @Override
            public int compare(String [] me, String [] other ){ //기본 오름차순
                if(me[0].equals(other[0])){
                    return me[1].compareTo(other[1]);
                }else{
                    return me[0].compareTo(other[0]);
                }
                
            }
        });
        System.out.println(Arrays.deepToString(Tickets));
        dfs("ICN","");
        System.out.println("answers> "+answers);
        //2. 
        answer= answers.split(" ");
        String [] newAnswer = new String[answer.length-1];
        for(int i=0; i<newAnswer.length; i++){
            newAnswer[i] = answer[i+1];
        }
        
     
        return newAnswer;
    }
}