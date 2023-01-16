class Solution {
    int ans=0;
    boolean [] visit;
    String Begin, Target;
    String [] Words;
    int len;
     
    public void dfs(int wordIdx, int depth){
     
        if(Target.equals(Words[wordIdx])){ //찾으려는 단어를 찾았다면
            if( ans ==0) ans = depth;
            else ans = Math.min(ans, depth);
            
        }else{
            
            
        }
        visit[wordIdx] = true;
        
        
        for(int i=0; i<Words.length; i++){ //다음으로 가능한 단어 탐색.
            if(visit[i]) continue; //방문한 적 있다면 
            
            int sameCnt=0;
            for(int j=0; j < len; j++){ //하나씩 비교해서
                
                if(Words[i].charAt(j)!=Words[wordIdx].charAt(j)){ //현재 단어와 같다면 
                    sameCnt++;
                }
            } 
            //가능하면 dfs호출
            if(sameCnt==1){
                dfs(i, depth+1);
                visit[i]=false; //visit 되돌려놓기
            }
            
        }
        
    }
    public int solution(String begin, String target, String[] words){
        Begin = begin;
        Target = target;
        Words = new String[words.length+1];
        for(int i=0; i<words.length; i++){ //Words deep copy
            Words[i] = words[i];
        }
        Words[words.length] = begin; //시작 단어도 넣어준다.
        len = words[0].length(); //모든 단어의 길이
        visit = new boolean [words.length+1];
        
        //1. dfs 도착하면 ans갱신
        dfs(words.length ,0);
        
        return ans;
    }
}