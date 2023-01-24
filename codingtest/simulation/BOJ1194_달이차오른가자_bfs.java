import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y, key, count;

        public Node(int x, int y, int key, int count) {
            this.x = x;
            this.y = y;
            this.key = key;
            this.count = count;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static boolean visit[][][];
    static int N, M, ans;
    static Node S;
    static char[][] graph;
    static int key = 0;

    static void input() {
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new char[N][M];
        visit = new boolean[64][N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = sc.nextLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == '0') S = new Node(i, j, 0, 0);
            }

        }

    }
    static int [] dx = {0,0,-1,1}, dy={-1,1,0,0};
    static void bfs(Node start){

        //만약 1에 도착했으면 ans 갱신
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        visit[start.key][start.x][start.y] = true;
        while (!q.isEmpty()){
            Node node = q.poll();
            int x = node.x; int y = node.y; int key = node.key; int count = node.count;
            if (graph[x][y] =='1'){
//                System.out.println("도착 "+ count);
                if (ans ==-1 ) ans = count;
                else ans = Math.min(ans, count);
            }

            for (int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx <0 || ny <0 || nx >= N || ny >=M ) continue;
                if (graph[nx][ny] == '#') continue;
                if (visit[key][nx][ny]) continue; //현재 키에서 방문한 적 있다면 continue;

                if (graph[nx][ny] >= 'A' && graph[nx][ny] <='Z'){
                    int door = 1 << graph[nx][ny] -'A';
                    if ((door & key) <= 0 ) continue; //문에 맞는 키가 없다면 그렇지 않으면 갈 수 있음.
                }

                if (graph[nx][ny] >= 'a' && graph[nx][ny] <='z'){
                    int newKey = ( key | (1 << graph[nx][ny] - 'a') );
                    q.add(new Node(nx, ny, newKey, count+1));
                    visit[newKey][nx][ny] = true; //새로운 key에서의 시작점 이기도 하기 때문에 true 처리
                    visit[key][nx][ny] = true;

                }else{ // 공백 또는 현재위치 또는 출구 또는 문
                    visit[key][nx][ny] = true;
                    q.add(new Node(nx, ny, key, count+1));
                }



            }
        }
    }

    static void pro() {
        ans = -1;
        bfs(S);
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




