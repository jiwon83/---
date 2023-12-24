import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*

 */
public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int answer;
  static int[][] rate;
  static int [][] map;
  static int [][] dirs = {{-1,0}, {0,1}, {1,0},{0,-1}};
  static int N;
  static int mr, mc;


  private static void input() throws IOException{
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    mr = N/2; mc = N/2;
    for (int i= 0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j <N; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  private static int sol(int mr, int mc){
    //1. 토네이도 순회
    //1 1 2 2 3 3 4 4 크기만큼 이동
    // 각각 방향 반시계 방향 회전, rate도 같이 회전
    //2. rate 순회 일정한 비율로 분배 모래가 쌓인다.
    int dir = 0;
    int x = mr; int y = mc;
    for (int l = 1; l <= N; l++){
      for (int i = 0; i <2; i++){

        rotateRate(rate);
//        printArr("회전 후 ..", rate);
        dir = (dir - 1) < 0? 3: dir-1;
        for (int j = 0; j < l; j++){
          x += dirs[dir][0];
          y += dirs[dir][1];
          if (!inArea(x,y)) break;
//          System.out.println(" x ="+x + " y= "+y);
          fileSand(x, y, rate, map);
        }
      }
    }
    return answer;
  }


  private static void printArr(String s, int[][] rate) {
    System.out.println(s);
    for (int i =0; i< rate.length; i++){
      System.out.println(Arrays.toString(rate[i]));
    }
  }

  static final int rateN = 5;
  //2중 배열의 메모리 구조
  static void rotateRate(int[][] rate){

    int [][] tmp = new int[rateN][rateN];
    for (int i = 0; i <rateN; i++){
      for (int j = 0; j <rateN; j++){
        int ni = rateN-1 - j;
        int nj = i;
        tmp[ni][nj] = rate[i][j];
      }
    }
    for (int i = 0; i<rateN; i++){
      rate[i] = tmp[i].clone();
    }
  }

  static void fileSand(int r, int c, int [][] rate, int[][] map){
//    System.out.println("fileSand + "+ map[r][c]);
//    printArr(" -- before -- ", map);
//    System.out.println("r = " + r + " c = "+ c);
//    int [][] view = new int[N][N];

    Point alpha= new Point(-1,-1);
    int left = map[r][c];
    for (int i = 0; i<rate.length; i++){
      for (int j = 0; j <rate[0].length; j++){
        if (rate[i][j]==0) continue;
        int mi = i + r - 2;
        int mj = j + c - 2;
        if(rate[i][j] == -1){
          alpha = new Point(mi, mj);
          continue;
        }
        int file = (int)(map[r][c] * ((double)rate[i][j]/100));
        if (!inArea(mi, mj)){
          answer += file;
        }else {
          map[mi][mj] += file;
//          view[mi][mj] = rate[i][j];
        }
//        System.out.println(" i = "+ i + " j = "+ j+ " =>");
//        System.out.println(" mi = "+ mi + " mj = "+ mj+ " : "+file);
        left -= file;
      }
    }
    if (inArea(alpha.x, alpha.y)) map[alpha.x][alpha.y] += left;
    else answer += left;
    map[r][c]= 0;
//    printArr("----view----", view);
//    printArr(" -- after -- ", map);

  }
  static boolean inArea(int r, int c){
    return r >= 0 && c >=0 && r <N && c <N;
  }
  static void initRate(){
    rate = new int[][]{
            {0,0,5,0,0},
            {0, 10, -1, 10,0},
            {2, 7, 0, 7, 2},
            {0,1, 0, 1, 0},
            {0,0,0,0,0}
          };
  }

  public static void main(String[] args) throws IOException {
//    System.out.println((int)(45 * 0.1));
    input();
    initRate();
    int ans = sol(mr, mc);
    System.out.println(ans);
//    System.out.println(sol(mr, mc));
  }

}
