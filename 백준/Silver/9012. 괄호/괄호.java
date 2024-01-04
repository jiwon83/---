import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
public class Main {

  static String str;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static String N = "NO", Y = "YES";

  static void input() throws IOException{
      str = br.readLine();

  }
  static void pro(String s) {
    ArrayDeque<Character> stack = new ArrayDeque<>();
    for (char  c : s.toCharArray()){
      if(c == '('){
        stack.addLast(c);
      }else{
        if (stack.size() == 0){
          sb.append(N);
          return;
        }else{
          stack.pollLast();
        }
      }
    }
    if (stack.isEmpty()) sb.append(Y);
    else sb.append(N);
  }

  public static void main(String[] args) throws IOException{
    int T = Integer.parseInt(br.readLine());
    for (int tc = 0; tc <T; tc++){
      input();
      pro(str);
      sb.append("\n");
    }
    System.out.println(sb);

  }
}
