import java.util.*;
import java.io.*;

class Main4
{
    static boolean [][] oxygen;
    static int [][] map;
    static int N, M;
    static int lastCnt;
    static int cheeseCnt;
    static FastReader sc = new FastReader();
    static int [] dx = {0,0,1,-1}, dy = {-1,1,0,0};

    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int [N][M];
        for (int i=0 ; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }
    }
    static void initialCheeseCount(){
        cheeseCnt=0;
        for (int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j]==1) cheeseCnt++;
            }
        }
    }
    static void initialOxygen(int r, int c){ //추가된 산소를 갱신
        //bfs
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r,c});
        oxygen[r][c]=true;

        while (!q.isEmpty()){
            int [] now = q.poll();
            int x = now[0]; int y = now[1];
            for (int i=0; i<4; i++){
                int nx = x + dx[i]; int ny = y + dy[i];
                if (!inArea(nx, ny)) continue;
                if( !oxygen[nx][ny] && map[nx][ny]==0){ //이미 산소인 것은 살필 필요 X, 추가로 구멍에 대해서만 작업
                    oxygen[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }
            }
        }
    }

    static boolean touchOxygen(int r, int c){
        //4 방향 중 산소와 닿아 있는지 확인
        for (int i=0; i<4; i++){
            int nx = r + dx[i]; int ny = c + dy[i];
            if (inArea(nx, ny) && oxygen[nx][ny]){
//                System.out.println("r: "+r +" c: "+c+" 가 "+ i+" 방향으로 공기에 닿음");
                return true;
            }
        }
        return false;
    }

    static void newOxygen(){
        for (int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if (oxygen[i][j]) continue; //이미 밖인 건 고려 X
                if ( map[i][j] == 0 && touchOxygen(i,j)){ //0인 것 중 산소와 맡닿은 게 있다면
                    //새로 밖으로 연결된 구멍
                    initialOxygen(i, j);
//                    oxygen[i][j] = true;
//                    System.out.println("new oxygen "+ i+" , "+j);

                }
            }
        }
    }
    static void melting(){
        Queue<int [] > willMelt = new LinkedList<>();//가장자리만 걸러내기 위해 큐 사용.
        for (int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(oxygen[i][j] || map[i][j]==0) continue;
                //여기까지는 치즈
                if (touchOxygen(i,j)){
                    willMelt.add(new int[]{i,j});
                }
            }
        }
        cheeseCnt -= willMelt.size();
        while (!willMelt.isEmpty()){
            int [] n = willMelt.poll();
            oxygen[n[0]][n[1]] = true;
        }

    }
    static void pro(){
        oxygen= new boolean[N][M];
        initialOxygen(0,0);//벽인 부분 기준으로 처음 산소 초기화
        initialCheeseCount();
        int time=0;
        while (true){

            lastCnt = cheeseCnt;
            time++;
//            System.out.println("time = "+time+ "cheeseCnt = "+cheeseCnt);
            //밖이 아닌 곳 탐색
            //1. 새로운 구멍 탐색
            newOxygen();
//            System.out.println(" ... 새로운 구멍 ...");
//            printNow();
            //2. 치즈가 녹는다.
            melting();
//            System.out.println(" ... 치즈 녹음 ..."+ cheeseCnt);
//            printNow();
            //치즈갯수 count
            if (cheeseCnt == 0){
                break;
            }
            //if(치즈갯수==0) 종료
        }
        System.out.println(time +"\n"+ lastCnt);

    }
    public static void printNow(){

        StringBuilder sb = new StringBuilder();
        for (int i=0 ; i<N; i++){
            for (int j=0 ; j<M; j++){
                if (oxygen[i][j]){
                    sb.append(0);
                }else if(map[i][j]==1){
                    sb.append(1);
                }else{
                    sb.append("#");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) {
        input();
        pro();

    }
    static boolean inArea(int r, int c){
        return r >= 0 && c >= 0 && r < N && c < M;
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