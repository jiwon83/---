import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static StringTokenizer st;
   public static void main(String[] args) throws Exception{
      PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
      int N = Integer.parseInt(br.readLine());
      for(int i = 1; i<=N; i++){
         int num = Integer.parseInt(br.readLine());
         if (num==0){
            System.out.println(pq.size() > 0? pq.poll() : "0");
         }else {
            pq.add(num);

         }
      }

   }
}
