import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
    static int N, M;
    static char [][] map;
    static boolean [][][] visit;
    static Queue<int []> queue;
    static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0};
    static StringBuilder sb = new StringBuilder();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{

        input();

        queue = new LinkedList<>();
        for (int i=0; i<N ; i++){
            for (int j=0; j<M; j++){
                if (map[i][j] == 'W'){
                    queue.add(new int[]{i,j});

                }
            }
        }
//        System.out.println("queue.size() > "+ queue.size());
        while (!queue.isEmpty()){
            int [] now = queue.poll();
            for (int i=0; i<4; i++){
                visit[i][now[0]][now[1]] =true;
            }
            dfs(now[0], now[1], -1);
        }

        //출력
        for (int i=0; i<N; i++){
            for (int j = 0; j<M; j++){
                if ( (!visit[0][i][j] && !visit[1][i][j] && !visit[2][i][j] && !visit[3][i][j]) && map[i][j] =='.'){
                    sb.append("P");
                }else{
                    sb.append(map[i][j]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);


    }
    static void dfs(int x, int y, int dir){
        char c = map[x][y];
//        System.out.println("x = "+ x + " y = "+ y + " dir = "+ dir + " c = "+ c);
        if (c =='+'){ //빙하
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (!inArea(nx, ny)) return;
//            if(v[nx][ny]) return;
            if (map[nx][ny]=='#'){ //현재 방향으로 다음이 산이라면
                for (int d=0; d<4; d++){
                    if (d==dir) continue;
                    if(visit[d][x][y]) continue;
                    visit[d][x][y] = true;
                    dfs(x, y, d); // 다른 방향으로 이동
                }

            }else if(map[nx][ny]=='.' || map[nx][ny]=='W' ){
                if (visit[dir][nx][ny]) return;
                for (int d=0; d<4; d++) visit[d][nx][ny] = true;

                dfs(nx, ny , -1);

            }else{
                dfs(nx, ny, dir);
            }
        }else if( c== '.' || c== 'W' ){ //초원
            for (int d = 0; d <4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (!inArea(nx, ny) || map[nx][ny] == '#') continue;
                if(visit[d][nx][ny]) continue;
                visit[d][nx][ny] = true;
                dfs(nx, ny, d);
            }

        }
        //빙판이라면 그 방향 그대로, 산 X
        //초원이라면 상하좌우로 산 X
    }
    static boolean inArea(int x , int y){
        return x >=0 && y >=0 && x <N && y <M;
    }
    static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[4][N][M];

        for (int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
        }
    }
}
