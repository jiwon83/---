import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
실버 2 https://www.acmicpc.net/problem/4963
시도: X
 */
public class CountOfIsland {
    FastReader scan = new FastReader();
    int [][] graph;
    boolean [][] visit;

    int [][] dist = {{-1,-1},{-1,0},{0,-1},{-1,1},{1,-1},{1,0},{0,1},{1,1}};
    //{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    int w,h;

    void input2() {
        w = scan.nextInt();
        h = scan.nextInt();
        graph = new int[w][h];
        for (int i = 0; i < w; i++) for (int j = 0; j < h; j++) graph[i][j] = scan.nextInt();
        visit = new boolean[w][h];
    }
    int input(){

        w=scan.nextInt();
        h=scan.nextInt();
        if (w==0 && h==0){
//            System.out.println("end");
            return -1;
        }
        graph = new int[w][h];
        visit = new boolean[w][h];

        int count =0;
        for (int i=0; i<h; i++){
            String row = scan.nextLine();
            for (int j=0; j<w; j++){
                graph[j][i] = row.charAt(j)-'0';
            }
        }
//        System.out.println(Arrays.deepToString(graph));
        for (int i=0; i<w; i++){
            for (int j=0;j<h; j++){
                if (!visit[i][j] && graph[i][j]==1){
                    count++;
                    dfs(i, j);

                }
            }
        }
        System.out.println(count);
        return 1;

    }


    void dfs(int x, int y){
        visit[x][y]= true;
        for (int i=0; i<dist.length; i++){
            int nx = x + dist[i][0];
            int ny = y + dist[i][1];

            if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;

            if (!visit[nx][ny] && graph[nx][ny]==1){
                dfs(nx,ny);
            }
        }

    }
    void solve(){
        while (true){
            input();
            if (input()==-1){
                break;
            }
        }
    }

    public static void main(String[] args) {
        CountOfIsland c = new CountOfIsland();
        c.solve();
    }


    class FastReader {
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

    }
}
