package programmers.D_22_12_02;

import java.io.*;
import java.util.*;

/*
콜라츠 추측
https://school.programmers.co.kr/learn/courses/30/lessons/12943
num을 long형으로 안써서 계속 틀렸었음. 626331을 넣었을 때, 실제 7,222,283,188 값이 중간에 나옴.
 */
public class Solution1 {

    static int count;

    //    public int solution(long num) {
//        //while 문으로 돌려보자 1이 나올때까지
//        int answer = 0;
//        int count = 0;
//
//        while (num != 1) {
//
//            if (count == 500) {
//                count = -1;
//                break;
//            }
//            if (isEven(num)) { //짝수라면
//                num /= 2;
//
//            } else {
//                num = num * 3 + 1;
//            }
//            count++; //1
//
//        }
//        answer = count;
//        return answer;
//    }
    public long solution(long num) {

        long answer = recur(num);
        return answer;
    }

    public long recur(long num) {
        if (num == 1) return 0;

        if (isEven(num)) {
            count++; this.recur(num / 2);
        }
        else { count++;this.recur(num * 3 + 1);}

        if (count >= 500) {

            return -1;
        }

        return count;
    }

    public boolean isEven(long num) {
        if (num % 2 == 0) return true;
        else return false;

    }


    public static void main(String[] args) {
        Solution1 s = new Solution1();
        System.out.println(s.solution(626331));


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
