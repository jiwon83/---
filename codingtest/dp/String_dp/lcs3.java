import java.util.*;
import java.io.*;
public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static String A, B, C;

    static void input() {
        A = sc.nextLine();
        B = sc.nextLine();
        C = sc.nextLine();
    }
    static int[][] getLCS(String A, String B){
        int [][] dp = new int[A.length()+1][B.length()+1];
        for (int i = 1; i<=A.length(); i++){
            for (int j=1; j<=B.length(); j++){
                if (A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp;
    }
    static Set<String> listLCS = new HashSet<>();
    static void backtrakingLCS(int x, int y, String a, String b, String str, int [][] LCS){ //표에 대해서 LCS를 찾는다.

        int [] left = x >=1 && y-1 >=1 && LCS[x][y-1] == LCS[x][y] ? new int[]{x,y-1} : null;
        int [] up = x-1 >=1 && y >=1 && LCS[x-1][y] == LCS[x][y] ? new int[]{x-1,y} : null;
        // 범위를 벗어났거나, 더이상 갈 수 있는 선택지가 없다면 종료
        boolean isFinish = true;
        if (a.charAt(x-1)==b.charAt(y-1)){ //같다면
            str = a.charAt(x-1) + str;
            if ( x-1 >=1 && y-1 >= 1){
                isFinish =false;
                backtrakingLCS(x-1, y-1, a, b, str, LCS);
            }

        }
        else if (left != null && up !=null){
            backtrakingLCS(x-1, y, a, b, str, LCS);
            backtrakingLCS(x, y-1, a, b, str, LCS);
            isFinish =false;
        }else if (left != null){
            backtrakingLCS(x, y-1, a, b, str, LCS);
            isFinish =false;
        } else if (up != null) {
            backtrakingLCS(x-1, y, a, b, str, LCS);
            isFinish =false;
        }
        if (isFinish){
            listLCS.add(str);
        }

    }
    static void pro() {
        int ans = Integer.MIN_VALUE;
        //1. 첫번째, 두번째 LCS를 구한다.
        int [][] LCS = getLCS(A, B);
        //2. 표의 LCS를 구한다.
        backtrakingLCS(A.length(), B.length(), A, B, "", LCS);
        //3. 각 LCS마다 세번째 문자열과 LCS를 구한다.
//        System.out.println(Collections.unmodifiableSet(listLCS));
        for (String l : listLCS){
            int [][] LCS2 = getLCS(l, C);
            ans = Math.max(ans, LCS2[l.length()][C.length()]);
        }
        //4. 이때 가장 큰 LCS값을 갱신한다.
        System.out.println(ans);

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