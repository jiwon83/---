import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 실수한 부분: queue에서 poll 해주어야 하는 부분 poll 안하고 반복분 돌기만 하는 것 주의 !!
 */
class Main {

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int [][] extra ; // 지속적으로 업데이트되는 양분
	static int [][] map; //양분의 양 정보

	static Map<Integer, Deque<Integer>> deathTrees = new HashMap<>();

	static Map<Integer, Deque<Integer>> willSpreadTrees = new HashMap<>();

	static Map<Integer, Deque<Integer>> treesInfoMap = new HashMap<>(); // 좌표 정보, 나무들의 나이

	static int N, M, K;

	static int [][] dirs = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0, -1}, {-1,-1}};


	public static void main(String[] args) throws IOException {

		init();
		for (int k = 1; k <=K; k++){
			// System.out.println(" k  = "+ k);
			sol();
		}
		System.out.println(countOfTrees());

	}
	private static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		extra = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i <N; i++){
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <N; j++){
				extra[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}


		// treesInfoMap
		for (int key = 0; key <100; key++) treesInfoMap.put(key, new ArrayDeque<>());

		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			treesInfoMap.get(getKey(x, y)).addLast(age);
		}

		//printMap(map);
		//printExtra(extra);
		//printTreesInfoMap();

	}
	private static int getKey(int x, int y){
		return x * 10 + y;
	}
	/* 사계절 동안 처리*/
	private static void sol(){
		spring();
		summer();
		fall();
		winter();
	}

	private static void summer() {
		for (Map.Entry<Integer, Deque<Integer>> entry : deathTrees.entrySet()){
			Deque<Integer> deque = entry.getValue();
			while (!deque.isEmpty()){
				int x = entry.getKey()/10;
				int y = entry.getKey()%10;
				map[x][y] += deque.pollFirst() / 2;
			}
		}
	}

	private static void fall() {
		// System.out.//println("fall...");
		// System.out.//println("before...");
		// //printTreesInfoMap();

		for (Map.Entry<Integer, Deque<Integer>> entry : willSpreadTrees.entrySet()){
			// 8 방향에 age 1인 나무 생성
			int x = entry.getKey()/10;
			int y = entry.getKey()%10;
			if (!entry.getValue().isEmpty()){
				int size = entry.getValue().size();
				for (int s = 0; s < size; s++){
					int age = entry.getValue().pollFirst(); //중요하지 않음.
					for (int d = 0; d < 8; d++){
						int nx = x + dirs[d][0];
						int ny = y + dirs[d][1];
						if (!inArea(nx, ny)) continue;
						treesInfoMap.get(getKey(nx,ny)).addFirst(1);
					}
				}

			}

		}
		// System.out.//println("after...");
		// //printTreesInfoMap();
	}

	private static void winter(){
		for (int i = 0; i <N; i++){
			for(int j = 0; j< N; j++){
				map[i][j] += extra[i][j];
			}
		}
	}

	private static void spring() {
		// System.out.println("spring...");
		// System.out.println("before map");
		// printMap(map);
		// System.out.println("before willSpreadTrees");
		// printHashMap(willSpreadTrees, "가을에 퍼질 나무들");
		// System.out.println("before deathTrees");
		// printHashMap(deathTrees, "죽은 나무들");
		for (Map.Entry<Integer, Deque<Integer>> entry : treesInfoMap.entrySet()){
			if (!entry.getValue().isEmpty()){
				//어린 나무부터 양분을 먹는다.  -> 항상 큐에 어린나이 나무를 앞으로 넣어주면 됨.

				int x = entry.getKey() / 10;
				int y = entry.getKey() % 10;
				int qSize = entry.getValue().size();
				for (int i = 0; i < qSize; i++){

					int age = entry.getValue().pollFirst();
					// System.out.println("양분을 먹는다.  key: ("+ x+" , "+y+") age :" +age);
					int food = map[x][y];
					if (food >= age){
						map[x][y] -= age;
						entry.getValue().addLast(age+1);
						if ((age+1) % 5 == 0){
							if (! willSpreadTrees.containsKey(entry.getKey())) willSpreadTrees.put(entry.getKey(), new ArrayDeque<>());
							willSpreadTrees.get(entry.getKey()).addLast(age+1);
						}
					}else{
						if (! deathTrees.containsKey(entry.getKey())) deathTrees.put(entry.getKey(), new ArrayDeque<>());
						deathTrees.get(entry.getKey()).addLast(age);
					}
				}
			}
		}
		// System.out.println("after map");
		// printMap(map);
		// printTreesInfoMap();
		// System.out.println("after willSpreadTrees");
		// printHashMap(willSpreadTrees, "가을에 퍼질 나무들");
		// System.out.println("after deathTrees");
		// printHashMap(deathTrees, "죽은 나무들");


	}

	private static int countOfTrees(){
		int count = 0;
		for (Map.Entry<Integer, Deque<Integer>> entry : treesInfoMap.entrySet()){
			count += entry.getValue().size();
		}
		return count;
	}

	private static boolean inArea(int x , int y){
		return x >= 0 && x < N && y >=0 && y < N;
	}

	private static void printMap(int [][] arr){
		System.out.println("===== 양분 map ======");
		for (int i = 0; i <N; i++ ){
			System.out.println(Arrays.toString(arr[i]));
		}
	}

	private static void printExtra(int [][] arr){
		System.out.println("===== 추가 양분 map ======");
		for (int i = 0; i <N; i++ ){
			System.out.println(Arrays.toString(extra[i]));
		}
	}

	private static void printTreesInfoMap(){
		System.out.println("===== 좌표에 대응하는 나무 정보 ======");
		for (Map.Entry<Integer, Deque<Integer>> entry : treesInfoMap.entrySet()){
			if (!entry.getValue().isEmpty()){
				System.out.println("key : " + entry.getKey());
				System.out.println(entry.getValue());
			}
		}
	}
	private static void printHashMap(Map<Integer, Deque<Integer>> map, String msg){
		System.out.println("===== "+msg+"======");
		for (Map.Entry<Integer, Deque<Integer>> entry : map.entrySet()){
			if (!entry.getValue().isEmpty()){
				System.out.println("key : " + entry.getKey());
				System.out.println(entry.getValue());
			}
		}
	}

}