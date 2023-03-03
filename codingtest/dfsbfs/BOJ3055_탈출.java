import java.io.*;
import java.util.*;

public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static char [][] map;
    static int R,C;
    static int [][] water_time;
    static int [] S = new int [2];
    static int [] D = new int [2];
    static int [][] hedgehog_time;
    static Queue<int[]> waterQ = new LinkedList<>();
    static Queue<int[]> Q = new LinkedList<>();
    static boolean [][] visit;

    static void input() {
        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];
        water_time = new int[R][C];
        visit = new boolean[R][C];
        hedgehog_time = new int[R][C];
        for (int i=0; i<R; i++){
            String temp = sc.nextLine();
            for(int j=0; j<C ; j++){
                map[i][j] = temp.charAt(j);
                if (map[i][j]=='S') {
                    S[0]=i;
                    S[1]=j;
                }
                if (map[i][j]=='D'){
                    D[0]=i;
                    D[1]=j;
                }
                // 모든 물 Q에 넣어주기 + -1 초기화 + visit처리
                if (map[i][j]=='*'){
                    waterQ.add(new int[]{i,j});
                    water_time[i][j] = 0;
                }else{
                    water_time[i][j] = -1;
                }
            }
        }
    }
    static int [] dx = {-1,1,0,0}, dy={0,0,-1,1};
    public static void spreadWater(){
        while (!waterQ.isEmpty()){
            int[] now = waterQ.poll();
            for (int i=0; i<4; i++){
                int nx = now[0] + dx[i]; int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >=R || ny >=C) continue;
                if (map[nx][ny]=='D' || map[nx][ny]=='X' || map[nx][ny]=='*' ) continue; //S도 넣어도 상관없긴함.
                if (water_time[nx][ny] != -1) continue;

                water_time[nx][ny]= water_time[now[0]][now[1]] + 1 ;
                waterQ.add(new int[]{nx,ny});
            }
        }
    }
    public static void move(int [] S){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                hedgehog_time[i][j] = -1;
                visit[i][j] = false;
            }
        }
        Q.add(S);
        visit[S[0]][S[1]] = true;
        hedgehog_time[S[0]][S[1]] = 0;
        while (!Q.isEmpty()){
            int[] now = Q.poll();
            for (int i=0; i<4; i++){
                int nx = now[0] + dx[i]; int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >=R || ny >=C) continue;
                if (map[nx][ny]=='X' || hedgehog_time[nx][ny] != -1) continue; //벽이거나 이미 갔던 길이라면
                if (water_time[nx][ny] != -1 && hedgehog_time[now[0]][now[1]] +1 >= water_time[nx][ny] ) continue; //**water가 간 적이 있을때 조건에 부합하지 않으면

                hedgehog_time[nx][ny]= hedgehog_time[now[0]][now[1]] + 1 ;
                Q.add(new int[]{nx,ny});
            }
        }

    }
    static void pro() {
//        for (int i=0; i<R; i++){
//            System.out.println(Arrays.toString(map[i]));
//        }
        spreadWater();
//        System.out.println("------------");
//        for (int i=0; i<R; i++){
//            System.out.println(Arrays.toString(water_time[i]));
//        }

        move(S);
//        System.out.println("------------");
//        for (int i=0; i<R; i++){
//            System.out.println(Arrays.toString(hedgehog_time[i]));
//        }

        if (hedgehog_time[D[0]][D[1]] == -1) System.out.println("KAKTUS");
        else System.out.println(hedgehog_time[D[0]][D[1]] );

    }
    public static void main(String[] args) {
        input();
        pro();
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
