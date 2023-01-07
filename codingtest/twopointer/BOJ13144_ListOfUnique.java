package twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BOJ13144_ListOfUnique {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static long ans;
    static int [] A;
    static void input() {
        N = sc.nextInt();
        A = new int[N+1];

        for (int i=1; i<=N; i++){
            A[i] = sc.nextInt();
        }
    }
    static void pro() {
        find(A);
        System.out.println(ans);
    }

    private static void find(int[] a) {
        int R=0;
        for (int L =1; L <=N; L++){
            //R이동
            R = L;
            while (R <= N){

//                int[] destArray = new int[R - L + 1 + 1];
                int[] destArray = new int[R - L + 1 ];
                System.arraycopy(a, L, destArray, 0, R-L+1);
                if (check(destArray)){
//                    System.out.println(Arrays.toString(destArray));
                    ans++;
                }
                R++;
            }
        }
    }

    private static boolean check(int[] destArray) {
        //index 1부터 사용
        //1. set으로 변환
        Set<Integer> set = new HashSet<Integer>(Arrays.stream(destArray).boxed().collect(Collectors.toList()));
        return set.size()==destArray.length? true: false;
    }

    public static void main(String[] args) {
        input();
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
