package math;
/*
 * 소인수 분해
 * 브론즈 1
 * https://www.acmicpc.net/problem/11653
 * 체감 난이도 : 쉬움
 * 시도: X X
 * 
 */
import java.util.*;
public class Factorization {
	
	public void solve() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int cnt=0;
		for(int i=2; i<=Math.sqrt(N); i++) {
			//System.out.println("반복중 "+ i );
			if( N%i==0 ) {
				while(N%i==0) {
					N /= i;
					cnt ++;
					System.out.println(i);
//					System.out.println("N = "+ N );
				}
				
			}
			
		}
//		if(cnt ==0) {
		if(N != 1) {
			System.out.println(N);
		}
		
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.solve();
		

	}

}
