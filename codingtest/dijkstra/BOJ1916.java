package dijkstra;

import java.io.*;
import java.util.*;

/*
최소 비용 구하기 https://www.acmicpc.net/problem/1916
 */
public class BOJ1916 {

    static int N,M,S,A;
    static int [] dist;
    static ArrayList<Edge> [] edges;

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
        N = sc.nextInt();
        M = sc.nextInt();
        dist = new int[ N+1 ]; 
        edges = new ArrayList [N+1];//배열 공간 할당 //실수 했었음 [M+1] 만약 노드의 수 > 간선의 수 일 때(모든 노드가 다 이어져 있지 않을 때)는 간선이 간선의 갯수보다 더 큰 번호의 노드와 연결된다면 ArrayIndexOutOfBounds Error
        for (int i=1; i<=N; i++){
            edges[i]= new ArrayList<Edge>();
        }
        for (int i=1; i<=M; i++){
            //122 133
            int from = sc.nextInt();
            int to = sc.nextInt();
            int w = sc.nextInt();
            edges[from].add(new Edge(to,w));

        }
        S = sc.nextInt();
        A = sc.nextInt();
        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
        //        System.out.println(Arrays.toString(edges));
    }
    static void dijakstra(int start){
        //dist배열 초기화
        for (int i=1; i<=N; i++) dist[i]= Integer.MAX_VALUE; //반드시 최대 비용보다 커야 함.
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
        System.out.println(dist[A]); //출력
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
