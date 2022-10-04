/**
 * 실버4
 * https://www.acmicpc.net/problem/3474
 * >>>>>>>>>>>해결 방법 >>>>>>>>>>>>
 * 1. "주어진 숫자의 팩토리얼 값에서 0의 갯수는 5*2의 갯수와 같다."
 * 2. "2는 5의 갯수보다 무조건 많기 때문에 효율성을 위하여 5의 갯수만 구하면 된다."
 * 3. " 주어진 숫자에서 1까지 5가 얼마나 들어있는지, 25가 얼마나 들어있는지  . . 구하면 된다."
 * 4. " 25 , 125 이렇게 5의 거듭제곱을 한 번 더 구하는 이유는 그 숫자에는 5가 중복되어 있기 때문이다. 예를들어, 25의 경우 5 *5이기 때문에 5로 나눠서 미처 구하지 못한 나머지 5를 25로 나눔으로써 구할 수 있다."
 * 5. " 주어진 숫자 보다 작은 모든 수 들 중 5의 배수가 얼마나 들어있는 지는 해당 수를 5의 배수로 나눈 몫으로 알 수 있다. " 
 */
package math;
import java.util.*;

/**
 * XO
 * 
 */
public class Baek3474 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //입력 받을 갯수
		for( int i =0; i<n; i++) {
			int num = sc.nextInt();
			int x5 = 5;
			int count= 0;
			while(x5 <= num) {
				count += num / x5;
				x5 *= 5;
			}
			System.out.println(count);	//result
		}
		
	}

}
