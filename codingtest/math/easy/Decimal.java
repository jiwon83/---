package math;
/*
 * 소인수 분해
 * 브론즈 1
 * https://www.acmicpc.net/problem/11653
 * 
 * 시도: X 
 * 1. : 몫을 넣어야 하는 자리에 나머지를 넣어버렸음.. 꼼꼼히 논리를 확인할 것. 참고 https://st-lab.tistory.com/152
 */
import java.util.*;
public class Decimal {
	
	public static int decimal(int num) {
		int decimalMin=num; //예들들어 6이 들어올 경우 2로 나가야함. 
		
		for(int i=num-1; i>1; i--) {
			if( num % i == 0) {
				decimalMin = i;
			}
			
		}
		
		return decimalMin;
	}
	public static boolean isDecimal(int num) {
		int decimalMin=num; //예들들어 6이 들어올 경우 2로 나가야함. 
		
		for(int i=num-1; i>1; i--) {
			if( num % i == 0) {
				decimalMin = i;
			}
			
		}
		if(decimalMin==num) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		
		int ex = 8;
		int ex2 = 8;
		ex /= 2;
		ex2 = ex2/2;
		
		System.out.println(ex);
		System.out.println(ex2);
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		for(int j =2; j<=n; j++ ) {

			if(isDecimal(j)) {	//소수이면
				while( n % j ==0 ) { //나머지가 0이면 계속 반복
					if(n/j==1) {
						n = n/j; //num에 j를 나눈 나머지를 준다. 
						//n /= j;
						System.out.println(j);
						break;
					}
					n = n/j; //num에 j를 나눈 나머지를 준다. 
					//n /= j;
					System.out.println(j);
					
				}//while
			}//if
		}

	}

}
