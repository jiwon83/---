package implement.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
실버 3 : 반지 https://www.acmicpc.net/problem/5555
시도: X
 */
public class BOJ5555반지 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String target;
    static int N,cnt;

    public static void main(String[] args) throws IOException{
        target = br.readLine();
        N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(br.readLine());
            sb.append(sb);
            if (sb.toString().contains(target)) cnt++;
        }
        System.out.println(cnt);

    }

}
