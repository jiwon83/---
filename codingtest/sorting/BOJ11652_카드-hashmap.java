import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static int MAXCnt;
    static long result;
    static long [][] nums;
    static HashMap<Long, Integer> hm = new HashMap<>();

    static void input() {
        N= sc.nextInt();
        for(int i=0; i<N; i++){
            long num = sc.nextLong();
            hm.put(num, hm.getOrDefault(num,0)+1);
        }
        for (Map.Entry<Long, Integer> entry : hm.entrySet()){
            if (MAXCnt < entry.getValue()){
                MAXCnt = entry.getValue();
                result = entry.getKey();
            }else if(MAXCnt== entry.getValue() && result > entry.getKey()){
                result = entry.getKey();
            }
        }
        System.out.println(result);

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