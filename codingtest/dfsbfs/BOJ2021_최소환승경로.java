import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int node, line, transCnt;

        public Edge(int node, int line, int transCnt) {
            this.node = node;
            this.line = line;
            this.transCnt = transCnt;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, L, S, E;
    static ArrayList<Integer>[] lines;// 각 라인에 있는 스테이션 정보
    static ArrayList<Integer>[] stations;// 각 스테이션의 Line 정보
    static boolean[] visitLine;
    static boolean[] visitStation;
    static int ans=-1;

    static void input() {
/*
10 3
1 2 3 4 5 -1
9 7 10 -1
7 6 3 8 -1
1 10
 */
        N = sc.nextInt();
        L = sc.nextInt();
        stations = new ArrayList[L + 1];
        lines = new ArrayList[N + 1];
        visitLine = new boolean[L + 1];
        visitStation = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            lines[i] = new ArrayList<>();
        }
        for (int i = 1; i <= L; i++) {

            stations[i] = new ArrayList<>();
            String[] arr = sc.nextLine().split(" ");
            for (int j = 0; j < arr.length - 1; j++) {
                int station = Integer.parseInt(arr[j]);
                lines[station].add(i);
                stations[i].add(station);

            }
        }

        S = sc.nextInt();
        E = sc.nextInt();
    }

    static void bfs() {
        Queue<Edge> q = new LinkedList<>();
        visitStation[S] = true;
        for (int line : lines[S]) {
            q.add(new Edge(S, line, 0));
            visitLine[line] = true;
        }

        while (!q.isEmpty()) {
            Edge edge = q.poll();
            if (edge.node == E) {
                ans = edge.transCnt;
                break;
            }
            for (int node: stations[edge.line]){
                if (visitStation[node]) continue;
                visitStation[node] = true;
                q.add(new Edge(node, edge.line, edge.transCnt));
            }
            for (int nextLine : lines[edge.node]){
                if (visitLine[nextLine]) continue;
                visitLine[nextLine] = true;
                q.add(new Edge(edge.node, nextLine, edge.transCnt+1));
            }

        }
    }
    static void dfs(int node, int line, int count){
        if (node==E){
            ans = Math.min(ans,count);
            return;
        }
        for (int next : stations[line]){
            if (visitStation[next]) continue;
            visitStation[next] = true;
            dfs(next, line, count);
        }
        for (int nextLine : lines[node]){
            if (visitLine[nextLine]) continue;
            visitLine[nextLine] = true;
            dfs(node, nextLine, count+1);
        }
    }

    public static void main(String[] args) {
        input();
        bfs();
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();

            } catch (IOException e) {
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