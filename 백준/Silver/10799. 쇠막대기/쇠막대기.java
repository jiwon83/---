import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;

public class Main {
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   public static void main(String[] args) throws IOException{
      
      String s = br.readLine();
      Stack<Character> stack = new Stack<>();
      int ans =0;
      for (int i =0; i <s.length(); i++){
         char c = s.charAt(i);
         if (c == '('){
            if (i+1 <s.length() && s.charAt(i+1)==')'){ //if () 이면 레이저임
               ans += stack.size();
               i++;
            }else{ //if (이면 넣는다.
               stack.push(c);
            }
         }else{
            ans += 1;
            stack.pop();
         }
      }
      ans += stack.size();
      System.out.println(ans);

   }
}
