import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
class Main2
{
    static int N, M, x, y, dir;
    static boolean [][] clean;
    static int [][] map;
    static int ans=1;
    static FastReader sc = new FastReader();

    public static void main(String[] args) throws IOException{
        input();
//        System.out.println(" I am in "+ x +" , "+ y+ " dir = "+ dir);
        recur(x, y, dir);
        System.out.println(ans);
    }
    static void input() throws IOException{

        N = sc.nextInt();
        M = sc.nextInt();
        x= sc.nextInt();
        y = sc.nextInt();
        dir = sc.nextInt();
        map = new int[N][M];
        clean = new boolean[N][M];
        for (int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }


        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
    }
    static int [][] go = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    static int [][] back = {{1,0}, {0,-1}, {-1,0}, {0,1}};

    public static void recur(int r, int c, int dir){
//        System.out.println("r = "+ r + " c = "+ c + "dir = "+ dir);
        clean[r][c] = true;

        int dirR=dir;
        for (int i = 0; i< 4; i++){
            dirR = rotateL(dirR);
            int nr = r + go[dirR][0];
            int nc = c + go[dirR][1];
            if ( inArea(nr, nc) && map[nr][nc] == 0 && !clean[nr][nc]){
                ans++;
//                System.out.println(dir+ "방향으로 앞으로 이동 "+ nr+" , "+ nc);
                recur(nr, nc, dirR);
                return;
            }
        }
        int nr = r + ( -1 * go[dir][0]);
        int nc = c + ( -1 * go[dir][1]);
        if ( inArea(nr, nc) && map[nr][nc] == 0){
//                System.out.println(dir+"방향으로 후진 "+ nr +" , "+ nc);
            recur( nr, nc, dir);
        }
    }
    static int rotateL(int dir){
        return dir - 1 >= 0 ? dir - 1: dir -1 + 4;
    }
    static boolean inArea(int r, int c){
        return r >=0 && c >=0 && r <N && c < M;
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