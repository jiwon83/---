package bfsdfs;
import java.io.*;
import java.util.*;
/*
https://www.acmicpc.net/problem/7562
 */
public class NightsMove {

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static int StarX, StarY, endX,endY;
    static int T, I;
    static int graph [][];
    static int [][] move = {{2,-1}, {2,1}, {-2,-1},{-2,1},{1,2},{1,-2},{-1,-2},{-1,2}};



    static void input() {
        //변수 입력 받음
        I = sc.nextInt();
        StarX = sc.nextInt();
        StarY = sc.nextInt();
        endX = sc.nextInt();
        endY = sc.nextInt();
        graph = new int[I][I];

    }
    static void bfs(int x, int y) { //최단거리 그래프 채우기
        for (int i=0; i< I; i++){
            for (int j=0; j<I; j++){
                graph[i][j] = -1;
            }
        }
        //시작점 추가
        graph[x][y] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        while (!q.isEmpty()){
            int px = q.poll();
            int py = q.poll();
            if (px == endX && py ==endY ){
                break;
            }
            for (int i=0; i<8; i++){
                int nx = px + move[i][0];
                int ny = py + move[i][1];
                if ( 0 <= nx && 0 <= ny && I > nx && I > ny && graph[nx][ny] == -1){
                    graph[nx][ny] = graph[px][py] + 1;
                    q.add(nx);
                    q.add(ny);
                }
            }
        }
        if (graph[endX][endY] == -1) sb.append(0).append("\n");
        else sb.append(graph[endX][endY]).append("\n");

    }
    public static void main(String[] args) {
        T= sc.nextInt();
        while (T-- >0){
            input();
            bfs(StarX, StarY);
        }
        System.out.println(sb);

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
