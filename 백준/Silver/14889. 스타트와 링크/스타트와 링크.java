import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
class Main
{
    static int [] select;
    static int ans, N;
    static int [][] S;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        //1. input
        input();
        //2. 팀 분배 조합 구하기
        combi(1);
        System.out.println(ans);
    }
    static void combi(int k){
        if(k == N/2 + 1){
//            System.out.println("combi complete > "+ Arrays.toString(select));
            //3. 능력치 합산
            getPowerEachTeam(select);
            return;
        }
        for (int i= select[k-1]+1; i < N; i++){
            select[k] = i;
            combi(k+1);
        }

    }
    static void getPowerEachTeam(int [] select){
        //3-1. 현재 select로 팀을 분배
        List<Integer> our = new ArrayList<>();
        List<Integer> other = new ArrayList<>();

        for (int i : select){
            if ( i == -1) continue;
            our.add(i);
        }
        for (int i=0 ;i < N; i++){
            if (!our.contains(i)) other.add(i);
        }
//        System.out.println("our  : " + Collections.unmodifiableList(our));
//        System.out.println("other  : " + Collections.unmodifiableList(other));

        //3-2. 팀의 점수 합산
        int gap = Math.abs( getScoreByTeam(our) - getScoreByTeam(other) );
//        System.out.println("gap > " +  gap );
        ans = Math.min(ans, gap);
    }
    static int getScoreByTeam(List<Integer> teamList){
        int score = 0;
        for (int i : teamList){
            for (int j : teamList){
                score += S[i][j];
            }
        }
//        System.out.println("score > " +  score );
        return score;
    }
    static void input() throws IOException{
        ans = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        select = new int[N/2 + 1];
        select[0] = -1;
        S = new int[N][N];

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }


    }
}
