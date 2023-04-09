import java.util.*;
class Solution {
    boolean visit [];
    ArrayList<Integer> [] graph;
    public int dfs(int x, int[] remove){

        boolean cango=false;
        int cnt=1;
        for(int next: graph[x]){
                if(visit[next]) continue;
                if((x==remove[0] && remove[1] ==next) || (x==remove[1]&& next==remove[0])) continue;
                cango = true;
                visit[next]=true;
                cnt += dfs(next, remove);
        }
        if(!cango){
            return 1;
        }
        return cnt;
    }
    public int solution(int n, int[][] wires) {
        int ans =n;
        graph = new ArrayList[n+1];
        for(int i=1; i<= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<wires.length; i++){
            int from = wires[i][0];
            int to = wires[i][1];
            graph[from].add(to);
            graph[to].add(from);
        }
        for(int i=0; i<wires.length; i++){
            visit = new boolean[n+1];
            visit[1] = true;
            int cntGroup1 =  dfs(1, wires[i]);
            ans = Math.min(ans, Math.abs((n - cntGroup1) - cntGroup1));
        }
        
        return ans;
    }
}