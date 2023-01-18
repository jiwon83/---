import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static String command;
    static int maxX, minX, maxY, minY, nowDir, nowX, nowY;
    static int [] diX ={0,1,0,-1}, diY = {1,0,-1,0};

    static void input() {
        command = sc.next();
    }
    static void pro() {
        nowX=0; nowY=0; maxX=0; minX=0;minY=0;maxY=0;nowDir=0;
        for (int i=0; i<command.length(); i++){
            char c = command.charAt(i);
            switch(c){
                case 'F':
                    nowX += diX[nowDir];
                    nowY += diY[nowDir];
                    break;
                case 'B':
                    nowX -= diX[nowDir];
                    nowY -= diY[nowDir];
                    break;
                case 'L':
                    nowDir -= 1;
                    if (nowDir==-1) nowDir=3;
                    break;
                case 'R':
                    nowDir = (nowDir+ 1) % 4;
                    break;
            }
            minX = Math.min(minX, nowX);
            maxX = Math.max(maxX, nowX);
            minY = Math.min(minY, nowY);
            maxY = Math.max(maxY, nowY);
        }
        System.out.println((maxX - minX) * (maxY - minY));

    }
    public static void main(String[] args) {
        int T = sc.nextInt();
        while (T-- >0 ){
            input();
            pro();
        }
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




