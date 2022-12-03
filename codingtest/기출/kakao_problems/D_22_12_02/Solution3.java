package programmers.D_22_12_02;

import java.io.*;
import java.util.*;
/*
프로그래머스 수식 최대화 https://school.programmers.co.kr/learn/courses/30/lessons/67257
한번 더 풀기
 */
public class Solution3 {

    static ArrayList<Long> nums = new ArrayList<>();
    static ArrayList<String> opers = new ArrayList<>();


    static String[] basicOper = {"+", "-", "*"};
    static long ans = Long.MIN_VALUE;
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    /* 필요한 함수들 */
    //step1.
    public void parsing(String s) {
        //nums와 operation 분리
        String n = "";
        for (int i = 0; i < s.length(); i++) {
            char exp = s.charAt(i);
            if (exp == '+' || exp == '-' || exp == '*') {
                opers.add(exp + "");
                nums.add(Long.valueOf(n));
                n = "";
            } else {
                n += exp;
            }
        }
        nums.add(Long.valueOf(n));
//        System.out.println(Collections.unmodifiableList(nums));
//        System.out.println(Collections.unmodifiableList(opers));
    }

    //step2
    static int[] priOper = new int[3]; //연산자 우선 순위 조합. [1,2,0] [1,1,1] 이 문제에선 최대 3개임.
    static boolean[] visit = new boolean[3];


    public void makeCombiOperation(int k) {
        //재귀를 통해 조합 생성
        int max = priOper.length;

        if (k == max) { //모든 자릿수에 다 넣었다면
            calculation(priOper);
        } else {
            for (int i = 0; i < max; i++) { //0 1 2
                if (!visit[i]) {
                    priOper[k] = i;
                    visit[i] = true;
                    this.makeCombiOperation(k + 1);
                    visit[i] = false;
                }
            }
        }


    }

    //step3
    public void calculation(int[] priOper) {
        System.out.println("연산자 우선 순위"+ basicOper[priOper[0]]+" > "+ basicOper[priOper[1]]+ " > "+ basicOper[priOper[2]]);
        ArrayList<Long> thisNums = new ArrayList<>();
        ArrayList<String> thisOpers = new ArrayList<>();
        thisOpers.addAll(opers);
        thisNums.addAll(nums);

        //연산자 순위로 값을 계산
        for (int i = 0; i < priOper.length; i++) { //우선 순위 배열을 모두 순회하면서
            //주어진 nums와 opers를 순차적으로 돌면서 계산
            for (int j = 0; j < thisOpers.size(); j++) {
                if (thisOpers.get(j).equals(basicOper[priOper[i]])) {
                    //연산
                    long res = operation(thisNums.get(j), thisNums.get(j + 1), priOper[i]);
                    System.out.println("결과값="+res + "숫자1: "+ thisNums.get(j)+basicOper[priOper[i]]+" 숫자 2: "+thisNums.get(j + 1) );
                    thisNums.remove(j+1); //** 큰 것 부터 지워줘야 함.
                    thisNums.remove(j);
                    thisOpers.remove(j);
                    thisNums.add(j, res);
                    j--;
                }

            }
        }

        //배열에 갱신
        //ans갱신
        ans = Math.max(ans, Math.abs(thisNums.get(0)));
    }

    public long operation(long num1, long num2, int operator) { //+ - *
        long num=0;
        switch (operator){
            case 0: num = num1 + num2; break;
            case 1: num = num1 - num2; break;
            case 2: num = num1 * num2; break;
        }
        return num;
    }


    public long solution(String expression) {


        parsing(expression);
//        calculation(new int[]{0, 1, 2});
        makeCombiOperation(0);
        return ans;
    }


    public static void main(String[] args) {
        Solution3 s = new Solution3();
//        System.out.println(s.solution("100-200*300-500+20"));
        System.out.println(s.solution("100-200"));
        System.out.println(s.solution("100-200-300+600"));
        sc.close();

    }

    //입출력 관련
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
