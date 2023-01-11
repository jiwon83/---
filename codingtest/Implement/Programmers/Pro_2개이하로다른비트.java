class Solution {
    
    boolean ShapeOfAllOne(String long_binary){
        return !long_binary.contains("0");     
    }
    
    public long[] solution(long[] numbers) {
        
        long[] answer = new long[numbers.length];
        
        //1.numbers를 이진수 변환 후 1111 형태인지 10001 형태 인지 확인
        for(int i=0; i<numbers.length; i++){
            
            String long_binary =Long.toBinaryString(numbers[i]);
            
            //2.전자의 경우, 앞자리에 1을 붙이고 그 바로뒤에는 0을 붙인다. 나머지는 그대로 붙인다.
            StringBuilder sb = new StringBuilder();
            if(ShapeOfAllOne(long_binary)){
                
                sb.append("10").append(long_binary.substring(1));
                
            }else{ //3. 후자의 경우 뒤에서 부터 순회하면서 0이 있으면 1로 바꿔준다.
                sb = new StringBuilder(long_binary);
                int zeroIdx = sb.lastIndexOf("0");
                if(zeroIdx == sb.length()-1){ //** 10000, 11000 등의 형태
                    sb.setCharAt(zeroIdx, '1');
                }else{ //10001, 10011, 10101 등 중간에 0이 들어간 형태.
                    sb.setCharAt(zeroIdx, '1');
                    sb.setCharAt(zeroIdx+1,'0');
                }
            }
            
            //4. 2진수를 10진수로 변환하여 출력한다.
            answer[i] = Long.valueOf(sb.toString(), 2);
            
        }
        return answer;
    }
}