import java.util.*;
import java.io.*;
//swea 1486. 장훈이의 높은 선반
class Solution
{
    static Set<Integer> [] dp;
    static int [] arr;
    static int N, B, res;
    static boolean[] visit;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String args[]) throws Exception
    {

        int T;
        T=Integer.parseInt(br.readLine());
         for(int test_case = 1; test_case <= T; test_case++)
        {

            StringTokenizer st = new StringTokenizer(br.readLine());
            //1. 총합으 구한다.
            N = Integer.parseInt(st.nextToken());
            B =  Integer.parseInt(st.nextToken());
            arr = new int[N];
            visit = new boolean[N];
            dp = new HashSet[N];
            for(int i=0; i<N; i++) dp[i] = new HashSet<>();
            int sum=0;
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                sum +=arr[i];
            }
            res=sum-B;

            //2. dfs로 방문하지 않았던 곳을 방문
            dfs(0,sum ,"");
            System.out.printf("#%d %d%n", test_case, res);

        }
    }
    static void dfs(int k, int h, String route){
        System.out.println("k= "+k +"현재 h= "+ h+" , route "+route);
        if( h < B || k==N) return; //모든 탑을 선택 해제 했거나, B보다 이미 작다면 return
        res = Math.min(res, h - B); //최솟값으로 계속 갱신
        for(int i=0; i<N; i++){
            if(visit[i]) continue; //이전에 선택 해제했던 탑을 해제할 수는 없다.
            if(dp[k].contains(h-arr[i])) continue; //현재 같은 제거 level에서 이미 같은 값이 나왔다면 구할 필요 X
            dp[k].add(h-arr[i]);
            visit[i] = true;
            dfs(k+1,h-arr[i], route+" " +arr[i]);
            visit[i]=false;
        }
    }
}