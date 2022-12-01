package topologicalSort;
import java.io.*;
import java.util.*;
/*
골드 3 줄세우기 https://www.acmicpc.net/problem/2252
시도: 위상정렬의 가장 기본, 반복해도 좋으나 조금 변경된 문제를 풀어봐도 좋을듯.
 */
public class BOJ2252 {
    static int N,M;
    static int []  indeg; //내쪽으로 들어오는 간선의 갯수를 저장하는 배열
    static ArrayList<Integer> [] graph;
    static ArrayList<Integer> sorted;
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static void input() {
          N = sc.nextInt();
          M = sc.nextInt();
          indeg = new int[N+1];
          graph = new ArrayList[N+1];
          for (int i=1; i<=N; i++){
              graph[i]= new ArrayList<>();
          }
          for (int i=0; i<M; i++){
              int a = sc.nextInt();
              int b = sc.nextInt();
              graph[a].add(b); //graph에 add
              indeg[b]++; //indegree 증가
          }
    }
    static void pro() {

        Deque<Integer> q = new LinkedList<>(); //deque는 queue와 달리 양쪽으로 add poll이 가능
        //초기화, indeg인 0인 것들을 먼저 정렬
        for (int i=1; i<=N; i++){
            if (indeg[i]==0) q.add(i);
        }
        while (!q.isEmpty()){
            int vertex = q.poll();
            sb.append(vertex).append(" ");
//            sorted.add(vertex);
            for (int y : graph[vertex]){ // que에서 꺼낸 vertex에서 연결된 모든 vertex에서
                indeg[y]--; //vertex의 삭제 처리 => 대상 vertex indegree의 갯수를 -- 처리
                if (indeg[y]==0) q.add(y); //만약 indegree가 0이면 가장 위상이므로 큐에 넣어준다.
            }
        }

        //sorted출력
//        for (int i=0;i<sorted.size(); i++){
//            sb.append(sorted.get(i)).append(" ");
//        }
        System.out.println(sb);

    }
    public static void main(String[] args) {
        input();
        pro();

        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
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
