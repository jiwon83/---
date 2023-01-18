import java.util.*;
class Solution {
    //1. 정렬를 다르게 할 클래스 생성
    class Num implements Comparable<Num>{
        
        public Num(int i){
            num = i;
        }
        public int num;
        
        @Override
        public int compareTo(Num other){
            int frontThis = String.valueOf(num).charAt(0)-'0';
            int frontOther = String.valueOf(other.num).charAt(0)-'0';
            // if(frontThis != frontOther) return ;
            return String.valueOf(other.num).compareTo(String.valueOf(num));
        }
        public String toString(){
            return num+"";
        }
        
    }
    int [] Numbers;
    List<Num> nums= new ArrayList<Num>();
    public String solution(int[] numbers) {
        for(int i=0; i<numbers.length; i++){
            nums.add(new Num(numbers[i]));
        }
        Collections.sort(nums);
        System.out.println(Collections.unmodifiableList(nums));
        System.out.println("-------");
        String answer = "";
        // Numbers =numbers;
        //2. 정렬 
        // Arrays.sort(Numbers);

        //3. 모든 원소를 이어 붙인다.
        for(Num x : nums){
            answer += x.num;
        }
       
        return answer;
    }
}