import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
class Main
{


    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer> [] graph;
    static int N, M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        //양방향 그래프 입력
        graph = new ArrayList[N+1];
        for (int i=1; i<=N; i++) graph[i] = new ArrayList<>();
        for (int f=1; f<=N; f++){
            st = new StringTokenizer(br.readLine());
            for (int t = 1; t<=N; t++){
                char c = st.nextToken().charAt(0);
                if(c == '1'){
                    graph[f].add(t);
                }
            }
        }
//        for (int i=1; i<=N; i++) System.out.println(graph[i]);

        //여행 계획 입력
        int [] plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++){
            plan[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println("plan "+ Arrays.toString(plan));
        //탐색
        boolean possiblePlan = true;
        for(int i=0; i<M-1; i++){
            int from = i;
            int to = i+1;
            if( !possible(plan[from], plan[to])) {
                possiblePlan = false;
                break;
            }
        }
        String res = possiblePlan ? "YES" : "NO";
        System.out.println(res);

    }
    static boolean possible(int from, int to){
        //bfs로 탐색해서 만약 to가 나오면 true를 return
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(from);
        boolean [] visit = new boolean[N+1];
        visit[from] = true;
        while(!q.isEmpty()){
            int now = q.pollFirst();
            if(now == to) return true;
            for(int node : graph[now]){
                if(visit[node]) continue;
                visit[node] = true;
                q.addLast(node);
            }

        }
        return false;
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
