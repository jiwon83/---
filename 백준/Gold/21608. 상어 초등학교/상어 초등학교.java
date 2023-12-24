import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

/*

 */
public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int [][] seat;
  static HashSet<Integer> [] likeSet;
  static int N;
  static class Info{
    int like, empty, r, c;

    public Info(int like, int empty, int r, int c) {
      this.like = like;
      this.empty = empty;
      this.r = r;
      this.c = c;
    }
  }
  static int [][] students;
  private static void input() throws IOException{
    N = Integer.parseInt(br.readLine());
    seat = new int[N+1][N+1];
    students = new int[N*N+1][5];
    likeSet = new HashSet[N*N+1];
    for (int i= 1; i <=N*N; i++){
      likeSet[i] = new HashSet<>();
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j <5; j++){
        students[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    for (int i= 1; i <=N*N; i++){
      int idx = students[i][0];
      for (int j = 1; j <5; j++){
        likeSet[idx].add(students[i][j]);
      }
    }
  }

  private static int sol(int[][] students){
    //1. 모든 주어진 순서에 따라 학생들에 대하여
    for (int i = 1; i <=N*N; i++){
      int no = students[i][0];
      //2. 비어있는 칸 중 탐색: 인접한 좋아하는 학생 수 , 빈칸 수
      List<Info> selectInfo = getSelectInfo(students[i], seat);
      //3. 우선순위에 따라 자리 선택
      Collections.sort(selectInfo, (o1, o2) -> {
        if (o1.like != o2.like) return o2.like-o1.like;
        if (o1.empty != o2.empty) return o2.empty-o1.empty;
        if(o1.r != o2.r) return o1.r - o2.r;
        return o1.c - o2.c;
      });
      seat[selectInfo.get(0).r][selectInfo.get(0).c] = no;
    }
    //4. 만족도를 구한다.
    return getHappyScore(seat);
  }
  static int getHappy(int like){
    return (int)Math.pow(10, like-1);
  }
  static int [] dr = {-1,1,0,0}, dc = {0,0,-1,1};
  private static List<Info> getSelectInfo(int [] likes, int [][] seat){
    List<Info> selectInfo = new ArrayList<>();
    for (int r = 1; r <=N; r ++){
        for (int c = 1; c <=N; c++){
        if (seat[r][c] != 0) continue;
        int like = 0;
        int empty = 0;
        for (int d = 0; d <4; d++){
          int nr = r + dr[d];
          int nc = c + dc[d];
          if (!inArea(nr, nc)) continue;
          if (likeSet[likes[0]].contains(seat[nr][nc])) like++;
          if(seat[nr][nc]==0) empty++;
        }
        selectInfo.add(new Info(like, empty,r,c));
      }
    }
    return selectInfo;
  }
  private static int getLikeCnt(int [][] seat, int r, int c){
    int no = seat[r][c];
    int result = 0;
    for (int d = 0; d <4; d++){
      int nr = r + dr[d];
      int nc = c + dc[d];
      if (!inArea(nr, nc)) continue;
      if (likeSet[no].contains(seat[nr][nc])) result++;
    }
    return result;
  }
  private static int getHappyScore(int [][] seat){
    int score = 0;
    for (int r = 1; r <=N; r ++) {
      for (int c = 1; c <= N; c++) {
        score += getHappy(getLikeCnt(seat, r, c));
      }
    }
    return score;
  }
  private static boolean inArea(int r ,int c){
    return r > 0 && c > 0 && r <= N && c <=N;
  }

  public static void main(String[] args) throws IOException {
    input();
    System.out.println(sol(students));
  }

}
