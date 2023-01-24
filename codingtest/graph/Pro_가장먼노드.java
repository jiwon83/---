import java.util.*;
class Solution {
    public int N, ans;
    public ArrayList<Integer> [] graph;
    public boolean [] visit;
    public int max_cnt = 0;
    public int [] dist;
    
    public int solution(int n, int[][] edge) {
        N =n;
        //1. 그래프 작성
        graph = new ArrayList[N+1];
        dist = new int [N+1];
        visit = new boolean [N+1];
        for(int i=1; i<=N; i++ ) graph[i] = new ArrayList<>();

        for(int i=0; i<edge.length; i++){
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        // System.out.println(Arrays.toString(graph));
        
        //dist 초기화
        for(int i=2; i<=N; i++) dist[i] = Integer.MAX_VALUE;
        
        //2. bfs로 탐색 (dist 갱신, ans 갱신)
        Queue<Integer> q = new LinkedList<>();
        q.add(1); dist[1] =0;
        while(!q.isEmpty()){
            int x = q.poll();
            visit[x] = true;
            for(int y : graph[x]){
                if(visit[y]) continue;
                if(dist[x] + 1 < dist[y]){
                    dist[y] = dist[x] + 1;
                    max_cnt = Math.max(max_cnt, dist[y]);
                    q.add(y);
                        
                }
                
            }
        }
        ans=0;
        // System.out.println(max_cnt);
        // System.out.println(Arrays.toString(dist));
        //3. ans 출력
        for(int x: dist){
            if(x == max_cnt) ans++;
        }
        
        return ans;
    }
}