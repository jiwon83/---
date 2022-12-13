package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1068_트리 {
    static ArrayList<Integer>[] graph;
    static int N, R, count;
    static int [] parentsInfo;
    static ArrayList<Integer> roots;
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static void input() {
        //N, R, graph, roots
        N  = sc.nextInt();
        //초기화
        graph = new ArrayList[N];
        parentsInfo = new int[N];
        roots = new ArrayList<>();
        for (int i=0; i<N; i++) graph[i]= new ArrayList<>();

        //일단 parents정보를 받는다.
        for (int i=0; i<N; i++){
            parentsInfo[i] = sc.nextInt();
        }

        //R remove
        R = sc.nextInt();
        //parentsInfo에서 삭제. 임의의 값으로 변경
        parentsInfo[R] = N+1;

        //parentsInfo를 기반으로 graph를 생성하고 roots 정보 넣는다.
        for (int i=0; i<N; i++){
            int parent = parentsInfo[i];
            if(parent== -1) {
                roots.add(i);
                continue;
            }
            if (parent == N+1) { //삭제된 노드임
                continue;
            }
            graph[parent].add(i);
        }

    }

    static void dfs(int node) {

        if (graph[node].size() == 0) {  // 만약 leaf 노드가 0이라면 count up!
            count++;
        }
        for (int x : graph[node]) { //그래프에서 node에 이어진 곳을 탐색
            dfs(x);
        }
    }

    static void pro() {
        for (int startNode : roots){
            dfs(startNode); //0
        }
        System.out.println(count);
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
