import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
/*
5
55 185
58 183
88 186
60 175
46 155
 */
public class Main {
    static class P{
        int x, y, upCnt, order;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
            this.upCnt=0;
            order = -1;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    ", upCnt=" + upCnt +
                    ", order=" + order +
                    '}';
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;


    public static void main(String[] args) throws IOException {
        N= Integer.parseInt(br.readLine());
        P [] people = new P[N+1];
        int [] upCnt = new int[N+1];
        PriorityQueue<P> pq = new PriorityQueue<>(Comparator.comparingInt( o -> o.upCnt));
        for(int i = 1; i<= N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            people[i] = new P(x, y);
        }
        for (int i = 1; i<= N; i++){
            P me = people[i];
            int bigger = 0;
            for(int j = 1; j<= N; j++){
                if(i == j) continue;
                P other = people[j];
                //x, y비교
                if (me.x < other.x && me.y < other.y){ //나보다 크다면
                    bigger++;
                }

            }
            me.order = bigger+1;
//            me.upCnt = count;
//            pq.add(me);
        }
//        int nowOrder = 1;
//        int nowParCnt = 0;
//        int nowNodeCnt = 0;
//        while (!pq.isEmpty()){
//            //하나씩 꺼내서 순위를 매긴다.
//            P now = pq.poll();
//
//            if (nowParCnt == now.upCnt){
//                now.order = nowOrder;
//                nowNodeCnt++;
//            }else{
//                nowOrder = nowNodeCnt + 1;
//                nowNodeCnt++;
//                now.order =nowOrder;
//                nowParCnt = now.upCnt;
//            }
////            System.out.println(now);
//
//        }
        for(int i = 1; i<=N; i++){
            sb.append(people[i].order+" ");
        }
        System.out.println(sb);

    }
    static int [] dx = {0,1, 0, -1}, dy = {1,0, -1, 0};
    static boolean inArea(int x, int y){
        return x >= 0 && y >= 0 && x <N && y <N;
    }

}
