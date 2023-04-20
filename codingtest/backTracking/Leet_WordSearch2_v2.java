class Solution {
    class TrieNode{
        HashMap<Character, TrieNode> map;
        boolean isWord;
        String word;
        public TrieNode(){
            map = new HashMap<>();
            this.isWord = false;
        }
    }

    int R, C;
    boolean [][] visit;
    char[][] board;
    Set<String> ans = new HashSet<>();
    TrieNode head = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {
        R = board.length;
        C = board[0].length;
        visit = new boolean[R][C];
        this.board = board;
        
        for(String word: words){ //1. 단어를 트리에 넣는다. 
            insertWord(word);
        }

        for(int r=0; r<R; r++){ //2.board 탐색
            for(int c=0; c<C; c++){
                if(head.map.containsKey( board[r][c] )){
                    dfs(r, c, head);
                    // visit[r][c] = true;
                    // dfs(r, c, head.map.get(board[r][c]), board[r][c]+"");
                    // visit[r][c] = false;
                }
            }
        }
        return new ArrayList<>(ans);
    }
    
    private void insertWord(String word){
        TrieNode curNode = head;
        for(char c : word.toCharArray()){
            if(!curNode.map.containsKey(c)){ //존재하지 않는다면,
                curNode.map.put(c, new TrieNode());
                curNode = curNode.map.get(c);
            }else{
                curNode = curNode.map.get(c);
            }
        }
        curNode.word = word;
        curNode.isWord = true;
    }
    
    int [] dx = {-1,1,0,0}, dy={0,0,-1,1};
    public void dfs(int x, int y, TrieNode node){
        
        if(x < 0 || y < 0 || x >= R || y >= C) return;
        if(visit[x][y]) return;
        if(!node.map.containsKey(board[x][y])) return;
        
        visit[x][y] = true;
        // sb.append(board[x][y]);
        TrieNode curNode = node.map.get(board[x][y]);
        if(curNode.isWord){
            ans.add(curNode.word);
        }
        
        dfs(x-1, y, curNode);  
        dfs(x+1, y, curNode);
        dfs(x, y-1, curNode);
        dfs(x, y+1, curNode);

        // sb.setLength(sb.length()-1); // 같은 메모리를 사용하기 때문에
        visit[x][y] = false; //같은 가지에 있는 단어들을 방문할 수 있도록
        
        
        
        // for(int i=0; i<4; i++){
        //     int nx = x + dx[i]; int ny = y + dy[i];
        //     if(nx < 0 || ny < 0 || nx >= R || ny >= C || visit[nx][ny]) continue;
        //     if(node.map.containsKey(board[nx][ny])){
        //         visit[nx][ny] = true;
        //         dfs(nx, ny, node.map.get( board[nx][ny] ), str+board[nx][ny]);
        //         visit[nx][ny] = false;
        //     }
        // }

  
    }
}