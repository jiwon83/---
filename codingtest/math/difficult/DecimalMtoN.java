/*
 * 실버 3 , 소수 구하기
 * https://www.acmicpc.net/problem/1929
 * 시도: X(시간초과, 4초) X(2의배수 재거, 시간초과), X, X , X, O
 * 체감 난이도: 어려움,중상
 *  답안참고: https://st-lab.tistory.com/81,
 * "에라토스테네스의 체"
 * 소수: 1과 자기 자신만을 인수로 갖는 수 
 * 합성수: 3개 이상(1, 자기자신 포함 )소수의 곱으로 이루어진 수 
 * 소수의 곱들을 제외.
 */
package math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class DecimalMtoN {

//	public static boolean isDecimal(int num) {
//
//		for (int i = 2; i <= Math.sqrt(num); i++) {
//			if (num % i == 0) {
//				return false;
//			}
//
//		}
//
//		return true;
//	}

	public static boolean[] composite;

	public static void compositeNum() {

		composite[0] = composite[1] = true;

		for (int i = 2; i <= Math.sqrt(composite.length); i++) { // root만큼 만 나눠봐도 소수가 아님을 알 수 있다.

			if (composite[i] == true) { // 소수가 아니면, 합성수이면,
				continue;
			}

			for (int j = i + i; j < composite.length; j += i) {
				composite[j] = true;
			}
			composite[i] = false;

		} // for
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String [] nums = sc.nextLine().split(" ");
		int M = Integer.parseInt(nums[0]); 
		int N = Integer.parseInt(nums[1]);

		final long start = System.nanoTime();
		
		composite = new boolean[N+1];
		
		compositeNum();
		
		for(int i=M; i<=N; i++) {
			if(!composite[i]) {
				System.out.println(i);
			}
		}
		final long end = System.nanoTime();
		System.out.println("소요시간 : " + (end - start) * 1e-9 + " sec");
		




	}

}
