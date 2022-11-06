import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
백준 실버1 귀여운 라이언 https://www.acmicpc.net/problem/15565
체감 난이도: 중
시도: X(예제는 잘 나오지만 틀림, 다른 input에서 틀린듯)
** 현재 틀린 답안. 수정 필요
 */
public class CuteRyan {

    static int [] arr;
    static ArrayList<Integer> ones =new ArrayList<>();
    static int N,K, result;

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
        int result=N+1;
        int cnt=0;
        int R=1;
        for (int L=1; L<=N; L++){ //L의 이동

            if (arr[L]!=1){
                continue;
            }

            if(arr[L]==1){

                while(cnt<3 && R<=N){
                    if(arr[R]==1){
//                        System.out.println("1발견, R="+R);
                        ones.add(R);
                        cnt++;

                    }
                    R++;

                }
                if (cnt>=3){
//                    System.out.println("R: "+R+"L: "+L);
//                    System.out.println("길이: "+(R-L+1-1));
                    result = Math.min(result,(R-L+1-1));
                    R--;
                    cnt -= 2;
                    //L을 다음 1로 옮겨줌.

                }

            }


        }
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
