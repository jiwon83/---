import java.util.*;
import java.io.*;
public class Main2 {
    static class P{
        int x,y;
        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    static Queue<P> waterQ, nextQ, Q;
    static int R, C;
    static char [][] graph;
    static boolean isMeet=false; //백조를 만난 갯수
    static int day=0;
    static int [] dx = {0,0,-1,1}, dy={1,-1,0,0};
    static boolean [][] visit;
    static P [] swan = new P[2];
    static boolean [] swanTF = new boolean[2];



    public static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt( st.nextToken() ); C = Integer.parseInt( st.nextToken());
        graph = new char[R][C];
        visit = new boolean[R][C];
        waterQ = new LinkedList<>();
        nextQ = new LinkedList<>();
        Q = new LinkedList<>();


        int swanIdx=0;
        for (int i=0; i<R; i++){
            graph[i] = br.readLine().toCharArray();
            for (int j=0; j<C; j++){
                if (graph[i][j] != 'X') waterQ.add( new P( i, j ) ); //백조가 있는 자리도 물이라고 가정하면
                if (graph[i][j] == 'L')  swan[swanIdx++] = new P(i,j);
            }
        }
//        System.out.println("----swan-----");
//        System.out.println(Arrays.toString(swan));
//        System.out.println("----waterQ-----");
//        System.out.println(waterQ);

    }
    public static void bfs (){
        // 물의 확장
        int size = waterQ.size();
        for (int i=0; i< size; i++){
            P node = waterQ.poll();
            for (int j=0;j<4; j++) {
                int nx = node.x + dx[j];
                int ny = node.y + dy[j];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (graph[nx][ny]=='X') {
                    graph[nx][ny]='.';
                    waterQ.add(new P(nx, ny)); // ** 물이 사방으로 둘러쌓여져 있는 경우 반복 될 수 있다.
                }
            }
        }

    }

    static void moveSwan(){
        //만약 얼음이 아니라면 이동.
        // 다른 백조를 찾으면 true처리
        while (!Q.isEmpty()){
            P now = Q.poll();
//            System.out.println("백조 찾는 중.. "+now);

            if (now.x == swan[1].x && now.y == swan[1].y) {
                isMeet = true;
                break;
            }
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (visit[nx][ny]) {
                    continue;
                }

                if (graph[nx][ny] == 'X' && !visit[nx][ny]) {
                    nextQ.add(new P(nx, ny)); //이건 어쨌든 맨처음 백조의 위치가 물이여야만 가능. 백조가 얼음으로 둘러써야 있을 경우를 위해.
                    visit[nx][ny] = true;
                    continue;
                }
                Q.add(new P(nx,ny));
                visit[nx][ny] = true;
            }
        }

    }

    public static void pro(){
        Q.add(swan[0]);
        visit[swan[0].x][swan[0].y]=true;
        while (!isMeet){ //2가되어서 백조를 모두 만나면 종료.

            //1. 백조의 움직임
            moveSwan();
            Q = nextQ;
            nextQ = new LinkedList<>();
            //2. 물의 확장 waterQ에 대해서만;
            bfs();
            //2. 다음 날 처리
            day++;
        }
        System.out.println(day-1);

    }
    public static void main(String[] args) throws IOException{
        input();
        pro();

    }

}
