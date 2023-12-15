import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static boolean [] ch;

  static void sol(String str, int start, int end){
//    System.out.println(" start = "+ start + " ~ end = "+ end);
    if (start == end){
      ch[start] = true;
      sb.append(getWord(str)+"\n");
      return;
    }
    //가장 작은 수를 찾아라!
    int minIdx = -1;
    int minNum = 26;
    for(int i = start; i <= end; i++){
      if(minNum > str.charAt(i)-'A'){
        minNum = str.charAt(i)-'A';
        minIdx = i;
      }
    }
//    System.out.println("min = "+ minIdx + " " +str.charAt(minIdx));
    ch[minIdx] = true;
    sb.append(getWord(str)+"\n");

    if(minIdx + 1 <= end)sol(str, minIdx+1, end); //뒤
    if (minIdx -1 >= start )sol(str, start, minIdx-1); //앞

  }
  static String getWord(String str){
    StringBuilder sb =new StringBuilder();
    for (int i = 0; i< str.length(); i++){
      if(ch[i] ) sb.append(str.charAt(i));
    }
    return sb.toString();
  }

  public static void main(String[] args) throws IOException {
    String word = br.readLine();
    ch = new boolean[word.length()];
    sol(word, 0, word.length()-1);
    System.out.println(sb);
  }
}
