package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ8911_거북이 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [] dx = {0,1,0,-1};
    static int [] dy = {1,0,-1,0};
    static String command;

    static void input() {
        int T = sc.nextInt();
        while (T-- > 0){
            command= sc.next();
            pro();
        }
    }
    static void move(char com){
        //com과 현재 방향에 따라 이동하고, 값들을 갱신.
        switch (com){
            case 'F':
                nowX = nowX + dx[nowDirect];
                nowY = nowY + dy[nowDirect];

                break;
            case 'B':
                nowX = nowX - dx[nowDirect];
                nowY = nowY - dy[nowDirect];

                break;
            case 'L':
                nowDirect = nowDirect-1;
                if (nowDirect==-1) nowDirect=3;

                break;
            case 'R':
                nowDirect = (nowDirect+1) % 4;
                break;
        }
        maxX = Math.max(maxX, nowX);
        maxY = Math.max(maxY, nowY);
        minX = Math.min(minX, nowX);
        minY = Math.min(minY, nowY);
    }
    static int maxX, maxY, minX, minY;
    static int nowX, nowY, nowDirect;
    static void pro() {
        minX =0; minY=0; maxX=0; maxY=0;
        nowX =0; nowY=0; nowDirect=0; // 북 0 동 1 남 2 서 4
        for (int i=0; i<command.length(); i++){
            char com = command.charAt(i);
            move(com);
        }
        int result = (Math.abs(maxY-minY))*(Math.abs(maxX-minX));
        sb.append(Math.abs(result)).append("\n");

    }
    public static void main(String[] args) {
        input();
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
