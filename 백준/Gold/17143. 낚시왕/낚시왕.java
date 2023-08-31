import java.io.*;
import java.util.*;

/*
4 6 8
4 1 3 3 8
1 3 5 2 9
2 4 8 4 1
4 5 0 1 4
3 3 1 2 7
1 5 8 4 3
3 6 2 1 2
2 2 2 3 5
 */

public class Main {


    static class Shark{
        int r, c, speed, d, size;
        public Shark(int r, int c, int speed, int d, int size){
            this.r = r;
            this. c= c;
            this.speed = speed;
            this.d  = d;
            this.size = size;
        }

        public String toString(){
            return " ( " + r + " , " + c +" ) " + "speed = " + speed  + " d = "+ d+" size = "+ size;
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C, M; //M = 상어의 수
    static int ans; //잡은 상어 크기의 합'
    static Shark [][] map; //상어들의 참조값 map , 없으면 null / 1,1부터 시작
    static Shark [][] mapCp;
    static int [] dx = {0,-1,1, 0,0}, dy = {0,0,0,1,-1};
    public static void main(String[] args) throws IOException {

        //입력 (+ 상어들의 정보)
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Shark[R+1][C+1];
        for (int i = 1; i<= M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r, c, s, d, z);
            map[r][c] = shark;
        }

        ans = 0;

        int time = 0; //낚시왕의 위치
        while( ++time <= C){
//            System.out.println("낚시왕의 위치 "+ time);
            //상어 잡기
            for(int i = 1; i <= R; i++){
                if(map[i][time] != null){
                    //상어를 잡는다.
//                    System.out.println("상어를 잡았다! "+ map[i][time]);
                    ans += map[i][time].size;
                    map[i][time] = null;
                    break;
                }
            }

            //상어 이동

            //상어의 이동 후 격자
            mapCp = new Shark[R+1][C+1];
//            System.out.println("상어들의 이동");
            for(int i= 1; i<= R; i++){
                for(int j = 1; j<= C; j++){
                    if(map[i][j] == null) continue;
                    Shark shark = map[i][j];
//                    System.out.println(" shark :"+shark);
                    int nx = i; int ny = j;
                    int move = shark.d <= 2 ? shark.speed % (( R - 1) * 2 ) : shark.speed % (( C - 1) * 2 );
                    while ( move-- > 0){
                        int mx = nx + dx[shark.d];
                        int my = ny + dy[shark.d];
                        if( !inArea(mx, my) ){
                            shark.d = reverseDir(shark.d); //영구적으로 방향 바꾼다.
                        }
                        nx = nx + dx[shark.d];
                        ny = ny + dy[shark.d];

                    }
//                    System.out.println("이동 후 " + nx + " , "+ ny);
                    if(mapCp[nx][ny] != null){
                        if(shark.size < mapCp[nx][ny].size){ //같으면 나자신
//                            System.out.println("먹혔다. "+map[nx][ny] + " > " + shark);
                            continue; //작다면 먹힌다.
                        }
                    }
                    shark.r = nx;
                    shark.c = ny;

                    mapCp[nx][ny] = shark;
//                    System.out.println("갱신 "+ shark);
                }
            }
            
            for (int i= 1; i<= R; i++){
                map[i] = mapCp[i].clone();
            }


        }

        System.out.println(ans);

    }
    static int reverseDir(int dir){
        if ((dir & 1)==1) return dir +1;
        else return dir -1;
    }

    static boolean inArea(int x, int y){
        return x > 0 && y > 0 && x <= R && y <= C;
    }

}
