import java.util.*;
import java.io.*;
public class Main3{
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return " ( "+x+" , "+y+" ) ";
        }
    }

    static int key, N, M;
    static int ans = -1;
    static Node start;
    static char [][] graph;
    static boolean [][][] visit;
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static void input() {
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new char[N][M];
        visit = new boolean[64][N][M];
        for (int i=0; i<N; i++){
            String temp = sc.nextLine();
            graph[i] = temp.toCharArray();
            for (int j=0; j<M; j++){
                if (graph[i][j] == '0') start = new Node(i, j);
            }
        }
        System.out.println("start "+ start);

    }
    static int [] dx = {0,0,-1,1}, dy = {-1,1,0,0};
    static String big = "ABCDEF";
    static String small ="abcdef";
    static void dfs(int x, int y, int count){
        System.out.println("dfs "+ x +" "+y +" count = "+count+" key = "+key);
        if (graph[x][y] =='1'){
            System.out.println("end에 도착 "+ count);
            if (ans==-1) ans = count;
            else ans = Math.min(ans, count);
        }else{
            if (small.contains(graph[x][y]+"")){ //열쇠라면
                int thisKey = 1 << graph[x][y] -'a';
                key = key | thisKey;
                System.out.println("열쇠 발견 !! "+ key);
                visit[key][x][y] = true;
            }

            for (int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx <0 || ny <0 || nx >= N || ny >= M) continue;
                if (graph[nx][ny] == '#') continue;
                if (visit[key][nx][ny]) continue;

                if (small.contains(graph[nx][ny]+"")){ //열쇠라면
//                    int thisKey = 1 << graph[nx][ny] -'a';
//                    key = key | thisKey;
//                    System.out.println("열쇠 발견 !! "+ key);
                }
                if (big.contains(graph[nx][ny]+"")){ //문이라면

                    int door = 1 << graph[nx][ny] -'A';
                    if ((key & door) <= 0) { //들어갈 수 없다면
                        System.out.println("문에 열쇠 없음! " + door+ " key :"+ key);
                        continue;
                    }
                    System.out.println("문 발견 !! " + door);
                }
                visit[key][nx][ny]=true;
                dfs(nx, ny, count+1);
//                visit[key][nx][ny]=false;
            }

        }

    }
    static void pro() {
        visit[0][start.x][start.y] = true;
        dfs(start.x, start.y,0);
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

//class Node {
//
//    int row;
//    int col;
//    int cnt;
//    int key;
//
//    public Node(int row, int col, int cnt, int key) {
//        this.row = row;
//        this.col = col;
//        this.cnt = cnt;
//        this.key = key;
//    }
//
//}
