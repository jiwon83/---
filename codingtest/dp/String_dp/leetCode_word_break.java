class Solution {
    int [][] dp;
    public int find(int starIdx, int endIdx, String s, List<String> wordDict){
        if(dp[starIdx][endIdx] != -1){
            return dp[starIdx][endIdx];
        }
        if(wordDict.contains(s.substring(starIdx, endIdx+1))){
            dp[starIdx][endIdx] = 1;
            return dp[starIdx][endIdx];
        }else if(starIdx==endIdx){
            dp[starIdx][endIdx]=0;
            return dp[starIdx][endIdx];
        }else{
            for(int sp=starIdx; sp< endIdx; sp++){
                if((find(starIdx, sp, s, wordDict) & find(sp+1, endIdx, s, wordDict) )==1 ){
                    return 1;
                }
            }
        }
        dp[starIdx][endIdx]=0;
        return dp[starIdx][endIdx];
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        dp = new int[s.length()][s.length()];
        for(int i=0; i<s.length(); i++){
            for(int j=0; j<s.length(); j++){
                dp[i][j] = -1;
            }
        }
        int a = find(0,s.length()-1, s, wordDict);
        System.out.println(Arrays.deepToString(dp));
        if( a == 1 ) return true;
        else return false;
    }
}