package bruteforce;

import naver.Day22_12_03.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
/*
https://www.acmicpc.net/problem/1759
 한번 더 풀어보기
 */
public class BOJ1759_암호만들기 {
    static int M,N;
    static String [] A;
    static int [] selected;
    static int consonant, vowel;
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();

    static void input() {
        M = sc.nextInt();
        N= sc.nextInt();
        A = new String[N+1];
        selected = new int[M+1];
        for (int i=1; i<= N; i++){
            A[i] = sc.next();
        }

    }
    static void recur(int k){
        if (k == M+1){
            //자음의 갯수와 모음의 갯수를 count
            vowel=0; consonant =0;
//            System.out.println(Arrays.toString(selected));
            for (int i=1; i<=M; i++){
                if (isVowel(A[ selected[i] ])){ //모음이라면
                    vowel++;
                }else consonant++;

            }
            if (consonant>=2 && vowel >=1){ //조건을 충족하면
                for (int i=1; i<=M; i++) sb.append(A[selected[i]]);
                sb.append("\n");
            }
            return;
        }
        for (int cand = selected[k-1]+1; cand <=N; cand++){
            selected[k] = cand;
            recur(k+1);
            selected[k] =0;

        }

    }
    static public boolean isVowel(String s) {
        boolean tf = false;
        if (s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u")) {
            tf = true;
        }
        return tf;
    }
    static void pro() {

        Arrays.sort(A, 1, N+1);
        recur(1);
        System.out.println(sb);

    }
    public static void main(String[] args) {
        input();
        pro();
    }
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

        }
        String next(){
            while (st == null || !st.hasMoreTokens()){  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){return Long.parseLong(next()); }

        double nextDouble(){return Double.parseDouble(next());}

        String nextLine(){
            String str ="";
            try {
                str = br.readLine();

            }catch (IOException e){
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
