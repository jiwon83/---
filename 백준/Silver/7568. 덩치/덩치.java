import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        N= Integer.parseInt(br.readLine());
        Point [] people = new Point[N+1];
        for(int i = 1; i<= N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            people[i] = new Point(x, y);
        }
        for (int i = 1; i<= N; i++){
            Point me = people[i];
            int bigger = 0;
            for(int j = 1; j<= N; j++){
                Point other = people[j];
                if (me.x < other.x && me.y < other.y){ //나보다 크다면
                    bigger++;
                }
            }
            sb.append((bigger+1)+" ");
  
        }
        System.out.println(sb);
    }
}
