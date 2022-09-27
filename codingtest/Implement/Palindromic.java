public class Palindromic {
	
	public static boolean isPalindromic(String str) {
		for(int i=0, j=str.length()-1; i<=j; i++, j-- ) {
			System.out.println("반복중"+i +":"+str.charAt(i)+j +":"+str.charAt(j));
			if(str.charAt(i) !=str.charAt(j) ) {
				System.out.println("not equal  "+i +","+j);
				return false;
			}
		}
		System.out.println("This is palindromic");
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//isPalindromic("사바사");
		isPalindromic("eR하kj하Re");
	}

}