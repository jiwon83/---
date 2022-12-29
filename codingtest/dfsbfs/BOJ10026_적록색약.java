package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10026_적록색약 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, ans1, ans2;
    static boolean [][] visit ;
    static String [][] graph;
    static int [] dx = {1,-1,0,0};
    static int [] dy = {0,0,-1,1};
    static void input() {
        N = sc.nextInt();
        visit = new boolean[N][N];
        graph = new String[N][N];
        //입력받고
        for (int i=0; i<N; i++){
            String str = sc.nextLine();
            for (int j=0; j<N;j++){
                graph[i][j] = str.charAt(j)+"";
            }
        }

    }
    static void dfsYES(int x, int y){

        visit[x][y] = true;

        for (int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];



            if (nx <0 || ny <0 || nx >=N || ny >=N) continue;
            if (visit[nx][ny]) continue;

            if (graph[x][y].charAt(0)=='B'){
//            if (graph[x][y].equals("B")){
                if (graph[nx][ny].equals(graph[x][y])){
                    dfsYES(nx,ny);
                }
            }else {
                if (graph[nx][ny].equals("B")) continue;
                dfsYES(nx,ny);
            }
        }

    }
    static void dfsNo(int x, int y){


        visit[x][y] = true;

        for (int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx <0 || ny <0 || nx >=N || ny >=N) continue;

            if (visit[nx][ny]) continue;

            if (graph[nx][ny].equals(graph[x][y])){
                dfsNo(nx,ny);
            }

        }
    }
    static void pro() {
        for (int i=0; i<N; i++){
            for (int j =0; j<N; j++){
                if (!visit[i][j]){
                    ans1 ++;
                    dfsNo(i,j);
                }
            }
        }
        visit = new boolean[N][N];
        for (int i=0; i<N; i++){
            for (int j =0; j<N; j++){
                if (!visit[i][j]){
                    ans2++;
                    dfsYES(i,j);
                }
            }
        }

        System.out.println(ans1 + " "+ ans2);
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

