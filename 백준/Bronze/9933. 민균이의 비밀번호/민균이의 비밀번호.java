import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

class Main{

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException{
    HashSet<String> set = new HashSet<>();
    int N = Integer.parseInt(br.readLine());
    for (int i = 0; i<N; i++){
      String word = br.readLine();
      String reversed = reverseWord(word);
      if (word.equals(reversed) || set.contains(reversed)){
        System.out.println(word.length() + " "+ word.charAt(word.length()/2));
        return;
      }else{
        set.add(word);
      }
    }


  }
  private static String reverseWord(String word){
    char [] arr = word.toCharArray();
    int mid = word.length()/2; //0 4 , 1 3
    for (int i = 0; i < mid; i++){
      swap(arr, i, word.length()-1-i);
    }
    return new String(arr);
  }
  private static void swap(char [] array, int a, int b){
    char temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

}