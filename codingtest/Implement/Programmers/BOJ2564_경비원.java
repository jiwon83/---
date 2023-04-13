import java.util.*;
import java.io.*;

public class Main3{
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int W, H,N, MAX;
    static int me;
    static int [] stores; //상점의 번호는 i+1;

    static void input() {
        /**
         * 10 5
         * 3
         * 1 4
         * 3 2
         * 2 8
         * 2 3
         */
        W = sc.nextInt();
        H = sc.nextInt();
        N = sc.nextInt();
        MAX = W * 2+ H *2;
        stores = new int[N];
        // 1 4 2 3 북 동 남 서
        for (int i=0; i<N; i++){
            stores[i] = getLinePos(sc.nextInt(), sc.nextInt());
        }
        me = getLinePos(sc.nextInt(), sc.nextInt());
//        System.out.println(Arrays.toString(stores) + me);
    }

    static int getLinePos(int dir, int pos){
        switch (dir){
            case 1:
                return pos;
            case 4:
                return W + pos;
            case 2:
                return W + H + (W-pos);
            case 3:
                return 2*W + H + (H-pos);

        }
        return -1;
    }
    static int getMinDist(int me, int pos, int max){
        int inClock = Math.abs(me - pos);
        int unClock = me >= pos ? max-me + pos : max - pos + me;
        return Math.min(inClock, unClock);
    }
    static void pro() {
        int min_sum = 0;
        for (int i : stores){
            min_sum += getMinDist(me, i, MAX);
            System.out.println((i+1)+"번째 "+ getMinDist(me, i, MAX));
        }
        System.out.println(min_sum);
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
