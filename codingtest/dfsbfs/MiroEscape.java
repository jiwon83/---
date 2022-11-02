import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
이것이 코딩테스트다 미로찾기
시도: X

 */
public class MiroEscape {
    public static String [] graph;
    public static boolean [][] visit;
    public static int cnt,N,M;
    public static int [][] dist = {{-1,1,0,0},{0,0,-1,1}};

    int isNear(int x,int y,int tx,int ty){ //가까울수록 적은 값을 반환
        return  Math.abs(tx-x)+Math.abs(ty-y);
    }

    void bfs(int x, int y){
        Queue<Integer> que = new LinkedList<>();
//        que.add(graph[x].charAt(y));
    }
    public static void input() throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new String[N];
        visit = new boolean[N][M];

        for(int i=0; i<N; i++) {
            graph[i]= br.readLine();
        }


    }
    public static void main(String [] args){

    }
}
