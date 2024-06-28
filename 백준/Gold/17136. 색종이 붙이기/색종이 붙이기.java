import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static class Info{
		int x, y, maxLen;

		public Info(int x, int y, int maxLen) {
			super();
			this.x = x;
			this.y = y;
			this.maxLen = maxLen;
		}

		public int getMaxLen() {
			return maxLen;
		}

		@Override
		public String toString() {
			return "Info [x=" + x + ", y=" + y + ", maxLen=" + maxLen + "]";
		}

	}
	static List<Info> fillList = new ArrayList<>();
	static int minCnt = Integer.MAX_VALUE;//-1;
	static int N = 10;
	static boolean isClear = false;
	static int [][] ch = new int[N][N];
	static int [][] map = new int[N][N];
	static int[] paperSizeCount = {0,5,5,5,5,5};
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		
		// 1. 모든 빈칸을 찾아서 List에 저장
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j <N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j =0; j <N; j++) {
				if(map[i][j] == 1) {
					int maxWidth = getSquareMaxWidth(i, j, map, ch);
					fillList.add(new Info(i, j, maxWidth));
				}
			}
		}
		// Greedy, 큰크기의 사각형으로 정렬해줘서 그나마 최적의 해를 더 빨리 찾도록 도와준다. 
//		Collections.sort(fillList, Comparator.comparing(Info::getMaxLen).reversed());
		
//		System.out.println(fillList);
		// 2. 빈칸들에 대해 색종이를 붙여본다. 
		recurAttach(0, 0);
		// 3. 최소 사용된 색종이 수를 RETURN
		System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
		
	
	}
	private static void recurAttach(int k , int cnt) {
		if(cnt >= minCnt) return; // 이미 갯수를 넘어갔다면 리턴
		if(k == fillList.size()) {
			minCnt = Math.min(minCnt, cnt);
			return;
		}
		Info pos = fillList.get(k);
		if(ch[pos.x][pos.y] == 1) {
			recurAttach(k+1, cnt);
			return;
		}
//		System.out.println(pos);
		int maxWidth = getSquareMaxWidth(pos.x, pos.y, map, ch);
//		System.out.println("maxWidth " + maxWidth);
		
		if(maxWidth != -1) {
			for(int w = maxWidth; w >= 1; w--) {
				if(paperSizeCount[w] <= 0) continue;
				fill(pos.x, pos.y, w, ch, 1);
				paperSizeCount[w] -= 1;
				recurAttach(k+1, cnt+1);
				fill(pos.x, pos.y, w, ch, 0);
				paperSizeCount[w] += 1;
			}
		}
	}
	private static int getSquareMaxWidth(int sx, int sy, int[][] map, int[][] ch) {
		int maxWidth = -1;
		loop:for(int size = 1; size <=5; size++) {
			for(int x = sx; x <= sx + size -1; x ++) {
				for(int y = sy; y <= sy + size -1; y++) {
					if(!inArea(x,y) || map[x][y]==0 || ch[x][y] == 1) continue loop;
				}
			}
			maxWidth = Math.max(maxWidth, size);
		}
		return maxWidth;
	}
	static boolean inArea(int x, int y) {
		return x >=0 && y >=0 && x < N && y < N;
	}
	private static void fill(int sx, int sy, int length, int [][] ch, int state) {
		for(int x = sx; x <= sx + length -1; x++) {
			for(int y = sy; y <= sy + length - 1; y++) {
				ch[x][y] = state;
			}
		}
	}

}