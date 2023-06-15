import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
class Main2
{
    static class Cube{
        int [] vertical;
        int [] horizon;

        public Cube(){
            vertical = new int[4];
            horizon = new int[4];
        }

        public void moveLeft(){
            //1. deep copy
            int [] cp = new int[4];
            for (int i=0; i<4; i++) cp[i] = horizon[i];
            for (int i=0; i<4; i++){
                //하나씩 옮긴다.
                int ni = i - 1 < 0 ? i - 1 + 4: i - 1;
                horizon[ni] = cp[i];
            }
            syncHorToVer();

        }
        public void moveRight(){
            //1. deep copy
            int [] cp = new int[4];
            for (int i=0; i<4; i++) cp[i] = horizon[i];
            for (int i=0; i<4; i++){
                //하나씩 옮긴다.
                int ni = ( i + 1 ) % 4;
                horizon[ni] = cp[i];
            }
            syncHorToVer();

        }
        public void moveUp(){
            //1. deep copy
            int [] cp = new int[4];
            for (int i=0; i<4; i++) cp[i] = vertical[i];
            for (int i=0; i<4; i++){
                //하나씩 옮긴다.
                int ni = i - 1 < 0 ? i - 1 + 4: i - 1;
                vertical[ni] = cp[i];
            }
            syncVerToHor();

        }
        public void moveDown(){
            int [] cp = new int[4];
            for (int i=0; i<4; i++) cp[i] = vertical[i];

            for (int i=0; i<4; i++){
                //하나씩 옮긴다.
                int ni = ( i + 1 ) % 4;
                vertical[ni] = cp[i];
            }
            syncVerToHor();
        }

        public int getTop(){
            return vertical[1];
        }
        public int getBottom(){
            return vertical[3];
        }
        public void setBottom(int x){
            vertical[3] = x;
            horizon[3] = x;
        }
        public void setTop(int x){
            vertical[1]=x;
            horizon[1]=x;
        }
        public void print(){
            System.out.println("-------------");
            System.out.println(Arrays.toString(vertical));
            System.out.println(Arrays.toString(horizon));
            System.out.println("--------------");
        }
        private void syncVerToHor(){
            horizon[1] = vertical[1];
            horizon[3] = vertical[3];
        }
        private void syncHorToVer(){
            vertical[1] = horizon[1];
            vertical[3] = horizon[3];
        }

    }

    static FastReader sc = new FastReader();
    static StringBuilder sb= new StringBuilder();
    static int [][] map;
    static int [] commands;
    static int nowX, nowY;
    static int [] dir = {0,1,2,3,4}; //동 서 북 남 
    static int [] dx = {0, 0, -1, 1};
    static int [] dy = {1, -1, 0, 0};
    static int K, N, M;

    public static void main(String[] args) {
        input();
        Cube cube = new Cube();
        
        for (int i=0; i<K; i++){
            int k = commands[i];
            //명령에 주사위, 현재 위치 맞게 이동
//            System.out.println("명령어 k = "+ k);
            switch (dir[k]){
                case 1://동쪽이라면
                    if (!isArea(nowX + dx[0], nowY + dy[0])) continue;
                    cube.moveRight();
                    nowX += dx[0];
                    nowY += dy[0];
//                    System.out.println("move 동쪽");
                    break;
                case 2:
                    if (!isArea(nowX + dx[1], nowY + dy[1])) continue;
                    cube.moveLeft();
                    nowX += dx[1];
                    nowY += dy[1];
//                    System.out.println("move 서쪽");
                    break;
                case 3:
                    if (!isArea(nowX + dx[2], nowY + dy[2])) continue;
                    cube.moveUp();
                    nowX += dx[2];
                    nowY += dy[2];
//                    System.out.println("move 북쪽");
                    break;
                case 4:
                    if (!isArea(nowX + dx[3], nowY + dy[3])) continue;
                    cube.moveDown();
                    nowX += dx[3];
                    nowY += dy[3];
//                    System.out.println("move 남쪽");
                    break;
                    
            }

            
            if (map[nowX][nowY]==0){
                map[nowX][nowY] = cube.getBottom();
            }else{
                cube.setBottom(map[nowX][nowY]);
                map[nowX][nowY] = 0;
            }

            sb.append(cube.getTop()+"\n");
//            System.out.println("윗면 = "+cube.getTop());
//            cube.print();
        }

        System.out.println(sb);

    }
    public static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        nowX = sc.nextInt();
        nowY = sc.nextInt();
        K = sc.nextInt();
        commands = new int[K];
        map = new int[N][M];
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }
        for (int i=0; i < K; i++){
            commands[i] = sc.nextInt();
        }
    }
    static boolean isArea(int x, int y){
        return x >=0 && x < N && y >=0 && y < M;
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