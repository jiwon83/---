import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static final int filterN = 5;
  static int[] f_center = {2,2};
  static int answer;
  static int N;
  static int [][] dirs = {{-1,0}, {0,1}, {1,0},{0,-1}};
  static int [][] map;

  static void move(int N, int [][] map){
    int inc = 1;
    int d = 3; // <-
    int x= N/2, y = N/2; //출발점
    L: while (inc <= N){
      for (int i = 0; i<2; i++){
        for (int j = 0; j <inc; j++){
          x = x + dirs[d][0];
          y = y + dirs[d][1];
          if (!inArea(x, y)) break L;
//          System.out.println("x="+x+" y="+y);
          int amount = map[x][y];
          if (amount==0) continue;
          map[x][y] = 0;
          sandSpread(map, x, y, d, amount);
        }
        d = nextDir(d);
      }
      inc++;
    }
  }

  static int [][] rateMap = {
          {0, 0, 5, 0 , 0},
          {0 , 10, 0, 10, 0},
          {2, 7, 0, 7, 2},
          {0 , 1, 0, 1, 0},
          {0 , 0, 0, 0, 0}
  };

  static int nextDir(int d){
    return d-1 < 0 ? 3:d-1;
  }
  static void sandSpread(int[][] map, int x, int y, int dir, int amount ){
    int [][] spreadFilter = toSpreadFilter(rateMap, amount);
    int [][] rotated = rotate(spreadFilter, dir, filterN);
    int [] trans = new int[]{x-f_center[0], y-f_center[1]};
    for (int i = 0; i < rotated.length; i++){
      for (int j = 0; j <rotated[0].length; j++){
        if (rotated[i][j]==0) continue;
        int nx = i + trans[0];
        int ny = j + trans[1];
        if (!inArea(nx, ny)) answer += rotated[i][j];
        else map[nx][ny] += rotated[i][j];
      }
    }
  }
  static boolean inArea(int r, int c){
    return r >= 0 && c >= 0 && r < N && c <N;
  }

  private static int[][] rotate(int[][] spreadFilter, int dir, int filterN) {
//    System.out.println("Main3.rotate");
    //현재 dir = 0
    int d = 0;
    while (dir != d){
      d = d + 1;
      int [][] cp = new int[filterN][filterN];
      for (int i = 0; i < filterN; i++){
        for (int j = 0; j <filterN; j++){
          int r = j;
          int c = (filterN-1)-i;
          cp[r][c] = spreadFilter[i][j];
        }
      }
      for (int i = 0; i< filterN; i++){
        spreadFilter[i] = cp[i].clone();
      }
    }
    return spreadFilter;
  }

  private static int[][] toSpreadFilter(int[][] rateMap, int amount) {
    int left = amount;
    int [][] spreadFilter = new int[filterN][filterN];
    for (int i = 0; i< filterN; i++){
      for (int j = 0; j<filterN; j++){
        spreadFilter[i][j] += (int) (rateMap[i][j]*((double) amount / 100));
        left -= spreadFilter[i][j];
      }
    }
    spreadFilter[1][2] = left;
    return spreadFilter;
  }

  private static void input() throws IOException{
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    for (int i = 0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j <N; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }
  public static void main(String[] args) throws IOException {
    input();
    move(N, map);
    System.out.println(answer);
  }
}
