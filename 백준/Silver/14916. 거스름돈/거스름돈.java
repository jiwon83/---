import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  public static void main(String[] args) throws IOException {
    int N = Integer.parseInt(br.readLine());
    int answer = -1;
    int max = N / 5;
    for (int i = max; i >= 0 ; i--){
//      System.out.println(i);
      if( (N - 5 * i) % 2 == 0){
//        System.out.println(" i = " + i);
        answer = i + ((N - 5 * i) / 2);
//        System.out.println( " 2로 나눈 몫 : " + ((N - 5 * i) / 2));
        break;
      }
    }
    System.out.println(answer);
  }

}