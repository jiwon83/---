/*
백준 실버 1 미로탐색
시도: X(결과 안나옴.), O
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MrioDetection {

    public int N,M;
    public int [][] graph= new int[100][100];
    public int [] dx= {-1,1,0,0};
    public int [] dy ={0,0,-1,1};

    public void bfs(int x, int y){
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x,y));

        while(!que.isEmpty()){
            Node node = que.poll();
            x = node.getX();
            y = node.getY();
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx <0 || ny<0 || nx >= N || ny>=M){
                    continue;
                }
                if (graph[nx][ny]==0){
                    continue;
                }
                if (graph[nx][ny] ==1){ //이 if문을 안써서 scan이 무한loop에 빠졌었다. 이건 꼭 써야 한다. 왜냐면 이미 갔던 곳은 1이아니라 2이상일 것이다. 그러면 이미 갔던 곳도 가고 무한루프로 빠진다.
                    graph[nx][ny]= graph[x][y]+1;
                    que.offer(new Node(nx, ny));
                }
//                graph[nx][ny]= graph[x][y]+1;
//                que.offer(new Node(nx, ny));
            }
        }

    }
    void input2(){
        Scanner sc = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력 받기
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine(); // 버퍼 지우기

        // 2차원 리스트의 맵 정보 입력 받기
        for (int i = 0; i < N; i++) {
            String str = sc.nextLine();//얘가 계속 되고 있음.
            for (int j = 0; j < M; j++) {
                graph[i][j] = str.charAt(j) - '0';

            }
        }
    }

//    void input() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        for (int i=0; i<N; i++){
////            String str = br.readLine();
////            System.out.println(str);
//            graph[i] = br.readLine();
////            for (int j=0; j<M;j++){
////                graph[i][j]= str.charAt(j)-'0';
////                System.out.println("스캐너반복"+i+":"+j);
////            }
////            for (int j=0; j<M;j++){
//////                graph[i][j] = Integer.parseInt(str.trim());
////                System.out.println("스캐너반복"+i+":"+j);
////            }
//
//        }
//    }

    void solve() throws IOException {
        this.input2();
        bfs(0,0);
        System.out.println(graph[N-1][M-1]);
    }

    public static void main(String [] args) throws IOException {
        MrioDetection md = new MrioDetection();
        md.solve();
    }
}
class Node{
    int x;
    int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
