
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;


public class Main {
    static int N, M;
    static int [][] map;
    static int score;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean [][] visit;

    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
    static boolean removeMaxGroup(){

        //maxGroup을 찾는다.
        visit = new boolean[N][N];
        int maxSize = 1;
        int preRainbowCnt = 0;
        List<Point> willRemoved = null;
        for (int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] >= 1 && map[i][j] <= M && !visit[i][j]){
                    Object[] result = bfsMakeGroup(map[i][j], i, j, maxSize, preRainbowCnt);
                    int rainbowCnt = (int)result[0];
                    List<Point> list = (List<Point>) result[1];
//                    System.out.println("i = "+ i+ " j = "+j + " map[i][j] =  "+map[i][j] ) ;
//                    System.out.println("rainbowCnt = "+rainbowCnt+" list = "+list.size());
//                    printVisit();

                    if( list.size() >= maxSize ){
                        maxSize = list.size();
                        preRainbowCnt = rainbowCnt;
                        willRemoved = list;
                    }
                }
            }
        }
        if (maxSize == 1) return false;
        //지운다.

        score += (int)Math.pow(maxSize, 2);
        for (Point p : willRemoved){
            map[p.x][p.y] = -2;
        }
        return true;
    }
    //해당하는 색의 블록을 찾는 bfs, maxGroupSize 보다 크다면 List<Point> return 그렇지 않다면 빈 List return
    static int [] dx = {-1,1,0,0}, dy={0,0,-1,1};
    static Object[] bfsMakeGroup(int color, int sx, int sy, int maxSize, int preRainbowCnt){ //Integer, List<Point>>
        // 검정 포함 X, 무지개 최대한 많이, 다른 것이라면 같은 색만.
        Object[] res = new Object[2];
        boolean [][] rainVisit = new boolean[N][N];
        List<Point> result = new ArrayList<>();
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.addLast(new Point(sx, sy));
        result.add(new Point(sx, sy));
        visit[sx][sy] = true;

        int rainbowCnt = 0;
        while(!q.isEmpty()){
            Point now = q.pollFirst();
            for(int d = 0; d <4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(map[nx][ny] != 0 && map[nx][ny] != color) continue;
                if(map[nx][ny] != 0 && visit[nx][ny]) continue;
                if(map[nx][ny] == 0 && rainVisit[nx][ny]) continue;

                if (map[nx][ny] == 0){
                    rainbowCnt++;
                    rainVisit[nx][ny] = true;
                }else{
                    visit[nx][ny] = true;
                }
                result.add(new Point(nx, ny));
                q.addLast(new Point(nx, ny));
            }
        }
        if (maxSize == result.size() && rainbowCnt < preRainbowCnt){ //같으면
            result.clear();
        }
        res[0] =  rainbowCnt; res[1] = result;
        return res;

    }
    static void fall(){
        for (int col = 0; col < N; col++){
            for (int r = N-1; r >=0; r--){
                if (map[r][col] >= 0 && map[r][col] <=M){ //무지개나 일반 블록이라면
                    int down = r;
                    int block = map[r][col];
                    while (down + 1 < N && map[down+1][col] == -2){ //아래가 빈칸인 동안
                        map[down][col] = -2; //빈 공간
                        down++;
                    }
                    map[down][col] = block;
                }
            }
        }

    }
    static int reverIdx(int idx, int N){
        return Math.abs((N-1)- idx);
    }
    static void rotateRev90(){
        int mapCp [][] = new int[N][N];
        for (int i = 0 ;i<N; i++){
            mapCp[i] = map[i].clone();
        }
        for (int i = 0; i<N; i++){
            for (int j =0; j<N; j++){
                map[i][j] = mapCp[j][reverIdx(i, N)];
            }
        }
    }
    static void printVisit(){
        System.out.println("--------visit----------");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <N; j++){
                if(visit[i][j]){
                    sb.append("T");
                }else{
                    sb.append("F");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
        System.out.println("------------------");
    }
    static void printMap(){
        System.out.println("--------map----------");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("------------------");

    }
    public static void main(String[] args) throws Exception{
        input();
        while (removeMaxGroup()){
            fall();
            rotateRev90();
            fall();
        }
        System.out.println(score);
    }
}
