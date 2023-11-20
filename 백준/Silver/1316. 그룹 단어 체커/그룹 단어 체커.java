import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Main{

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException{
    int N = Integer.parseInt(br.readLine());
    int count = 0;
    for (int i = 0; i<N; i++){
      String s = br.readLine();
      if (isWord(s)) count++;
    }
    System.out.println(count);


  }
  static boolean isWord(String s){
    boolean [] alpha = new boolean[26];
    for (int i =0; i<s.length(); i++){
      int ch = s.charAt(i)-'a';
      if (alpha[ch]) return false;
      alpha[ch] = true;
      while (i+1 < s.length() && s.charAt(i+1) - 'a' == ch) i++;
    }
    return true;
  }
}