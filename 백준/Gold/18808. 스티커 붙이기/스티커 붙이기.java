import java.awt.Point;
import java.io.*;
import java.util.*;


public class Main {
    static class Sticker{
        int r,c;
        List<Point> contents;
        public Sticker(int r, int c){
            this.r = r;
            this.c  = c;
            contents = new ArrayList<>();
        }
        void rotate(){

                for (int i = 0; i<contents.size(); i++){
                    Point p = contents.get(i);
                    int rX = r-1 - p.x;
                    contents.get(i).setLocation(p.y, rX);
                }
                int tmp = r;
                this.r = c;
                this.c = tmp;
        }
    }

    static int [][] board;
    static int N, M, K;
    static List<Sticker> stickers;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        input();
        for (Sticker sticker : stickers){
            attach(sticker, board);
        }
        System.out.println(countAttached(board)); //4. 칸의 갯수 센다.
    }

    private static int countAttached(int[][] board) {
        int cnt = 0;
        for (int i = 0; i< N; i++){
            for(int j = 0; j< M; j++){
                if(board[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }

    private static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        K = Integer.parseInt(st.nextToken());
        stickers = new ArrayList<>();
        for (int i = 0; i<K ; i++){

            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            stickers.add(new Sticker(R, C));

            for (int r = 0; r <R; r++){
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c <C; c++){
                    int n = Integer.parseInt(st.nextToken());
                    if(n == 1) stickers.get(i).contents.add(new Point(r, c));
                }
            }
//            System.out.println("sticker : " + stickers.get(i));
        }

    }

    private static void attach(Sticker sticker, int[][] board) {
        //2. 공간을 찾는다. K * N*M * 10*10
//        System.out.println(" sticker : "+ sticker.r + " , "+sticker.c);
//
        for (int rot = 0; rot < 4; rot++){ // 4번 rot 만큼 회전해본다.
            if (rot != 0) sticker.rotate();
            for (int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    boolean find = true;
                    for (Point p : sticker.contents){
                        int nx = i + p.x;
                        int ny = j + p.y;
                        if (!inArea(nx, ny) || board[nx][ny] == 1){
                            find = false;
                            break;
                        }
                    }
                    if (find) { // 스티커를 붙인다.
                        for (Point p : sticker.contents){
                            board[p.x + i][p.y + j] = 1;
                        }
//                        printBoard(board);
                        return;
                    }
                }
            }
        }

    }
    static Point reverse(int x, int y , int rot){
        System.out.println(" x = " + x + " y = "+y + " rot " + rot);
        Point p = new Point(x, y);
        for (int r = 0; r <rot; r++){
            int reverX = N-1 - p.x;
            p = new Point( p.y, reverX);
        }
        System.out.println(" result :  "+ p);
        return p;
    }
    static void printBoard(int [][] board){
        for (int i = 0; i< N; i++){
            System.out.println(Arrays.toString(board[i]));
        }
    }
    static boolean inArea(int x, int y){
        return x >=0 && y >=0 && x < N && y < M;
    }
}