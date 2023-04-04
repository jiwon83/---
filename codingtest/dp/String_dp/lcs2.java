import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [][] dp;
    static String A,B;

    static boolean isInArea(int x , int y){
        return x >=1 && y >= 1 && x <= A.length() && y<=B.length();
    }
    static void backtracking(){
        Queue<int []> q = new LinkedList<>();
        q.add(new int [] {A.length(), B.length()});
        while(!q.isEmpty()){
            int [] now = q.poll();
//            System.out.println(Arrays.toString(now));
            int x = now[0]; int y = now[1];
            if (A.charAt(x-1)==B.charAt(y-1)){ //최장 공통 부분 수열의 일부
                sb.append(A.charAt(x-1));
                if (isInArea(x-1, y-1)){
                    q.add(new int[]{x-1, y-1});
                }

            } else{ //최장 공통 부분 수열의 일부는 아님, 경로 추적

                if (isInArea(x-1,y) && isInArea(x,y-1) && dp[x-1][y] != dp[x][y-1]){
                    int [] next = (dp[x-1][y]> dp[x][y-1])? new int[]{x-1, y} : new int[]{x, y-1} ;
                    q.add(next);
                }
                //만약 한 곳이 범위밖이라면
                else if (!isInArea(x-1,y) && isInArea(x,y-1)){
                    q.add(new int[]{x, y-1});
                }
                else if (isInArea(x-1,y) && !isInArea(x,y-1)){
                    q.add(new int[]{x-1, y});
                }
                else if (isInArea(x-1,y) && isInArea(x,y-1) && dp[x-1][y] == dp[x][y-1] ){
                    q.add(new int[]{x-1, y}); //그냥 위로 가겠다.
                }
            }
        }

    }
    static void input() {
        A = sc.nextLine();
        B = sc.nextLine();
        dp = new int[A.length()+1][B.length()+1];
    }
    static void pro() {
        for (int i=1; i<=A.length(); i++){
            for (int j=1; j<=B.length(); j++){
                if (A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        backtracking();
        System.out.println(dp[A.length()][B.length()]);
        System.out.println(sb.reverse());
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