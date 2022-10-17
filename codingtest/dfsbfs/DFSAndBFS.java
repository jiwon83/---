package DFSBFS;

/* 난이도: 실버2 https://www.acmicpc.net/problem/1260
 * DFS와 BFS
 * 시도: X(시간초과, 결과 X), O
 * 체감 난이도: 중
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class DFSAndBFS {

	// grpah
	ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	// grpah에 값 넣기
	public void init(int key, int input) {
		ArrayList<Integer> node = new ArrayList<Integer>(2);
		node.add(key);
		node.add(input);
		graph.add(node);

	}

	public ArrayList<Integer> searchKey(int key) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < graph.size(); i++) {
			if (graph.get(i).get(0).equals(key)) {
				arr.add(graph.get(i).get(1));

			} else if (graph.get(i).get(1).equals(key)) {
				arr.add(graph.get(i).get(0));

			}
		}
		return arr;

	}

	public ArrayList<Integer> bfsFunc(ArrayList<ArrayList<Integer>> grpah, int startNode) {

		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> needVisit = new ArrayList<Integer>();

		needVisit.add(startNode);

		while (needVisit.size() > 0) {

			int node = needVisit.remove(0);

			if (!visited.contains(node)) {
				visited.add(node); // visited 갱신
				ArrayList<Integer> al = searchKey(node);
				Collections.sort(al); // 번호가 작은 것부터 방문하기 위하여
				needVisit.addAll(al);// 해당 노드의 모든 값을 찾아서 넣는다.

			}
		} // while

		return visited;
	}

	public ArrayList<Integer> dfsFunc(ArrayList<ArrayList<Integer>> grpah, int startNode) {

		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> needVisit = new ArrayList<Integer>();

		needVisit.add(startNode);

		while (needVisit.size() > 0) {

			int node = needVisit.remove(needVisit.size() - 1);

			if (!visited.contains(node)) {
				visited.add(node); // visited 갱신

				ArrayList<Integer> al = searchKey(node);
				Collections.sort(al, Collections.reverseOrder());
	
				// 만약 중복되는 것이 있다면 앞에꺼 삭제
				for (int i = 0; i < al.size(); i++) {
					if (needVisit.contains(al.get(i))) {
						needVisit.remove(al.get(i));
					}
				}
				needVisit.addAll(al);
			}
		} // while

		return visited;
	}

	public void solve() {

		Scanner sc = new Scanner(System.in);
		String[] nums = sc.nextLine().split(" ");
		int N = Integer.parseInt(nums[0]);
		int M = Integer.parseInt(nums[1]);
		int V = Integer.parseInt(nums[2]);

		for (int i = 0; i < M; i++) {
			String[] incres = sc.nextLine().split(" ");

			init(Integer.parseInt(incres[0]), Integer.parseInt(incres[1]));
		}
		// printTupleArr(graph);
		printAnswerArr(dfsFunc(graph, V));
		printAnswerArr(bfsFunc(graph, V));

	}

	private void printAnswerArr(ArrayList<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {

			System.out.print(arr.get(i) + " ");
		}
		System.out.println();
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
		DFSAndBFS bfs = new DFSAndBFS();
		bfs.solve();
		// TODO Auto-generated method stub

	}

	public void printMap(HashMap<String, ArrayList<String>> map) {
		Iterator<String> mapIter = map.keySet().iterator();

		while (mapIter.hasNext()) {

			String key = mapIter.next();
			ArrayList<String> value = map.get(key);
			for (int i = 0; i < map.get(key).size(); i++) {
				System.out.println(key + " : " + value.get(i));
			}

		}
	}

}
