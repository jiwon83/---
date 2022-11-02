package DFSBFS;
/*
 * 이것이 코딩테스트이다 음료수 얼려 먹기
 * 시도: X(코드 실수, 꼼꼼히), O
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IceDrink {
	
//	public static ArrayList<Integer> [] graph;
	public static String [] graph; 
	public static boolean [][] visit;
	public static int cnt,N,M;
	public static int [][] dist = {{-1,1,0,0},{0,0,-1,1}}; 
	
	
	
	public static void input() throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new String[N];
		visit = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			graph[i]= br.readLine();
		}
		/*
		 * 참고: String으로 안받고 int[][]로 받을 수도 있다.
		 *  ==> str.charAt(j) - '0';
		 */
		
//        for (int i = 0; i < n; i++) {
//            String str = sc.nextLine();
//            for (int j = 0; j < m; j++) {
//                graph[i][j] = str.charAt(j) - '0';
//            }
//        }
		/*
		 * 참고 end
		 */
	
	}
	
	public static void dfs(int x, int y) {
		visit[x][y]= true;
		
		for(int i=0; i<4; i++) {
			
			int nx = x+ dist[0][i];
			int ny = y+ dist[1][i];
			
			if( nx<0 || ny<0 || nx > (N-1) || ny > (M-1) ) {
				continue;
			}
			if(graph[nx].charAt(ny)=='0' && !visit[nx][ny] ) {
				dfs(nx,ny);
			}
		}
		
	}
	
	public static void solve() throws IOException, Exception {
		input();
		cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
//				System.out.println(graph[i].charAt(j));
				if(!visit[i][j] && graph[i].charAt(j)=='0') {
					System.out.println(i+":"+j);
					cnt++;
					dfs(i, j);
				}

			}
		}
		System.out.println(cnt);
		
	}
	public static void main(String[] args) throws IOException, Exception {
		IceDrink i = new IceDrink();
		i.solve();

	}

}
