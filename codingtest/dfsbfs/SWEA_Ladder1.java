import java.util.*;
import java.io.*;

class Solution
{
    static int [][] map = new int[100][100];
    static StringBuilder sb = new StringBuilder();
    static int[] E;
	public static void main(String args[]) throws Exception
	{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int test_case = 1; test_case <= 10; test_case++)
        {
            map = new int[100][100];
            sb.append("#"+test_case+" ");

            int n = Integer.parseInt(br.readLine());
            ArrayList<Integer> starts = new ArrayList<>();

            int x=-1;

            for(int i=0; i<100; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<100; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if( map[i][j]==2) E = new int[]{i,j};
                }
            }
            for(int i=0; i<100; i++){
                if(map[0][i] ==1){
                    dfs(0, i, i);
                }
            }
        }
        System.out.println(sb);
	}//main
    static int [][] dir = new int[][]{ {0,-1},{0,1},{1,0} };
    
    static void dfs(int x , int y, int start){
       if(x == E[0] && y== E[1]){
           sb.append(start+ "\n");
       }else{
           for(int i=0; i<3; i++){
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
              
                if(ny <0 || nx >=100 || ny >=100) continue;
                if( map[nx][ny] != 1 &&  map[nx][ny] != 2 ) continue;
                map[nx][ny] = -1;
 				dfs(nx, ny, start);
                map[nx][ny] = 1;
                break;
            }
       }//else
    }

}