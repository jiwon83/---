import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
class Main2
{
    static class Point{
        int x,y;
        char fill;
        public Point(int x, int y, char fill){
            this.x = x;
            this.y = y;
            this.fill = fill;
        }
        public String toString(){
            return "x = "+x+" y = "+y+" fill = "+fill;
        }
    }
    static int X, N;
    static boolean[][] visit;
    static char[][] map;
    static FastReader fr = new FastReader();
    public static void main(String[] args) throws IOException{
        input();

        //recur(N/2, N/2, '*');
        bfs();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < N; i++){
            sb.append(new String(map[i])+"\n");
        }
        System.out.println(sb);
    }
    static void input() throws IOException{
        X = fr.nextInt();
        N = (X - 1 ) * 2 + ( X * 2 - 1);
        map = new char[N][N];
        visit = new boolean[N][N];


    }
    static int [][] dir = {{-1,0}, {-1, 1}, {0, 1}, {1,1}, {1,0}, {1,-1}, {0,-1},{-1,-1}};

    static void bfs(){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(N/2, N/2, '*'));
        visit[N/2][N/2]=true;

        while (!q.isEmpty()){
            Point p = q.poll();
            // map에 채운다.
            map[p.x][p.y] = p.fill;
            // ' ' -> * , '*' -> ' ' 로 바꾼다.
            p.fill = (p.fill=='*')? ' ' : '*';
            // visit 처리
            for (int i=0; i<8; i++){
                int nx = p.x + dir[i][0];
                int ny = p.y + dir[i][1];
                if (!inArea(nx, ny) || visit[nx][ny]) continue;
                visit[nx][ny]=true;
                q.add(new Point(nx, ny, p.fill));
            }

        }


    }
    static boolean inArea(int x, int y){
        return x >=0 && y >=0 && x < N && y < N;
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