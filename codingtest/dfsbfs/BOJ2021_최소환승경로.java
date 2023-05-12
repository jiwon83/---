import java.util.*;
import java.io.*;
/*
test case
10 3
1 2 3 4 5 9 -1
7 3 10 -1
7 9 6 3 8 2 -1
1 10
*/
public class Main {
    static class Info{
        int node, line, transCnt;
        public Info(int node, int line, int transCnt){
            this.node = node;
            this.line = line;
            this.transCnt = transCnt;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static ArrayList<Integer> [] linesOfNode;
    static ArrayList<Integer> [] nodesOfLine;
    static boolean [] visitNode, visitLine;
    static int N, L, S, E, ans;
    static int [] memo;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() {
        N = sc.nextInt();
        L = sc.nextInt();
        ans = L;
        linesOfNode = new ArrayList[N+1];
        nodesOfLine = new ArrayList[L+1];
        visitNode = new boolean[N+1];
        visitLine = new boolean[L+1];
        memo = new int[L+1];
        for (int i=1; i<=N; i++) linesOfNode[i] = new ArrayList<>();
        for (int i = 1; i <= L; i++) nodesOfLine[i] = new ArrayList<>();

        for (int i=1; i<=L; i++){
            String [] arr = sc.nextLine().split(" ");
            for (int j=0; j<arr.length-1; j++){
                int n = Integer.parseInt(arr[j]);
                nodesOfLine[i].add(n);
                linesOfNode[n].add(i);


            }
        }
        S = sc.nextInt();
        E = sc.nextInt();

    }
    static void recur(int node, int line, int transCnt){
        //System.out.println("node = " + node + ", line = " + line + ", transCnt = " + transCnt);
        if (node==E) ans = Math.min(ans, transCnt);
        else{
            for (int n : nodesOfLine[line]){
                if (!visitNode[n]){
                    visitNode[n] = true;
                    recur(n, line, transCnt);
                    visitNode[n] = false;
                }
            }
            for (int l : linesOfNode[node]){

                if ( !visitLine[l] || memo[l] >= transCnt+1 ) {
                    visitLine[l] = true;
                    memo[l] = transCnt+1;
                    recur(node, l, transCnt+1);
                    //visitLine[l] = false;
                }
            }
        }
    }
    static void pro() {
        System.out.println(bfs());
    }
    static int bfs(){
        Queue<Info> q = new LinkedList<>();
        for (int l : linesOfNode[S]){
            visitNode = new boolean[N+1];
            visitLine = new boolean[L+1];
            visitNode[S] = true;
            visitLine[l] = true;
            q.add(new Info(S, l,0));
        }
        while (!q.isEmpty()){
            Info info = q.poll();
            if (info.node==E) return info.transCnt;
            //같은 라인 방문
            for (int n : nodesOfLine[info.line]){
                if (visitNode[n]) continue;
                visitNode[n] = true;
                q.add(new Info(n, info.line, info.transCnt));
            }
            //환승
            for ( int l :linesOfNode[info.node]){
                if (visitLine[l]) continue;
                visitLine[l] = true;
                q.add(new Info(info.node, l, info.transCnt+1));
            }
            //환승
        }
        return -1;

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