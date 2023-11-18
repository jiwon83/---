import java.io.IOException;
import java.util.*;
class Main{

  static Scanner sc = new Scanner(System.in);
  static StringBuilder sb = new StringBuilder();

  public static void main(String [] args) throws IOException {
    while (true){
      String str = sc.nextLine();
      if (str.equals("END")) break;
      Stack<Character> stack = new Stack<>();
      for(char c : str.toCharArray()){
        stack.push(c);
      }
      while (!stack.isEmpty()){
        sb.append(stack.pop());
      }
      sb.append("\n");

    }
    System.out.println(sb);

  }

}