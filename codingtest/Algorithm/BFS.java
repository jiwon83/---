package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/*
 * grpah를 HaspMap를 사용해 구현하고, startNode를 인자로 받아, BFS Search 를 구현
 */
public class BFS {
	
	//grpah
	HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
	
	
	//grpah에 값 넣기
	public void init() {
		graph.put("A", new ArrayList<String>(Arrays.asList("B", "C")));
		graph.put("B", new ArrayList<String>(Arrays.asList("A", "D")));
		graph.put("C", new ArrayList<String>(Arrays.asList("A", "G", "H", "I")));
		graph.put("D", new ArrayList<String>(Arrays.asList("B", "E", "F")));
		graph.put("E", new ArrayList<String>(Arrays.asList("D")));
		graph.put("F", new ArrayList<String>(Arrays.asList("D")));
		graph.put("G", new ArrayList<String>(Arrays.asList("C")));
		graph.put("H", new ArrayList<String>(Arrays.asList("C")));
		graph.put("I", new ArrayList<String>(Arrays.asList("C", "J")));
		graph.put("J", new ArrayList<String>(Arrays.asList("I")));
	}
	
	
	public ArrayList<String> bfsFunc(HashMap<String, ArrayList<String>> grpah, String startNode){
		
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> needVisit = new ArrayList<String>();
		
		needVisit.add(startNode);
		
		while(needVisit.size() >0) {
			
			String node = needVisit.remove(0);
			
			if(!visited.contains(node)) {
				visited.add(node); // visited 갱신
				needVisit.addAll(grpah.get(node)); //그래프레있는 데이터를 needVisit

			}
		}//while
		
		return visited;
	}
	
	public void solve() {
		init();
		System.out.println(bfsFunc(graph, "A"));
		
	}
	
	public static void main(String[] args) {
		BFS bfs = new BFS();
		bfs.solve();
		// TODO Auto-generated method stub
		
	}

}
