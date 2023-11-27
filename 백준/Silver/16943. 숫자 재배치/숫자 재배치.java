import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int possibleCount = 1;
  static Integer [] asc;
  static Integer [] desc;
  static int B;

  static void input() throws IOException{
    st = new StringTokenizer(br.readLine());
    int A = Integer.parseInt(st.nextToken());
    B = Integer.parseInt(st.nextToken());

    asc = toArr(A);
    Arrays.sort(asc);
    desc = asc.clone();
    reversArray(desc, 0, desc.length-1);

    for(int c = asc.length; c >= 1; c--) possibleCount *= c;
  }

  public static void main(String[] args) throws IOException{
    input();

    if (!isStartZero(asc) && !isSmall(asc, B)) { //애초에 만들 수 있는 가장 작은 수가 B보다 크거나 같다면
      System.out.println(-1);
      return;
    }
    if (!isStartZero(desc) && isSmall(desc, B)){
      System.out.println(toIntFromArr(desc));
      return;
    }
    boolean isExist = false;

    while (possibleCount-- > 0){
      prevPermutation(desc);
      if (isSmall(desc, B)) {
        isExist = true;
        break;
      }
    }
    int num = toIntFromArr(desc);
    System.out.println( ( !isExist || isStartZero(desc) )? -1 : num);
  }

  private static boolean isStartZero(Integer[] asc) {
    return asc[0] == 0;
  }

  static int toIntFromArr(Integer[] arr){ //3 4 5 6
    int num = 0;
    for (int i = 0; i<arr.length; i++){
      num += arr[i] * (int) Math.pow(10,arr.length-1 - i);
    }
    return num;
  }
  static Integer[] toArr(int num) {
    String str = num+"";
    Integer [] res = new Integer[str.length()];
    for (int i =0; i<str.length(); i++ ){
      res[i] = str.charAt(i)-'0';
    }
    return res;
  }
  static void reversArray(Integer[] array, int from, int to){
    int mv = (to - from + 1) / 2;
    for (int i =0; i< mv; i++){
      swap(array, from+i, to - i);
    }
  }

  private static boolean isSmall(Integer[] target, int stand){
    int num = toIntFromArr(target);
    return num < stand;
  }
  static Integer[] prevPermutation(Integer[] a){
    //맨뒤에서부터 첫번째 하향 지점을 찾는다.
    int i = a.length-1;
    while ( i > 0 && a[i - 1] <= a[i] ){
      i--;
    }
    if (i == 0) return a; //이미 가장 작은 배열이다.
    int ch = i-1;// 바꿀 지점
    int to = a.length-1;
    //ch보다 1 작은 수를 찾는다.
    while (a[ch] <= a[to]) to--;
    swap(a, ch, to);
    //내림차순 정렬
    reversArray(a, i, a.length-1);
    return a;
  }
  static void swap(Integer[] arr , int from, int to){
    int temp = arr[from];
    arr[from] = arr[to];
    arr[to] = temp;
  }
}