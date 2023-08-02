import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int ans ;
    static int [][] arr;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        
        N = Integer.parseInt(br.readLine());
        ans = Integer.MAX_VALUE;
        arr = new int[N+1][2];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int sour = Integer.parseInt(st.nextToken());
            int bitter = Integer.parseInt(st.nextToken());
            arr[i][0] = sour;
            arr[i][1] = bitter;

        }
        recur(0, 1, 0); 

        System.out.println(ans);

    }

    /**
     * k번째에서 재료를 선택하거나 or 안하거나
     * @param k = k번째 재료
     * @param sour
     * @param bitter
     */
    static void recur(int k, int sour, int bitter ) {
        
        // 재료는 1개 이상 사용해야만 한다. 
        if(sour != 0 && bitter != 0) ans = Math.min(ans, Math.abs( sour - bitter));

        if(k == N + 1) { //모든 재료를 돌았다면 : 종료 조건
            return;
        }
        //선택 하거나
        recur(k+1, sour * arr[k][0], bitter + arr[k][1]);

        //말거나
        recur(k+1, sour, bitter);


    }
}
