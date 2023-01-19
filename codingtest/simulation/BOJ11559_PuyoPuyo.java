import java.util.*;
import java.io.*;
public class Main2 {
    static class X{
        int x, y;

        public X(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static char [][] graph= new char[12][6];
    static boolean [][] visit= new boolean[12][6];
    static int ans;
    static void input() {
        //  그래프 입력 받기
        for (int i=0; i<12; i++){
            String temp = sc.nextLine();
            for (int j=0; j<6; j++){
                graph[i] = temp.toCharArray();
            }
        }
    }
    static void down(){
        for (int col=0; col <6; col++){
            int deleteCnt=0; // 지워진 -1을 count;
            for (int row=11; row >=0; row--){ //아래부터 찾아본다.
                if (graph[row][col] == '.') { //만약 빈 공간이면
                    deleteCnt++;
                }else if (graph[row][col] != '.' && deleteCnt > 0){
                    graph[row + deleteCnt][col] = graph[row][col];
                    graph[row][col] = '.';
                }

            }
        }

    }
    static int [] dx = {-1,1,0,0}, dy={0,0,-1,1};
    static boolean bfs(int startX, int startY){
        /*
        해당 케이스에 대한 오류로 수정 -> stack을 사용하면 for문을 돌지만 위아래좌우 중 가장 마지막에 넣은 곳에서만 이동 되므로, 오류가 발생.
        따라서 리스트를 그냥 따로 만들어서 담아주어야 함.
......
......
......
......
......
......
......
......
YYYY..
BYG...
RYGB..
B..BG.
         */
        ArrayList<X> list = new ArrayList<>();
        Stack<X> stack = new Stack<>();
        list.add( new X(startX,startY) );
        stack.add( new X(startX,startY) );
        visit[startX][startY]=true;
        while (!stack.isEmpty()){ //갈곳이 하나라도 있는 동안.
            X next = stack.pop();
            int x = next.x; int y= next.y;
            for (int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (nx <0 || ny <0 || nx >= 12 || ny >= 6) continue;
                if (visit[nx][ny]) continue;
                if (graph[nx][ny] == graph[startX][startY]){ //같아야지만 이동 가능.
                    visit[nx][ny] = true;
                    list.add(new X(nx,ny));
                    stack.add(new X(nx,ny));
                }
            }
        }//while
        if (list.size() >= 4){
            list.stream().forEach(node -> graph[node.x][node.y] = '.');
            return true; //4개이상이 모여서 터트릴 수 있다면 true를 return.
        }
        return false;
    }
    static void pro() {
        while (true){   //더이상 터질 것이 없을 때까지 반복
            boolean flag = false;
            visit = new boolean[12][6];
            for (int i=0; i<12; i++){
                for (int j=0; j<6; j++){
                    if (visit[i][j])  continue;//상관없을듯?
                    if (graph[i][j] == '.') continue;
                    if (bfs(i, j)) flag =true;
                }
            }

            if (flag){
//                System.out.println("-------터지고 ------");
//                for (int i=0; i< graph.length; i++){
//                    System.out.println(Arrays.toString(graph[i]));
//                }
                ans++;
                down();
//                System.out.println("-------내려오고 ------");
//                for (int i=0; i< graph.length; i++){
//                    System.out.println(Arrays.toString(graph[i]));
//                }
            }else{
                break;
            }
        }
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
