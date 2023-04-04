//https://school.programmers.co.kr/learn/courses/30/lessons/17687
class Solution {
    public String solution(int n, int t, int m, int p) {

        int minLen=m*t;
        int nowNum=0;
        StringBuilder temp =  new StringBuilder();
        while(temp.length() < minLen ){
            temp.append(Integer.toString(nowNum++, n)); //진수 변환후 다 더한다. 
        }
        // System.out.println(temp);
        //* 소문자 -> 대문자
        StringBuilder answer = new StringBuilder();
        for(int i=p-1; i<minLen; i+= m){
            String s = (temp.charAt(i)+"").toUpperCase();
            answer.append(s);
        }
        //소문자만 대문자로 바꿔서 출력
      
        return answer.toString();
    }
}