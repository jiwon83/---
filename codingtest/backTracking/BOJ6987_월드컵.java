import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static final int MAX_MATCH_COUNT = 15;
    static int [][] graph;
    static int [][] maches = new int[15][2]; //1부터 사용.

    static void makeMatches(){

        for (int f = 0, idx =0; f <5; f++){
            for (int s = f+1; s<6; s++){
                maches[idx][0] = f;
                maches[idx++][1] = s;
            }
        }

    }
    static boolean makeGraph(String result){

        String [] input = result.split(" ");
        graph = new int[6][3];
        for (int i=0; i<6; i++){
            int sum=0;
            for (int j =0; j<3; j++){
                graph[i][j] = Integer.parseInt(input[i*3+j]);
                sum+= graph[i][j];
            }
            if(sum != 5) {

                return false;
            }
        }
        return true;

    }
    static boolean isPossible;
    static boolean recur(int matchIdx, int [][] graph){
        if (matchIdx == MAX_MATCH_COUNT){
//            System.out.println("모든 매치에 성공!");
            isPossible=true;
            return true;
        }else{
            int a = maches[matchIdx][0];
            int b = maches[matchIdx][1];
            //win - lose
            if ( graph [a][0] > 0 && graph[b][2] > 0){
                graph [a][0]--;
                graph[b][2]--;
                recur(matchIdx+1,graph);
                graph [a][0]++;
                graph[b][2]++;

            }
            //draw - draw
            if ( graph [a][1] > 0 && graph[b][1] > 0){
                graph [a][1]--;
                graph[b][1]--;
                recur(matchIdx+1,graph);
                graph [a][1]++;
                graph[b][1]++;
            }
            // lose - win
            if ( graph [a][2] > 0 && graph[b][0] > 0){
                graph [a][2]--;
                graph[b][0]--;
                recur(matchIdx+1,graph);
                graph [a][2]++;
                graph[b][0]++;
            }
            return false;
        }

    }
    static void pro() {
        /*
        0. 한 줄 씩 처리한다. + 초기화 필요
        0-1. graph를 받는다.
        1. 대진표를 짠다. int [][] matches;
        2. 대진표 1 index부터 승패를 예측하고 그래프를 수정한다. -> 해당 예측으로 recur()
        3. 더이상 win-lose, draw - draw, lose - win 모두 불가하면 return;
        4. 만약 matchCnt가 16라면 (모든 매치를 다 돈 것이므로 ) return true;
        5. 1 또는 0으로 결과 저장
         */
        makeMatches();
        for(int T=0; T<4; T++){

            isPossible=false;
            String result = sc.nextLine();
            if (makeGraph(result)){
                recur(0, graph);
                if(isPossible){
                    sb.append("1").append("\n");
                }else{
                    sb.append("0").append("\n");
                }
            }else{ //만약 총 갯수가 30이 아니라면

                sb.append("0").append("\n");
            }

        }
        System.out.println(sb);


    }
    public static void main(String[] args) {
        pro();
    }
    static class FastReader {
            BufferedReader br;
            StringTokenizer st;

            public FastReader() {
                br = new BufferedReader(new InputStreamReader(System.in));

            }
            String next(){
                while (st == null || !st.hasMoreTokens()){  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }

            int nextInt(){
                return Integer.parseInt(next());
            }
            long nextLong(){return Long.parseLong(next()); }

            double nextDouble(){return Double.parseDouble(next());}

            String nextLine(){
                String str ="";
                try {
                    str = br.readLine();

                }catch (IOException e){
                    e.printStackTrace();
                }
                return str;
            }
            void close() {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
}



