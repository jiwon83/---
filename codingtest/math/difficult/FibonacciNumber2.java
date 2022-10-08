/*
 * 브론즈 1
 * https://www.acmicpc.net/submit/2748
 * 시도: X
 * 체감 난이도 : 쉬움
 */
package math;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FibonacciNumber3  {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		
		long [] FIBO = new long[91]; // 90으로 하면 안됨. 91로 해야한다. 
		
		for(int i=0; i<=N; i++) {
			if(i==0) {
				FIBO[i] = 0L; 
			}else if(i==1) {
				FIBO[i] = 1L; 

			}else {
				FIBO[i] =FIBO[i-2]+ FIBO[i-1]; 
			}
			//System.out.println("FIBO[i]"+FIBO[i]);
		}
		System.out.println(FIBO[N]);
		
		
	}

}
