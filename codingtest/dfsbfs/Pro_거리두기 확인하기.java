import java.util.*;
class Solution {
    boolean[][] visit;
    int [] dx ={-1,1,0,0}, dy={0,0,-1,1};
    
    /*
    [["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"],
    */
    public boolean bfs(int x, int y, String [] map){
        
        Queue<int []> q = new LinkedList<>();
        q.add(new int []{x,y,0}); //x, y , 거리 첫 기준 P의 위치
        visit[x][y] = true;
        
        while(!q.isEmpty()){
            int [] now = q.poll();

            if(now[2] >0 && map[now[0]].charAt( now[1])=='P' ) return false;
            if(now[2]==2) continue; //거리가 2라면 더이상 찾을 필요가 없다. 
            
            for(int i=0; i<4; i++){
                int nx = now[0] + dx[i]; int ny = now[1]+dy[i];
                //만약 파티션이면 다음 노드에 넣을 필요가 없음.
                if( nx <0 || ny <0 || nx >=5 || ny >=5) continue;
                if(visit[nx][ny]) continue;
                if( map[nx].charAt(ny)=='X') continue;
                
                if( map[nx].charAt(ny)=='P') { //only 성능 향상 
                    return false;
                }
                
                visit[nx][ny] =true;
                q.add(new int[]{nx,ny,now[2]+1});
         
            }
        }
        return true;
    }//bfs
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int idx=0;
        for(int t=0; t<5; t++){
            visit = new boolean[5][5];
            boolean isOk=true;
            Loop: for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    if(places[t][i].charAt(j)=='P' && !visit[i][j]){
                        if(!bfs(i,j, places[t])){
                            isOk=false;
                            break Loop;
                        }
                    }
                }
            }//Loop
            if(isOk) answer[idx++] = 1;
            else answer[idx++] = 0;

        }//t    
        return answer;
    }
}