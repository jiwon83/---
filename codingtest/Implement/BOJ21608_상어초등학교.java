import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static class Seat implements Comparable<Seat>{
        int r, c, cntLike, cntBlank;
        public Seat(int r, int c){
            this.r = r;
            this.c = c;
            cntLike =0;
            cntBlank = 0;
        }
        int getScore(){
            //cntLike에 따라서 만족도 반환
            if (cntLike == 0) return 0;
            else return (int) Math.pow(10, cntLike -1);

        }
        @Override
        public int compareTo(Seat other){
            if (this.cntLike != other.cntLike) return other.cntLike - this.cntLike;
            if( this.cntBlank != other.cntBlank) return other.cntBlank - this.cntBlank;
            if( this.r != other.r) return this.r - other.r;
            else return this.c - other.c;
        }
        @Override
        public String toString(){
            return "r :"+r+" c: "+c+" cntLike: "+cntLike+" cntBlank: "+cntBlank;
        }

    }
    static FastReader sc = new FastReader();
    static int[][] info;
    static int [][] classroom;
    static int N;
    static int totalScore = 0;
    static int [] dx = {1,-1,0,0}, dy={0,0,-1,1};
    static Seat [][] finalSeats;
    static void pro(){
        for(int i=0; i<N*N; i++){ //순서에 따라

//            System.out.println("순서 : "+ i);
//            for(int q = 0; q<N; q++){
//                System.out.println(Arrays.toString(classroom[q]));
//            }
//            System.out.println("----------------"+ i);
            int st = info[i][0];
            // int [] likes =
            List<Seat> seats = new ArrayList<>();
            //1. 빈자리를 찾는다.
            for (int j=0; j<N; j++){
                for (int w = 0; w < N; w++){
                    if( classroom[j][w] == 0) seats.add(new Seat(j, w));
                }
            }

            //2. 모든 빈자리에 대하여
            // 상하좌우에 좋아하는 학생수, 인접 빈칸 수, r,c를 구한다.
            for(Seat seat : seats){
                int x = seat.r;
                int y = seat.c;
                for(int j=0; j<4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if( nx < 0 || ny < 0 || nx >=N || ny >= N) continue;
                    if( isThereLike(i, nx,ny) ) seat.cntLike++;
                    if( classroom[nx][ny]==0) seat.cntBlank++;
                }
            }

            // 정렬
            Collections.sort(seats);

            //자리 배정
            classroom[seats.get(0).r][seats.get(0).c] = st;

//            System.out.println("자리 선정 완료");
//            System.out.println(seats.get(0));

            //갱신
//            System.out.println("score "+ seats.get(0).cntLike +" -> "+ seats.get(0).getScore());
//            totalScore += seats.get(0).getScore();

        }
        updateTotalScore();
        System.out.println(totalScore);
    }
    static void updateTotalScore(){

        for (int i=0; i<N; i++){
//            System.out.println(Arrays.toString(classroom[i]));
            for(int j=0; j<N; j++){
                Seat seat = new Seat(i,j);
                for(int d=0; d<4; d++){
                    int nx = seat.r + dx[d];
                    int ny = seat.c + dy[d];
                    if( nx < 0 || ny < 0 || nx >=N || ny >= N) continue;
                    //순서를 찾아라
                    int stPos=-1;
                    for(int o=0; o<N*N; o++){
                        if(info[o][0]==classroom[i][j]){
                            stPos = o;
                            break;
                        }
                    }
                    if( isThereLike(stPos, nx,ny) ) seat.cntLike++;
                }
//                System.out.println("( "+i+","+j+" ) " +"cntLike = "+ seat.cntLike+" getScore="+ seat.getScore());
                totalScore += seat.getScore();
            }
        }
    }
    static boolean isThereLike(int stIdx, int r, int c){
        int [] likes = new int[] { info[stIdx][1] , info[stIdx][2], info[stIdx][3], info[stIdx][4]};
        for (int friend : likes){
            if(classroom[r][c] == friend) return true;
        }
        return false;
    }
    static void input(){
        /*
3
4 2 5 1 7
3 1 9 4 5
9 8 1 2 3
8 1 9 3 4
7 2 3 4 8
1 9 2 5 7
6 5 2 3 4
5 1 9 2 8
2 9 3 1 4
         */
        N = sc.nextInt();
        classroom = new int[N][N];
        finalSeats = new Seat[N][N];
        info = new int[N*N][5];
        for (int i=0; i<N*N; i++){
            for (int j=0; j<5; j++){
                info[i][j] = sc.nextInt();
            }
        }

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