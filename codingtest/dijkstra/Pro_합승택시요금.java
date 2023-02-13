import java.util.*;
class Solution {
    class Info{
        int idx, dist;
        public Info(int idx, dist){
            this.idx = idx;
            this.dist = dist;
        }
    }
    class Edge{
        int to, weight;
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    
    public ArrayList<Edge> [] edges;
    boolean [] clear = new boolean [2];
    boolean [] visit;
    int ans =Integer.MAX_VALUE;
    int S, A, B;
    
    public int dfs(int x, int par){
        System.out.println("node > "+x);
        int result=0;
        for(Edge e : edges[par]){
            if(e.to == x){
                result =  e.weight;
            }
        }
        
        if(x==A || x==B){
            if(x==A){
                clear[0]=true;
            }else clear[1]=true;
            return result;
            
        }
        
        for(Edge e: edges[x]){
            if(visit[e.to]) continue;
            
            visit[e.to]=true;
            int add = dfs(e.to, x);
            if( clear[0] && clear[1]){
                result += add;
            }
            System.out.println("add..."+add+" result.."+result);
            visit[e.to]=false;
            
        }
        if(x==S && clear[0] && clear[1]){
            System.out.println("백트래킹 완료.."+ result);
            result = Math.min(ans, result);
        }
        return result;
    }
    public int solution(int n, int s, int a, int b, int[][] c) {
        int answer = 0;
        S = s;
        A = a;
        B = b;
        visit = new boolean [n+1];
        edges = new ArrayList [n+1];
        for(int i=0; i<=n; i++){
            edges[i] = new ArrayList<>();
        }
        edges[0].add(new Edge(S,0));
        //1. 그래프 작성
        for(int i=0; i<c.length; i++){
            int from = c[i][0];
            int to = c[i][1];
            int weight = c[i][2];
            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));

        }    
        visit[S]=true;
        //2. dfs()로 돌고 return 값을 ans 로 갱신
        answer = dfs(S, 0);
        
        return answer;
    }

}