import java.awt.*;
import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Port{
        Point from, to;

        public Port(int x1, int y1, int x2, int y2) {
            this.from = new Point(x1, y1);
            this.to = new Point(x2, y2);
        }


    }
    static Point start, end;
    static Port [] ports;
    static int [][] memo;
    static long ans = Long.MAX_VALUE;
    static StringTokenizer st ;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ports = new Port[6];

        // input
        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()) );
        st = new StringTokenizer(br.readLine());
        end = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()) );
        for (int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            ports[i] = new Port(x1, y1, x2, y2);
            ports[i+3] = new Port(x2, y2, x1, y1);
        }


        Arrays.fill(select, -1);

        //부분집합 생성
        recur(1);
        //결과 출력
        System.out.println(ans);
//        bw.write(ans);

//        bw.write((N % 2 == 1) ? "SK" : "CY");
        bw.flush();
        bw.close();
        br.close();
    }
    static int [] select = new int[7]; // 기본값 -1, 텔레포트 index를 저장, 순서 있게
    static boolean [] ch = new boolean[7];

    static void recur(int k){
        //현재 선택된 것으로 거리 측정 및 갱신
//        System.out.println(Arrays.toString(select));
        long result =getTotalDistance(select);
        ans = Math.min(ans, result);
//        System.out.println("result : "+ result);
        if(k == 6+1){
            return;
        }
        for (int idx = 0; idx<6; idx++){ // 텔레 포트 사용
            if (ch[idx]) continue;
            select[k] = idx;
            ch[idx] = true;
            recur(k+1);
            select[k] = -1;
            ch[idx] = false;
        }
    }
    static long getTotalDistance(int [] select){
//        System.out.println("Main.getTotalDistance");
//        System.out.println(Arrays.toString(select));
        long dist = 0;
        List<Integer> visited = new ArrayList<>();

        for (int i= 1; i<=6; i++){
            if (select[i] != -1) visited.add(select[i]);
        }
        if (visited.size() == 0){
            return getDist(start, end);
        }
//        System.out.println(visited);

        for (int to = 0; to < visited.size(); to++){
            Port port = ports[visited.get(to)];
//            System.out.println("port to= "+ port);
            if(to==0){ //start -> i까지

                dist +=getDist(start, port.from);
//                dist += getDist(port.from, port.to);
                dist += 10;
//                System.out.println(" 증가 첫번째에서 "+ dist);
                if (port.from == end) break;
                continue;
            }
            Port beforePort = ports[visited.get(to-1)];
            //i-1 ~ i까지
            dist +=getDist(beforePort.to, port.from);
//            dist +=getDist(port.from, port.to);
            dist += 10;
//            System.out.println(" 증가  "+ dist);
            if (port.from == end) break;
        }
        //3~end 까지
        if (visited.size() != 0){
            Port finalPort = ports[visited.get(visited.size()-1)];

            dist +=getDist(finalPort.to, end);
//            System.out.println(" 증가 마지막 "+ dist);
        }

        return dist;
    }
    static int getDist(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }


}