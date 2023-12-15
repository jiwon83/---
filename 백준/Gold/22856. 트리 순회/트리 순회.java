import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

/*
test-case

6
1 2 3
2 4 5
3 6 -1
4 -1 -1
5 -1 -1
6 -1 -1

3
1 2 3
2 -1 -1
3 -1 -1

2
1 2 -1
2 -1 -1
 */
public class Main {
  static class Node{
    int l, r, p;
    public Node(int l, int r){
      this.l =l;
      this.r = r;
    }
  }
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static boolean [] visit;

  static Node[] graph;

  static int N, end_node;
  static int cnt;

  static void sol() throws IOException{
    init();
    end_node = findEnd(1);
    inOrder(1);
    System.out.println(cnt-1);
  }
  static int findEnd(int x){
    if (graph[x].r != -1){
      return findEnd(graph[x].r);
    }
    return x;
  }
  static void init() throws IOException{
    N = Integer.parseInt(br.readLine());
    visit = new boolean[N+1];
    graph = new Node[N+1];
    for (int i = 1; i<=N; i++){
      st = new StringTokenizer(br.readLine());
      int num = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph[num] = new Node(a, b);
    }
  }
  static void inOrder(int x){
    cnt++;
    int left = graph[x].l;
    int right = graph[x].r;
    if(left != -1 && !visit[left]){
      graph[left].p = x;
      visit[left] = true;
      inOrder(left);
    }else if(right != -1 && !visit[right]){
      graph[right].p = x;
      visit[right] =true;
      inOrder(right);
    }else if(x == end_node){
      return;
    }else{
      inOrder(graph[x].p);
    }
  }

  public static void main(String[] args) throws IOException {
    sol();
  }
}
