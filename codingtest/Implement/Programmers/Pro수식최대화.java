import java.util.*;
class Solution {
    int [] select = new int[3];
    boolean [] visit = new boolean[3];
    List<Integer> opers = new ArrayList<>();
    List<Long> nums = new ArrayList<>();
    long ans= Long.MIN_VALUE;
    
    public void recur(int k){
        if(k==3){
            // System.out.println(Arrays.toString(select));
            calculate(select);
        }else{
            for(int i=0; i<3; i++){
                if(visit[i]) continue;
                visit[i] = true;
                select[k]=i;
                recur(k+1);
                visit[i] = false;
            }
        }
        
    }
    public long calNums(long num1, long num2, int oper){
        switch(oper){
            case 0:
                return num1 + num2;
            case 1:
                return num1 - num2;
            case 2:
                return num1* num2;
        }
        return -1;
    }
    public void calculate(int [] select){ //이에 따른 수식 계산 결괏값 갱신 => 중간 계산 값은 long
        long result = 0;
        ArrayList<Long> numsCp = new ArrayList<>();
        ArrayList<Integer> opersCp = new ArrayList<>();
        numsCp.addAll(nums);
        opersCp.addAll(opers);
        //절댓값으로 갱신
        for(int i=0; i<3; i++){
            int seq = select[i];
            for(int j=0; j<opersCp.size(); j++){
            
                if(seq == opersCp.get(j)){
                    //바로 계산
        
                    long val = calNums(numsCp.get(j), numsCp.get(j+1), opersCp.get(j));
                    
                    numsCp.add(j,val);
                    numsCp.remove(j+1); numsCp.remove(j+1); 
                    opersCp.remove(j);
                    j--;
                    
                }
                
            }
        }

        result = Math.abs(numsCp.get(0));
        ans = Math.max(ans, result);
    }
    public long solution(String expression) {
        long answer = 0;
        //1. 연산자와 숫자 분리해서 저장
        StringBuilder sb =new StringBuilder();
        for(int i=0; i<expression.length(); i++){
            char c = expression.charAt(i);
            if(!Character.isDigit(c)){//숫자가 아니면 이전 값 nums에 저장, oper 저장 , 비우기
                nums.add(Long.parseLong(sb.toString()));
                sb.setLength(0);
                opers.add(changeToInt(c));
            
            }else{//숫자이면 keep
                sb.append(c);
            } 
        }
        nums.add(Long.parseLong(sb.toString()));

        
        //2. 우선순위 정하기 // 우선순위는 + - * 0 1 2의미
        recur(0);
        //3. 이에 따른 수식 계산 결괏값 갱신 => 중간 계산 값은 long
        
        
        return ans;
    }
    public int changeToInt(char oper){
        switch(oper){
            case '+':
                return 0;
            case '-':
                return 1;
            case '*':
                return 2;
        }
        return -1;
    }
}