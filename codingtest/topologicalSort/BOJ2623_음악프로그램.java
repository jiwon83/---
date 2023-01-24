import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, M;
    static ArrayList<Integer> [] graph;
    static int [] indeg;

    static void input() {
          N = sc.nextInt();
          graph = new ArrayList[N+1];
          for (int i=1; i<=N; i++){
              graph[i] = new ArrayList<>();
          }
          indeg = new int[N+1];
          M = sc.nextInt();
          for (int i=1; i<=M; i++){
//              int cnt = sc.nextInt();
              String [] temp = sc.nextLine().split(" "); // 6 2 5 4
              int cnt = Integer.parseInt(temp[0]);
              for (int j=1; j<=cnt-1; j++ ){//0,1,2
                  int from = Integer.parseInt(temp[j]);
                  int to = Integer.parseInt(temp[j+1]);
                  graph[from].add(to);
                  indeg[to]++;
              }
          }
//        System.out.println(Arrays.toString(graph));
//        System.out.println(Arrays.toString(indeg));

    }
    static void pro() {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> q = new LinkedList<>();
        for (int i=1; i<=N; i++){
            if (indeg[i]==0) q.add(i);
        }
        while(!q.isEmpty()){
            Integer x = q.poll();
            ans.add(x);
            for (Integer y : graph[x]){
                indeg[y]--;
                if (indeg[y]==0){
                    q.add(y);
                }
            }
        }
        if (ans.size()== N){
            for (int x : ans) sb.append(x).append("\n");
        }
        else sb.append(0);
        System.out.println(sb);
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




