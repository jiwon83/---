package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
문자열 압축 https://school.programmers.co.kr/learn/courses/30/lessons/60057
 */
public class Pro_문자열압축 {
    static int ans;
    static StringBuilder sb = new StringBuilder();

    public static int solution(String s) {
        ans = Integer.MAX_VALUE;

        Stack<String> stack = new Stack<>();

        for (int u=1; u< s.length()/2+1; u++){ //가능한 단위로 분할 1, 2, 3 ...
//            System.out.println("단위 "+u);
            StringBuilder sb = new StringBuilder();
            //분할
            for (int i=0; i<s.length() / u * u -u + 1; i += u){ //시작점
                String part = s.substring(i, i+u);

                // 스택의 peek와 다른 시점에서 꺼낸다.
                if (!stack.isEmpty() && !stack.peek().equals(part)){

                    if (stack.size() > 1)  sb.append(stack.size());
                    sb.append(stack.peek());

                    stack.clear();
                    stack.add(part);
                }else{
                    stack.add(part);
                }
            }
            //스택에 남아있는 것을 이어 붙인다.
            if (!stack.isEmpty()){
                if (stack.size() != 1)  sb.append(stack.size());
                sb.append(stack.peek());
                stack.clear();
            }
            //남은 부분을 이어 붙인다.
            int left_idx = s.length() - ( s.length() - u * (s.length()/u) );
            sb.append(s.substring(left_idx));

            ans = Math.min(ans,sb.length());


        }
        if (ans ==Integer.MAX_VALUE) return s.length();
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(solution("a"));

    }




}
