package implement.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18312_시각 {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, cnt;
    static String K;

    public static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = st.nextToken();
    }

    public static void main(String[] args) throws IOException {
        input();
        for (int i=0; i<=N; i++){
            String hour = String.format("%02d",i);
            for (int j=0; j<60; j++){
                String min = String.format("%02d",j);
                for (int k = 0; k<60; k++){
                    String sec = String.format("%02d",k);

                    if (hour.contains(K) || min.contains(K) || sec.contains(K)) cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

}
