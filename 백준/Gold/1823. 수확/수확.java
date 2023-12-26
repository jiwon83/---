import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;


public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int N;
  static int [][] dy = new int[N+1][N+1];

  private static int input() throws IOException{
    N = Integer.parseInt(br.readLine());
    int [] a = new int[N+1];
    dy = new int[N+1][N+1];
    for (int i = 1; i<=N; i++){
      a[i] = Integer.parseInt(br.readLine());
      dy[i][i] = a[i] * N;
    }
    return sol(a);
//    return recur(a, 1, N, 1);
  }

  private static int sol(int [] a){
    for (int len = 2; len <=N; len++){
      for (int i = 1; i<=N; i++){
        int j = i + len - 1;
        if (j > N) continue;
        int multi = N+1 - len;
        dy[i][j] = multi * a[i] + dy[i+1][j];
        dy[i][j] = Math.max(dy[i][j], dy[i][j-1] + a[j] * multi);
      }
    }
    return dy[1][N];
  }
//  private static int recur(int [] a, int from, int to, int multi){
////    System.out.println(from + " , "+ to + " x  "+ multi);
//    if (dy[multi][from][to]!= 0) return dy[multi][from][to];
//    if (from == to){
//      dy[multi][from][to] = multi * a[from];
////      System.out.println(from + " , "+ to +" multi :"+multi+ "   dy = "+ dy[multi][from][to]);
//      return dy[multi][from][to];
//    }
//    int bigIdx, smallIdx;
//    if (a[from] >= a[to]){
//      bigIdx = from;
//      smallIdx = to;
//    }else{
//      bigIdx = to;
//      smallIdx = from;
//    }
//    //1 - 2
//    // 2 - 1
//    int max = a[bigIdx] * (multi+1);
//    int min = a[smallIdx] * multi;
//
//    dy[multi][from][to] = Math.max(dy[multi][from][to], max + min + recur(a, from+1, to-1, multi+2) );
////    System.out.println(from + " , "+ to +" multi :"+multi+ "   dy = ( 1 _ 2) (2 _ 1)  "+ dy[multi][from][to]);
//
//    //12 -
//    if (to - from + 1 <= 2){
////      System.out.println(from + " , "+ to +" multi :"+multi+ "   dy = "+ dy[multi][from][to]);
//      return dy[multi][from][to];
//    }
//
////    dy[from][from+1] = multi * Math.min(a[from], a[from+1]) + (multi+1) * Math.max(a[from], a[from+1]);
//    dy[multi][from][to] = Math.max( dy[multi][from][to],
//            multi * a[from] + (multi+1)*a[from+1]
//                    +  recur(a, from+2, to, multi+2) );
////    System.out.println(from + " , "+ to +" multi :"+multi+ "   dy = ( 1,2 _ )  "+ (multi * a[from] + (multi+1)*a[from+1] + dy[multi][from+2][to]));
//
//    // - 21
////    dy[from][to-2] = multi * Math.min(a[from], a[from+1]) + (multi+1) * Math.max(a[from], a[from+1]);
//    dy[multi][from][to] = Math.max(dy[multi][from][to],
//            multi * a[to] + (multi+1) * a[to-1] +  recur(a, from, to-2, multi+2) );
////    System.out.println(from + " , "+ to +" multi :"+multi+ "   dy = ( _, 2,1 )  "+ (multi * a[from] + (multi+1)*a[from+1] + dy[multi][from+2][to]));
//
//
//
////    System.out.println("----result ---- "+ from + " , "+ to +" multi :"+multi+ "   dy = "+ dy[multi][from][to]);
//    return dy[multi][from][to];
//  }

//  private static boolean inArea(int r ,int c){
//    return r > 0 && c > 0 && r <= N && c <=N;
//  }

  public static void main(String[] args) throws IOException {
    System.out.println(input());

//    for (int i = 1; i<=N; i++){
//      System.out.println(" =====multi = "+ i+" ====== ");
//      for (int j = 1; j<=N; j++){
//        System.out.println(Arrays.toString(dy[i][j]));
//      }
//      System.out.println(" ===================== ");
//
//    }
  }

}
