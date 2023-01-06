package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2290_LCDTEST {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int s;
    static String N;
    static int [][] chart = new int[10][8]; //[숫자][구역 여부]


    static void input() {
        s = sc.nextInt();
        N = sc.next();
    }
    static void preprocess(){
        chart = new int[][]{{0, 1, 1, 1, 0, 1, 1, 1},//0
                {0, 0, 0, 1, 0, 0, 1, 0},//1
                {0, 1, 0, 1, 1, 1, 0, 1},
                {0, 1, 0, 1, 1, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 0}, //4
                {0, 1, 1, 0, 1, 0, 1, 1}, //5
                {0, 1, 1, 0, 1, 1, 1, 1}, //6
                {0, 1, 0, 1, 0, 0, 1, 0}, //7
                {0, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 0, 1, 1}};//9
    }
    static void pro() {
        //1. 전처리 표를 만들고 1~7 구역으로 나눠서 존재 여부를 표시
        preprocess();
        // 5 줄로 나눠서 한 줄 씩 완성한다.
        //2. n의 숫자 하나씩 순회
        for (int i=0; i<N.length(); i++){
            makePrintNum(N.charAt(i)+"", 1);
            //글자마다 숫자 사이에 공백
            sb.append(" ");
        }
        sb.append("\n");
        for (int j=1; j<=s; j++){
            for (int i=0; i<N.length(); i++){ //숫자마다
                makePrintNum(N.charAt(i)+"", 2);
                //글자마다 숫자 사이에 공백
                sb.append(" ");
            }
            sb.append("\n");
        }
        for (int i=0; i<N.length(); i++){ //숫자마다
            makePrintNum(N.charAt(i)+"", 3);
            //글자마다 숫자 사이에 공백
            sb.append(" ");
        }
        sb.append("\n");
        for (int j=1; j<=s; j++){
            for (int i=0; i<N.length(); i++){ //숫자마다
                makePrintNum(N.charAt(i)+"", 4);
                //글자마다 숫자 사이에 공백
                sb.append(" ");
            }
            sb.append("\n");
        }
        for (int i=0; i<N.length(); i++){ //숫자마다
            makePrintNum(N.charAt(i)+"", 5);
            //글자마다 숫자 사이에 공백
            sb.append(" ");
        }
        sb.append("\n");

        System.out.println(sb);

    }

    private static void makePrintNum(String str, int row) {
        int num = Integer.parseInt(str);
        switch (row){
            case 1:
                sb.append(" ");
                String part = " ";
                if (chart[num][1]==1){
                    part = "-";
                }

                for (int i=1; i<= s; i++){
                    sb.append(part);
                }
                sb.append(" ");
                break;

            case 2:
                String part2=" ", part3=" ";
                if (chart[num][2]==1){
                    part2 = "|";
                }
                sb.append(part2);
                for (int i=1; i<= s; i++){
                    sb.append(" ");
                }
                if (chart[num][3]==1){
                    part3 = "|";
                }
                sb.append(part3);
                break;

            case 3:
                sb.append(" ");
                String part4 = " ";
                if (chart[num][4]==1){
                    part4 = "-";
                }
                for (int i=1; i<= s; i++){
                    sb.append(part4);
                }
                sb.append(" ");
                break;
            case 4:
                String part5=" ", part6=" ";
                if (chart[num][5]==1){
                    part5 = "|";
                }
                sb.append(part5);
                for (int i=1; i<= s; i++){
                    sb.append(" ");
                }
                if (chart[num][6]==1){
                    part6 = "|";
                }
                sb.append(part6);
                break;
            case 5:
                sb.append(" ");
                String part7 = " ";
                if (chart[num][7]==1){
                    part7 = "-";
                }
                for (int i=1; i<= s; i++){
                    sb.append(part7);
                }
                sb.append(" ");
                break;

        }

    }

    public static void main(String[] args) {
        input();
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



