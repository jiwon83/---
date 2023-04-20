class Solution {
    Set<String> possible = new HashSet<>();
    boolean [][] visit;
    int [] dx ={-1,1,0,0,}, dy={0,0,-1,1};
    int R, C;
    
    public void dfs(int x, int y, String str, char[][] board){
        boolean cango = false;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >=R || ny >=C) continue;
            if(visit[nx][ny]) continue;
            cango = true;
            visit[nx][ny] = true;
            dfs(nx, ny, str+board[nx][ny], board);
            visit[nx][ny] = false;
        }
        
        if(!cango){
            possible.add(str);
        }
        
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        R=board.length;
        C= board[0].length;
        visit = new boolean[R][C];
        
        //1. 모든 시작점으로 시작해서 모든 단어를 만든다. 
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                visit[i][j] = true;
                dfs(i,j,board[i][j]+"",board);
                visit[i][j] = false;
            }
        }
        System.out.println(Collections.unmodifiableSet(possible));
        //2. words를 순회하며 set에서 순회하면서 contains에 있다면 answer에 담는다. 
        for(String word: words){
            for(String pos : possible){
                if(pos.contains(word)){
                    ans.add(word);
                    break;
                }
            }
        }
        return ans;
        
    }
}