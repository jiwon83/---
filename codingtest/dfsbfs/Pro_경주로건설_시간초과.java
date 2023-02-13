class Solution {
    int [][] Board;
    boolean [][] visit;
    int N;
    int ans = Integer.MAX_VALUE;
    int [] dx = {0,0,-1,1}, dy ={-1,1,0,0};
    int count =0;
    
    public void dfs(int x, int y, int move, int corner, int preDir){
        if(count++ <20) {
            // System.out.println("x = "+x +" y = "+y + " corner = "+corner);
        }
        if(x==N-1 && y ==N-1){
            int cost = move*100 + corner*500;
            if( count <20){
            // System.out.println("move = "+move +"corner = "+corner );
            }
            ans = Math.min( ans, cost );
            return;
        }
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || ny <0 || nx >=N|| ny >=N) continue;
            if( visit[nx][ny] || Board[nx][ny]==1 ) continue;
            
            visit[nx][ny]=true;
            if(preDir!=-1){
                if(preDir != i){
                    dfs(nx, ny, move+1, corner+1, i);
                    visit[nx][ny]=false;    
                }else{ //직선 도로
                dfs(nx, ny, move+1, corner, i);
                visit[nx][ny]=false;   
                
                }
            }else{
                dfs(nx, ny, move+1, corner, i);
                visit[nx][ny]=false;  
            }
            if(count <20) {
            // System.out.println("return nx = "+nx +"ny = "+ny );
            }
        }
        
    }
    public int solution(int[][] board) {
        int answer = 0;
        Board = board;
        N = board.length;
        visit = new boolean[N][N];
        visit[0][0]=true;
        
        dfs(0,0,0,0,-1);
        return ans;
    }
}