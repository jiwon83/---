import java.awt.*;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int bump[] = new int[3];
  static int M, N;
  static int [] numbers;
  static Point mid;
  static int [][] blizadInfo;

  private static void input() throws IOException{
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    blizadInfo = new int[M][2];
    mid = new Point(N/2, N/2);
    int [][] map = new int[N][N];
    for (int i = 0; i <N; i++){
      st = new StringTokenizer(br.readLine());
      for (int j= 0; j <N; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    numbers = trans2D(map, mid);
    for (int i = 0; i<M; i++){
      st = new StringTokenizer(br.readLine());
      int di = Integer.parseInt(st.nextToken());
      if (di == 1) di = 0;
      if(di == 4) di = 1;
      int si = Integer.parseInt(st.nextToken());
      blizadInfo[i][0] = di;
      blizadInfo[i][1] = si;
    }
  }

  private static void sol(){
    for(int i = 0; i< M; i++){
      step1(blizadInfo[i][0], blizadInfo[i][1]);
      while (step2()){};
      step3();
    }
  }
  static int [][] dirs = {{-1,0}, {0, 1}, {1,0}, {0,-1}};
  private static void step1(int d, int s){
    int [][] map = trans2D(numbers, mid);
    for (int i = 1; i <= s; i++){
      int nx = mid.x + dirs[d][0]*i;
      int ny = mid.y + dirs[d][1]*i;
      if (!inArea(nx, ny)) break;
      map[nx][ny] = -1;
    }
    numbers = trans2D(map, mid);
    move();

  }
  private static int[][] trans2D(int[] numbers, Point point) {
    int numbersIdx = 1;
    int [][] map = new int[N][N];
    int dir = 3;
    Point now = new Point(point.x, point.y);
    L : for (int l = 1; l <=N; l++){
      for (int i = 0; i<2; i++) {
        for (int j= 0; j <l; j++){
          int nx = now.x + dirs[dir][0];
          int ny = now.y + dirs[dir][1];
          if (!inArea(nx, ny)) break L;
          map[nx][ny] = numbers[numbersIdx++];
          now = new Point(nx, ny);
        }
        dir = nextDir(dir);
      }
    }
    return map;
  }

  private static int[] trans2D(int[][] map, Point point) {
    int [] numbers = new int[N*N];
    Point now = new Point(point.x, point.y);
    int dir = 3;
    int numbersIdx = 1;
    L : for (int l = 1; l <=N; l++){
      for (int i = 0; i<2; i++) {
        for (int j= 0; j <l; j++){
          int nx = now.x + dirs[dir][0];
          int ny = now.y + dirs[dir][1];
          if (!inArea(nx, ny)) break L;
          numbers[numbersIdx++] = map[nx][ny];
          now = new Point(nx, ny);
        }
        dir = nextDir(dir);
      }
    }
    return numbers;
  }
  static boolean inArea(int r, int c){
    return r >=0 && c >=0 && r < N && c <N;
  }
  static int nextDir(int dir){
    return dir - 1 < 0 ? 3 : dir - 1;
  }

  private static boolean step2(){
    boolean isRemove=false;
    for (int i = 1; i < N*N; i++){
      if (numbers[i]==0) continue;
      int cnt = 1;
      int j = i;
      while (j + 1 < N*N && numbers[j] == numbers[j+1]){
        j++;
        cnt++;
      }
      if (cnt >= 4){
        bump[numbers[i]-1] += cnt;
        for (int w = i; w <=j; w++){
          numbers[w] = -1;
        }
        isRemove = true;
      }
      i = j;
    }
    move();
    return isRemove;
  }
  private static void step3() {
    int [] cp = new int[N*N];
    int cpIdx = 1;
    for (int i = 1; i < N * N; i++) {
      if (numbers[i] == 0) continue;
      int cnt = 1;
      int j = i;
      while (j+1 < N*N && numbers[j] == numbers[j+1]){
        j++;
        cnt++;
      }
      cp[cpIdx++] = cnt;
      if (cpIdx >= N*N) break;
      cp[cpIdx++] = numbers[i];
      if (cpIdx >= N*N) break;
      i = j;
    }
    numbers = cp.clone();
  }
  private static void move(){
    int [] arr = new int[N*N];
    int blankCnt = 0;
    for (int i = 1; i < N *N; i++){
      if (numbers[i]==-1) blankCnt++;
      else arr[i-blankCnt] = numbers[i];
    }
    numbers =arr.clone();
  }

  private static int getAnswer(){
    return bump[0] + 2 * bump[1] + 3*bump[2];
  }
  public static void main(String[] args) throws IOException {
    input();
    sol();
    System.out.println(getAnswer());
  }
}
