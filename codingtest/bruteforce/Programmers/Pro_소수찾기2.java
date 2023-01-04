import java.util.*;
class Solution {
    HashSet<Integer> set = new HashSet<Integer>();
    String Numbers;
    int Ans=0;
    
    //recur 함수
    void recur(String prefix, String left){
        if(prefix!=""){
            
            set.add(Integer.parseInt(prefix));
          
        }
        //recur로 확장 현재 prefix에 Numbers의 값을 붙이고 나머지를 넣어서 재귀호출.
        int n = left.length(); // 남은 문자열의 길이만큼 반복
        for(int i=0; i<n; i++){
            String leftOfleft = left.substring(0,i);
            String rigthOfleft = left.substring(i+1);
            recur(prefix+left.charAt(i), leftOfleft+rigthOfleft);
        }
    }
    //소수찾기 함수
    boolean isPrime(int num){
        if(num <= 1) return false;
        else{
            for(int i=2; i<=Math.sqrt(num); i++){
                if(num % i ==0){
                    return false;
                }
            }
            return true;
        }
        
    }
    
    public int solution(String numbers) {
        recur("", numbers); 
        Iterator<Integer> iter = set.iterator();
        while(iter.hasNext()){
            int element = iter.next();
            System.out.println(element);
            if(isPrime(element)){
                Ans++;
            }
        }
        return Ans;
    }
}