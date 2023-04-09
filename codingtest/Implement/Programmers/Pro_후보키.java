import java.util.*;
class Solution {
    int R, C;
    int [] select;
    List<Integer> list = new ArrayList<>();
    
    public boolean isUnique(int []  select,  String[][] relation){
        Set<String> set = new HashSet<>();
        for(int r=0; r <R; r++){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<C; i++){
                if(select[i] == 1) sb.append(relation[r][i]).append(" ");
            }
            set.add(sb.toString());  
        }
        return set.size() == R;  
    }
    //최소성을 만족하는가
    public int isMinimim(List<Integer> list){  
        //list에서 중복되는 것이 있는 지 확인 
        boolean [] tf = new boolean[list.size()];
        
        for(int i=0; i<list.size(); i++){
            if(tf[i]) continue;
            for(int j=i+1; j<list.size(); j++){
                if(tf[j]) continue;
                if((list.get(i) & list.get(j)) == list.get(i)){
                    // System.out.println("최소성 만족 X"+ Integer.toBinaryString(list.get(i))+" , "+ Integer.toBinaryString(list.get(j)));
                    tf[j] = true;
                }
                
            }
        }
        int count =0;
        for(int i=0; i<list.size(); i++){
            if(!tf[i]) count++;
        }

        return count;

    }
    public int numToBinary(String binary){
        return Integer.parseInt(binary, 2);
    }
    public void printBinaryToStr(List<Integer> list){ //이진수를 String으로 
        for(Integer i : list){
            System.out.println(Integer.toBinaryString(i));
        }
        
    }
    public String selectToStr(int [] select){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<select.length; i++){
            sb.append(select[i]);
        }
        return sb.toString();
    }
    public void recur(int k, String[][] relation){
        if(k==C){
            if(isUnique(select, relation)){
                //이진수로 바꿔서 저장
                list.add(numToBinary(selectToStr(select)));
            }
            return;
        }
        select[k]=0;
        recur(k+1,relation);
        select[k] =1;
        recur(k+1, relation);

    }

    public int solution(String[][] relation) {

        R = relation.length;
        C = relation[0].length;
        select = new int[C];

        //1. 후보키 조합을 만들어본다. 
        recur(0, relation );

        Collections.sort(list);

        return isMinimim(list); //2. 최소성을 만족시키도록 가공

    }
}