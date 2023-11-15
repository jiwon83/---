import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    int N = Integer.parseInt(br.readLine());
    int ans = 0;
    String input = br.readLine();
    for (int i =0; i<input.length(); i++ ){
      ans += input.charAt(i)-'0';
    }
    System.out.println(ans);

  }
}
