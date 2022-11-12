import java.io.*;
import java.util.*;

public class Beer3 {
    ArrayList<Point> a;
    ArrayList<ArrayList<Integer>> graph; //그래프는 굳이 x,y좌표로 갖을 필요 없음. a 배열에서 조건을 허용하는 것만 인덱스로 사용
    boolean [] visit;
    StringBuilder sb = new StringBuilder();
    FastReader sc = new FastReader();
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;

    void input(){
        //N input
        N = sc.nextInt();
        //a, graph 초기화
        a = new ArrayList<>();
        graph = new ArrayList<>();
        visit= new boolean[N+2];
        //a에 모든 Point를 담는다.
        for (int i=0; i< N+2; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            a.add( new Point(x,y));

            graph.add(new ArrayList<Integer>());// graph의 하위 리스트 초기화.
        }
        //graph에 맨허튼 조건을 만족하면 양방향 연결한다.
        for (int i =0; i< a.size(); i++){
            for (int j =i+1; j<a.size(); j++){ //입력의 좌표가 순서대로 입력되지 않을 경우를 대비하여 다음 모든 좌표를 계산.
                if (Math.abs( a.get(i).x- a.get(j).x ) + Math.abs( a.get(i).y- a.get(j).y )<= 1000){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }



    }
    boolean bfs(int start){
        // N+1 이되면 true를 return
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visit[start]=true;

        while (!q.isEmpty()){
            int now = q.poll();
            visit[now]=true;

            if (now==N+1){
                return true;
            }
            for (int next: graph.get(now)){

                if (!visit[next]){
                    q.offer(next);
                }

            }
        }
        return false;
    }

    void pro() throws IOException {
        int t = sc.nextInt();
        while (t-- >0){
            input();
            sb.append( (bfs(0) ? "happy" : "sad") + '\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        ;

    }
    public static void main(String[] args) throws IOException {
//        int [] test = {1,2,3,4};
//        for (int next: test){
//            System.out.println("next=>"+next);
//
//        }
        Beer3 b = new Beer3();
        b.pro();

    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
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

    }
}
