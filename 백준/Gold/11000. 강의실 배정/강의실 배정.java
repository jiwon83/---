import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
        static ArrayList<int[]> list = new ArrayList<>();
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer st;

        public static void main(String[] args) throws IOException {
                int N = Integer.parseInt(br.readLine());
                for (int i=0; i<N; i++){
                        st=new StringTokenizer(br.readLine());
                        int start = Integer.parseInt(st.nextToken());
                        int end = Integer.parseInt(st.nextToken());
                        list.add(new int[]{start,end});

                }
                Collections.sort(list, (o1, o2) -> o1[0] != o2[1]? o1[0] - o2[0] : o1[1]-o2[1] );

                PriorityQueue<Integer> pq = new PriorityQueue<>();
                pq.add(list.get(0)[1]);
                int ans=1;
                for (int i = 1; i<N; i++){
                        if (pq.peek() <= list.get(i)[0]){
                                pq.poll();

                        }
                        pq.add(list.get(i)[1]);
                        ans = Math.max(ans,pq.size());
                }

                System.out.println(ans);
        }
    
}