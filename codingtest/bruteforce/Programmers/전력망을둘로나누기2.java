import java.util.*;
class Solution {
    int N;
    ArrayList<Integer> [] graph;
    
    public int bfs(int start, int [] delete){
        
        boolean [] visit = new boolean[N+1];
        int count = 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        
        
        while(!q.isEmpty()){
            
            int node = q.poll();
            visit[node] = true;
            count++;
            
            for(int next : graph[node]){
                if(visit[next]) continue;
                if(node == delete[0] && next ==delete[1]) continue;
                if(next == delete[0] && node ==delete[1]) continue;
                q.add(next);
            }
            
        }
        return count;
    }
    
    public int solution(int n, int[][] wires) {
        N = n;
        graph = new ArrayList [N+1];
        int answer = Integer.MAX_VALUE;
        
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i < wires.length; i++){
            int from = wires[i][0];
            int to = wires[i][1];
            graph[from].add(to);
            graph[to].add(from);
            
        }
        
        for(int i=0; i<wires.length; i++){
            int countG1 = bfs( 1, wires[i]); //시작점은 삭제할 간선를 제외한 점들 중 렌덤으로 시작 =>wires[i][0]+1)%N 
            System.out.println(countG1);
            answer = Math.min(answer, Math.abs(N - 2 * countG1));
        }
        
        return answer;
    }
}