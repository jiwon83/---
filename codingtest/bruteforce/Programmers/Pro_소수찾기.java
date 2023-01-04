import java.util.*;
class Solution {
    String [] select;
    boolean [] visit;
    int count =0;
    String Numbers;
    ArrayList<Integer> selects = new ArrayList<Integer>();
    
    public boolean isPrime(int num ){
        if(num <= 1){
            return false;
        }
        for(int i=2; i<= Math.sqrt(num); i++){
            if(num % i==0) {
                System.out.println(num + "is not Prime.");
                return false;
            }
        }
        return true;
    }

    public void recur(int k){
        if(k==select.length){
            // System.out.println(Arrays.toString(select));
            String sel="";
            for(int i=0; i<select.length; i++){
                sel += select[i];
            }
            int num = Integer.parseInt(sel);
            
            if(!selects.contains(num)){
                selects.add(num);
                
                System.out.println(num);
                if(isPrime(num)) count++;

            }
            //3. 소수인지 확인한다.
        }else{
            
            for( int i=0; i<Numbers.length(); i++){ //0 1 ->  1 7 
                //select에 없다면
                if(!visit[i]){
                    visit[i] = true;
                select [k] = Numbers.charAt(i)+"";
                recur(k+1);
                visit[i] =false;
                }
            }         

        }
    }
    public int solution(String numbers) {
        Numbers = numbers;
        
        //1. 숫자들을 모두 선택할지 말지로 정한다. recur
        for(int i=1; i<=numbers.length(); i++){
            select = new String[i];
            visit = new boolean [numbers.length()];
            recur(0);
        }
        
        //2. 1인것만 붙여서 숫자로 만든다.
        //3. 소수인지 확인한다.
        return count;
    }
    
}