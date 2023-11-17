import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    String s = br.readLine();
    System.out.println(isCorrectMultiCheck(s)?1: 0);
  }
  private static boolean isCorrectMultiCheck(String s){
    //w, o, l , f의 갯수가 순서대로, 동일한 갯수만큼 있다면
    //그러한 단어 2개를 이은 경우도 가능.
    //w 가 나온다면, 이전까지의 string을 검사.
    // 검사해서 wolf 갯수를 [] 에 저장 [w, o, l, f]
    // w를 제외하고 이전 index의 결과와 다르다면 return false


    String word = "wolf";
    int p = 0;
    Stack<Character> stack = new Stack<>();
    for (int i=0; i<s.length(); i++){
      char c = s.charAt(i);
      if (p == word.length()){ //f까지 다했다면
//        System.out.println(" 2개로 분리");
        return isCorrect(s.substring(0,i-1)) && isCorrectMultiCheck(s.substring(i-1));
      }
      if (stack.size() == 0 || (stack.size() > 0 && c == stack.peek())) stack.push(c);
      else {

        if ( word.charAt((p+1)%word.length()) == c) stack.push(c); //순서에 맞는 지 확인
         else return false;
        p++;
      }

    }
    return isCorrect(s);

  }
  static boolean isCorrect(String s){
//    System.out.println("isCorrect "+s);
    //갯수 확인
    int [] cnt = new int[4];
    String word = "wolf";
    for (int i = 0; i<s.length(); i++){
      cnt[word.indexOf(s.charAt(i))]++;
    }
    for (int i = 0; i<3; i++){
      if (cnt[i] != cnt[i+1]) return false;
    }
    return true;
  }


}
