package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
dp 완전 기초 1+2+3으로 더하기
https://www.acmicpc.net/problem/9095
 */
public class Add {
    static int N, T;
    static int [] Dy;
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static void input(){
        N = sc.nextInt();
    }
    static void pro(){

        Dy = new int[11];
        preprocess();

        T = sc.nextInt();
        while (T-- >0){
            input();
            sb.append(Dy[N]).append('\n');

        }
        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
        System.out.println(sb);
    }

    static void preprocess(){
        //초기값 지정
        Dy[1]=1;
        Dy[2]=2;
        Dy[3]=4;
        //값 넣어주기>for문
        for (int i=4; i<=10; i++){
            Dy[i] = Dy[i-3]+ Dy[i-2]+ Dy[i-1];
        }

    }
    public static void main(String[] args) {
        pro();
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
