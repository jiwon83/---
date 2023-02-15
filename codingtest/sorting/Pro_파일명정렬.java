import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        // String[][] temp = {{"abc","2"},{"abc","1"}};
        String[][] temp = new String[files.length][3];
        //1. files를 2차원 배열로 나눠서 담는다. 
        for(int j=0; j<files.length; j++){
            String file = files[j];
            StringBuilder sb = new StringBuilder();
            String HEAD="";
            String NUMBER="";
            String TAIL="";
                
            for(int i=0; i<file.length(); i++){
                char c = file.charAt(i);
                if(Character.isDigit(c)){ //처음으로 숫자가 나온 시점
                    HEAD = sb.toString();
                    sb.setLength(0);
                    
                    sb.append(c);
                    //이제 숫자가 안나올때까지 반복
                    while( i+1<file.length() && Character.isDigit(file.charAt(i+1)) && sb.length()<5){
                        sb.append(file.charAt(++i));
                 
                    }
                    //
                    NUMBER = sb.toString();
                    //이 시점의 i+1부터는 Tail
                    if(i+1 < file.length()){
                        TAIL = file.substring(i+1);
                    }
                    break;
                }
                sb.append(c);
            }//for i
            // System.out.println(HEAD+" / "+NUMBER+" / "+TAIL);
            temp[j][0] = HEAD;
            temp[j][1] = NUMBER;
            temp[j][2] = TAIL;
            
        }
        //2. 정렬 조건 정리
        
        Arrays.sort(temp, new Comparator<String []>(){
           @Override
            public int compare(String[] s1, String[] s2){
                int headVaule = s1[0].toString().toUpperCase().compareTo(s2[0].toString().toUpperCase());
                
                if(headVaule==0){
                    
                    return Integer.parseInt(s1[1]) - Integer.parseInt(s2[1]);  
                    
                }else{
                    return headVaule; //==0
                }
               
            }
        });
        
        // System.out.println(Arrays.deepToString(temp));
        //change to 1d array 
        
        for(int w=0; w<temp.length; w++){
            String s = temp[w][0]+temp[w][1]+temp[w][2];
            answer[w] = s;
        }
        
        return answer;
    }
}