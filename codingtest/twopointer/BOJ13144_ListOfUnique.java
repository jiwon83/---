package twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13144_ListOfUnique {

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static int [] A;
    static int [] cnt =new int[100001];
    static long ans=0L;

    static void input() {
        N = sc.nextInt();
        A = new int[N+1];
        for (int i=1; i<=N; i++){
            A[i] = sc.nextInt();
        }
    }

    static void pro() {

        for (int L =1, R=0; L <=N; L++){
//            System.out.println("L="+L+" R="+ R);

            while ( R+1 <=N && cnt[A[R+1]] < 1){ //R을 옮겨도 괜찮은지 확인 다음 수의 cnt값이 1보다 작아야 한다.
                cnt[ A[++R] ]++;
//                System.out.println("L= "+L+" , R= "+R);

            }
            //ans 갱신
//            System.out.println( R - L +1+" 이 더해짐.");
            ans += R - L +1;
            //L 이동
            cnt[ A[L] ]--;
        }
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
