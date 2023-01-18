class Solution {
    int count=0;
    int seq=0;
    String Word;
    String [] alpha = {"A","E","I","O","U"};
    boolean isFinish = false;
    void recur(int k, String str){
        
        if(isFinish) return;
        
        System.out.println(str+ " >> "+count);// => 먼가 이상
        if(k==5+1){
            if(str.equals(Word)) {
                
                seq = count;
                return;
            }
            count++;
            
        }else{
            if(str.equals(Word)) {
                
                seq = count;
                isFinish =true;
                return;
            }
            else{
                count++;
                
                for(int i=0; i<5; i++){
                    
                    recur(k+1, str+alpha[i]);
                }
                
            }
            
            
        }
    }
    public int solution(String word) {
        Word=word;
        recur(1,"");
        return seq;
    }
}