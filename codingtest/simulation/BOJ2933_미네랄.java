import java.io.*;
import java.util.*;

public class Main{
    static class P{
        int x, y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int R, C, N;
    static char [][] graph;
    static int [] A; //막대들의 높이

    static void input() {
        R = sc.nextInt();
        C = sc.nextInt();
        graph = new char[R][C];
        for (int i=0; i<R; i++){
            String input = sc.nextLine();
            graph[i] = input.toCharArray();
        }
        N = sc.nextInt();
        A = new int[N];
        for (int i=0; i<N; i++){
            A[i]= sc.nextInt();
        }
//        for (int i=0; i<R; i++){
//            System.out.println(graph[i]);
//        }
    }
    static int[] throww (int ball, int direction){
        //1. 막대를 던진다. : 방향에 따라 해당 row에서 x를 찾으면 삭제. ** 뒤집어서 생각해야함 R - A[]를 하면 됨.
        int row = R - ball;

        if (direction%2==0){ //0이면 왼쪽에서,
            for (int col = 0; col< C; col++){
                if (graph[row][col]=='x'){
                    graph[row][col]='.';
                    return new int[]{row, col};
                }
            }
        }
        else { // 1이면 오른쪽에서.
            for (int col = C-1; col>=0; col--){
//                System.out.println("막대 던지는 중>> "+ col);
                if (graph[row][col]=='x'){
                    graph[row][col]='.';
                    return new int[]{row, col};
                }
            }

        }
        return null;
    }
    static ArrayList<P> above_cluster;
    static int [] dx = {1,-1,0,0}, dy={0,0,-1,1};
    static boolean isAbove(int x, int y){
//        System.out.println("isAbove "+ x+" "+y);
        //시작점을 상 하 좌 우 로 준다. bfs
        //클러스터들을 ArrayList에 따로 저장한다.
        above_cluster = new ArrayList<>();
        Queue<P> q = new LinkedList<>();
        boolean visit[][] = new boolean[R][C];
        q.add(new P(x, y));
        visit[x][y]=true;
        above_cluster.add(new P(x, y));

        while (!q.isEmpty()){
            //만약 땅에 닿으면 return false.
            P node = q.poll();
            for (int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || ny <0 || nx >=R || ny >=C) continue;
                if (visit[nx][ny]) continue;
                if (nx == R-1) return false; //땅에 닿으면
                if (graph[nx][ny] == '.') continue;
                above_cluster.add(new P(nx, ny));
                q.add(new P(nx, ny));
                visit[nx][ny]=true;
            }
        }
        return true; //땅에 닿지 않았다면
    }
//    static boolean checkCanDrop(){
//
//    }
    static void drop(){
        //ArrayList들을 row순서대로 정렬하고, 순회하면서 떨어진 칸 정도를 찾고 내려준다.
//        System.out.println(Collections.unmodifiableList(above_cluster));

//        for (int i=0; i<above_cluster.size(); i++){
//            //하나씩 graph row랑 비교해서(아래방향으로) 최소 떨어진 칸 크기를 갱신한다.
//            int count =0;
//            P stand = above_cluster.get(i);
//            for (int j = stand.x+1; j< R; j++){ //6 ~ <8
//                if (graph[j][stand.y]=='x') break;
//                count++;
//
//            }
//            above_dist = Math.min(above_dist, count);
//            System.out.println("above_dist: "+ above_dist);
//
//        }
        //이제 내려준다.
        for (int i=0; i<above_cluster.size(); i++){
            P node = above_cluster.get(i);
            graph[node.x][node.y] ='.'; //먼저 다 지워주고,
        }
        int above_dist=1;
        OUTER: for (int up=1; up<R-1; up++){ //클러스터들과 바닥 또는 다른 클러스터들과 부딪히지 않는 거리.
            for ( P p : above_cluster){
                if ( p.x + up >= R  || graph[p.x + up][p.y]=='x'){ //바닥 보다 아래이거나, 다른 클러스터와 겹쳐지거나
                    above_dist= up-1;
                    break OUTER;
                }
            }

        }

        for (int i=0; i<above_cluster.size(); i++){
            P node = above_cluster.get(i);
            graph[ node.x + above_dist ][ node.y] ='x'; //내려서 채워주기
        }



    }
    static void pro() {
        for (int i =0; i< N; i++){ // 막대의 수만큼 만복

            //1. 막대를 던진다. : 방향에 따라 해당 row에서 x를 찾으면 삭제. ** 뒤집어서 생각해야함 R - A[]를 하면 됨.
            int [] removed = throww(A[i], i);
            System.out.println(Arrays.toString(removed));
            System.out.println(" 막대를 던졌다. "+A[i]+" "+i);
            for (int w = 0; w< graph.length; w++){
                System.out.println(graph[w]);
            }

            if (removed !=null && removed.length ==2){ // 삭제된게 있다면,

                //2. 클러스터가 분리 됐는 지 확인(공중에 떠있는지) : 상 좌 우 로 탐색해서 땅으로 이어져있지 않다면 true를 return.
                for (int j=0; j<4; j++){
                    int nx = removed[0] + dx[j];
                    int ny = removed[1] + dy[j];
                    if (nx < 0 || ny <0 || nx >=R || ny >=C) continue;
                    if (graph[nx][ny]=='x'){
                        if (isAbove(nx, ny)){
//                            System.out.println(Collections.unmodifiableList(above_cluster));
                            drop(); //3. true라면 떨어뜨린다. : row로 순회하면서 가장 땅 또는 클러스터와 가까운 지점의 떨어진 칸 수 를 찾고, 모든 공중 클러스터에서 row를 칸 만큼 -.

                            System.out.println("떨어트렸다. ");
                            for (int w = 0; w< graph.length; w++){
                                System.out.println(graph[w]);
                            }

                            break; //문제에서  두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우도 없다.라고 했으므로, 하나만 찾으면 된다.

                        }

                    }

                }

            }

        }
        for (int w = 0; w< graph.length; w++) {
            System.out.println(graph[w]);
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




