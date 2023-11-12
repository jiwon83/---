import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringBuilder ans = new StringBuilder();
        static int N, M;
        public static void main(String[] args) throws IOException {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                System.out.println(getMaxMove());
        }
        static int getMaxMove() {
                if (N == 1) {// M의 범위와 상관없이 한 번 밖에 이동 못함
                        return 1;
                } else if (N == 2) {
                        if (M % 2 == 0) return (M / 2) < 5 ? (M / 2) : 4;
                        else return (M / 2 + 1) < 5? (M / 2 + 1) : 4;
                } else {
                        if (M <= 4) {
                                return M;
                        } else if (M <= 5) {
                                return 4;
                        } else {
                                return M - 2;
                        }
                }
        }

}