import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [][] result = new int[6][3];
    static int [][] matches = new int[15][2]; //ABCDEF => 0,1,2,3,4,5
    static boolean possible = false;
/*
5 0 0 3 0 2 2 0 3 0 0 5 4 0 1 1 0 4
4 1 0 3 0 2 4 1 0 1 1 3 0 0 5 1 1 3
5 0 0 4 0 1 2 2 1 2 0 3 1 0 4 0 0 5
5 0 0 3 1 1 2 1 2 2 0 3 0 0 5 1 0 4

1 0 0 3 0 2 2 0 3 0 0 5 4 0 1 1 0 4
1 1 0 3 0 2 4 1 0 1 1 3 0 0 5 1 1 3
5 2 0 4 0 1 2 2 1 2 0 3 1 0 4 0 0 5
5 0 0 3 1 1 2 1 2 2 0 3 0 0 5 2 0 4
 */
    static void makeMatches(){
        int idx =0;
        for (int fir=0; fir<5; fir++){
            for (int bac = fir+1; bac<6; bac++){
                matches[idx][0] = fir;
                matches[idx++][1] = bac;
            }
        }

    }

    static void input() {
        possible = false;
        boolean matchCountFine=true;
        result = new int[6][3]; //초기화

        for (int i=0; i<6; i++){
            int teamPerGame=0;
            for (int j=0; j<3; j++){
                result[i][j] = sc.nextInt();
                teamPerGame+= result[i][j];
            }
            if (teamPerGame != 5) matchCountFine=false;
        }
        if (!matchCountFine){
            sb.append(0).append(" ");
            return;
        }
        recur(0,result);
        sb.append(possible ? 1 : 0).append(" ");

    }
    static void recur(int k, int [][] result){

        if (k==15){
            possible=true;

        }else{
            //경기를 예측
            int our = matches[k][0];
            int other = matches[k][1];
            //1. 승- 패
            if (result[our][0] > 0 && result[other][2] > 0){
                result[our][0]--;
                result[other][2]--;
                recur(k+1, result);
                result[our][0]++;
                result[other][2]++;
            }

            //2. 패 - 승
            if (result[our][2] > 0 && result[other][0] > 0){
                result[our][2]--;
                result[other][0]--;
                recur(k+1, result);
                result[our][2]++;
                result[other][0]++;
            }
            //3. 무-무
            if (result[our][1] > 0 && result[other][1] > 0){
                result[our][1]--;
                result[other][1]--;
                recur(k+1, result);
                result[our][1]++;
                result[other][1]++;

            }
        }
    }

    public static void main(String[] args) {
        makeMatches();
        for (int i=0; i<4; i++){
            input();
        }
        System.out.println(sb);

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