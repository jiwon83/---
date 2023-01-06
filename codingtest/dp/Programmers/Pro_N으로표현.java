import java.util.HashSet;
import java.util.Set;
import java.util.*;
class Solution {       
    static int answer = -1;
    public static int solution(int N, int number) {
        
        //1. N으로 1~ 8까지 사용한 수를 만들어 set에 갱신할 것임 (dp).
        Set<Integer> [] set = new HashSet[9];
        int num=0;
        //2. 그전에 초기화 5, 55, 555
        for(int i=1; i<=8; i++){
            
            set[i] = new HashSet<>();
            num = 10 * num  + N;
            set[i].add(num);
        
        }
        //3. for n = 1 ~ 8 N 이 사용된 갯수이자 , set 배열의 index
        for(int n = 1; n <=8; n++){
            
        //4. for i = 1~ n/2
            for( int i = 1; i <= n / 2; i ++ ){
                //5. j = n-i, set[i] 와 set[j] 를 cross 계산
                int j = n - i;
                
                //6. for Integer a = set[i]
                for( Integer a : set[i]){
                    //7. for Integer b = set[j]
                    for( Integer b : set[j]){
                        //8. a + b, a - b, a*b. a/b, b/a 를 set[n].add();
                        set[n].add( a + b);
                        set[n].add( a - b);
                        set[n].add( a * b);
                        if( b > 0) set[n].add( a / b);
                
                        if( a > 0)set[n].add( b / a);
                        
                    }
                    
                }
      
            }
            //9. set[n] 을 모두 돌며 number가 있는 지 확인 있다면, return n;
            if( set[n].contains(number)) return n;

        }// n
        
        return -1;
    }//solution
}