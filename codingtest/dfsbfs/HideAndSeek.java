package DFSBFS;
/*
 * 실버1 숨바꼭질 https://www.acmicpc.net/problem/1697
 * 시도: X
 * 체감 난이도: 상
 */
import java.util.ArrayList;
import java.util.Scanner;

public class HideAndSeek {

	ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	public ArrayList<Integer> inputNode(int up) {
		ArrayList<Integer> node = new ArrayList<Integer>(3);
		node.add(up);
		node.add(up - 1);
		node.add(up + 1);
		node.add(up * 2);
		graph.add(node);
		return node;
	}
	

	public int init(int up, int K) {
		// int position = K;]
		int isSuccess =-1;
		int i=0;
		while(true) {
			if ((up - 1) == K || (up + 1) == K || (up * 2) == K) {
				isSuccess =1;
				System.out.println("K에 도달!");
				break;
			}else {
				inputNode(up);
			}
		}
		while (true) {
			//System.out.println("position: " + position);
			System.out.println("K: " + K);

			ArrayList<Integer> node = new ArrayList<Integer>(3);
			node.add(up);
			node.add(up - 1);
			node.add(up + 1);
			node.add(up * 2);
			graph.add(node);
			System.out.println(" " + up +","+ (up - 1)+","+( up + 1) +","+up * 2);
			i++;
			if ((up - 1) == K || (up + 1) == K || (up * 2) == K) {
				isSuccess =1;
				System.out.println("K에 도달!");
				break;
			}else {
				inputNode(up);
//				init(up - 1, K);
//				init(up + 1, K);
//				init(up * 2, K);
			}

			
			if(up==0) {
				System.out.println("찾을 수 없음.");
				break;
				
			}
			if(i==10) {
				break;
			}

			

		}

		return isSuccess;

	}

	public void solve() {
		Scanner sc = new Scanner(System.in);
		String[] nums = sc.nextLine().split(" ");
		int N = Integer.parseInt(nums[0]);
		int K = Integer.parseInt(nums[1]);
//		int position = N; // 현재 노드의 위치
		// K를 찾을 때까지 반복한다.-> position 노드가 K와 같아질 때까지 반복
		//init(N, K);
		ArrayList <Integer> list =  inputNode(N);
		while(!list.contains(K)) {
			for(int i=1; i<4; i++) {
				list = inputNode(list.get(i));
				if(list.contains(K)) {
					System.out.print("okay");
					break;
				}
				//System.out.println(list);
			}
		}
		printTupleArr(graph);
		System.out.println("찾음");
		
		

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HideAndSeek hs = new HideAndSeek();
		hs.solve();

	}

}
