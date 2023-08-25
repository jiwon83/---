import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class Main {
    static class P{
        int x, y, time;
        int isBreak;
        public P(int x, int y, int time, int isBreak) {
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
    static boolean [][][] visit; //[0]은 뿌신
    static int [][] map;
    static int [][][] time;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        //input
        /*
        6 4
0100
1110
1000
0000
0111
0000
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        time = new int[2][N+1][M+1];
        for (int i= 1; i<=N; i++){
            Arrays.fill(time[0][i], Integer.MAX_VALUE);
            Arrays.fill(time[1][i], Integer.MAX_VALUE);
        }
        visit = new boolean[2][N+1][M+1]; // 0 = 벽을 안뿌신것 , 1 = 벽을 뿌신 것 => 안 뿌셔지는 벽이 아닌 곳에 대해 둘다 방문할 수 있도록
        for (int i=1; i<=N; i++){
            String temp = br.readLine();
//            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M ; j++ ){
                map[i][j] = temp.charAt(j-1)-'0';//Integer.parseInt(st.nextToken());
            }
        }

//        System.out.println(Arrays.deepToString(map));

        bfs(1,1);


    }
    static void bfs(int sx, int sy)
    {
        int minTime = -1;
        ArrayDeque<P> q = new ArrayDeque<>();
        q.addLast(new P(sx, sy, 1, 0));
        visit[0][sx][sy] = true;

        while(!q.isEmpty()) {
            P now = q.pollFirst();
//            System.out.println(now);
            int x = now.x;
            int y = now.y;
            time[now.isBreak][x][y] = now.time;

            if(x == N && y == M){ //종료 조건
                minTime = now.time;
                break;
            }
            // 4방 탐색
            for (int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(!inArea(nx, ny)) continue;
                if(map[nx][ny] == 1){ //벽이라면
                    if(now.isBreak==0 && !visit[1][nx][ny]){
                        visit[1][nx][ny] = true;
//                        System.out.println( nx + " , "+ ny + " 벽을 부시고 간다");
                        time[1][nx][ny] = now.time+1;
                        q.addLast(new P(nx, ny, now.time+1, 1));
                    }
                }
                if(map[nx][ny] == 0){
//                    System.out.println(" not wall "+ nx + " , "+ ny);
                    if(now.isBreak==1 && ( !visit[1][nx][ny] || time[1][nx][ny] > now.time + 1) ){
                        visit[1][nx][ny] = true;
                        time[1][nx][ny] = now.time+1;
                        q.addLast(new P(nx, ny, now.time+1, now.isBreak));
                    }
//                    System.out.println("condition 2 : "+ (now.isBreak == 0) + (!visit[0][nx][ny]));
                    if(now.isBreak == 0 && !visit[0][nx][ny]){
                        visit[0][nx][ny] = true;

                        q.addLast(new P(nx, ny, now.time+1, now.isBreak));
                    }

                }


            }
        }
//        System.out.println("not break");
//        for (int i= 1; i<= N ; i++ ) System.out.println(Arrays.toString(time[0][i]));
//        System.out.println(" break");
//        for (int i= 1; i<= N ; i++ ) System.out.println(Arrays.toString(time[1][i]));
        System.out.println(minTime);
    }
    static boolean inArea(int x, int y){
        return x > 0 && y > 0 && x <= N && y <= M;
    }

}
