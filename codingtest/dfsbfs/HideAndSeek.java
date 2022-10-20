package DFSBFS;
/*
 * 실버1 숨바꼭질 https://www.acmicpc.net/problem/1697
 * 시도: X, X, O
 * 체감 난이도: 상(아이디어 생각해 내기가 어려움)
 * 참고: ﻿https://smartpro.tistory.com/m/18
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class HideAndSeek {
	int [] times = new int[100001]; //index = 0~100000
	
	public static void main(String[] args) throws IOException {
		HideAndSeek hs = new HideAndSeek();
		hs.solve();

	}

	private void solve() throws IOException {
		
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		String input = br.readLine();
		String [] inputs = input.split(" ");
		
		int N = Integer.valueOf(inputs[0]);
		int K = Integer.valueOf(inputs[1]);
		System.out.println(bfs(N,K));
	
	}

	private int bfs(int N, int K) {
		
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(N); //N=5
		times[N] = 0;
		
		while(!que.isEmpty()) {
			int node = que.remove();//가장 먼저 들어간 원소 삭제 및 반환.
	
			if(node ==K) {
				return times[node];
			}

			if(node-1 >=0 && times[node-1]==0 ) {
				times[node -1] = times[node] + 1;
				que.add(node-1);
			}
			if(node+1 <=100000 && times[node+1]==0) {
				times[node +1] = times[node] + 1;
				que.add(node+1);
			}
			if(node*2 <=100000 && times[node*2]==0 ) {
				times[node*2] = times[node] + 1;
				que.add(node*2);
			}
		}
		return -1;

	}
}
