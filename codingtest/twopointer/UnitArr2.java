import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UnitArr2 {
    static int [] arr ;
    static int N,M;
    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader2 scan = new FastReader2();

        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[N+M];
        for (int i=0; i<N; i++){
            arr[i]= scan.nextInt();
        }
        for (int i=N; i<M+N; i++){
            arr[i]= scan.nextInt();
        }

    }
    public static void main(String[] args) {
        input();
        Arrays.sort(arr);
        for (int i=0; i<arr.length; i++){
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }
    static class FastReader2 {
        BufferedReader br;
        StringTokenizer st;

        public FastReader2() {
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
