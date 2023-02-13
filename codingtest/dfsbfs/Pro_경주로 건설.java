import java.util.*;
class Solution {
    int [][][] price;
    int N;
    int [] dx = {-1,1,0,0};
    int [] dy ={0,0,-1,1};
    int [][] move = {{-1,0},{1,0},{0,-1},{0,1}};
    final int MAX = Integer.MAX_VALUE;
    int ans = MAX;
    
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        price = new int [4][N][N];
        
        // 초기화
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int n=0; n<4; n++){
                    price[n][i][j] = MAX;
                }
            }
        } 
        // System.out.println(Arrays.deepToString(price));
        // 상 하 좌 우 0 1 2 3
        Queue<int []> q = new LinkedList<>();
        if(board[1][0]==0){
            price[1][1][0] = 100; q.add(new int[]{1,0,1});
        }
        if(board[0][1]==0){
            price[3][0][1] = 100;  q.add(new int[]{0,1,3});
        }
        
        while(!q.isEmpty()){
            
            int [] cur = q.poll();
            int x = cur[0]; int y = cur[1]; int dir = cur[2];
            //만약 최신 정보가 아니라면 스킵해도 될 듯?
            if(price[dir][x][y])
            //도착지점이라면 갱신
            if( x == N-1 && y ==N-1){
                ans = Math.min(ans, price[dir][x][y]);
            }
            //다음 경주로를 탐색
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if( nx <0 || ny <0 || nx >=N || ny >=N || board[nx][ny]==1) continue;
                //갈 수 있는 모든 방향으로 이동 (내가 왔던 방향과 반대, 위로 가는 것은 제외)
                
                
                //방향이 다르다면 600 같다면 100
                if( i != dir){
                    if( i + dir == 5 || i + dir == 1) continue;
                    if(price[i][nx][ny] < price[dir][x][y] + 600) continue; //이미 있는 값이 에상 값보다 작다면 
                    price[i][nx][ny] = price[dir][x][y] + 600;
                    q.add(new int []{nx,ny,i});
                    
                }else{
                    if(price[i][nx][ny] < price[dir][x][y] + 100  ) continue;//이미 값이 있는 곳
                    price[i][nx][ny] = price[dir][x][y] + 100;
                    q.add(new int []{nx,ny,i});
                }

            }
    
        }
        
        return ans;
    }
}