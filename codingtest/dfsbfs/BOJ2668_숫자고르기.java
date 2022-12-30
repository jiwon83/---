package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
풀이 :
- 모든 시작점에서 다 해본다.
- routine 시작값과 같지 않거나, 더 이상 방문할 수 없는 경우 무산
- routine을 찾으면, 마지막 value를 저장 (= 시작점)
 */
public class BOJ2668_숫자고르기 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int[] graph;
    static boolean[] visit;
    static int N;
    static ArrayList<Integer> result = new ArrayList<>();
    static ArrayList<Integer> selected = new ArrayList<>();

    static void input() {
        //M, N을 입력 받고 그래프 초기화
        N = sc.nextInt();
        graph = new int[N + 1];
        visit = new boolean[N + 1];

        for (int j = 1; j <= N; j++) {
            graph[j] = sc.nextInt();
        }
//        System.out.println(Arrays.toString(graph));
    }

    static void dfs(int idx, int start) {

        if (visit[graph[idx]]==false){
            visit[graph[idx]]=true;
            dfs(graph[idx], start);
            visit[graph[idx]] =false;
        }
        if (graph[idx] == start){
            result.add(graph[idx]);
        }

    }

    static void pro() {
        for (int i = 1; i <= N; i++) {
            if (visit[i]) continue;
//            System.out.println(" dfs " + i);
            visit[i]=true;
            dfs(i,i); //1,1
            visit[i]=false;
        }
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

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

