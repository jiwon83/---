package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
실버 2: 트리의 부모 찾기 https://www.acmicpc.net/problem/11725
시도: O
 */
public class BOJ11725 {
    static int N;
    static int [] parents;
    static boolean [] visit;
    static ArrayList<Integer> [] graph;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1]; //graph의 index = 노드 번호
        for (int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        visit = new boolean[N+1];
        parents = new int[N+1];
       
        for (int i=1; i<= N-1; i++){ //간선을 graph에 적용
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        br.close();
    }
    /*
    v1 : boolean[] visit 사용
    static void dfs(int x){
        //parents갱신
        visit[x] = true;
        for (int i=0; i < graph[x].size(); i++ ){
            int next = graph[x].get(i);
            if (visit[next] == true) continue;
            parents[next] = x;
            dfs(next);
        }

    }
*/

    /*
    v2 : visit 없이, parent와 연결된 노드인 자식을 찾아주는 함수
          grandParent 이용  (x의 parent와 일치하면 continue;)
     */
    static void dfs(int x, int par){
        for (int y : graph[x]){
            if (y==par) continue;
            parents[y]=x;
            dfs(y,x);
        }
    }
    static void pro() {
        dfs(1, -1);
        for (int i=2; i<=N; i++){
            sb.append(parents[i]).append('\n');
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        System.out.println(sb);
    }
}
