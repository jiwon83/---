import java.util.*;
import java.io.*;
public class Main {
	private static List<Type> list;

	static class Type{
		String Name;
		int CombatPower;
		
		public Type(String name, int combatPower) {
			super();
			Name = name;
			CombatPower = combatPower;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); // 칭호의 개수 (1 ≤ N ≤ 105)
		int M = Integer.parseInt(st.nextToken()); // 캐릭터들의 개수  (1 ≤ M ≤ 105)
		
		list = new ArrayList<Type>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			String Name = st.nextToken();
			int CombatPower = Integer.parseInt(st.nextToken());
			list.add(new Type(Name, CombatPower));
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int score = Integer.parseInt(br.readLine());
			// 2-1. 이진 탐색
			String str = BinarySearch(score);
			sb.append(str).append("\n");
		}
		
		// 3. 정답 출력
		System.out.println(sb.toString());
	}

	private static String BinarySearch(int score) {
		int start = 0, end = list.size()-1, mid=0;
		
		while(start<=end) {
			mid = (start+end) / 2;
			if(score > list.get(mid).CombatPower) start = mid+1;
			else end = mid - 1;
		}
		
		return list.get(end+1).Name;
	}

}
