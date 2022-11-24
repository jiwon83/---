package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
실버 3 안전 영역: https://www.acmicpc.net/problem/2468
시도: O
다시 풀어볼 필요는 크게 없지만 방향 등 사소한 실수로 시간을 오래 잡아먹음.
 */
public class SafeArea {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, maxRain, minRain, max_res;
    static int[][] graph;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {

        minRain = Integer.MAX_VALUE;
        maxRain = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
//        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                minRain = Math.min(minRain, graph[i][j]);
                maxRain = Math.max(maxRain, graph[i][j]);
            }
        }

    }

    private static void bfs(int i, int j, int k) {
        visit[i][j] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;
            for (int w = 0; w < 4; w++) {
                int nx = x + dx[w];
                int ny = y + dy[w];

                if (isInArea(nx, ny) && ( graph[nx][ny] > k )&& !visit[nx][ny])  {
                    visit[nx][ny]=true;
                    q.add(new Point(nx, ny));
//                    System.out.println("q에들어감 x= "+ nx +" y="+ y);
                }
            }
        }
    }

    static boolean isInArea(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < N;
    }


    static void pro() throws IOException {
        input();

        max_res = 1; //와.,,이것때문에 틀렸다. 최소 그룹의 수는 1이다.
        for (int k = minRain; k < maxRain; k++) {
            visit = new boolean[N][N];
            int group_cnt=0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (visit[i][j] != true && graph[i][j] > k) {
                        group_cnt++;
                        bfs(i, j, k);
                    }
                }
            }
//            System.out.println("강수량이 "+k+"일 때 그룹의 수 ="+group_cnt);
            max_res = Math.max(max_res,group_cnt);
        }
        //visit 초기화 k가 바뀔때마다

        System.out.println(max_res);//최대 그룹의 수
    }


    public static void main(String[] args) throws IOException {
        pro();
    }
}
