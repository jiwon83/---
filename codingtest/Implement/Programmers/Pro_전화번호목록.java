import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        // 1. 정렬
        Arrays.sort(phone_book);
        // System.out.println(Arrays.toString(phone_book));
        for(int i=0; i<phone_book.length-1; i++){
            // 앞뒤를 비교.
            String stand = phone_book[i];
            String back = phone_book[i+1];
            
            if(back.startsWith(stand)){
                return false;
            }
            
        }
        return answer;
    }
}