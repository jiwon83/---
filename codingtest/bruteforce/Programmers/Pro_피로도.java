import java.util.*;
class Solution {

    public int K,N, Ans;
    public int [] select;
    public int [][] Dungeons;
    public boolean [] tf;
    
    public int start(int [] select, int K){ //K = 체력
        int cnt=0;
        //이 순서대로 던전을 탐험!
        for(int i=0; i<select.length; i++){
            int order = select[i];
            
           if( K >= Dungeons[order][0]){
               K -= Dungeons[order][1];
               cnt++;
               
           }else{
               return cnt;
           } 
        }
        return cnt;
        
    }//strat
    public void recur(int k){
        if(k == N){
            // System.out.println(Arrays.toString(select));
            Ans = Math.max(Ans, start(select, K));
        }else{
            for(int cand = 0; cand < N; cand++){
                if(!tf[cand]){
                    tf[cand] = true;
                    select[k] = cand;
                    recur(k+1);
                    tf[cand] = false;
                }
            }
        }
    }
    public int solution(int k, int[][] dungeons) {
        K = k;
        Dungeons = dungeons;
        N = dungeons.length;
        tf = new boolean[N];
        select = new int [N];
        int answer = 0;
        
        //1. 순서를 순열로 구한다.
        recur(0);
        //2. 이 순서로 갈 수 있는 던전의 수를 갱신
        return Ans;
    }
}