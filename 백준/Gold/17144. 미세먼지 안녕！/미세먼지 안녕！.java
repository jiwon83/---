import java.io.*;
import java.util.*;


public class Main {

    static class P{
        int x, y;
        public P(int x, int y ){
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C, T; //T = 시간
    static int ans ; //남아있는 미세먼지 양
    static int [][] map;
    static P [] airpurifier = new P[2];

    static int [][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {



        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R+1][C+1];

        int index= 0;
        for (int i = 1; i<= R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    airpurifier[index++] = new P(i,j);
                }
            }
        }


        for (int t = 1; t<=T; t++){
//            System.out.println("------------t = "+ t+"---------------");
//            //미세먼지 확산
//            System.out.println("미세먼지 확산 전");
//            printMap();
            spread();
//            System.out.println("미세먼지 확산 후");
//            printMap();
            //공기청정기 작동
            clear(airpurifier[0], -1, 1);
            clear(airpurifier[1], 1, 1);

//            System.out.println("공기청정기 작동 후 ");
//            printMap();

        }
        System.out.println(countMap());



    }
    static int countMap(){
        int count = 0;
        for (int i = 1; i<= R; i++){
            for (int j =1 ; j<=C; j++){
                if(map[i][j] == -1) continue;
                count += map[i][j];
            }
        }
        return count;
    }
    static void printMap(){
        for (int i = 1; i<= R; i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static void spread(){
        int [][] addMap = new int[R+1][C+1];
        for (int i = 1; i<= R; i++){
            for(int j = 1; j<=C; j++){
                if (map[i][j] == -1) continue;
                int spreadCnt = 0;
                int divide = map[i][j] / 5;
                for (int d = 0; d<4; d++){
                    int nx = i + dir[d][0];
                    int ny = j + dir[d][1];
                    if(!inArea(nx, ny) || isPurifier(nx, ny)) continue;
                    spreadCnt++;
                    addMap[nx][ny] += divide;
                }
                map[i][j] -= spreadCnt * divide;

            }
        }
        for (int i = 1; i<= R; i++){
            for(int j = 1; j<=C; j++){
                map[i][j] += addMap[i][j];
            }
        }
    }
    static boolean isPurifier(int x, int y){
        for (int i=0; i<2; i++){
            if(airpurifier[i].x ==x && airpurifier[i].y==y) return true;
        }
        return false;
    }
    static void clear(P start, int rotate, int d){
        int x = start.x;
        int y = start.y;
        int pre = 0;
        while (true){

            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
//            if(nx == start.x && ny == start.y) return; //다음이 시작점이면 미세먼지 소멸 후 종료
            if(!inArea(nx, ny)){
                if (rotate == -1){
                    d = (d==0)? 3: d-1;
                }else {
                    d = (d + 1) % 4;
                }
            }
            if( isPurifier(x+dir[d][0], y+dir[d][1]) ) break; //다음지점이 공기청정기이면 종료

            //다음 위치 이동
            x = x + dir[d][0];
            y = y + dir[d][1];
            int temp = map[x][y];
            map[x][y] = pre; //현재 위치에는 이전 값을 넣는다.
            pre = temp;
        }

    }


    static boolean inArea(int x, int y){
        return x > 0 && y > 0 && x <= R && y <= C;
    }

}
