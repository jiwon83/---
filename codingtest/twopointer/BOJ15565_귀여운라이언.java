import java.io.*;
import java.util.*;

public class Main{
    public static int N,K;
    public static int [] A;
    public static int ans= Integer.MAX_VALUE;
 
    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[N+1];
        for (int i=1; i<=N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void pro(){
        int count =0;
        for (int L=1,R = 0;L <= N; L++){
            while ( R + 1 <= N && count < K ){
                R++;
                if (A[R]==1) count++;
            }
            if (count >= K){
                ans = Math.min(ans, R-L+1);
            }
            if (A[L]==1) count--;
        }
        
        if (ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }

    }
    public static void main(String[] args) throws IOException {

        input();
        pro();
    }
}
