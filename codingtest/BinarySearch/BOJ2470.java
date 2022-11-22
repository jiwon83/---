package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
두 용액 https://www.acmicpc.net/problem/2470
시도 : X
 */
public class BOJ2470 {

    static int[] arr;
    static int N, ans;
    static int[] ansArr = new int[2];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];//1~N인덱스만 사용.
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    //최적의 합을 만드는 값의 index를 반환하는 이분 탐색 함수
    //** 비교 대상인 index x는 제외 해야함. 
    static int lower_bound(int L, int R, int x) {

        int result = L - 1;
        while (L <= R) { //**부등호 조심할 것. L이 R보다 작거나 같은 동안 반복
            int mid = (L + R) / 2;

            if (arr[mid] + x == 0) return mid; //합이 0이면 더 좋은 값이 있을 수 없음.
            if (L == R) return mid; //다 찾으면

            if (arr[mid] + x < 0) {
                L = mid + 1;

            } else {
                R = mid - 1;
            }
        }
        //아마 이단계로 오지 않을 것임.
        return result;
    }

    static void pro() {
        Arrays.sort(arr, 1, arr.length );//*toIndex이전 인덱스 까지임. 주의
//        System.out.println(Arrays.toString(arr));
        ans = Integer.MAX_VALUE;
        for (int i = 1; i <= N-1; i++) {
            int x = i;
            int y = lower_bound(x+1, arr.length - 1,arr[x]);

            int sol_sum = Math.abs(arr[i] + arr[y]);
            if (sol_sum < ans) {
                ans = Math.min(ans, sol_sum);
                ansArr[0] = arr[x];
                ansArr[1] = arr[y];
//                System.out.println(x + "/ " + y);
//                System.out.println("-------");
//                System.out.println(ansArr[0] + "/ " + ansArr[1]);

            }

        }
        Arrays.sort(ansArr);
        System.out.println(ansArr[0] + " " + ansArr[1]);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
