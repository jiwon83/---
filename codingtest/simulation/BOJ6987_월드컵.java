
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [][] result;
    static int [][] matches;
    static boolean isPossible;


    static void input() {
        int tc = 4;
        Loop1: while(tc-- >0){
            //한 줄 씩 결과를 받는다.
            String [] temp = sc.nextLine().split(" ");
            result = new int[6][3];
            isPossible = false;

            for (int i=0; i<6; i++){

                int count =0;
                for (int j=0; j<3; j++){
                    result[i][j] = Integer.parseInt( temp[i*3+j] );
                    count += result[i][j];
                }
                if(count !=5){
                    sb.append("0").append(" ");
                    continue Loop1;
                }
            }
            recur(0,result);
//            System.out.println(Arrays.deepToString(result));
            if(isPossible) sb.append("1").append(" ");
            else sb.append("0").append(" ");


        }

    }

    private static void recur(int k, int[][] result) {
        if(k==15){ //모든 매치를 다 돌렸다면
            isPossible=true;
            return;
        }else{
            int me = matches[k][0];
            int other = matches[k][1];
            //승-패
            if( result[me][0] > 0 && result[other][2] > 0 ) {
                result[me][0]--;
                result[other][2]--;
                recur(k+1,result);
                result[me][0]++;
                result[other][2]++;
            }
            //패-승
            if( result[me][2] > 0 && result[other][0] > 0 ) {
                result[me][2]--;
                result[other][0]--;
                recur(k+1,result);
                result[me][2]++;
                result[other][0]++;

            }
            //무-무
            if( result[me][1] > 0 && result[other][1] > 0 ) {
                result[me][1]--;
                result[other][1]--;
                recur(k+1,result);
                result[me][1]++;
                result[other][1]++;

            }

        }
    }
    static void initMatches(int k){
        if (k==3){
            for(int i=1; i<3; i++){
                matches[matIdx][i-1] = select[i];

            }
            matIdx++;
            return;
        }
        for (int i=select[k-1]+1; i<6; i++){
            select[k]= i;
            initMatches(k+1);

        }
    }
    static int [] select= new int[3];
    static int matIdx=0;
    public static void main(String[] args) {
        select[0] =-1;
        matches = new int[15][2];

        initMatches(1);
        System.out.println(Arrays.deepToString(matches));
        input();

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
