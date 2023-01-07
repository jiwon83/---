package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236_아기상어 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, ans;
    static int [][] graph, dist;
    static int bite=0, size=2;
    static int targetSize;
    static int [] pos =new int[2];

    static void initialdist(){
        dist = new int[N][N];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                dist[i][j] =-1;
            }
        }
    }
    static void input() {
        N = sc.nextInt();
        graph = new int[N][N];
        initialdist();

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                graph[i][j] = sc.nextInt();
                if (graph[i][j]==9){
                    pos[0] = i;
                    pos[1] = j;
                    graph[i][j]=0;
                }
            }
        }
    }
    static void pro() {
//        System.out.println(Arrays.deepToString(graph));
        //1. 먹이를 찾는다.
        while (true){

            initialdist();
            ArrayList<Integer> food = seekFood(pos[0], pos[1]);
//            System.out.println(Collections.unmodifiableList(food));
            if (food.size()==0) break;
            //2. 그곳으로 이동
            pos[0] = food.get(0); pos[1] = food.get(1);
            //3. 먹은 물고기와 크기 갱신, dist에 걸린 시간도 갱신
            bite++;
            if (bite >= size){
                bite -= size;
                size++;
            }

            graph[pos[0]][pos[1]]=0; //물고기 삭제 ** 주의
            ans += dist[pos[0]][pos[1]];
        }
        System.out.println(ans);
    }

    private static ArrayList<Integer> seekFood(int posx, int posy) {
        ArrayList<Integer> food = new ArrayList<>();
        int [] dx = {-1,1,0,0};
        int [] dy = {0,0,-1,1};
        int minDist = Integer.MAX_VALUE;
        //가장 거리가 가까운 물고기를 찾는다.
        Queue<Integer> queue = new LinkedList<>();
        queue.add(posx); queue.add(posy);
        dist[posx][posy] =0;

        while (!queue.isEmpty()){

            int x = queue.poll();
            int y = queue.poll();

//            System.out.println(" bfs 현재 위치 "+ x +" , "+y);

            for (int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny <0 || nx >=N || ny >= N) continue;
                if (dist[nx][ny] !=  -1) continue; //방문 하지않은곳, dist의 값이 0인 곳만 갈 수 있다.
                if (graph[nx][ny] > size) continue;

                //여기까지 통과하면, 방문한 적 없으며, 물고기가 없거나, 물고기가 사이즈보다 작거나 같은 것.
                // 이동
                dist[nx][ny] = dist[x][y] + 1;

                if (graph[nx][ny] < size && graph[nx][ny] != 0){ //먹을 수 있다면
//                    System.out.println("먹을 수 있다"+ nx + " , "+ ny);
                    if (minDist >= dist[nx][ny]){ // 현재 최단거리보다 작거나 같다면
                        if (minDist == dist[nx][ny]){ //거리가 같다면
                            if (nx == food.get(0)){ // 높이가 같다면
                                if ( ny < food.get(1)){
                                    food.clear();
                                    food.add(nx); food.add(ny);
                                }

                            }else{
                                //높이가 높은 걸로 갱신
                                if (nx < food.get(0)){
                                    food.clear();
                                    food.add(nx); food.add(ny);
                                }
                            }

                        }else { //거리가 같지 않다면 => 작다면
                            food.clear();
                            food.add(nx); food.add(ny);
                            minDist = dist[nx][ny];
                        }

                    }else { //minDist와 같지 않다면


                    }
                }//if 먹을 수 있는 지
                else{
                    //먹을 수 없다면,
                    queue.add(nx); queue.add(ny);
                }

            }
        }
        return food;
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
