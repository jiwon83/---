import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

class Main{
  static class Ball{
    int r,c,m,s,d;

    public Ball(int r, int c, int m, int s, int d) {
      this.r = r;
      this.c = c;
      this.m = m;
      this.s = s;
      this.d = d;
    }

    @Override
    public String toString() {
      return "Ball{" +
              "r=" + r +
              ", c=" + c +
              ", m=" + m +
              ", s=" + s +
              ", d=" + d +
              '}';
    }
  }

  static List<Ball> [][] map;
  static int N,M, K;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  static void input() throws IOException{
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    map = new List[N+1][N+1];
    initMap(map);
    while (M-- > 0){
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());

      Ball ball = new Ball(r, c, m, s, d);
      map[r][c].add(ball);
    }
//    System.out.println("초기값 ");
//    for (int i = 1; i <= N; i++) {
//      System.out.println(Arrays.toString(map[i]));
//    }
//    System.out.println("------------");
  }
  static void initMap(List<Ball> [][] map){
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        map[i][j] = new ArrayList<>();
      }
    }
  }


  public static void main(String[] args) throws IOException {
    input();
    while (K-- > 0){
      //1. 모든 파이어볼 이동
      allMove();
      //2. 2개 이상의 파이어볼이 있다면 합치기
      //3. 4개로 나뉘기
      //4. 4개를 퍼뜨리기
      UnionBall();

    }
    //5.남아있는 파이어볼 질량의 합
    System.out.println(getRemainMass());

  }
  static void UnionBall(){
//    System.out.println("UnionBall 전 ");
//    for (int i = 1; i <= N; i++) {
//      System.out.println(Arrays.toString(map[i]));
//    }
    List<Ball> [][] newMap = new List[N+1][N+1];
    initMap(newMap);
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <=N; j++){
        if (map[i][j].size()==0) continue;
        if (map[i][j].size() == 1) {
          newMap[i][j].add(map[i][j].get(0));
          continue;
        }

        int unionS=0, unionM=0;
        int dir = ( map[i][j].get(0).d & 1 ); //1이면 홀, 0이면 짝
        boolean sameDir = true; //모두 홀수이거나 모두 짝수인지
        for( Ball fireBall : map[i][j]){
          unionM += fireBall.m;
          unionS += fireBall.s;
          if (dir != (fireBall.d & 1 )) sameDir = false;
        }
        //4개의 파이어볼로 나뉘어서 저장
        unionS /= map[i][j].size();
        unionM /= 5;
        if (unionM > 0){
          for (int k = 0; k <= 6; k+=2) { //0 2 4 6
            newMap[i][j].add(new Ball(i,j,unionM,unionS, k+(sameDir?0:1)));
          }
        }

      }
    }

    map = newMap;
//    System.out.println("UnionBall 후 ");
//    for (int i = 1; i <= N; i++) {
//      System.out.println(Arrays.toString(map[i]));
//    }

  }
  static void allMove(){
    List<Ball> [][] newMap = new List[N+1][N+1];
    initMap(newMap);
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <=N; j++){
        for( Ball fireBall : map[i][j]){
          //방향 및 속력만큼 이동,
//          System.out.println("ball 이동 전 : "+fireBall);
          movedPoint(fireBall);
//          System.out.println("ball 이동 후 : "+fireBall);
          newMap[fireBall.r][fireBall.c].add(fireBall);

        }
      }
    }
    map = newMap;
//    System.out.println("all move 후 ");
//    for (int i = 1; i <= N; i++) {
//      System.out.println(Arrays.toString(map[i]));
//    }
  }
  static int[][] dirs = {{-1,0}, {-1,1}, {0,1},{1,1}, {1,0}, {1,-1},{0,-1},{-1,-1}};
  static void movedPoint(Ball ball){
    //N ball.r ball.c ball.s ball.d
    //1. 속력 만큼 이동
    ball.r += ball.s * dirs[ball.d][0];
    ball.c += ball.s * dirs[ball.d][1];
    if(inArea(ball.r, ball.c)) return;
    //2. 한 바퀴 이상으로 이동할 경우 마지막 위치 찾도록 처리 : % N
    ball.r %= N;
    ball.c %= N;
    //3. 음수와 0이 포함된 해당 위치를 1~N인 인덱스로 변경
    if (ball.r < 0) ball.r += N;
    if (ball.r == 0) ball.r = N;
    if (ball.c < 0) ball.c += N;
    if (ball.c == 0) ball.c = N;
  }

  private static boolean inArea(int r, int c) {
    return r > 0 && c > 0 && r <=N && c <= N;
  }

  static int getRemainMass(){
    int massSum = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        for (Ball fireBall : map[i][j]) {
          massSum += fireBall.m;
        }
      }
    }
    return massSum;
  }
}