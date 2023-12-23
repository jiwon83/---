import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
   static int N1,M1, N2,M2;
   static char [][] map = new char[150+1][150+1];
   static char [][] a, b;
   static int answer;

   static void printPuzzle(char[][] puzzle){
      System.out.println(" ===== printPuzzle =====");
      for (int i =0; i<puzzle.length; i++){
         System.out.println(Arrays.toString(puzzle[i]));
      }
      System.out.println(" =====  ===== =====  =====");

   }
   static void setA(char[][] map, char[][] a){
      for (int i = 0; i<a.length; i++){
         for (int j = 0; j <a[0].length; j++){
            map[i+50][j+50] = a[i][j];
         }
      }
   }

   static void sol(){
      setA(map, a);
      for (int i = 0; i < 4; i++){

//         System.out.println("---- rotate before ----");
//         printPuzzle(b);

         b = rotate(b);
//         System.out.println("---- rotate after ----");
//         printPuzzle(b);

         for (int r = 0; r <=100; r++){
            for (int c = 0; c <= 100; c++){
               int area = setB(r, c, b, map);
               if (area != -1) answer = Math.min(answer, area);
            }
         }
      }
      System.out.println(answer);
   }
   private static char[][] rotate(char[][] b){
      // (c, -r)
      char [][] temp = new char[b[0].length][b.length];
      for (int i = 0; i< b.length; i++){
         for (int j = 0; j <b[0].length; j++){
            int nx = j;
            int ny = b.length-1 - i;
            temp[nx][ny] = b[i][j];
         }
      }
      return temp;
   }
   private static int setB(int r, int c, char [][] b, char[][] map){

      if (r == 51 && c == 51){
//         System.out.println(" set B" );
//         System.out.println(" r= "+ r + " c = "+ c );
//         printPuzzle(b);
      }
//      System.out.println(" set B" );
//      System.out.println(" r= "+ r + " c = "+ c );
//      printPuzzle(map);
//      printPuzzle(b);

      int area = -1;
      for (int i = 0; i <b.length; i++){
         for (int j = 0; j<b[0].length; j++){
            if( map[r + i][c + j] == '1' && b[i][j] =='1' ){
               return area;
            }
         }
      }
      int width = Math.max( c+b[0].length-1, 49+M1) - Math.min(c, 50) +1;
      int height = Math.max( r+b.length-1, 49+N2) - Math.min(r, 50) +1;
      if (r == 51 && c == 51){
//         System.out.println("width:"+width+" height: "+ height);
//         System.out.println("area = " + width*height);
      }
//      System.out.println("width:"+width+" height: "+ height);
//      System.out.println("area = " + width*height);
//      System.out.println("  set B  end  ");
      return width*height;
   }

   private static void input() throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N1 = Integer.parseInt(st.nextToken());
      M1 = Integer.parseInt(st.nextToken());
      a = new char[N1][M1];
      for (int i = 0; i<N1; i++){
         String s = br.readLine();
         for (int j = 0; j<M1; j++){
            a[i][j] = s.charAt(j);
         }
      }

      st = new StringTokenizer(br.readLine());
      N2 = Integer.parseInt(st.nextToken());
      M2 = Integer.parseInt(st.nextToken());
      b = new char[N2][M2];
      for (int i = 0; i<N2; i++){
         String s = br.readLine();
         for (int j = 0; j<M2; j++){
            b[i][j] = s.charAt(j);
         }
      }
      answer = (N1 + N2) * (M1+M2);
   }
   public static void main(String[] args) throws IOException {
      input();
      sol();
   }
}
