import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static int answer;
   static HashSet<String> set;
   static int[][] trains;
   static int N, M;
   static StringTokenizer st;

   static void sol(){
      for (int i=1; i<= N; i++){
         String info = getInfo(trains[i]);
         if(set.contains(info)) continue;
         set.add(info);
         answer++;
      }
      System.out.println(answer);
   }
   static String getInfo(int [] train){
      StringBuilder sb = new StringBuilder();
      for(int i  = 1; i<=20; i++){
         sb.append(train[i]);
      }
      return sb.toString();
   }
   static void input() throws IOException{
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      trains = new int[N+1][20+1];
      set = new HashSet<>();
      for (int i = 0; i< M; i++){
         // 명령 수행
         String [] input = br.readLine().split(" ");
         int [] inputNum = new int[3];
         for (int j = 0; j <input.length; j++){
            inputNum[j] = Integer.parseInt(input[j]);
         }
         act(inputNum[0], inputNum[1], inputNum[2]);
      }

   }
   static void act(int num, int trainIdx, int seatIdx){
      if (num == 1){
         trains[trainIdx][seatIdx] = 1;
      }else if(num == 2){
         trains[trainIdx][seatIdx] = 0;
      }else if( num == 3){
         for (int i =20; i >=2; i--){
            trains[trainIdx][i] = trains[trainIdx][i-1];
         }
         trains[trainIdx][1] = 0;
      }else{
         for (int i = 1; i <= 19; i++){
            trains[trainIdx][i] = trains[trainIdx][i+1];
         }
         trains[trainIdx][20] = 0;
      }
   }

   public static void main(String[] args) throws IOException {
      input();
      sol();
   }
}