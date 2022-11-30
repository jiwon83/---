package dp;
import java.util.*;
import java.io.*;
/*
실버3 계단 오르기 https://www.acmicpc.net/problem/2579
심화 : 경로 출력 -> bfs 사용 X , more 클린 코드
시도 : O
 */
public class ClimbingStairs {

    static class From{
        int idx, depth;

        public From(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }

    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int [] A;
    static int[][] Dy;
    //심화
    static From [][] From;

    static void input(){
        N= sc.nextInt();
        A = new int[N+1];
        Dy = new int[N+1][2];
        From = new From[N+1][2];
        for (int i=1; i<=N; i++ ){
            A[i] = sc.nextInt();
        }
    }
    static void preprocess() {

        Dy[1][0]=A[1];
        Dy[1][1]=0;

        From[1][0] = new From(0,0);
        From[1][1] = new From(0,0);

        if (N >=2){
            Dy[2][0]=A[2];
            Dy[2][1]=Dy[1][0]+ A[2];

            From[2][0] = new From(0,0);
            From[2][1] = new From(1,0);
        }
        for (int i=3; i<=N; i++){
            Dy[i][0] = Math.max(Dy[i-2][0], Dy[i-2][1] )+ A[i];
            Dy[i][1] = Dy[i-1][0]+ A[i];

            /* 심화, 경로구하기 */
            From[i][0] = new From(i-2, Dy[i-2][0] > Dy[i-2][1] ? 0: 1);
            From[i][1] = new From(i-1,0);

        }

    }
    static void pro(){
        input();
        preprocess();
        System.out.println(Math.max(Dy[N][0], Dy[N][1]));

        /* 심화, 경로구하기 */
        From fr = From[N][ Dy[N][0] > Dy[N][1] ? 0:1];
        while (fr != null){ //From table의 0,0은 null이다.
            sb.insert(0,fr.idx+" ");
            fr = From[fr.idx][fr.depth];
        }
        sb.append(N);
        System.out.println(sb);

    }

    public static void main(String[] args) {

        pro();
        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
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
