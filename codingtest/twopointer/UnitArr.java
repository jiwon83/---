import java.io.*;
import java.util.*;

public class Main {
    static int N, S, result;
    static int[] arr;
    StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
    }

    static void pro() {
        result = N + 1;
        int sum = 0, R = 0;
        for (int L = 1; L <= N; L++) { //L은 1~ N까지 모두 이동
            sum -= arr[L - 1];

            while (R + 1 <= N && sum < S) { //R은 오른쪽으로만 이동, 각 L에서 sum이 S가 되기 전까지만 이동
                sum += arr[++R];
            }

            if (sum >= S) {
                result = Math.min(result, R - L + 1);

            }

        }
        if (result == N + 1) {
            result = 0;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        input();
        pro();
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();


//        bw.write(sb.toString());
//        bw.flush();//FileWriter 내부 버퍼의 내용을 파일에 writer합니다
//        bw.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
//        br.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.

    }
}

