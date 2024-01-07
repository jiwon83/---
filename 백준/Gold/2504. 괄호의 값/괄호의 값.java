import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Main {
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static StringTokenizer st;
   static int answer;
   static String str;
   static void input() throws IOException{
      str = br.readLine();
   }
  
   static boolean sol(String str){
      //괄호의 값을 더이상 분해할 수 없을 때까지 분해하고 값을 return
      ArrayDeque<Object> stack = new ArrayDeque<>();
      answer = 0;
      for (int i = 0; i<str.length(); i++){
         char c = str.charAt(i);
         if (c == '(' || c == '['){
            stack.addLast(c);
         }else if(c == ')'){
            int num = 0;
            if(!stack.isEmpty() && stack.peekLast() instanceof Character && (char) stack.peekLast() == '(') num = 2;
            else{
               while (!stack.isEmpty() && ( stack.peekLast() instanceof Integer || (char)stack.peekLast() != '(')){
                  Object out = stack.pollLast();
                  if (out instanceof Integer) num += (int) out;
                  else if((char)out == '[') return false;
               }
               num *= 2;
            }

            if (stack.isEmpty()) return false;
            stack.pollLast();
            stack.addLast(num);

         }else if(c == ']'){
            int num = 0;
            if(!stack.isEmpty() && stack.peekLast() instanceof Character && (char) stack.peekLast() == '[') num = 3;
            else{
               while (!stack.isEmpty() && ( stack.peekLast() instanceof Integer || (char)stack.peekLast() != '[')){
                  Object out = stack.pollLast();
                  if(out instanceof Integer) num += (int) out;
                  else if((char)out == '(') return false;
               }
               num *= 3;
            }

            if (stack.isEmpty()) return false;
            stack.pollLast();
            stack.addLast(num);

         }
      }
      while (!stack.isEmpty()){
         Object obj = stack.pollLast();
         if(obj instanceof Character) return false;
         answer += (int) obj;
      }
      return true;
   }

   public static void main(String[] args) throws IOException {
      input();
      if(sol(str)){
         System.out.println(answer);
      }else{
         System.out.println(0);
      }

   }
}