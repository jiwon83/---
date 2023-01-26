import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
//    static boolean [] visit = new boolean[100001];
    static boolean [] visit = new boolean[100001];
    static int [] times = new int[100001];
    static int me, sister, ans;

    static void input() {
          me = sc.nextInt();
          sister = sc.nextInt();

    }
    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()){
            int node = q.poll();
            if(node == sister){
                ans = times[node];
                break;
            }
            int [] moves = { node + 1, node - 1,  node * 2};
            for (int i=0; i<3; i++){
                if (moves[i] < 0 || moves[i] >100000 || visit[moves[i]] ) continue;
                times[moves[i]] = times[node] + 1;
                q.offer(moves[i]);
                visit[moves[i]]=true;
            }

        }
    }
    static void pro() {
        bfs(me);
        System.out.println(ans);

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
