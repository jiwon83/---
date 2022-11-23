package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
골드 5 / 두 용액 https://www.acmicpc.net/problem/2470
시도 : X X
한 번 더 풀기!!
 */
public class BOJ2470 {

    static int[] arr;
    static int N, best_sum;
    static int v1, v2; //항상 v1 < v2
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

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
    //// 그런 게 없다면 R + 1 을 return 한다 ???
    static int lower_bound(int L, int R, int x) {

        int result = R +1 ; //** 아마 모든 값이 X보다 작을 경우 R+1을 리턴할 것이다.

        while (L <= R) { //**부등호 조심할 것. L이 R보다 작거나 같은 동안 반복
            int mid = (L + R) / 2;

            if (arr[mid] == x) return mid; //합이 0이면 더 좋은 값이 있을 수 없음.

            if (arr[mid] <  x) {
                L = mid + 1;

            } else {
                result = mid;
                R = mid - 1;
            }
        }
        return result;
    }

    static void pro() {
        Arrays.sort(arr, 1,N+1 );//*toIndex이전 인덱스 까지임. 주의

        best_sum = Integer.MAX_VALUE;
        int v1=0, v2=0;

        for (int i = 1; i <= N-1; i++) {
            int x = i;
            int y = lower_bound(x+1, N, -arr[x]);

            //예외 처리 만약 x=70이고 69와 78이 남았다면, 사실 69와 더 가깝지만 lowerbound 함수의 조건문에 의해 78이 반환된다.
            //따라서 이문제에서는 최소나 최대가 아니기 때문에 구한 값과 구한값의 인덱스 -1까지도 비교해봐야 하는 것이다.
            if ( x < y-1  &&  Math.abs(arr[y-1] + arr[x]) < best_sum){
                best_sum = Math.abs(arr[y-1] + arr[x]);
                v1 = arr[x];
                v2 = arr[y-1];
            }
            if ( y <= N && Math.abs(arr[y] + arr[x]) < best_sum ){ //만약 R+1이 반환됐다면 OutOfIndexArray발생하기 때문에 범위 조건도 처리해준다.
                best_sum = Math.abs(arr[y] + arr[x]);
                v1 = arr[x];
                v2 = arr[y];
            }

        }
        sb.append(v1).append(" ").append(v2);

    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        System.out.println(sb);
    }
}
