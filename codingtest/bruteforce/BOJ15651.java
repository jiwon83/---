package bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/*
실버 3 N과 M https://www.acmicpc.net/problem/15651
시도: X
** 잘 이해안감. 그냥 외울 것.
 */
public class BOJ15651 {
    static int N, M;
    static int [] selected;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader sc = new FastReader();
        N = sc.nextInt(); //1~N 까지의 수
        M = sc.nextInt(); //길이가 M, M개를 뽑는 것.
        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
        selected = new int[M+1];
    }

    static void rec_func(int k) {
        if (k==M+1){ //M만큼 다 뽑았다면,
            for (int i=1; i<=M; i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append('\n');
        }else{  //그렇지 않다면,
            //1~ N까지 자연수를 전부 탐색한다.
            // recursive function을 이용해서
            //중복을 허용.
            for (int cand=1; cand<=N; cand++){//1~4
                selected[k]=cand;//1 1 k=3 -> 1 0 -> 1 2 /1 3 k=2 k=1
                rec_func(k+1);
               // selected[k]=0;//해줘도되고 안해줘두 됨.
            }


        }

    }

    public static void main(String[] args) throws IOException {
        input();
        rec_func(1);
        //출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();//FileWriter 내부 버퍼의 내용을 파일에 writer합니다
        bw.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
