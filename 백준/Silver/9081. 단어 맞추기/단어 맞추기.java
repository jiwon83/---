import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringBuilder ans = new StringBuilder();

        public static void main(String[] args) throws IOException {
             //주어진 알파벳-> int로 변환 후 사전순으로 정렬
             //b e e r 2 3 3 4
             //beer -> bere 다음 단어를 찾는다.
                int N = Integer.parseInt(br.readLine());
                for (int i = 0; i < N; i++){
                        String input = br.readLine();
                        int [] alphabets = new int[input.length()];
                        for(int j = 0; j < alphabets.length; j++){
                                alphabets[j] = input.charAt(j)-'A';
                        }
                        ans.append(getNextPermutation(alphabets)+"\n");
                }
                System.out.println(ans);


        }
        static String getNextPermutation(int [] alphabets){
//                System.out.println("Main.getNextPermutation" + Arrays.toString(alphabets));

                int pivot = alphabets.length-1;
                int target = alphabets.length-1;
                //1 2 4 3
                //오름차순 마지막점 pivot 찾는다.
                while (pivot - 1 >= 0 && alphabets[pivot-1] >= alphabets[pivot]){
                        pivot--;
                }
                if (pivot == 0){
                        return integersToString(alphabets);
                }
                //pivot-1의 다음 큰 지점을 찾는다.
                while (target -1 >= pivot && alphabets[pivot-1] >= alphabets[target]){ // pivot-1이 더 작은 동안 반복
                        target--;
                }
//                System.out.println("pivot-1: "+ (pivot-1) + " , target: "+ target  );
                //swap
                swap(pivot - 1, target, alphabets);
//                System.out.println("swqp 후 "+ Arrays.toString(alphabets));
                //뒤를 오름차순으로 정렬한다.
                reverse(pivot, alphabets.length-1, alphabets);
//                System.out.println("정렬 후 "+ Arrays.toString(alphabets));
                

                return integersToString(alphabets);

        }
        static String integersToString(int [] alphabets){
                StringBuilder sb = new StringBuilder();
                for (int i : alphabets){
                        sb.append((char)(i+'A'));
                }
                return sb.toString();
        }
        static void swap(int a, int b, int [] alphabets){
                int temp = alphabets[a];
                alphabets[a] = alphabets[b];
                alphabets[b] = temp;
        }
        static void reverse(int s, int e, int [] alphabets){
                int L = s; int R = e;
                while (L < R){
                       swap(L++, R--, alphabets);
                }
        }

}