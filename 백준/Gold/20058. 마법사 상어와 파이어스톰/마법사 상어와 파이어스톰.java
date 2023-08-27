import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
3 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1
 */

public class Main {


    static int [] dx = {-1,0,1,0}, dy = {0,1,0,-1};

    static int [][] map;
    static int N, Q;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
        Q = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];

        for (int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N ; j++ ){
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i= 1; i <= Q; i++){
            int L = Integer.parseInt(st.nextToken());//st.nextToken().charAt(0)-'0';
            int len = (int) Math.pow(2, L);
            //길이가 2^L 인 격자로 나눈다.
            for(int j = 1; j <= N; j+= len){
                for(int w = 1; w <= N; w += len){
//                    System.out.println("나눈 시작 점 x = "+ j + " y = "+ w);
                    //이 격자를 90도 회전
                    rotate90(j, w, len);

                }
            }
            //이후 얼음과 3면이상 인접한지 확인 후 1 감소
            ArrayDeque<int []> willMelt = new ArrayDeque<>();
            for(int j = 1; j <= N; j++){
                for(int w = 1; w <= N; w++){
//                    System.out.println("얼음 확인 x = "+ j + " y = "+ w);
                    if( map[j][w] != 0 && isMelting(j, w) ){ //이후 얼음과 3면이상 인접한지 확인
                        willMelt.addLast(new int[]{j,w});
                    }
                }
            }
            while (!willMelt.isEmpty()){
                int [] melt = willMelt.pollFirst();
                map[melt[0]][melt[1]] -= 1;
            }
//            System.out.println("얼음 녹이기 완료");
//            printMap();

        }//Q
        //전체 결과 조회 : bfs로 가장 큰 그룹의 크기와, 총합을 조회
        int [] result = bfsResult();
//        System.out.println(Arrays.toString(result));

        System.out.print(result[0]+"\n"+result[1]);

    }
    static int[] bfsResult(){
        //전체 결과 조회 : bfs로 가장 큰 그룹의 크기와, 총합을 조회
        int [] res = new int[2]; //index  0 = 총합, 1 = 가장 큰 그룹의 크기
        boolean [][] visit = new boolean[N+1][N+1];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        for(int i=1; i<= N; i++){
            for(int j =1 ; j<= N; j++){
                if(!visit[i][j] && map[i][j] > 0){
                    //bfs 시작
                    q.clear();
                    q.addLast(new int[]{i,j});
                    visit[i][j] = true;
                    int gCnt = 0;
                    while(!q.isEmpty()){
                        int [] now = q.pollFirst();
                        res[0]+= map[now[0]][now[1]]; gCnt++;
                        for (int d = 0; d<4; d++){
                            int nx = now[0] + dx[d];
                            int ny = now[1] + dy[d];
                            if(!inArea(nx,ny) || visit[nx][ny] || map[nx][ny] <= 0) continue;
                            visit[nx][ny] = true;
                            q.addLast(new int[]{nx, ny});
                        }
                    }
                    res[1] = Math.max(res[1], gCnt);

                }
            }
        }
        return res;
    }
    static void rotate90(int x, int y, int len){

//        System.out.println("rotate90 x ="+ x+ " y = "+ y + " len = "+ len);

        int [][] cpMap = new int[len][len]; //copy
        for (int i=x; i< x+len; i++){
            for(int j = y; j< y+len; j++){
                cpMap[i-x][j-y] = map[i][j];
            }
        }

        for(int i= 0; i<len; i++){
            for(int j = 0; j<len; j++){
                int targetX = j;
                int targetY = reverseP(i, 0, len-1);
                map[targetX+x][targetY+y] = cpMap[i][j];
            }
        }

//        System.out.println("rotate complete ");
//        printMap();

    }
    static void printMap(){
        for (int i=0; i<=N; i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static int reverseP(int index, int startIdx, int endIdx){
//        System.out.println("reverseP "+ index +" > "+ (startIdx + endIdx - index));
        return startIdx + endIdx - index;
    }
    static boolean isMelting(int x, int y){
        //이후 얼음과 3면이상 인접한지 확인
        int count = 0;
        for(int d=0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(inArea(nx, ny) && map[nx][ny] > 0){
                count++;
            }
        }
        return count < 3;
    }

    static boolean inArea(int x, int y){
        return x > 0 && y > 0 && x <= N && y <= N;
    }

}
