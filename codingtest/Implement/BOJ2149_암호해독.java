
import java.io.*;
import java.security.cert.PolicyNode;
import java.util.*;

public class Main {
    static StringBuilder original = new StringBuilder();
    static FastReader sc = new FastReader();
    static char [][] sorted_arr, ori_arr;
    static String sorted_key, ori_key;
    static String modified;
    static int unit;
    static int R , C;

    static void input() {
        ori_key = sc.nextLine();
        modified = sc.nextLine();
        C = ori_key.length();
        unit = modified.length() / ori_key.length();
        R = unit;
        sorted_arr = new char[R][C];
        ori_arr = new char[R][C];
    }
    static void pro() {
        //1. 정렬된 배열로 바꾼다.
        for (int c =0; c<C; c++){
            for (int r =0; r<R; r++ ){
                sorted_arr[r][c] = modified.charAt(unit * c + r);
            }
        }
//        System.out.println(Arrays.deepToString(sorted_arr));

        //2. 이를 원래 정렬되징 낳은 키로 재정렬해서 ori_arr에 집어 넣는다.
        char [] temp = ori_key.toCharArray();
        Arrays.sort( temp );
        sorted_key = new String (temp);

        boolean [] visit = new boolean[ori_key.length()];
        for (int i=0; i< ori_key.length(); i++){
            char stand = ori_key.charAt(i);
            for (int j=0; j<sorted_key.length(); j++){
                if (visit[j]) continue;
                if(stand == sorted_key.charAt(j)){
                    visit[j] = true;
                    for (int w=0; w<unit; w++){
                        ori_arr[w][i] = sorted_arr[w][j]; //copy array
                    }
                    break;

                }
            }
        }
//        System.out.println(Arrays.deepToString(ori_arr));

        //3. 가로 방향으로 평문을 만든다.
        for (int r = 0; r < R; r++){
            for (int c=0; c <C; c++){
                original.append(ori_arr[r][c]);
            }
        }

        System.out.println(original);

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
