import java.util.*;
class Solution {
    
    public ArrayList<String> listA = new ArrayList<>();
    public ArrayList<String> listB = new ArrayList<>();
    public ArrayList<String> union = new ArrayList<>();
    public ArrayList<String> inter = new ArrayList<>();
    
    public int solution(String str1, String str2) {
        
        int answer = 0;
        //1. 대문자로 변경
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        
        //2. 다중집합으로 만든다.
        for(int i=0; i<str1.length()-1; i++){
            String temp = Character.toString(str1.charAt(i))+ Character.toString(str1.charAt(i+1));
            if(!temp.matches("^[a-zA-Z]*$")) continue;
            listA.add(temp);
        }
        for(int i=0; i<str2.length()-1; i++){
            String temp = Character.toString(str2.charAt(i))+ Character.toString(str2.charAt(i+1));
            if(!temp.matches("^[a-zA-Z]*$")) continue;
            listB.add(temp);
        }

        //4. inter , union 구한다. 
        for(String s : listA){
            if(listB.remove(s)){
                union.add(s);
                inter.add(s);
            }else{
                union.add(s);
            }
        }
        union.addAll(listB);
        
        //5. 자카드 유사도 출력 
        double interD = (double)inter.size();
        double unionD = (double)union.size();
        double jack;
        if(union.size()==0) jack= 1 * 65536;
        else jack = interD / unionD  * 65536;

        return (int) jack;
    }
}