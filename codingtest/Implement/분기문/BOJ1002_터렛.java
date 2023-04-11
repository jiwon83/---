import java.util.*;
import java.io.*;
public class Main2 {

    /*
    4
1 1 1 1 1 1
1 1 1 1 1 5
-1 0 1 0 0 5
0 0 4 0 -2 2


//-1 (일치)
//0
//0
//1 (내접)
     */
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int x1, y1, r1, x2,y2, r2;
    static double maxR, minR, distance;

    static void input() {
        x1 = sc.nextInt();
        y1 = sc.nextInt();
        r1 = sc.nextInt();
        x2 = sc.nextInt();
        y2 = sc.nextInt();
        r2 = sc.nextInt();
    }
    static void pro() {
        maxR = Math.max(r1, r2);
        minR = Math.min(r1, r2);
        distance = Math.sqrt( Math.pow(x2-x1,2) + Math.pow(y2-y1,2) );

        if (distance==0L && maxR ==minR) sb.append(-1).append("\n");
        else if (distance < maxR - minR) {
            sb.append(0).append("\n");
        }
        else if (distance == maxR - minR){ //내접
            sb.append(1).append("\n");
        } else if (distance > maxR - minR && distance < maxR + minR) {
            sb.append(2).append("\n");
        } else if (distance == maxR + minR){ //외접
            sb.append(1).append("\n");
        }
        else  sb.append(0).append("\n");
    }
    public static void main(String[] args) {
//        System.out.println(0.0 >= 0L && 0.0 < 5.0);
        int T=sc.nextInt();
        while (T-- >0){
            input();
            pro();
        }
        System.out.println(sb);

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