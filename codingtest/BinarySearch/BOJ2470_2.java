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
 */
public class BOJ2470_2 {

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


    static int lower_bound(int L, int R, int x) {
        int initalR = R;
        int res = R+1;
        while (L <= R){
//            System.out.println("L ="+L+" R="+R+" res= "+res);
            int mid = ( L + R ) / 2;

            if (arr[mid] ==x) return mid;

            if (arr [mid] < x){
                res = mid;
                L = mid +1;

            }else{
                R = mid -1;
            }
        }

        if (res == initalR+1){ //만약 그대로=// R+1이면 원하는 X가 모든 mid 값보다 작았던 것이니까 x와 가장 가까운 값은 L이다.
            System.out.println("그대로임, result = "+ res);
            return L;
        }else{
//            System.out.println("result = "+ res);
            return  res;
        }
    }

    static void pro() {
        best_sum=Integer.MAX_VALUE;
        Arrays.sort(arr, 1,N+1);//1~N까지
        for (int i=1; i<= N-1; i++){
            int y = lower_bound(i+1, N, -arr[i]);
//            System.out.println("i="+i +" arr[i]="+arr[i] +" y="+y);
            int this_sum = arr[i] + arr[y];
                if ( Math.abs(best_sum) > Math.abs(this_sum)){
                    best_sum = this_sum;
                    v1 = arr[i];
                    v2 = arr[y];
                }
        }
        sb.append(v1).append(" ").append(v2); //v1은 v2보다 항상 작다.



    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        System.out.println(sb);
    }
}
