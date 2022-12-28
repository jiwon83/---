package bruteforce;

import java.io.*;
import java.util.*;

public class NQueen {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, res;
    static int[] Qcol;

    static void input() {
        N = sc.nextInt();
        Qcol = new int[N];
    }
    static void recur(int row) {
        if (row == N) { //퀸을 다 놓았다면,
            res++;
        }
        //row에서 모든 열을 순회하며 Q를 넣을 수 있는 지 확인
        for (int col = 0; col < N; col++) {

            // 이전의 모든 queen들과 확인하면서 퀸을 놓을 수 있다면, 퀸을 놓는다.
            boolean putable=true;
            for (int q = 0; q <= row - 1; q++) {
                int qCol = Qcol[q]; //이전에 놓았던 퀸의 열 좌표
                if (attackable(q, qCol, row, col)){
                    putable = false;
                    break;
                }
            }
            if (putable){
                Qcol[row] = col;
                recur(row+1);
            }
        }
    }

    private static boolean attackable(int qRow, int qCol, int row, int col) {
        //1. col이 같으면 true
        if ( qCol == col ) return true;
        //2. row - col이 같으면 true
        if (qRow-qCol == row - col) return true;
        //3. col + row이 같으면 true
        if (qRow + qCol == row + col) return true;
        return false;
    }

    public static void main(String[] args) {
        input();
        recur(0);
        System.out.println(res);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();

            } catch (IOException e) {
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

