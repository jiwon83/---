import java.util.*;
import java.io.*;
class Main{
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static ArrayDeque<Character> stack = new ArrayDeque<>();
   static ArrayDeque<Character> buffer = new ArrayDeque<>();

   public static void main(String [] args) throws IOException{

      initStack(br.readLine());

      int M = Integer.parseInt(br.readLine());
      for (int i = 0; i < M; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         char cmd = st.nextToken().charAt(0);
         char put=0;
         if (cmd=='P'){
            put = st.nextToken().charAt(0);
         }
         conduct(cmd, put);
      }
      print();
   }
   static void print(){
      StringBuilder sb = new StringBuilder();
      while (!stack.isEmpty()){
         sb.append(stack.pollFirst());
      }
      while (!buffer.isEmpty()){
         sb.append(buffer.pollLast());
      }
      System.out.println(sb);
   }
   static void initStack(String str){
      for (int i = 0; i < str.length(); i++) {
         stack.addLast(str.charAt(i));
      }
   }
   static void conduct(char cmd, char data){
      switch (cmd){
         case 'L':
            if (stack.size() > 0) buffer.addLast(stack.pollLast());
            return;
         case 'D':
            if (buffer.size() > 0) stack.addLast(buffer.pollLast());
            return;
         case 'B':
            if (stack.size() > 0) stack.pollLast();
            return;
         case 'P':
            stack.addLast(data);
      }
   }
}