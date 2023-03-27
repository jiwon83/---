import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static Queue<Integer> q = new LinkedList<>();
    static Queue<Integer> assistQ = new LinkedList<>();
    static int [][][] box;
    static int time, notYet=0;
    static int M,N,H;
    static void input() {
        N = sc.nextInt(); M=sc.nextInt(); H=sc.nextInt();
        box = new int[H][M][N];
        for (int i=0; i<H; i++){
            for (int j=0; j<M; j++){
                for (int w=0; w<N; w++){
                    box[i][j][w]= sc.nextInt();
                    if (box[i][j][w]==0) notYet++;
                    if (box[i][j][w]==1) {
                        q.add(i);
                        q.add(j);
                        q.add(w);
                    }
                }
            }
        }

    }
    static int [][] dir = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0},{0,0,-1},{0,0,1}};
    static void bfs(){
        while (!q.isEmpty()){
            int h = q.poll();
            int x = q.poll();
            int y = q.poll();

            //상하좌우
            for (int i=0; i<6; i++){
                int nh = h + dir[i][0]; int nx = x + dir[i][1]; int ny = y + dir[i][2];
                if (nx < 0 || ny <0 || nx >= M || ny >=N || nh < 0 || nh >= H) continue;
                if (box[nh][nx][ny]==0){
                    box[nh][nx][ny]=1;
                    assistQ.add(nh);
                    assistQ.add(nx);
                    assistQ.add(ny);
                    notYet--;
                }
            }
        }
    }
    static void pro() {
        while (true){
            if (notYet==0 || q.isEmpty()) {
                if (q.isEmpty() && notYet!=0) {
                    System.out.println(-1);
                    break;
                }
                System.out.println(time);
                break;

            }else{

                bfs();
                time++;
                Iterator<Integer> iter = assistQ.iterator();
                while (iter.hasNext()){
                    q.add(iter.next());
                }
                assistQ = new LinkedList<>();

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