package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
골드 5 노드 사이의 거리 https://www.acmicpc.net/problem/1240
시도: O(221124)
특이점: 구현까다로움.
 */
public class BOJ1240 {
    static class Edge {
        int to, dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static int result, N, M;
    static ArrayList<Edge>[] graph; // index가 from
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean [] visit;
    static StringBuilder sb =  new StringBuilder();

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for (int i=1;i<=N; i++){
            graph[i]= new ArrayList<>();
        }
        for (int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            //그래프 작성, 양방향 , 거리도 같이
            graph[from].add(new Edge(to,dist));
            graph[to].add(new Edge(from,dist));
        }

        //여기서 bfs 호출, bfs를 M만큼 반복
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            result = 0;
            visit = new boolean[N+1];
            dfs(start, end,0);
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int x, int end, int total_dist) {
//        System.out.println("현재 방문 x =" +x+ "end= " + end + " total_dist = "+total_dist);
        visit[x]= true; //이 노드는 방문한 것임.

        if ( x == end ) {
            result= total_dist;
            return;
        }
        for (int i = 0; i<graph[x].size(); i++){
            if (!visit[ graph[x].get(i).to]){
//                total_dist += graph[x].get(i).dist;
                dfs(graph[x].get(i).to, end, total_dist+graph[x].get(i).dist ); // 1이 갔다오면 2로 넘어가는 것
            }
        }

        //이동할 때마다 total_dist를 갱신
        //만약, end와 같다면 result = total_dist;

    }

    static void pro() throws IOException {
        input();
    }

    public static void main(String[] args) throws IOException {
        pro();
    }
}
