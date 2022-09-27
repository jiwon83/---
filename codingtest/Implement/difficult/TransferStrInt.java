package implement;

public class Transfer {

	public static String intToString(int x) {
		boolean isNegative= false;
		if(x<0) {
			isNegative= true;
		}
		StringBuilder s = new StringBuilder();
		do {
			
			 s.append((char) Math.abs(x % 10));
			 
		}while( x/10 > 0);
		
		if(isNegative) {
			s.append("-");
		}
		String result = s.reverse().toString();
		return result;
	}
	public static int strToInt(String str) {
		int result=0;
		
		for(int i = str.charAt(0) == '-' ? 1: 0; i<str.length(); ++i) { //첫 문자가 -이면 1부터 시작, 그렇지 않으면 0부터 시작
			System.out.println("전치 "+i);
			final int digit = str.charAt(i) - '0';	//아스키 코드를 사용한 char to int 

			System.out.println("digit"+ digit);
			result = result *10 + digit;
			
		}
		return str.charAt(0) =='-'? -result: result;
		
	}
	public static void main(String[] args) {
		//Trial X
		int num =strToInt("357");
		System.out.println(num);

	}

}
