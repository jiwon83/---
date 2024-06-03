import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 실수한 부분: queue에서 poll 해주어야 하는 부분 poll 안하고 반복분 돌기만 하는 것 주의 !!
 */
class Main {
	// 방향은 1부터,

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static long [][] map, tmpMap; // index : 0~

	static int [][] ch;
	
	static int [] sequence; // 이동 순서 정보 : 0 ~ 4
	static long maxNumber = 0;

	static int trial = 5;


	public static void main(String[] args) throws IOException {
		init();
		recurSequence(0);
		System.out.println(maxNumber);
	}
	private static void recurSequence(int k){
		if (k == trial){
			tmpMap = copyMap(map);
			// System.out.println(" 시작 !");
			// System.out.println(Arrays.toString(sequence));
			for (int i = 0; i < trial; i++){
				int dir = sequence[i];
				// System.out.println(" move : "+ dir);
				// printMap(tmpMap, "before");
				ch = new int[N][N];
				move(dir, tmpMap);
				// printMap(tmpMap, "after");
			}
			updateMaxNumber(tmpMap);
			return;
		}
		for (int d = 1; d <= 4; d++){
			sequence[k] = d;
			recurSequence(k+1);
			sequence[k] = 0;
		}
	}
	private static void printMap(long [][] map , String msg){
		System.out.println(" ======= "+ msg + "========");
		for (int i = 0; i < N; i++){
			System.out.println(Arrays.toString(map[i]));
		}
	}
	private static void move(int dir, long [][] map){
		// System.out.println("...........in move .............");

		switch (dir){
			case 1: // up
				for (int c = 0; c < N; c++){
					int top = 0;
					int pos = 1;
					while (pos < N){
						// printMap(tmpMap, "before");
						// System.out.println("top :"+ top + " pos : "+ pos);
						if( map[pos][c] != 0 && map[top][c] == 0){ // 현재값이 0이 아니고, 위가 빈칸 일 때
							swap( top, c, pos, c, map);
						}
						else if (map[pos][c] != 0 && map[top][c] != 0 && map[pos][c] != map[top][c]){ // 현재값이 0이 아니고, 위와 현재값이 다를 때
							int blank = Math.abs(top - pos);
							if (blank > 1 ) swap(top+1, c, pos, c, map); // top 바로 아래에 놓는다.
							top += 1;
						}
						else if (map[pos][c] != 0 && map[pos][c] == map[top][c]){ // 현재값이 0이 아니고, 위와 현재값이 같을 때
							if (ch[top][c] == 1){
								int blank = Math.abs(top - pos);
								if (blank > 1 ) swap(top+1, c, pos, c, map); // top 바로 아래에 놓는다.
								top += 1;
							}else{
								map[top][c] *= 2;
								ch[top][c] = 1;
								map[pos][c] = 0;
							}
						}
						pos += 1;
						// printMap(tmpMap, "before");
					}
				}
				break;
			case 2: // left
				for (int r = 0; r < N; r++){
					int top = 0;
					int pos = 1;
					while (pos < N){
						if( map[r][pos] != 0 && map[r][top] == 0){ // 현재값이 0이 아니고, 위가 빈칸 일 때
							swap( r, top, r, pos, map);
							// top += 1;
						}
						else if (map[r][pos] != 0 && map[r][top] != 0 && map[r][pos] != map[r][top]){ // 현재값이 0이 아니고, 위와 현재값이 다를 때
							int blank = Math.abs(top - pos);
							if (blank > 1 ) swap(r, top+1, r, pos, map); // top 바로 아래에 놓는다.
							top += 1;
						}
						else if (map[r][pos]!= 0 && map[r][pos] == map[r][top]){ // 현재값이 0이 아니고, 위와 현재값이 같을 때
							if (ch[r][top] == 1){
								int blank = Math.abs(top - pos);
								if (blank > 1 ) swap(r, top+1, r, pos, map); // top 바로 아래에 놓는다.
								top += 1;
							}else{
								map[r][top] *= 2;
								ch[r][top] = 1;
								map[r][pos] = 0;
							}
						}
						pos += 1;
					}
				}
				break;
			case 3: //down
				for (int c = 0; c < N; c++){
					int top = N-1;
					int pos = N-2;
					while (pos >= 0){
						if( map[pos][c] != 0 && map[top][c] == 0){ // 현재값이 0이 아니고, 위가 빈칸 일 때
							swap( top, c, pos, c, map);
						}
						else if (map[pos][c] != 0 && map[top][c] != 0 && map[pos][c] != map[top][c]){ // 현재값이 0이 아니고, 위와 현재값이 다를 때
							int blank = Math.abs(top - pos);
							if (blank > 1 ) swap(top-1, c, pos, c, map); // top 바로 아래에 놓는다.
							top -= 1;
						}
						else if (map[pos][c] != 0 && map[pos][c] == map[top][c]){ // 현재값이 0이 아니고, 위와 현재값이 같을 때
							if (ch[top][c] == 1){
								int blank = Math.abs(top - pos);
								if (blank > 1 ) swap(top-1, c, pos, c, map); // top 바로 아래에 놓는다.
								top -= 1;
							}else{
								map[top][c] *= 2;
								ch[top][c] = 1;
								map[pos][c] = 0;
							}
						}
						pos -= 1;
					}
				}
				break;
			case 4: // right
				for (int r = 0; r < N; r++){
					int top = N-1;
					int pos = N-2;
					while (pos >= 0){
						if( map[r][pos] != 0 && map[r][top] == 0){ // 현재값이 0이 아니고, 위가 빈칸 일 때
							swap( r, top, r, pos, map);
						}
						else if (map[r][pos] != 0 && map[r][top] != 0 && map[r][pos] != map[r][top]){ // 현재값이 0이 아니고, 위와 현재값이 다를 때
							int blank = Math.abs(top - pos);
							if (blank > 1 ) swap(r, top-1, r, pos,  map); // top 바로 아래에 놓는다.
							top -= 1;
						}
						else if (map[r][pos] != 0 && map[r][pos] == map[r][top]){ // 현재값이 0이 아니고, 위와 현재값이 같을 때
							if (ch[r][top] == 1){
								int blank = Math.abs(top - pos);
								if (blank > 1 ) swap(r, top-1, r, pos, map); // top 바로 아래에 놓는다.
								top -= 1;
							}else{
								map[r][top] *= 2;
								ch[r][top] = 1;
								map[r][pos] = 0;
							}
						}
						pos -= 1;
					}
				}
		}

	}
	private static void swap(int x1, int y1, int x2, int y2, long [][] map){
		long tmp = map[x1][y1];
		map[x1][y1] = map[x2][y2];
		map[x2][y2] = tmp;
	}
	private static long [][] copyMap(long [][] map){
		long [][] arr = new long[N][N];
		for (int i = 0; i < N; i++){
			arr[i] = map[i].clone();
		}
		return arr;
	}
	private static void updateMaxNumber(long [][] tmpMap){
		for (int i = 0; i < N; i++){
			for (int j = 0;j < N; j++){
				maxNumber = Math.max(maxNumber, tmpMap[i][j]);
			}
		}
	}
	private static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new long[N][N];
		sequence = new int[trial];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++){
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}

	}


}