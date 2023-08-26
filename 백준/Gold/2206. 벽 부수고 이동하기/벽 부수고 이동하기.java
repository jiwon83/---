import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
7 4
0000
1110
1100
1101
0000
0111
0000
 */

public class Main {
    static class P{
        int x, y, time;
        boolean isBreak;

        public P(int x, int y, int time, boolean isBreak) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.isBreak = isBreak;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    ", time=" + time +
                    ", isBreak=" + isBreak +
                    '}';
        }
    }


    static int [] dx = {-1,0,1,0}, dy = {0,1,0,-1};
    static int N, M;
    static int [][] map;
    static int [][] time; //최소시간
    static boolean [][] visit; //방문여부
    static int ans;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        time = new int[N+1][M+1]; //시작지점부터 i, j지점까지의 최단 거리
        visit = new boolean[N+1][M+1];
        for (int i=1; i<=N; i++){
            String temp = br.readLine();
            for (int j = 1; j <= M ; j++ ){
                map[i][j] = temp.charAt(j-1)-'0';
            }
        }

        for(int i=1; i <= N; i++){
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        ans = -1;
        bfs(1,1);
        System.out.println(ans);


//        System.out.println("----------");
//        for(int i=1; i <= N; i++) System.out.println(Arrays.toString(time[i]));
//        for(int i=1; i <= N; i++) System.out.println(Arrays.toString(visit[i]));

    }
    static void bfs(int sx, int sy)
    {
        ArrayDeque<P> q = new ArrayDeque<>();
        q.addLast(new P(sx, sy, 1, false));
        visit[sx][sy] = true;
        time[sx][sy] = 1;

        while(!q.isEmpty()){
            P out = q.pollFirst();
            if(out.x == N&& out.y == M){
                ans = time[out.x][out.y];
                break;
            }
            for(int d = 0; d < 4; d++){
                int nx = out.x + dx[d];
                int ny = out.y + dy[d];
                if( !inArea(nx, ny) ) continue;
                if(!out.isBreak && map[nx][ny] == 1 && !visit[nx][ny]){
                    //벽을 깨고 이동
                    visit[nx][ny] = true;
                    time[nx][ny] =  out.time + 1;
                    q.addLast(new P(nx, ny, out.time+1, true));

                }else if(map[nx][ny] == 0){
//                    if(nx == 7 && ny == 3){
//                        System.out.println("들어옴 "+ out+ " / "+time[nx][ny]);
//                    }

                    if(out.isBreak && (out.time + 1 < time[nx][ny])){ //더 유리할 때만 이동
                        time[nx][ny] =  out.time + 1;
                        q.addLast(new P(nx, ny, out.time+1, true));

                    }else if(!out.isBreak && !visit[nx][ny]){ //방문한 적 없다면 이동

                        visit[nx][ny] = true;
                        time[nx][ny] =  out.time + 1; //time 배열도 갱신해준다.
                        q.addLast(new P(nx, ny, out.time+1, false));
                    }
                }
            }
        }

    }
    static boolean inArea(int x, int y){
        return x > 0 && y > 0 && x <= N && y <= M;
    }

}
