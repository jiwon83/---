import java.io.*;
import java.util.*;

class Main{

    static class Edge{
        int to, usado;

        public Edge(int to, int usado){
            this.to = to;
            this.usado = usado;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,Q;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Edge> [] edges;
    public static void main(String[] args) throws IOException{

        /*
        4 3
1 2 3
2 3 2
2 4 4
1 2
4 1
3 1
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N+1];

        for (int i = 1; i<=N; i++) edges[i] = new ArrayList<>();

        for (int i =1; i<N; i ++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges[s].add(new Edge(e, v));
            edges[e].add(new Edge(s, v));
        }

        for (int i = 1; i <= Q; i++){
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            sb.append(bfs(node, K)+"\n");
        }
        System.out.println(sb);
    }
    static int bfs(int start, int K){
//        System.out.println("bfs "+ start + " K = "+ K);
        boolean [] visit = new boolean[N+1];
        ArrayDeque<Edge> q = new ArrayDeque<>();
        visit[start] = true;
        q.addLast(new Edge(start, Integer.MAX_VALUE));
        int count = 0;
        while (!q.isEmpty()){
            Edge now = q.pollFirst();
            if(now.usado != Integer.MAX_VALUE && now.usado >= K){
//                System.out.println(now.to +" : "+ now.usado);
                count++;
            }
//            System.out.println("edge > "+ now.to + " : "+ now.usado);
            for (Edge edg : edges[now.to]){
                if(visit[edg.to]) continue;
                visit[edg.to] = true;

                q.addLast(new Edge(edg.to, Math.min(edg.usado, now.usado)));
            }
        }

        return count;
    }
}