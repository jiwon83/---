/**
 * 브론즈 5 
 * https://www.acmicpc.net/problem/10872
 * >>>>>>>>>>>해결 방법 >>>>>>>>>>>>
 *  시도: O
 *  체감 난이도 : 쉬움
 */
package math;
import java.util.*;

public class Factorial {
	
	public static long factorial(int num ) {
		long result=1;
		for(int i=num; i>1; i--) {
			System.out.print(i+"일때 ");
			result *= i;
			System.out.println(result);
		}
		return result;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); 
		System.out.println(factorial(N));
		
		
		
	}

}
