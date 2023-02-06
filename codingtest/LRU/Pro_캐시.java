import java.util.*;
class Solution {
    static final int CACHE_HIT = 1;
    static final int CACHE_MISS = 5;
    public int answer = 0;
    public ArrayList<String> cache = new ArrayList<>();
    
    public int solution(int cacheSize, String[] cities) {
       
        if( cacheSize==0) return 5 * cities.length; //cache가 0일 때
         
        for(int i=0; i<cities.length; i++){
            
            cities[i] = cities[i].toUpperCase(); //대소문자 구별 X
            
            if( cache.size() !=0 && cache.contains( cities[i] ) ){ //캐시안에 있다면
                
                answer += CACHE_HIT;
                cache.remove( cities[i]  );
                cache.add( cities[i] ); //최신에 사용됐으므로 뒤로 이동
                
            }else{ //캐시안에 없다면
                if(cache.size() < cacheSize){        
                    cache.add( cities[i] );
                }
                else if(cache.size() == cacheSize){ //크게 될 경우는 없어서
                    cache.remove(0); 
                    cache.add( cities[i] );
                }
                answer += CACHE_MISS; 
            }
        }//for
        return answer;
    }
}