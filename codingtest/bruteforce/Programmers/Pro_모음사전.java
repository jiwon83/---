class Solution {
    int count =0;
    String mou = "AEIOU";
    int ans;
    public void recur(int k, String str, String word){
        if(k==6){
            return;
        }
        for(int i=0; i<mou.length(); i++){  
            count++;
            if((str + mou.charAt(i)).equals(word)){
                ans =count;
                return;
            }
            recur(k+1, str + mou.charAt(i), word);
        }
        
    }
    public int solution(String word) {
        int answer = 0;
        recur(1, "", word);
        return ans;

    }
}