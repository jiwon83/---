import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

class Main{

  //memory = 4* 10^5 byte mb kb

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



  public static void main(String[] args) throws IOException{
    int N = Integer.parseInt(br.readLine());
    int [] a = new int[N];
    for(int i = 0; i< N; i++){
      a[i] = Integer.parseInt(br.readLine());
    }
    int ans = a[N-1];
    Arrays.sort(a);
    for (int i=0; i<N-1; i++){
      int choose = a[i]*(a.length-i);
      int notchoose = a[i+1]*(a.length-i-1);
      if (choose >= notchoose){
        ans = Math.max(ans, choose);
      }

    }
    System.out.println(ans);

  }


}