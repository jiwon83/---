package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
골드 5 / 두 용액 https://www.acmicpc.net/problem/2470
시도 : X X X
한 번 더 풀기!!
코테는 내신이다...!
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

    // 구하고자 하는 X 이상의 값 중에서 가장 작은 값 선택하면
    // 그 값 또는 그 값 보다 바로 앞에 있는 값 중에 정답일 것이다.
    // 그 이유는 X 기준으로 가장 가까운 작은 값과 가장 가까운 큰값 이기 때문이다.
    //예를 들어 구하고자 하는 값이 2 라고 할때 [ -1 4 98 ]이 있다면 2이상인 값 중 가장 같은 4와 -1중에 하나일 것이다.
    // 둘 중에 정할 수는 없다. 왜냐하면 이분 탐색에서는 큰 지 작은 지만 보고 얼마나 작은지 얼마나 큰지는 반영할 수 없기 때문이다.
    // 동시에, 그렇다면 이함수에서 res에 저장하는 값은 x기준 이상인 값만을 저장하기 때문에
    //만약, 모든 리스트의 값이 x 보다 작다면 (ex, x=99, list =[-1,-2,4,98] ) res는 초기값인 R+1을 반환하게 된다.

    static int lower_bound(int L, int R, int x) {

        int result = R +1 ; //** 아마 모든 값이 X보다 작을 경우 R+1을 리턴할 것이다.

        while (L <= R) { //**부등호 조심할 것. L이 R보다 작거나 같은 동안 반복
            int mid = ( L +R ) / 2;
            if (arr[mid] >= x){
                result = mid;
                R = mid -1;
            }else {
                L = mid + 1;

            }
         }
        System.out.println("x= "+ x+ " result = "+ result);
        return result;
    }

    static void pro() {
        Arrays.sort(arr, 1,N+1 );//*toIndex이전 인덱스 까지임. 주의

        best_sum = Integer.MAX_VALUE;
        int v1=0, v2=0;

        for (int i = 1; i <= N-1; i++) {
            int x = i;
            int y = lower_bound(x+1, N, -arr[x]);

            //만약 y = N+1 이라면, 이 if문에 들어오게 된다. 그렇지 않더라도 이 if문에 들어오게 된다.
            if ( x <y-1 &&  Math.abs(arr[x] + arr[y-1]) < best_sum ){
                best_sum = Math.abs(arr[y-1] + arr[x]);
                v1 = arr[x];
                v2 = arr[y-1];
            }
            //만약 y <= N 이라면, 구하려는 x보다 작은 값이 하나 이상 있어서 result값이 제대로 나온 것.
            if (y <= N && Math.abs(arr[x] + arr[y]) < best_sum ) {
                best_sum = Math.abs(arr[x] + arr[y]);
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
