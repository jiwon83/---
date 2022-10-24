package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 바이러스 실버3 https://www.acmicpc.net/problem/2606
 * 시도:X O
 * 체감 난이도: 중하
 */
public class Virus {
	
	//이중 ArrayList vs ArrayList배열 => 간선의 수가 매우 많거나, 노드의 수가 아주 많다면 배열이 유리. 반대의 경우라면 arrayList가 유리함.
	//public static ArrayList<Integer>[] graph = new ArrayList[101];
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();//index를 key처럼 사용하고 있음.
	public static boolean[] tf = new boolean[101];
	public static int count=0;

	public void dfs(int node) {
		if(tf[node]) return; //이미 방문한 노드는 return.
		tf[node] =true;
		count ++;
		for(int i=0; i<graph.get(node).size(); i++) { // 연결 노드의 갯수만큼 반복, 2개이면 2번 
			if(!graph.get(node).isEmpty()) {
				dfs(graph.get(node).get(i));
			}
			
		}
	}
	
	public void solve() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine()); //노드
		int M = Integer.parseInt(br.readLine()); // 간선

		for (int i = 1; i <= N; i++) { //노드 수만큼 arraylist생성, index가 노드의 지표이다. 즉, 노드 2의 연결 노드 정보는 graph.get(2)에 있을 것.
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			int[] chains = new int[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			chains[0] = Integer.parseInt(st.nextToken());
			chains[1] = Integer.parseInt(st.nextToken());
			
			graph.get(chains[0]).add(chains[1]);
			graph.get(chains[1]).add(chains[0]);//양방향
	        
		}
		
		dfs(1);
		System.out.println(count-1);//node 1은 제외
	
	}

	public static void main(String[] args) throws IOException {
		Virus vi = new Virus();
		vi.solve();

	}


}
