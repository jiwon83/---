package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 백준: 유기농 배추 https://www.acmicpc.net/problem/1012
 * X X
 * 참고: https://github.com/ndb796/Fast_Campus_Algorithm_Lecture_Notes/blob/master/Solutions/%5B15%5D_2.java
 */
public class OrganicCabbage {
	public static int[][] data = new int [50][50];
	public static boolean[][] visited = new boolean [50][50];
	public static int [] dx = {-1,1,0,0};
	public static int [] dy = {0,0,-1,1};
	public static int M, N, K, count;
	
	
	public void solve() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int r = Integer.parseInt(br.readLine()); //반복 횟수
		while(r--!=0) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); //M x 가로의 길이 
			N = Integer.parseInt(st.nextToken()); //N y 세로의 길이 
			K = Integer.parseInt(st.nextToken()); //
			data = new int [50][50];
			visited = new boolean [50][50];
			count= 0;
			
			for(int i=0; i<K; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st2.nextToken()); //M x
				int b = Integer.parseInt(st2.nextToken()); //N y
				
				data[a][b]= 1;
			}
			
			
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {
					//dfs 함수는 위아래옆에 1이 있을 경우에만 계속해서 탐색한다. 따라서 반복문으로 다음 1이 있는 지 탐색해 주어야 한다. 
					if( data[i][j]==1 && !visited[i][j]) { 
						
						dfs(i,j);
						count++;
					}
				}
			}
			System.out.println(count);
		}
		
		
	}
	public void dfs(int x, int y) {
		
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int a = x + dx[i];
			int b = y + dy[i];
			
			if(a < 0 || a >= M || b <0 || b>= N) {
				continue;
			}
			if(data[a][b]==1 && !visited[a][b]) { //null인 경우는 없지 않은가 ??
				dfs(a,b);
			}
			
		}
	
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		OrganicCabbage p = new OrganicCabbage();
		p.solve();

	}

}
