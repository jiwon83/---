import java.util.*;
import java.io.*;
class Main {
    static int [][] dp, sum;
    static int [] arr ;
    static int N;
    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N+1][N+1];
        sum = new int[N+1][N+1];
    }
    static void pro(){

        //초기값
        for(int i=1; i<=N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        //초기값 길이 1
        for(int i=1; i<=N; i++){
            dp[i][i]= arr[i];

        }
        //초기값 길이 2
        for (int s=1; s+2-1 <= N; s++){
            dp[s][s+1] = dp[s][s]+dp[s+1][s+1];
            sum[s][s+1] =dp[s][s+1];
        }

        //dp 채우기
        for (int l=3; l<=N; l++){
            for (int s=1; s+l-1 <= N; s++){
                int e = s+l-1;

                for (int sp = s; sp < e; sp++){
                      dp[s][e] = Math.min(dp[s][e], dp[s][sp]+ sum[s][sp] + dp[sp+1][e]+ sum[sp+1][e]);
                      sum[s][e] =  dp[s][e];

                }
            }
        }
        for (int i=1; i<=N; i++) System.out.println(Arrays.toString(dp[i]));
        System.out.println(dp[1][N]);
    }
    public static void main(String[] args) throws IOException{
        int T= Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            input();
            pro();
        }
    }
}