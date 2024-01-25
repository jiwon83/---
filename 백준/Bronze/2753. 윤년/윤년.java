import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      System.out.println(isYunYear(n)? 1: 0);
   }
   public static boolean isYunYear(int num){
      if (num % 400==0) return true;
      else if (num % 100==0) return false;
      else return num%4==0;
   }

}