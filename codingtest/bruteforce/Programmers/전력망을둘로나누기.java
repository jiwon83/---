import java.util.*;
class Solution {
    
    int N;
    ArrayList<Integer> graph [] ;
    int [][] Wires;
    boolean [] visit;
    
    int dfs(int x, int [] deleteEdge, int cnt){
        
        visit[x] =true;
        int num=1;
        boolean cango=false;
        
        for(int node : graph[x]){
            if(x==deleteEdge[0] && node == deleteEdge[1]) continue;
            if(x==deleteEdge[1] && node == deleteEdge[0]) continue;
            if(visit[node]) continue;
            cango = true;
            num += dfs(node, deleteEdge, cnt+1);
            
        }
        if(!cango) return num;
        return num;
        
    }
    
    public int solution(int n, int[][] wires) {
        N = n;
        graph =  new ArrayList [N+1];
        // cnt = new int[N+1];
        Wires = wires;
        visit = new boolean[N+1];
        
        
        
        //graph 초기화
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
            
        int answer = N;
        //1. 그래프를 생성
        for( int i=0; i<wires.length; i++){
            int from = wires[i][0];
            int to = wires[i][1];
            graph[from].add(to);
            graph[to].add(from);

            
        }
        
        
        System.out.println(Arrays.toString(graph));
        
        
        for(int i=0; i< wires.length; i++){ //삭제할 노드
            
            int [] deleteEdge = wires[i];
            int [] groups = new int[2];
            int idx = 0;
            visit = new boolean[N+1];
            
            System.out.println("deleteEdge"+Arrays.toString(deleteEdge));
            
            for(int start =1; start<=n; start++){ //그래프 탐색을 시작할 노드
                
                if(visit[start]) continue;
                
                int num = dfs(start, deleteEdge, 0);
                System.out.println("start "+start + " 노드 수 "+num);
                groups[idx++] = num;
                
                
            }
            
            System.out.println(Arrays.toString(groups));
            answer = Math.min(answer, Math.abs( groups[0] - groups[1] ));
        }
//         // System.out.println(Arrays.toString(graph));
//         //2. bfs 로 순회해서 cnt 갱신
//         for(int i=1; i<=N; i++){
//             dfs(i,i);
//         }
        // System.out.println(Arrays.toString(cnt));
        
        //3. cnt + 1중 n/2와 가장 비슷한 지점에서 자른다.
        //- cnt 값 + 1 과 n- 이 값의 차의 절댓값 반환.
//         for(int i=1; i<=N; i++){
//             int d = cnt[i]+1;
//             int gap = Math.abs( (N - d) - d );
//             if( gap < answer){
//                 answer = gap;
//             }
            
//         }
        return answer;
    }
}