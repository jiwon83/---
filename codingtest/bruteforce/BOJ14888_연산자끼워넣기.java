package bruteforce;

import java.io.*;
import java.util.StringTokenizer;
/*
 실버1 연산자 끼워넣기  https://www.acmicpc.net/problem/14888
 시도: X O O (시간단축 필요)
 */
public class BOJ14888_연산자끼워넣기 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, min, max;
    static int [] nums;
    static int [] selected;
    static int [] opers = new int[5];

    static void input() {
        N= sc.nextInt();
        nums = new int[N+1]; //1부터
        selected = new int[N]; //1부터
        for (int i=1; i<=N; i++){
            nums[i]= sc.nextInt();
        }
        for (int i=1; i<=4; i++){
            opers[i]= sc.nextInt();
        }

    }
    static void recur(int k){
        if (k == N){ //다뽑았다면
            complete(selected);
            return;
        }
        for (int cand=1; cand<=4; cand++){
            if (opers[cand] > 0){
                selected[k] = cand;
                opers[cand]--;
                recur(k+1);
                selected[k]=0;
                opers[cand]++;
            }
        }
    }

    private static void complete(int[] selected) {
        int val = nums[1];
        for (int i=1; i< selected.length; i++){
            val = cal(val, nums[i+1], selected[i]);

        }
        min = Math.min(min, val);
        max = Math.max(max, val);

    }

    private static int cal(int num1, int num2, int i) {
        int result =0;
        switch (i){
            case 1:
                result = num1 + num2;
                break;
            case 2:
                result = num1 - num2;
                break;
            case 3:
                result = num1 * num2;
                break;
            case 4:
                result = num1 / num2;
                break;
        }
        return result;
    }

    static void pro() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        recur(1);

        System.out.println(max);
        System.out.println(min);

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
