package DFSBFS;
/*
 * 다시 풀어볼 것
 * 백준: 유기농 배추 https://www.acmicpc.net/problem/1012
 * X
 * 참고: https://github.com/ndb796/Fast_Campus_Algorithm_Lecture_Notes/blob/master/Solutions/%5B15%5D_2.java
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class OrganicCabbage {
	
	public static int[][] data = new int [50][50];
	public static boolean[][] visited = new boolean [50][50];
	public static int [] dx = {-1,1,0,0};
	public static int [] dy = {0,0,-1,1};
	public static int M, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int r = Integer.parseInt(br.readLine()); //반복 횟수
		while(r--!=0) {		//if r=3, r= 2,1,0
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); //M x
			N = Integer.parseInt(st.nextToken()); //N y
			int K = Integer.parseInt(st.nextToken()); //N y
			int result =0;
			
			data = new int[50][50];
			visited = new boolean[50][50];
			
			for(int i=0; i<K; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st2.nextToken()); //M x
				int b = Integer.parseInt(st2.nextToken()); //N y
				
				data[a][b]=1;
								
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(data[i][j]> 0 && !visited[i][j]) {
						move(i, j);
						result++;
					}
				}
			}
			
			System.out.println(result);
		}
//		printTupleArr(visited);
	
	}
	public static void move(int x, int y) {
		//if(visited[x][y]) return;
		visited[x][y]= true;
		
		for(int i=0; i<4; i++) {	//위 , 아래, 우, 좌 노드로 이동
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if( nx<0 || ny<0 || nx >=N || ny >=M ) {
				continue;
			}
			if( data[nx][ny]==1 && !visited[x][y]) {
				move(nx,ny);
			}
		}
	}
	
	private static void printTupleArr(boolean [][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(arr[i][j]==true) {
					System.out.println(i+":"+j);
				}
			}
			System.out.println();
		}

	}

}
