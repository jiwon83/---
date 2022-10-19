package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class HIdeAndSeek4 {

	static int [] times = new int[100000+1]; //걸린 시간을 담을 배열, index가 위치
	
	public static void main(String[] args) throws IOException {
		
		

		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		String input = br.readLine();
		String [] inputs = input.split(" ");
		
		int N = Integer.valueOf(inputs[0]);
		int K = Integer.valueOf(inputs[1]);
		
		System.out.println(bfs(N,K));
	}

	private static int bfs(int N, int K) {
		
		Queue<Integer> queue = new LinkedList<Integer>(); //다음 노드를 담을 queue
		
		int node = N; // 현재 찾는자의 위치 노드이자 times배열의 index.
		times[node]=0;//초기화, 찾는자의 처음 위치에 걸린시간은 0이다.
		queue.add(node);
		
		while(!queue.isEmpty()) { //queue에 노드가 있는 동안 반복
			node = queue.remove(); //삭제와 동시에 반환
			
			if(times[node]==K) {	//K를 찾았다면
				return times[node];
			}
			if( times[node-1]==0 && node-1 >=0) {
				times[node-1] = times[node] + 1;
				queue.add(node-1);
			}
			if( times[node+1]==0 && node+1 <=100000) {
				times[node+1] = times[node] + 1;
				queue.add(node+1);
			}
			if( times[node*2]==0 && node*2 <=100000) {
				times[node*2] = times[node] + 1;
				queue.add(node*2);
			}
			
		}
		return -1;
		
	}

}
