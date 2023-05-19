import java.util.*;
import java.io.*;
class Main{
    static int [][] dp, map;
    static int N,M;
    static boolean [][] visit;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String [] args) throws Exception{
        input();
        System.out.println(dfs(0,0));
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp= new int[N][M];
        map= new int[N][M];
        visit = new boolean[N][M];
        for(int i=0; i<N; i++){
            String [] temp = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                map[i][j] =Integer.parseInt(temp[j]);
                dp[i][j]= -1; //-1로 방문 확인, 이 처리를 안한다면 목적지에 도착하지 않았던 경로를 반복할 수 있다. -> 시간 초과
            }
        }
    }
    static int [] dx = {0,0,-1,1}, dy={-1,1,0,0};
    static int dfs(int x, int y){
        if(x==N-1 && y==M-1) return 1;
        if(dp[x][y] != -1) return dp[x][y];
        dp[x][y] = 0;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx>=N || ny >=M || map[nx][ny] >= map[x][y]) continue;
            dp[x][y]+= dfs(nx,  ny);
        }
        return dp[x][y];
    }
}