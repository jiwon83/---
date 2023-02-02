import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N = board.length;
        Stack<Integer> stack = new Stack<>();
        
        for( int k=0; k<moves.length; k++){
            //1. 뽑는다.
            int row = moves[k]-1;
            int item=-1;
            for(int c=0; c<N; c++){
                if(board[c][row] != 0){
                    item = board[c][row];
                    board[c][row]=0;
                    break;
                }
            }
            //2. 지운다.
            //3. 스택에 넣는다. 
            if(item != -1 && !stack.isEmpty() && stack.peek()==item){
                stack.pop();
                answer += 2;
            }else{
                stack.push(item);
            }
            
        }
        return answer;
    }
}