import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
        static int N;
        static List<Integer> negative = new ArrayList<>();
        static List<Integer> positive = new ArrayList<>();
        static List<Integer> zeros = new ArrayList<>();
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public static void main(String[] args) throws IOException {
                N = Integer.parseInt(br.readLine());

                for (int i = 0; i< N; i++){
                        int num = Integer.parseInt(br.readLine());
                        if (num > 0 ) positive.add(num);
                        if (num == 0) zeros.add(num);
                        if (num < 0 ) negative.add(num);
                }
                Collections.sort(negative); // 오름차순
                Collections.sort(positive);
                // -12 , -11 , -1 , 0 , 1, 10
                int sum = 0;

                // 음수는 음수끼리 묶음
                // 음수가 홀수 경우 -> 다음에 0일 있다면 0, 없다면 그냥 더한다.
                // 중간에 0이 여러개일 경우 -> 음수에 곱하는 수 빼고는 버린다.
                // 양수가 홀수일 경우 -> 가장 작은 수를 더한다.
                // 양수는 양수끼리 묶음 단, 양수가 1이라면 더하는 게 이득

                if ((negative.size() & 1) == 1 ){
//                        System.out.println(" += "+negative.get(negative.size()-1));
                        if (zeros.size() == 0)  sum += negative.get(negative.size()-1);
                }
                for (int i = 0; i + 1 < negative.size(); i+= 2){
                        sum += negative.get(i) * negative.get(i+1);
//                        System.out.println(" += "+negative.get(i) * negative.get(i+1));

                }
                int i =  positive.size()-1;
                while (i >= 0){
                        if (i - 1 >= 0 && positive.get(i - 1) != 1){
//                                System.out.println(" += "+  positive.get(i) * positive.get(i-1));
                                sum += positive.get(i) * positive.get(i-1);
                                i -= 2;
                        }else{
//                                System.out.println(" += "+ positive.get(i));
                                sum += positive.get(i--);
                        }
                }
                System.out.println(sum);
        }
}