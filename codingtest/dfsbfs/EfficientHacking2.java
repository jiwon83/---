package DFSBFS;
/*
 * 실버1 효율적인 해킹 https://www.acmicpc.net/problem/1325 신뢰방향 그대로(해킹방향과 반대) 풀이
 * x(해답 봄)
 * 체감 난이도: 중
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class EfficientHacking2 {

	public static ArrayList<Integer>[] data = new ArrayList[10001];
	public static boolean [] visited = new boolean[100001];//index가 노드
	public static int M, N, max; //N는 노드의 수, M은 간선의 수, max는 최대 연결된 노드의 수
	public static int[] saved = new int[10001]; //연결된 노드의 수를 저장할 배열, index가 노드 번호
	public static StringBuffer result = new StringBuffer();
	


	public static void bfs(int x) {

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(x); //큐에 넣기, add는 큐가 꽉찬 경우 error를 발생시키기 때문.
		visited = new boolean[10001];//초기화, 가장 상위의 노드 x 에따른 방문여부 visited를 초기화 해주어야 한다. 예시의 경우 1,2 가 상위 노드인데, 2에서도 계산할 수 있어야 한다. 
		visited[x]= true;
		
		while(!q.isEmpty()) {
			int now = q.poll();//꺼내면서 삭제. 
//			for(int i =0; i<data.get(now).size(); i++) {
			for(int i =0; i<data[now].size(); i++) {
//				int y = data.get(now).get(i);
				int y = data[now].get(i);
				if(!visited[y]) {
					q.offer(y);
					visited[y]=true;
					saved[y]++; // x가 현재 탐색의 가장 상위노드이므로, x에서의 연결노드수인 saved[x]에 +1 해준다.  
				}
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//0~N까지 인덱스의 2중 arrayList 초기화. 여기서 0은 사용하지 않는다. index가 상위노드 번호.
		for(int i=1; i<=N; i++) {
//			for(int i=0; i<=N; i++) {
			data[i]=new ArrayList<Integer>();
//			data.add(new ArrayList<Integer>());
		}
		//데이터를 넣어준다. 이때, 문제에서 y를 해킹하면 x도 해킹할 수 있다. y->x. 상위노드가 y이므로, 인덱스y에 값 x를 add한다.
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
//			data.get(y).add(x);
			data[x].add(y);
			
			
		}
		for(int i=1; i<=N; i++) {
			bfs(i);
		}
		//최대 깊이를 찾는다. 
		for(int i=1; i<=N; i++) {
			max = Math.max(max, saved[i]);
		}
		//String buffer에 넣는다.
		for(int i=1; i<=N; i++) {
			if(saved[i]==max) {
				result.append(i+" ");
			}
		}
		System.out.println(result);

	}



}
