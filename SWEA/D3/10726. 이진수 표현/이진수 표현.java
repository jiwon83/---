import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class Solution {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args)  throws IOException{
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <=T; tc++){
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      answer(N, M, tc);
    }
  }
  private static void answer(int N, int M, int tc){
    int mask = (1 << N) -1;
    System.out.printf("#%d %s\n", tc, (mask & M)==mask?"ON":"OFF");
  }

}
