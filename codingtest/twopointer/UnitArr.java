import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UnitArr {
    static int [] a;
    static int [] b;
    static StringBuilder sb = new StringBuilder();
    static int N,M;

    static void input(){
        UnitArr2.FastReader2 scan = new UnitArr2.FastReader2();

        N = scan.nextInt();
        M = scan.nextInt();

        a = new int[N];
        b = new int[M];
        for (int i=0; i<N; i++){
            a[i]= scan.nextInt();
        }
        for (int i=0; i<M; i++){
            b[i]= scan.nextInt();
        }


    }
    static void pro(){
        int L =0, R=0;
        while (true){
            if (L >= a.length){
                for (int i=R; i<b.length; i++){
                    sb.append(b[i]+" ");
                }
                break;
            }
            if (R >= b.length){
                for (int i=L; i<a.length; i++){
//                    System.out.println(i+":"+a[L]);
                    sb.append(a[i]+" ");
                }
                break;
            }

            if (a[L]<b[R]) sb.append(a[L++]+" ");
            else sb.append(b[R++]+" ");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) {
        input();
        pro();


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
