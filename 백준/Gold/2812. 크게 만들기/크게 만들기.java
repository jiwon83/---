import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int N, K;
  static String str;


  static void input() throws IOException{
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    str = br.readLine();

  }
  static String sol(int N, int K, String str){
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    int removeCnt = 0;
    int idx = 0;
    Loop: while (removeCnt < K && idx <str.length()){
      int num = str.charAt(idx)-'0';
      if ( stack.isEmpty() || stack.peekLast() >= num ){
        stack.addLast(num);
      }else{
        while ( !stack.isEmpty() && stack.peekLast() < num){
          stack.pollLast();
          removeCnt++;
          if (removeCnt == K) break Loop;
        }
        stack.addLast(num);
      }
      idx++;
    }
    while (removeCnt++ < K){
      stack.pollLast();
    }
//    System.out.println((idx-1)+" idx까지 순회, removeCnt =  "+removeCnt );
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()){
      sb.append(stack.pollFirst());
    }
    for (int i = idx; i < N; i++) {
      sb.append(str.charAt(i)-'0');
    }
    return sb.toString();

  }


  public static void main(String[] args) throws IOException {
    input();
    System.out.println(sol(N, K, str));

  }
}