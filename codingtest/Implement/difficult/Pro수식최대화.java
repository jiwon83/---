package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Pro수식최대화 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static ArrayList<Long> nums = new ArrayList<>();
    static ArrayList<String> opers = new ArrayList<>();
    static int[] priorOper = new int[3];
    static boolean[] vistPriorOper = new boolean[3];
    static String[] stand = {"+", "-", "*"}; //0 1 2
    static String expression;
    static long max_result=0;

    /* 필요한 함수들 */
    void preprocess() {
        //연산자와 숫자들 분리
        String store = "";
        for (int i = 0; i < expression.length(); i++) {
            char n = expression.charAt(i);

            if (n == '+' || n == '-' || n == '*') {
                nums.add(Long.valueOf(store));
                store = "";
                opers.add(n + "");
            } else {
                store += n;
            }
        }
        nums.add(Long.valueOf(store));
    }

    void recurMakePrior(int k) {

        //우선 순위 조합 생성
        if (k >= 3) {
            long result = getResult();
            max_result = Math.max(Math.abs(result), max_result);
        }
        for (int i = 0; i < 3; i++) {
            if (!vistPriorOper[i]) {
                priorOper[k] = i;
                vistPriorOper[i] = true;
                this.recurMakePrior(k + 1);
                vistPriorOper[i] = false;
            }

        }

        //조합에 따른 결과를 갱신해서
        //최종 최대값 반환

    }

    long getResult() {
//        System.out.print(Arrays.toString(priorOper)+ "/ ");
        //현재 우선순위를 기준으로 계산 결과를 도출
        ArrayList<Long> numsCp = new ArrayList<>();
        ArrayList<String> opersCp = new ArrayList<>();
        numsCp.addAll(nums);
        opersCp.addAll(opers);

        for (int i = 0; i < priorOper.length; i++) { //우선 순위 순으로 0 1 2
            for (int j = 0; j < opersCp.size(); j++) { //연산자들을 순회하며 + * -
                if (stand[priorOper[i]].equals(opersCp.get(j))) {
                    long val = calculate(numsCp.get(j), numsCp.get(j + 1), priorOper[i]);
//                    System.out.println("cal "+ numsCp.get(j) + " "+ stand[i]+ " "+  numsCp.get(j + 1)+" = "+val);
                    //nums에 계산 결과 갱신
                    numsCp.remove(j + 1);
                    numsCp.remove(j);
                    opersCp.remove(j);
                    numsCp.add(j, val);
                    //index 앞으로 땡기기
                    j--;

                }
            }
        }
//        System.out.println("결과: "+ numsCp.get(0));
        return numsCp.get(0);
    }

    long calculate(long a, long b, int operator) {
        long result = 0;
        switch (operator) {
            case 0:
                result = a + b;
                break;
            case 1:
                result = a - b;
                break;

            case 2:
                result = a * b;
                break;
        }
        return result;
    }


    public long solution(String expression) {
        this.expression = expression;
        preprocess();
        recurMakePrior(0);
        return max_result;
    }


    public static void main(String[] args) {
        Pro수식최대화 s = new Pro수식최대화();
        System.out.println(s.solution("100-200*300-500+20"));
        sc.close();

    }

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
