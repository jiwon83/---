import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
백준 실버1 귀여운 라이언 https://www.acmicpc.net/problem/15565
체감 난이도: 중
시도: X

 */
public class CuteRyan {

    static int [] arr;
    static ArrayList<Integer> ones =new ArrayList<>();
    static int N,K;

    static void input(){
        FastReader2 scan = new FastReader2();
        N = scan.nextInt();
        K = scan.nextInt();
        arr = new int[N+1];
        for (int i=1; i<=N; i++){
            arr[i]= scan.nextInt();
        }

    }
    static void pro(){
        int result = N+1;
        int cnt=0, R=0;
        for (int L=1; L<=N; L++){ //L의 이동
            //R의 이동
            while(cnt<K && R<N){
                R++;
                if(arr[R]==1){
//                    ones.add(R);
                    cnt++;
                }
            }
            if (cnt==K){
                result = Math.min(result,(R-L+1));
            }
            if(arr[L]==1){
                cnt--;

            }
        }
        if (result==N+1) result = -1;
        System.out.println(result);
    }
    public static void main(String[] args) {
        input();
        pro();
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
