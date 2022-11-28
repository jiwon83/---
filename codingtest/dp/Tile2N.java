package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

/*
https://www.acmicpc.net/problem/11726
 */
public class Tile2N {
    static int N;
    static int[] Dy;

    static void input() {
        FastReader sc = new FastReader();
        N = sc.nextInt();
        Dy = new int[1001];
        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
    }

    static void preprocess() {
        //초기값
        Dy[1]=1;
        Dy[2]=2;
        //점화식
        for (int i=3; i<=N; i++){
            Dy[i] = ( Dy[i-1]+ Dy[i-2] )  % 10007;
        }
    }

    static void pro() {
        input();
        preprocess();
        System.out.println(Dy[N]);

    }

    public static void main(String[] args) {
        pro();
    }

    static class FastReader{
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
            long nextLong(){return Long.parseLong(next()); }

            double nextDouble(){return Double.parseDouble(next());}

            String nextLine(){
                String str ="";
                try {
                    str = br.readLine();

                }catch (IOException e){
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
