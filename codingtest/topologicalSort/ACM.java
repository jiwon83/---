package topologicalSort;

import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

/*
ACM Craft : https://www.acmicpc.net/problem/1005
한번 더
 */
public class ACM {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int T, N, K, W;
    static int[] Dist, indeg;
    static int[] Tdone; //노드까지의 걸린시간
    static ArrayList<Integer>[] graph;

    static void input() {

        //배열, 변수 초기화
        N = sc.nextInt();
        K = sc.nextInt();
        Dist = new int[N + 1];
        graph = new ArrayList[N + 1];
        indeg = new int[N + 1];
        Tdone = new int[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            Dist[i] = sc.nextInt();
        }
        //add graph
        for (int i = 1; i <= K; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            graph[A].add(B);
            indeg[B]++;
        }
        W = sc.nextInt();

    }

    static void sorting() {
        Deque<Integer> deque = new LinkedList<>();
        for (int Node = 1; Node <= N; Node++) {
            if (indeg[Node] == 0) {
                deque.add(Node);
                Tdone[Node] = Dist[Node];
            }
        }
        while (!deque.isEmpty()) {
            int x = deque.poll();
            for (int y : graph[x]) {
                Tdone[y] = Math.max(Tdone[y], Tdone[x] + Dist[y]); //동시에 할 수 있으므로 걸리는 시간 중 최대가 최소 시간이다.
                indeg[y]--;
                if (indeg[y] == 0) deque.add(y);
            }
        }
    }
    static void pro() {
        input();
        sorting();
        sb.append(Tdone[W]).append('\n');
        //결과 값 sb에 갱신

    }

    public static void main(String[] args) {
        T = sc.nextInt();
        while (T-- > 0) {
            pro();
        }
        System.out.println(sb);

        sc.close();
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
