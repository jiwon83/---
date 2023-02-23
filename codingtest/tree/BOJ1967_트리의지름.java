
import java.io.*;
import java.security.cert.PolicyNode;
import java.util.*;

public class Main {
    static class Edge{
        int to, weight;
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static ArrayList<Edge> [] edges;
    static ArrayList<Integer> leaves = new ArrayList<>();
    static int ans =0;
    static int N;
    static boolean [] visit;

    static void input() {
        N = sc.nextInt();
        edges = new ArrayList[N+1];
        for (int i=1; i<=N; i++) edges[i]= new ArrayList<>();
        for (int i=1; i<N; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));

        }
        //leaves를 따로 넣는다. edges[i].size()==1인것들
        for (int i=1; i<=N; i++){
            if (edges[i].size()==1) leaves.add(i);
        }
    }
    static void dfs(int x, int dist, int start){
        //
        boolean cango=false;
        for (Edge e : edges[x]){
            if (visit[e.to]) continue;
            visit[e.to]=true;
            cango=true;
            dfs(e.to, dist + e.weight, start);
        }
        if (!cango){
            ans = Math.max(ans, dist);
        }
    }

    static void pro() {
        for (int start : leaves){
            visit = new boolean[N+1];
            visit[start] = true;
            dfs(start, 0, start);
        }
        System.out.println(ans);
    }
    public static void main(String[] args) {
        input();
        pro();
    }
    static class FastReader {
            BufferedReader br;
            StringTokenizer st;

            public FastReader() {
                br = new BufferedReader(new InputStreamReader(System.in));

            }
            String next(){
                while (st == null || !st.hasMoreTokens()){  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }

            int nextInt(){
                return Integer.parseInt(next());
            }
            long nextLong(){return Long.parseLong(next()); }

            double nextDouble(){return Double.parseDouble(next());}

            String nextLine(){
                String str ="";
                try {
                    str = br.readLine();

                }catch (IOException e){
                    e.printStackTrace();
                }
                return str;
            }
            void close() {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

}
