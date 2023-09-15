import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
/*
5
55 185
58 183
88 186
60 175
46 155
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int ans;
    static int P, K; // N = 빌딩 층 수, K = 자리 수 , P = 반전 횟수, X = 원래 수
    static String X, N;

    static int[][] numbers = new int[10][7]; //각 숫자를 7개의 1차원 배열로 표현 1 : 켜짐 0 꺼짐

    static void initNumbers(){
        numbers[0] = new int[] {1,1,1,1,1,1,0};
        numbers[1] = new int[] {0,1,1,0,0,0,0};
        numbers[2] = new int[] {1,1,0,1,1,0,1}; //0 1 3 4 6
        numbers[3] = new int[] {1,1,1,1,0,0,1};
        numbers[4] = new int[] {0,1,1,0,0,1,1};//1 2 5 6
        numbers[5] = new int[] {1,0,1,1,0,1,1}; // 0 2 3 5 6
        numbers[6] = new int[] {1,0,1,1,1,1,1}; //0 2 3 4 5 6
        numbers[7] = new int[] {1,1,1,0,0,0,0};
        numbers[8] = new int[] {1,1,1,1,1,1,1};
        numbers[9] = new int[] {1,1,1,1,0,1,1};


    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N= st.nextToken();
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = st.nextToken();

        while (X.length() < K){
            X = "0"+X;
        }
        while (N.length() < K){
            N = "0"+N;
        }
//        System.out.println("X = "+ X);

        ans = 0;
        initNumbers();
        recur(0, P, "", -1);
        // dfs로 순회하면서 k자릿수에 해당하는 X의 숫자를 반전시켜서 다른 숫자로 만든다.
        // 단, 남은 p 횟수가 반전에 필요한 수보다 크거나 같아야 한다.
        // 만약, K 자릿수 만큼 다 만들었다면 경우의 수 count up!
        System.out.println(ans);//아무것도 바꾸지 않은 경우를 생각해서 -1

    }
    static void recur(int k, int p, String memo, int pre){
        if( k == K){

            if (p == P) return;
            if(Integer.parseInt(memo) == 0) return;
//            System.out.println("완성 "+ memo);
            ans++;

            return;
        }
        int xNum = X.charAt(k)-'0';
        int limitNum = N.charAt(k)-'0';
//        System.out.println(k+"번째의 xNum: "+xNum);
        for(int i = 0; i <= 9; i++ ){

            if( (k == 0 || preAllSame(memo, k)) && limitNum < i) continue; //범위에 벗어난다.
//            if(limitNum < i) continue; //범위에 벗어난다.
            int needCount = getNeedCount(i, xNum);
            if(needCount <= p){
//                System.out.println(k+"번째의 xNum: "+xNum+" "+needCount+ "개를 반전시켜 만들었다... "+ i);
                recur(k+1, p - needCount, memo+""+i, i);
            }
        }

    }

    static boolean preAllSame(String made, int k){
        //한계점인 N과 비교한다.
//        System.out.println("limit = "+N+" made ="+made + " k = "+ k);
        //앞자리가
        for (int i = 0; i < k ; i++){
            if(made.charAt(i) != N.charAt(i)) return false;
        }
        return true;
    }
    static int getNeedCount(int target, int num){
        //i를 target으로 만드는데 필요한 반전 갯수 == 다른 갯수
        int cnt = 0;
//        System.out.println("getNeedCount "+ num+" > "+ target);
//        System.out.println("target = "+Arrays.toString(numbers[target]));
//        System.out.println("   num = "+Arrays.toString(numbers[num]));

        for (int i = 0; i < 7; i++){
            if(numbers[target][i] != numbers[num][i]){
                cnt++;
            }
        }
//        System.out.println("필요한 반전 수 "+ cnt);
        return cnt;
    }


}
