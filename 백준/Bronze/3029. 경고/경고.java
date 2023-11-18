import java.io.IOException;
import java.util.*;
class Main{
  static Scanner sc = new Scanner(System.in);

  public static void main(String [] args) throws IOException {
    //현재 시간 , 나트륨 던질 시간
    String temp1 = sc.nextLine();
    String temp2 = sc.nextLine();
    System.out.println(printFormat(timeCalculator(StringToArr(temp1), StringToArr(temp2))));
  }
  static String printFormat(int [] results){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i <3; i++){
      sb.append(String.format("%02d",results[i])).append(":");
    }
    return sb.substring(0, sb.length()-1);

  }
  static int [] StringToArr(String s){
    return new int[]{Integer.valueOf(s.split(":")[0]),Integer.valueOf(s.split(":")[1]), Integer.valueOf(s.split(":")[2]) };
  }
  static int [] timeCalculator(int [] now, int [] target){

    ifTargetIsNextDayThen(now, target);
    int [] result =new int[3];
    int [] weight = {60, 60, 1}; //계산 시 올림된 자릿수의 weight
    boolean borrowed = false;

    for (int u = 2; u >= 0 ; u--){ //작은 자리 ~ 큰자릿수
      int a = target[u] + (borrowed ? -1: 0);
      int b = now[u];
      borrowed = false;

      if ( a >= b ) result[u] = a - b;
      else{
        borrowed = true;
        result[u] = a + weight[u-1] - b;
      }
    }
    return result;
  }
  static void ifTargetIsNextDayThen(int [] now, int [] target){
    for (int i = 0; i < 3; i++){

      if( target[i] < now[i] ){
        add24hour(target);
        return;
      }else if( target[i] > now[i]){
        return;
      }
      if(i==2) add24hour(target);
    }
  }
  static void add24hour(int [] target){
    target[0] += 24;
  }
}