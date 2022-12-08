package bfsdfs;
import java.io.*;
import java.util.*;
/*
골드 3 내리막길: https://www.acmicpc.net/problem/1520
시도: 시간초과,
 */
public class BOJ1520_내리막길 {
    static int M,N, cnt;
    static boolean [][] visit;
    static int [][] graph;
    static int [] dx ={-1,1,0,0}, dy={0,0,-1,1};

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static void input() {
        //M, N을 입력 받고 그래프 초기화
        M = sc.nextInt();
        N= sc.nextInt();
        visit= new boolean[M+1][N+1];
        graph= new int[M+1][N+1];

        for (int i=1; i<=M; i++){
            for (int j=1; j<=N; j++){
                graph[i][j] = sc.nextInt();
            }
        }

    }
    static void pro() {
        dfs(1,1);
        System.out.println(cnt);

    }

    private static void dfs(int x, int y) {
        visit[x][y]= true;
        if (x == M && y== N){ //목적지에 도착하면
            cnt++;
            return;
        }
        for (int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 < nx && 0 < ny && M >= nx && N >=ny && isLower(graph[x][y] , graph[nx][ny])){
                dfs(nx,ny);
                visit[nx][ny] = false;
            }
        }
    }

    private static boolean isLower(int from, int to ) {
        return from > to;
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
