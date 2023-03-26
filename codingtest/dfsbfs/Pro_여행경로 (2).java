import java.util.*;
class Solution {
    ArrayList<String> results = new ArrayList<>();
    boolean [] visit;
    
    public void recur(String now, String route, String[][] tickets){
        boolean cango=false;
            for(int i=0; i<tickets.length; i++){
                if(visit[i]) continue;
                if(tickets[i][0].equals(now)){
                    visit[i]=true;
                    cango=true;
                    recur(tickets[i][1], route+" "+tickets[i][1],  tickets);
                    visit[i]=false;
                }
            }
            //갈수 있는 곳이 없다면
            if(!cango){
                if(route.length()==(tickets.length+1)*3+ tickets.length) results.add(route);
            }
    }
    
    public String[] solution(String[][] tickets) {

        visit = new boolean[tickets.length];
        recur("ICN","ICN",tickets);     
        Collections.sort(results);
        return results.get(0).split(" ");
    }
}