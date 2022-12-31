package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759_암호만들기2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int L,C;
    static String [] A;
    static String [] select;

    static void input() {
        L = sc.nextInt();
        C = sc.nextInt();

        A = new String[C+1];
        select = new String[L+1];

        for (int i=1; i<=C; i++){
            A[i] = sc.next();
        }

    }
    static void recur(int k, int begin){
        if (k == L+1){ //다 뽑았다면

            if (isOkCondition2(select)){
                for (int i=1; i<=L ; i++){
                    sb.append(select[i]);
                }
                sb.append("\n");
            }
        }else{
            for (int cand = begin; cand<=C; cand++){
                select[k]= A[cand];
                recur(k+1,cand+1);
                select[k] = "";

            }
        }
    }

    private static boolean isOkCondition2(String[] select) {
        String mo1 = "aeiou";
        int moCnt=0;
        for (int i=1; i<select.length; i++){

            if (mo1.contains(select[i])){
                moCnt++;
            }

        }
        if ( moCnt>0 && select.length - moCnt -1 >= 2){
//            System.out.println(Arrays.toString(select));
//            System.out.println("모음 수 > "+ moCnt);
//            System.out.println("자음 수 > "+  (select.length - moCnt) );
            return true;
        }else {
            return false;
        }
    }

    static void pro() {
        //0. 정렬
        Arrays.sort(A,1,A.length);
        //1. 조합생성 -> 2 조건 부합여부 확인
        recur(1,1);
        //2. 출력
        System.out.println(sb);
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

