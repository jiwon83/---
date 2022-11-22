package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
실버 3  https://www.acmicpc.net/problem/7795
 */
public class BOJ7795 {
    static int N,M, ans; //N = a 배열의 갯수
    static int [] A, B;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N+1];

        B = new int[M+1];



        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++){
            A[i]= Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=M; i++){
            B[i]= Integer.parseInt(st.nextToken());
        }


//        System.out.println(Arrays.toString(A));
//        System.out.println(Arrays.toString(B));

    }
    //적은 갯수(먹을 수 있는 갯수 반환
    static int lower_bound(int [] B, int L,int R, int x){
        int num = L-1;

        while (L <= R){
            //이분 탐색
            int mid = (L + R) / 2;
            /*
            이 부분 다시 생각해 볼 것 , 왜 안되는지
             */
            //++ 만약 mid == x 라면??
//            if (B[mid] == x){
//                num = mid - 1;
//                break;
//            }

            //만약 mid < X
            if (B[mid] < x){ //이럴 경우 mid는 무조건 X보다 작고 먹을 수 있으므로 num갱신
                num = mid;
                L = mid + 1;
            }else if ( B[mid] >= x){ //더 작은 영역에서 찾아봐야 함. 같아도
                R = mid - 1;
            }
        }
        return num;

    }

    static void pro() {
//        System.out.println("pro함수에 진입");

        Arrays.sort(B, 1, M+1);//B 정렬
        ans =0;
        for(int i=1; i <= N; i++ ){//A배열
            ans += lower_bound(B, 1, B.length-1, A[i]);
        }
//        sb.append(ans).append('\n');
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
//            System.out.println("T>>"+T);
            input();
            pro();

        }
//        System.out.println(sb);
        br.close();

    }
}
