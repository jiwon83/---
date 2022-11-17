package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/*
골드 4 N-Queen https://www.acmicpc.net/problem/9663
시도: X , X
 */
public class NQueen {

    static int[] colQ; //Queen들의 정보( 어짜피 퀸은 한 줄에 하나만 들어간다는 것을 이용), index => row, value => colum
    static int N, ans=0; //ans, answer의 약자 => 퀸을 놓는 방법의 수

    static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        colQ = new int[N+1];

    }

    static void recur(int k) {
        if (k == N+1){ //체스판 한 판을 다 돌면
            ans++;
        }else {
            for (int col=1; col<=N; col++){ //모든 열을 넣어본다.

                boolean tf = true; //모든 이전 퀸들과 비교 했을 때 가능해야함.

                for (int i = 1; i<k; i++){ //rowQ에서 모든 Queen들과 isPossible()를 통해 놓을 수 있는 지 확인하기 위한 구문. => k(현재row)이전까지만 확인하면 된다.
                    //여기서 퀸을 놓는다.
                    if (!isPossible(k, col , i, colQ[i])) {//가능하지 않은게 하나라도 있다면

                        tf= false;

                    }
                }
                if (tf){
                    //퀀을 놓는다.
                    colQ[k] = col; //현재 row는 k이고 colum 은 col임. 따라서 이 것을 rowQ에 저장.
                    recur(k+1);
                    colQ[k] =0;
                }


            }
        }

    }

    static boolean isPossible(int r, int c, int qr, int qc) {
        boolean tf = true;

        //같은 x좌표
        if (c==qc) tf=false;
        //같은 /
        if (r+c == qr+qc) tf =false;
        //같은 \
        if (r-c == qr-qc) tf =false;

        return  tf;
    }

    //참고, 알고리즘이랑은 상관 X
    //test(1)넣으면 for문이 아예 안돌아가지만 error는 발생하지 않음. test(1);
    static void test(int row) {
        for (int i = 1; i <= row - 1; i++) {
            // (i, col[i])
            System.out.println("is working");
        }
    }


    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().gc();//메모리 정리

        input();
        recur(1); //row 1부터 시작
        System.out.println(ans);

        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        StringBuilder retFormat = new StringBuilder();

        String[] strArr = {"byte", "KB", "MB", "GB"};

        if(usedMemory != 0) {
            int idx = (int)Math.floor(Math.log(usedMemory)/ Math.log(1024));
            DecimalFormat df = new DecimalFormat("#,###.##");
            double ret = usedMemory / Math.pow(1024, Math.floor(idx));
            retFormat.append(df.format(ret) ).append(" ").append(strArr[idx]); //MB로 출력.
            
        }else {
            retFormat.append(" ").append(strArr[0]); //byte로 출력
           
        }
        System.out.print("메모리 사용량>>"+retFormat);

    }

}
