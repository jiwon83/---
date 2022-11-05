import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
백준 부분합 https://www.acmicpc.net/problem/1806
시도: X
 */
public class SubTotal {
    static StringBuilder sb = new StringBuilder();
    static FastReader2 scan = new FastReader2();
    static int N, S;
    static int [] arr;

    static void input(){

        N = scan.nextInt();
        S = scan.nextInt();
        arr = new int[N+1];
        for (int i=1; i<N+1; i++){
            arr[i]= scan.nextInt();
        }
    }
    static int pro(){
        int R = 0;
        int sum =0;
        int ans = N+1;

        for (int L=1; L<=N; L++){
            //현재의 L~R 범위의 sum을 만들기 위해 이전 인덱스 L값을 빼준다.
            sum -= arr[L-1]; //맨처음에는 아무것도 없는 0을 sum에서 빼기 위해 배열(arr)을 인덱스 1부터 사용한 것이다.
            while (R+1 <= N && sum < S){
                sum += arr[++R]; //기존의 sum 값을 최대한 활용하여, R을 하나씩 옮겨 가며 sum에 추가해준다.
            }
            if (sum >= S){
                ans = Math.min(ans, R-L+1);
//                System.out.println("ans"+ans);
            }
        }
        if (ans == N+1) ans=0;

        return ans;
    }
    public static void main(String[] args) {
        input();
        System.out.println(pro());
    }
    static class FastReader2 {
        BufferedReader br;
        StringTokenizer st;

        public FastReader2() {
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
    }
}
