import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main{


   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int cash = Integer.parseInt(br.readLine());
      int [] days = new int[14];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i <14; i++){
         days[i] = Integer.parseInt(st.nextToken());
      }

      int a = bnp(days, cash);
      int b = timing(days, cash);
      if (a > b){
         System.out.println("BNP");
      }else if( a == b){
         System.out.println("SAMESAME");
      }else{
         System.out.println("TIMING");
      }

   }
   public static int bnp(int [] days, int cash){
      int stock = 0;
      for (int i = 0; i < days.length-1; i++){
         if (days[i] == 0 || cash == 0) continue;
         stock += cash / days[i];
         cash %= days[i];

      }
      return cash + stock*days[days.length-1];
   }
   public static int timing(int [] days, int cash){
      int stock = 0;
      int upper = 0;
      int down = 0;
      for (int i = 1; i < days.length-1; i++){
         int diff = days[i] - days[i-1];
         if (diff > 0){
            down = 0;
            upper++;
         }else if( diff < 0){
            upper=0;
            down++;
         }
         if (upper == 3){
            cash += stock * days[i];
            stock = 0;
         }
         if(down == 3 && days[i] != 0){
            stock += cash / days[i];
            cash %= days[i];
         }
      }
      return cash + stock*days[days.length-1];

   }



}