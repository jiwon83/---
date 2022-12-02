package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

/*
골드4 https://www.acmicpc.net/problem/1987

시도: X(시간초과) , X, O
 */
public class Alphabet {
    static FastReader sc = new FastReader();
    static int ans = Integer.MIN_VALUE;
    static int R, C; //R = 세로, C = 가로
    static String[][] graph;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};//오 왼 아래 위
    static int[] dy = {0, 0, -1, 1};
    static boolean[] alpha = new boolean[26]; //index => alpha


    static void input() {

        R = sc.nextInt();
        C = sc.nextInt();
        graph = new String[R + 1][C + 1];
        visit = new boolean[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            String row = sc.next(); //**주의
            for (int j = 1; j <= C; j++) {
                graph[i][j] = Character.toString(row.charAt(j - 1));
            }
        }
    }

    static void dfs(int x, int y, int level) { //level은 dfs의 재귀 단계 즉, 얼마나 이동 했는지

        visit[x][y] = true;
        int al = graph[x][y].charAt(0) - 'A'; //알파벳을 index로 변환한것
        alpha[al] = true;

        boolean isFinished = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 1 && ny >= 1 && nx <= R && ny <= C) {
                if (!visit[nx][ny]) {

                    int idxAlpha = graph[nx][ny].charAt(0) - 'A'; //알파벳을 index로 변환한것
                    if (!alpha[ idxAlpha ]) {
                        isFinished = false;
                        dfs(nx, ny, level + 1);
                        //dfs 후 처리
                        visit[nx][ny] = false;
                        alpha[idxAlpha] = false;

                    }
                }
            }
            if (isFinished) { //범위 내에 있지만 더 이상 갈 곳이 없다 => 모든 값을 다 마치고 범위내에 없을 때도
                ans = Math.max(ans, level);
            }
        }//for

    }//dfs

    static void pro() {
        input();
        dfs(1, 1, 1);
        System.out.println(ans);

    }

    public static void main(String[] args) {
        pro();
        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
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
