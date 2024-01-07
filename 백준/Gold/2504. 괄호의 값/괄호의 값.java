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

/**
 4 5
 50 45 37 32 30
 35 50 40 20 25
 30 30 25 17 28
 27 24 22 15 10
 */
class Main {
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static StringTokenizer st;
   static int answer;
   static String str;
   static void input() throws IOException{
      str = br.readLine();
   }
   /*
   (1+(2*(3+4)))
   (1+(2*3+4))
   (1+2*(3+4))
   (1+2*3+4)
   1+(2*(3+4))
    */
   static boolean sol(String str){
      //괄호의 값을 더이상 분해할 수 없을 때까지 분해하고 값을 return
      ArrayDeque<Object> stack = new ArrayDeque<>();
      answer = 0;
      for (int i = 0; i<str.length(); i++){
         char c = str.charAt(i);
//         System.out.println("i = "+ i + " c = "+ c);
         if (c == '(' || c == '['){
            stack.addLast(c);
         }else if(c == ')'){
            int num = 0;
            while (!stack.isEmpty() && ( stack.peekLast() instanceof Integer || (char)stack.peekLast() != '(')){
               Object out = stack.pollLast();
               if (out instanceof Integer){
                  num += (int) out;
               }else{
                  if((char)out == '[') return false;
               }
            }
            if (stack.isEmpty()) return false;
            stack.pollLast();
//            System.out.println("num = "+ num);

            if(num==0) stack.addLast(2);
            else stack.addLast(2*num);
//            System.out.println("결과  = "+ stack.peekLast());
//            System.out.println(stack);
         }else if(c == ']'){
            int num = 0;
            while (!stack.isEmpty() && ( stack.peekLast() instanceof Integer || (char)stack.peekLast() != '[')){
               Object out = stack.pollLast();
//               System.out.println(out.getClass().getName()+ out);
               if (out instanceof Integer){
                  num += (int) out;
               }else{
                  if((char)out == '(') return false;
               }
            }
            if (stack.isEmpty()) return false;
//            System.out.println("num = "+ num);
            stack.pollLast();
            if (num==0) stack.addLast(3);
            else stack.addLast(3*num);
//            System.out.println("결과  = "+ stack.peekLast());
//            System.out.println(stack);

         }
      }
//      System.out.println("final 남은 숫자들");
      while (!stack.isEmpty()){
         Object obj = stack.pollLast();
         if(obj instanceof Character) return false;
         answer += (int) obj;
      }
//      System.out.println("answer = "+ answer);
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