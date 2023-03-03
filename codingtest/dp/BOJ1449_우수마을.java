import java.util.*;
import java.io.*;

public class Main4{
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static ArrayList<Integer> [] groups;
    static int [] people;
    static int [][] dp;
    static boolean [] visit;
    static int N, ans;
    static void input() {
        N = sc.nextInt();
        groups = new ArrayList[N+1];
        people = new int[N+1];
        visit = new boolean[N+1];
        dp = new int[N+1][2];

        for (int i=1; i<=N; i++){
            groups[i] = new ArrayList<>();
            people[i] = sc.nextInt();

        }
        for (int i=1; i<N; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            groups[from].add(to);
            groups[to].add(from);
        }
    }
    static void dfs(int x, int par){
        dp[x][1] = people[x]; //자기자신 포함
        for (int ch : groups[x]){
            if (ch==par) continue;
            dfs(ch, x);
            dp[x][0] += Math.max(dp[ch][0], dp[ch][1]); //자식 노드중 그냥 최대 부모가 없기 때문에 상관 x
            dp[x][1] += dp[ch][0]; //자식 노드 비포함 최대
        }
    }
    static void pro() {
       dfs(1,-1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
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
