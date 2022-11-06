import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class TwoSolution {
    static int N;
    static int [] arr;
    static int [] answer = new int[2];
    static FastReader sc = new FastReader();

    static void input(){
        N = sc.nextInt();
        arr = new int[N];

        for (int i=0; i<N; i++){
            arr[i]= sc.nextInt();
        }
        //정렬
        Arrays.sort(arr);
    }
    static void pro(){
        int L, R, abs=2000000000;
        L = 0; R = arr.length-1;
        while (L<R){
            if (arr[L]+arr[R] > 0){
                if (arr[L]+arr[R] ==0){
                    answer[0] = L;
                    answer[1] = R;
                    return;
                }
                if ( Math.abs(arr[L] + arr[R]) < abs) {
                    abs =  Math.abs(arr[L] + arr[R]);
                    answer[0] = L;
                    answer[1] = R;
                }
                R--;

            }
            if (arr[L]+arr[R] < 0){
                if (arr[L]+arr[R] ==0){
                    answer[0] = arr[L];
                    answer[1] = arr[R];
                    return;
                }
                if (Math.abs(arr[L]+arr[R])<abs) {
                    abs = Math.abs(arr[L] + arr[R]);
                    answer[0] = arr[L];
                    answer[1] = arr[R];
                }
                L++;

            }
        }
    }


    public static void main(String[] args) {
        input();
        pro();
        System.out.println(answer[0]+" "+answer[1]);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

        }
        String next(){

            while (st == null || !st.hasMoreTokens()){  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
