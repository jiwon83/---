import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/*
12 10 4 7
2 4
7 3
3 1
5 6
4 7
12 10
8 6
 */

public class Main {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int [] lose, earn;
    static int N;
    static int [][] dp;

    /*
    3
1 21 79
20 30 25
     */
    public static void main(String[] args) throws IOException {


        N = Integer.parseInt(br.readLine());
        lose = new int[N+1];
        earn = new int[N+1];
        dp = new int[N+1][100]; //사실상 체력은 99까지 쓸 수 있다.

        st = new StringTokenizer(br.readLine());
        for (int i= 1; i<=N; i++){
            lose[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i= 1; i<=N; i++){
            earn[i] = Integer.parseInt(st.nextToken());
        }

        for (int k = 1; k<=99; k++){ //체력
            for (int n = 1; n<= N; n++){ //각각의 사람
                int nLose = lose[n];
                if( lose[n] > k ){ //n번째 사람에게 인사 시 소요체력이 주어진 체력보다 크다면
                     dp[n][k] = dp[n-1][k]; // 이전 사람까지의 최고 기쁨이 최대
                }else{
                    int select = dp[n-1][k-nLose] + earn[n]; // n번째 사람에게 인사했을 떄 에너지 (필요한 체력을 쓰고) +  이전까지의 나머지 체력에서의 에너지 최댓값
                    dp[n][k] = Math.max(select, dp[n-1][k]);
                }

            }
        }
        System.out.println(dp[N][99]);




    }

}
