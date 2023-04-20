import java.util.*;
class Solution {
    long [] terms;
    long MAX_TIME;
    int MAX=-1;
    
    public void makeTerms(int [] food_times){

        for(int i=0; i<food_times.length; i++){
            MAX = Math.max(MAX, food_times[i]);
        }
        terms = new long[MAX+1];
        //1. 일단 -없이 가능한 값을 채운다.
        for(int i=1; i<=MAX; i++){
            terms[i] = (i - 1) * food_times.length +1;
        }
         // System.out.println(Arrays.toString(terms));
        //2. -갯수를 센다.
        long minusCnt=0;
        for(int i=1; i<=MAX; i++){
            terms[i] -= minusCnt;
            for(int j=0; j<food_times.length; j++){
                if(food_times[j] < i){
                    minusCnt++;
                }
            }
            
        }
        MAX_TIME = food_times.length * MAX - minusCnt;
    }
    

    public int solution(int[] food_times, long k) {
        makeTerms(food_times); 
        // System.out.println(Arrays.toString(terms));
        //find
        //1. term을 찾는다.
        int myTerm= -1;
        if( k >= MAX_TIME) return -1;
        k = k+1;
        
        for(int i=1; i<MAX; i++){ //10^8
            long min = terms[i]; long max = terms[i+1];
            if( k  >= min && k < max){
                myTerm= i;
            }
        }
        if(k >= terms[MAX]) myTerm = MAX;
        // System.out.println("myTerm "+myTerm);
//         //2. 
        long moveCnt = k - terms[myTerm];
        // System.out.println("moveCnt "+moveCnt);
        
        int pos =0;
        for(int i=0; i<=moveCnt; i++){//0 1 2
            
            while(food_times[pos] < myTerm){ //food_times의 값이 term보다 작으면 skip;
                pos++;
            }
            pos++;

        }
        // System.out.println("--pos "+ pos);

        return pos;
        // return -1;
    }
}