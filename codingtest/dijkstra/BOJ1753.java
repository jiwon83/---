package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
최단경로 https://www.acmicpc.net/problem/1753

최악의 경우 1,290,308 연산 => 1초 안에 가능
첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다.
시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우(dist ==Integer.MAX_VALUE)에는 INF를 출력하면 된다.
w(가중치)는 10 이하의 자연수이다.
 */
public class BOJ1753 {

    static int V,E,S;
    static int [] dist;
    static ArrayList<Edge> [] edges;
    static StringBuilder sb = new StringBuilder();

    static class Info{ //pq에 담을 class
        public int idx,dist;

        public Info(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
    static class Edge{
        public int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "("+to+" , "+weight+")";
        }
    }
    static void input() {
        FastReader sc = new FastReader();
        V = sc.nextInt();
        E = sc.nextInt();
        S = sc.nextInt();
        dist = new int[ V+1 ]; 
        edges = new ArrayList [V+1];//배열 공간 할당 //실수 했었음 [M+1] 만약 노드의 수 > 간선의 수 일 때(모든 노드가 다 이어져 있지 않을 때)는 간선이 간선의 갯수보다 더 큰 번호의 노드와 연결된다면 ArrayIndexOutOfBounds Error
        for (int i=1; i<=V; i++){
            edges[i]= new ArrayList<Edge>();
        }
        for (int i=1; i<=E; i++){
            //122 133
            int from = sc.nextInt();
            int to = sc.nextInt();
            int w = sc.nextInt();
            edges[from].add(new Edge(to,w));

        }

        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
        //        System.out.println(Arrays.toString(edges));
    }
    static void dijakstra(int start){
        //dist배열 초기화
        for (int i=1; i<=V; i++) dist[i]= Integer.MAX_VALUE; //반드시 최대 비용보다 커야 함.
        //최소힙 생성
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        //시작점 처리
        dist[start] = 0;
        pq.add(new Info(start,0));

        //pq에 없어질 때까지
        while (!pq.isEmpty()){
            //dist가 최소인 값을 꺼낸다.
            Info info = pq.poll();

            //먼저, 가치판단. 이 info가 최신 정보인가?
            if (info.dist > dist[info.idx]) continue; //만약 dist의 정보 보다 크다면 옛날 정보이니 pass
            //가치가 있다면, 같거나 작다면(같을 경우는 시작점에서 밖에 없을 것이다. 왜냐면 이후 pq에 넣을 때 더 작은 경우에만 넣기 때문)
            // - > info에 연결된 간선들에 정보 갱신
            for (Edge e : edges[info.idx]){
                //info와 e 의 weight를 이용해 도착지인 e.to의 최단 거리 dist  갱신
                if ( info.dist + e.weight >= dist[e.to]) continue;
                //더 작은 값이라면,
                dist[e.to] = dist[info.idx] + e.weight;
                pq.add(new Info(e.to, dist[e.to]));
            }
        }
    }
    static void pro() {
        input(); //입력받고
        dijakstra(S); //다익스트라로 dist 테이블 갱신
        for (int i=1; i<=V; i++){
            if (dist[i]==Integer.MAX_VALUE){
                sb.append("INF").append('\n');
                continue;
            }
            sb.append(dist[i]).append('\n');
        }
        System.out.println(sb);
    }
    public static void main(String[] args) {
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
