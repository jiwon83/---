import java.util.*;
class Solution {
    int[][] Board;
    int[][] Skill;
    int [][] filter;
    int N, M;
    
    public void appendFilter(int direct){
        if( direct == 0){ //아래로
            for( int c=0; c<M; c++){
                for( int r=0; r<N-1; r++){
                    int r2=r+1;
                    filter[r2][c] = filter[r2][c] + filter[r][c];
                }
            }
        }else{ //오른쪽으로
            for( int r=0; r<N; r++){
                for( int c=0; c<M-1; c++){
                    int c2=c+1;
                    filter[r][c2] = filter[r][c2] + filter[r][c];
                }
            }     
        }//else
      
    }
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        N = board.length;
        M = board[0].length;
        Board = board;
        Skill = skill;
        filter = new int[N][M];
        
        //1. skill에 따라서 board를 변형
        for(int s = 0; s< skill.length; s++){
            //[type, r1, c1, r2, c2, degree]
            int degree;
            if(skill[s][0]==1) degree = -skill[s][5];
            else degree = skill[s][5];
            
            int r1 = skill[s][1]; int c1 =  skill[s][2];
            int r2 = skill[s][3]; int c2 =  skill[s][4];
            
            //filter에 입력.
            filter[r1][c1] += degree;

            if(c2+1 < M){
                filter[r1][c2+1] += -degree;
            }
            if(r2+1 < N){
                filter[r2+1][c1] += -degree;
            }
            if(c2+1 < M && r2+1 <N){
                filter[r2+1][c2+1] += degree;
            }
            
           // for(int i=0; i<N; i++){
           //      System.out.println(Arrays.toString(filter[i]));
           //  }
           //  System.out.println("----------------");
  
        }
        appendFilter(0); //row 아래로 필터 확장
        appendFilter(1); //col 오른쪽으로 필터 확장
    
        
        //2. 최종적으로 내구도 1 이상의 갯수를 return.
     
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(board[i][j] + filter[i][j] > 0){
                    answer++;
                } 
            }
        }
        return answer;
    }
}