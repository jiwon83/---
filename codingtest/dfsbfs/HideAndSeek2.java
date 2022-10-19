package DFSBFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 실버1 숨바꼭질 https://www.acmicpc.net/problem/1697
 * 시도: X, X
 * 체감 난이도: 상
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class HideAndSeek2 {
	static int N;
	static int K;
	static int visited[] = new int[100001];
	
	public void solve() {
	}
	private void printTupleArr(ArrayList<ArrayList<Integer>> arr) {
		// TODO Auto-generated method stub
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < arr.get(i).size(); j++) {
				System.out.print(arr.get(i).get(j) + " ");
			}
			System.out.println();
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		String input = br.readLine();
		String [] inputs = input.split(" ");
		
		N = Integer.valueOf(inputs[0]);
		K = Integer.valueOf(inputs[1]);
		
		int result =bfs(N);
		System.out.println(result);

	}
	private static int bfs(int node) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(node);
		int index = node;
		int n =0;
		visited[index] = 1;
		while(queue.isEmpty()==false) {
			n = queue.remove();
			
			if(n ==K) {
				return visited[n]-1;
			}
			
			if(n-1>=0 && visited[n-1]==0 ) {
				visited[n-1] = visited[n]+1;
				queue.add(n-1);
				
			}
			if(n+1 <= 100000 && visited[n+1]==0) {
				visited[n+1] = visited[n]+1;
				queue.add(n+1);
				
			}
			if(2*n <= 100000 && visited[2*n] ==0) {
				visited[2*n] = visited[n] +1;
				queue.add(2*n);
			}
			
			
			
		}
		return -1;
	}
	

}
