import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        
        Arrays.sort(files, new Comparator<String>(){
           @Override
            public int compare(String s1, String s2){
      
                String head1 = makeHead(s1);
                String head2 = makeHead(s2);
                int number=makeNumber(head1.length(), s1);
                int number2=makeNumber(head2.length(), s2);
   
                int CompareHead = head1.compareTo(head2.toString());

                if( CompareHead == 0 ){
                    if(number != number2){
                        return number - number2;
                    }else{
                        return 0;
                    }
                }
                return CompareHead;
            }
            
            public String makeHead(String str){
                str = str.toUpperCase();
                StringBuilder head = new StringBuilder();
                int idx = -1;
                while(! ('0' <= str.charAt(idx+1) && str.charAt(idx+1) <= '9') ){ //다음이 숫자가 아닌 동안.
                    head.append(str.charAt(++idx));
                }
                return head.toString();
            }
            
            public int makeNumber(int startIdx, String str){ //int구하기 : 한 글자에서 최대 다섯 글자 사이의 연속된 숫자
                int idx = startIdx;
                StringBuilder num = new StringBuilder();
                
                while( idx < str.length() && num.length()<5 && '0' <= str.charAt(idx) && str.charAt(idx) <= '9' ){
                    num.append(str.charAt(idx++));
                }
                return Integer.parseInt(num.toString());
            }
            
        });
        return files;
    }
}