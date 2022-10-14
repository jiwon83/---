package math;

import java.util.ArrayList;
import java.util.Scanner;

public class DecimalMtoN_sol2 {
	
	public void solve() {
		Scanner sc = new Scanner(System.in);
		int M= sc.nextInt();
		int N = sc.nextInt();
		boolean [] tf = new boolean[N+1]; //0~N까지 소수인지 여부를 확인하는 배열
		tf[0]=tf[1]=true;	//소수는 false, 합성수는 true
		
		for(int i=2; i<=N; i++) {
			
			if(tf[i]==true) {	//불필요한 반복 제거
				continue;
			}
			//i가 소수인지 확인 
			for(int j=2; j<= Math.sqrt(i); j++) {
				if(i%j ==0) {	//나누어 떨어진다면
					for(int w = j; w<=N; w+=j) {	//j의 배수만큼 true 처리
						tf[w] = true;
					}
					tf[j]=false; //j는 소수이므로 false 처리
				}
			}
		}//for
		
		for(int i=M; i<=N; i++) {
			if(tf[i]==false) {
				System.out.println(i);
			}
		}
		
	}

	public static void main(String[] args) {
		DecimalMtoN_sol2 main = new DecimalMtoN_sol2();
		main.solve();
		

	}

}
