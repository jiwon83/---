package DFSBFS;
/*
 * 백준 실버 1 단지번호 붙이기 https://www.acmicpc.net/problem/2667
 * 시도: X(푸는 방식은 맞았는데 결과가 나오지 않음. 꼼꼼하고 철저하게 로직을 세우자.)
 * 체감 난이도: 중하
 * 참고: https://github.com/rhs0266/FastCampus/blob/main/%EA%B0%95%EC%9D%98%20%EC%9E%90%EB%A3%8C/02-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98/09~11-%EA%B7%B8%EB%9E%98%ED%94%84%20%ED%83%90%EC%83%89/%EB%AC%B8%EC%A0%9C%EB%B3%84%20%EC%BD%94%EB%93%9C/2667-%EB%8B%A8%EC%A7%80%EB%B2%88%ED%98%B8%20%EB%B6%99%EC%9D%B4%EA%B8%B0/solution.java
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TownNumber {

	public static int N,cnt;// 단지 수
	
	public static int[][] adj = new int[25][25];
	public static ArrayList<Integer> houses = new ArrayList<>();
//	public static int[] houses = new int[25 * 25];
	public static boolean[][] visit = new boolean[25][25];
	public static int [] dx= {-1,1,0,0};
	public static int [] dy = {0,0,-1,1};
	public static StringBuffer result = new StringBuffer();

	public static void dfs(int x, int y) {
		cnt++;
		
		visit[x][y] =true;
//		houses[cnt] += 1;
		
		for(int i =0; i<4; i++) {
			
			int nx = x + dx[i]; //wow,,,,,,,dx를 i라고 씀.,,,,,,
			int ny = y+ dy[i];
			
			if( nx<0 || nx >= N || ny<0 || ny >=N) {
				continue;
			}
			if( adj[nx][ny]==1 && !visit[nx][ny]) {
				
//				System.out.println("재귀함수안에 들어옴.");
				dfs(nx,ny);
			}
			
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		cnt =0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				
				adj[i][j] = Integer.parseInt(st.nextToken());
				
			}

		}
		// insert data finish
		for (int i = 0; i < N; i++) {

			for (int j = 0; j < N; j++) {
				if(!visit[i][j] && adj[i][j]==1) { //방문한 적 없다면, 전체를 통틀어 딱 1번만 방문하면 된다
//					System.out.println("start:"+i+":"+j);
					cnt=0;//cnt 초기화
					dfs(i,j);
					houses.add(cnt);
				}
				
			}
		}
//		printArr();
		System.out.println(houses.size());
		Collections.sort(houses);
		for(int i=0; i<houses.size(); i++) {
			System.out.println(houses.get(i));
		}

	}//main
	public static void printArr() {
		for(int i=0; i<adj.length; i++) {
			for(int j=0; j<adj.length; j++) {
				System.out.print(adj[i][j]);
			}
			System.out.print("\n");
		}
	}

}
