
import java.util.*;
import java.io.*;


class Solution
{
    static Set<Integer> [] graph;
    static int N, k, ans;
    static boolean [] visit;
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
	public static void main(String args[]) throws Exception
	{
		int T;
		T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            sb.append("#"+test_case+" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            ans = 0;
            graph = new HashSet[N+1];
            
            //그래프 입력
            for(int i=1; i<=N; i++) graph[i] = new HashSet<>();
            
            for(int i=1; i<=k; i++){
                String [] temp = br.readLine().split(" ");
                for(int j=1; j<temp.length-1; j++){
                 	graph[ Integer.parseInt(temp[j])  ].add( Integer.parseInt(temp[ j + 1 ]) );
                }
            }
            for(int i=1; i<=N; i++){
                 sb.append( graph[i].size() +" ");
            }
            for(int i=1; i<=N; i++){
                visit = new boolean[N+1];
                visit[i] = true;
             	dfs(i, 1);   
            }
            //ans 출력
            sb.append(ans+"\n");
		}
        System.out.println(sb);
	}
    static void dfs(int x, int cnt){
        ans = Math.max(ans, cnt);
        for(int i : graph[x]){
            if(visit[i] ) continue;
             //전달 O
            visit[i] = true;
            dfs( i, cnt+1);
            visit[i] = false;
        }       
    }
}