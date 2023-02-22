import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};
        //정렬 커스터 마이징
        Arrays.sort(files, new Comparator<String>(){
           @Override
            public int compare(String o1, String o2){
                String [] me = distribute(o1);
                String [] other = distribute(o2);
                int first = me[0].toString().compareTo(other[0].toString());
                
                if(first==0){ //숫자 순
                
                    int meNum = Integer.parseInt(me[1]);
                    int otherNum = Integer.parseInt(other[1]);
                    
                    if(meNum != otherNum) return meNum - otherNum;
                    else return 0;//입력순
                }
                return first;//head의 사전 순
                
            }
            // 문자열을 나누는 함수
            private String[] distribute(String s){
                
                s = s.toUpperCase();
                String [] result = {"","",""};
                int i=-1;
                //head
                while(i+1 < s.length() && !Character.isDigit(s.charAt(i+1))){
                    result[0] += s.charAt(++i);
                }
                //number
                while(i+1 < s.length() && result[1].length() < 5 && Character.isDigit(s.charAt(i+1))){
                    result[1] += s.charAt(++i);
                }
                //tail
                result[2] = s.substring(i+1);     

                // System.out.println(Arrays.toString(result));
                return result;
            }
        });

        return files;
    }
}