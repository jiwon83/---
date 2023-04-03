import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, ans=0;

    static List<Integer> [] points = new ArrayList[5001];

    static void input() {
        N= sc.nextInt();

        for (int i=1; i<=5000; i++){
            points[i] = new ArrayList<>();
        }
        for (int i=0; i<N; i++){
            String [] input = sc.nextLine().split(" ");
            int color = Integer.parseInt(input[1]);
            points[color].add(Integer.parseInt(input[0])); //위치를 넣는다.
        }

        for (int i=1; i<=5000; i++){
            Collections.sort(points[i]); //색별로 정렬
        }

        for (int i=1; i<=5000; i++){
            for (int j=0; j<points[i].size(); j++){

                //양쪽 중 가까운 곳으로 이동해야 함.
                int R = (j != points[i].size()-1 ) ? points[i].get(j+1) - points[i].get(j): Integer.MAX_VALUE;
                int L = (j != 0) ? points[i].get(j) - points[i].get(j-1): Integer.MAX_VALUE;
                ans += Math.min(R, L);

            }
        }
        System.out.println(ans);


    }
    static void pro() {

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