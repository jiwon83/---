package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
실버2 나무 자르기 https://www.acmicpc.net/problem/2805

시도 : X(틀렸다고 한다. 예제는 잘 나옴), O

입력
4 7
20 15 10 17

출력
15

** sum => long type 유의 , 그러나 index는 int형 이여야한다.
 */
public class BOJ2805 {
    static long [] A; //나무들을 담을 배열
    static int N;
    static long M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //나무의 갯수
        M = Long.parseLong(st.nextToken()); //절단 높이

        A = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++){
            A[i] = Long.parseLong(st.nextToken());
        }

    }
    //최적의 길이 h를 구하기 위한 함수
    static long lower_bound(long L, long R, long x){
        long h=L-1;

        while (L <= R){

            long mid = (L + R) / 2;
            long sum =0;

            //나무 배열 A를 돌면서 sum구하기
            for (int i=1; i<=N; i++){
                if (mid < A[i]) {
                    sum += A[i] - mid;
                }
            }

            if (sum < M) {
                R = mid -1;
            }
            else if(sum >= M){
                L= mid +1;
                h= mid;
            }
        }
        return h;
    }
    static void pro() {
        Arrays.sort(A, 1, A.length); //**주의 1 ~ A.length-1까지만 정렬
        long L = 0;
        long R = A[N];
        long h = lower_bound(L, R, M);
        System.out.println(h);


    }
    public static void main(String[] args) throws IOException{
        input();
        pro();
    }

}
