import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
    static String [] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new String[R];
        for (int r = 0; r <R; r++){
            map[r] = br.readLine();
        }
    }
    static int [] dx = {0,0,-1,1}, dy ={-1,1,0,0};
    public static void main(String[] args) throws IOException{
        input();
        int ans = 0;
        //모든 점에서 time을 통해서 최대 이동 거리를 구한다.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j <C; j++){
                if(map[i].charAt(j) == 'L'){
                    // 이점에서 bfs 시작
                    ArrayDeque<Point> q = new ArrayDeque<>();
                    int [][] time = new int[R][C];
                    q.addLast(new Point(i, j));
                    int level = 0;
                    while (!q.isEmpty()){
                        int len = q.size();
                        level++;
                        for(int l = 0; l < len; l++){
                            Point now = q.pollFirst();
                            for (int d = 0; d< 4; d++){
                                int nx = now.x + dx[d];
                                int ny = now.y + dy[d];
                                if( nx  < 0 || ny < 0 || nx >= R || ny >=C) continue;
                                if( map[nx].charAt(ny)== 'W') continue;
                                if( nx == i && ny == j) continue;
                                if( time[nx][ny] != 0) continue;
                                q.addLast(new Point(nx, ny));
                                time[nx][ny] = level;
                            }
                        }

                    }
       
                    ans = Math.max(ans, level);
                }
            }
        }
        System.out.println(ans-1);

    }

}
