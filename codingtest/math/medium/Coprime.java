package math;
/*
 * 백준 골드1 9359번 서로소 https://www.acmicpc.net/problem/9359
 *  * 시도: X( 메모리 초과) 
 * 체감 난이도 : 중
 * 풀이: 메모리를 줄이려면 비트마스트 연산 개념을 알아야 할듯. 따로 공부한 후 다시 풀어볼 것.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Coprime {
	
	ArrayList<Integer> answer = new ArrayList<>(); 
	
	public static ArrayList<Integer> divisor(int num) {
		ArrayList<Integer> returned = new ArrayList<>();
		//returned.add(1);
		returned.add(num);
		for(int i =2; i< num; i++) {
			if(num%i==0) {
				returned.add(i);
			}
		}
		return returned;
	}
	
	public static void printArr(ArrayList<Integer> arr) {
		for(int i=0; i< arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int next = 0;
		try {
			next = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		while(next-- !=0) {
			String input = sc.nextLine();
			String[] arr = input.split(" ");
			int A = Integer.parseInt(arr[0]);
			int B = Integer.parseInt(arr[1]);
			int N = Integer.parseInt(arr[2]);
			
			boolean [] tf = new boolean[B+1]; //true이면 서로소가 아님.
			ArrayList<Integer> divisor = divisor(N);
			//System.out.print("divisor: ");
			//printArr(divisor);
			ArrayList<Integer> answer = new ArrayList<>();
			
			for(int i=A; i<=B; i++) {
				//System.out.println("i= "+i);
				
				if(tf[i]) {
					continue;
				}
				if(divisor.contains(i)) {
					for(int n=i; n<=B; n +=i) { //i의 배수
						//System.out.println("n= "+n);
						tf[n]=true;
					}
				}
				
				
				
				
			}//for
			for(int j=A; j<=B; j++) {
				if(tf[j]==false) {
					answer.add(j);
					//System.out.println("the answer is "+j);
				}
			}
			//답 출력
			System.out.println(answer.size());

			
		}//while
	}

}
