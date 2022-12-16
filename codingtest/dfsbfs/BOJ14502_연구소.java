package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_연구소 {
    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "("+ x +" , "+ y +")";
        }
    }
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int ans_max, N, M;
    static int [] selected = new int[4];
    static int [][] graph;
    static ArrayList<Point> zeros = new ArrayList<>();
    static ArrayList<Point> virus = new ArrayList<>();

//    static int [][] cpGraph;
    static int [] dx = {1,-1,0,0}, dy = {0,0,-1,1};
    static boolean [][] visit = new boolean[N+1][M+1];


    static void input() {
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new int[N+1][M+1];
        zeros.add(new Point(0,0));//안 쓸 것
        virus.add(new Point(0,0));//안 쓸 것
        for (int i=1; i<=N; i++){
            for (int j=1; j<=M; j++){
                graph[i][j] = sc.nextInt();
                if (graph[i][j]==0) zeros.add(new Point(i,j));
                if (graph[i][j]==2) virus.add(new Point(i,j));
            }
        }
    }
    static void recur(int k){ //조합 생성
        if (k == 4){
            //벽을 3개 세운다.
            makeWall(selected);
            spreadVirus();
        }else {
            for (int i = selected[k-1]+1; i< zeros.size(); i++){
                selected[k] = i;
                recur(k+1);
                selected[k] = 0;
            }
        }

    }

    private static void spreadVirus() {
        //2를 기준으로 bfs로 바이러스를 퍼트린다.
//        System.out.println("바이러스르 퍼트린다");
        for (int i=1; i<virus.size(); i++){
                bfs(virus.get(i).x,virus.get(i).y);
        }

//        System.out.println("바이러스 분포완료");
        int c = countZero();
        ans_max=Math.max(ans_max,c);

    }

    private static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        // 시작 지점 초기화
        q.add(new Point(x,y));
        while (!q.isEmpty()){
            Point p = q.poll();
            for (int i=0; i<4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 1 || ny < 1 || nx > N || ny > M) continue;
                if (visit[nx][ny]) continue;
                if (graph[nx][ny] == 0){
//                    cpGraph[nx][ny] = 2;
                    visit[nx][ny] = true;
                    q.add(new Point(nx, ny));

                }
            }
        }
    }

    public static int countZero(){
        int cnt =0;
        //해당 그래프의 0을 count해서 반환
        for (int i=1; i<=N; i++){
            for (int j=1; j<=M; j++){
                if ( !visit[i][j] && graph[i][j]==0){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void makeWall(int [] walls){
        //graph copy
        visit =  new boolean[N+1][M+1];
//        cpGraph = new int[N+1][M+1];
//        for (int i=1; i<= N; i++){
//            for (int j=1; j<= M; j++){
//                cpGraph[i][j] = graph[i][j];
//            }
//        }

        //zeros의 index 사용
        for (int i=1; i<= 3; i++){
            Point p = zeros.get(walls[i]);
            visit[p.x][p.y]=true;
//            cpGraph[p.x][p.y] = 1;
        }
    }
    static void pro() {
        input();
        ans_max = 0;
        recur(1);
        System.out.println(ans_max);

    }
    public static void main(String[] args) {
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
