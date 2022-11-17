package bruteforce;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 실버1 https://www.acmicpc.net/problem/14888
 시도: X O
 */
public class BOJ14888 {
    static int [] nums, selected; //nums는 주어진 숫자만을 담을 배열이며 selected는 선택된 연산자들의 정보를 담을 배열이다.
    static int [] operators = new int[5]; //operator의 갯수를 input()으로 담을 배열이며 각각의 index가 각각 연산자 +-*/를 의미.

    static int max, min, N;

    static StringBuilder sb = new StringBuilder();
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N+1];
        selected = new int[N]; //1~N-1까지 사용. 문제에서 연산자는 N-1개를 사용한다고 명시되어 있다.
        operators= new int[5]; //1~4인덱스까지 사용.

        //nums data insert
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++){

            nums[i] = Integer.parseInt(st.nextToken());
        }
        //insert operators data
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=4; i++){
            operators[i]= Integer.parseInt(st.nextToken());
        }
        br.close();

    }
    static int cal(int pre, int operator, int value){ //이전까지의 값. 연산자 index, 계산할 value
        int num = pre;
        //operators 1= +, 2= - 3=* 4= //
        switch (operator){
            case 1:
                num = pre + value;
                break;
            case 2:
                num = pre - value;
                break;
            case 3:
                num = pre * value;
                break;
            case 4:
                num = pre / value;
                break;
        }

        return num;
    }
    static void recur(int k) {
        if (k==N){ //모든 자릿수가 다 채워졌다면, 즉 하나의 수식이 완성됐다면,
            //전체를 계산
            int value=nums[1]; //value 초기화: nums의 가장 첫번째 값을 넣는다.
            for (int i=1; i<=N-1; i++){
                value =  cal(value, selected[i], nums[i+1] ); //value 갱신
            }
            min= Math.min(min, value);
            max = Math.max(max, value);

        }else {
            //한 자리씩 모든 후보자인 operators를 탐색한다.
            for (int cand=1; cand<=4; cand++){
                if (operators[cand] >= 1){ //연산자를 사용할 수 있는 지 확인
                    operators[cand]--; //사용표시
                    selected[k]= cand; //선택된 연산자를 selected 배열에 갱신
                    recur(k+1); //재귀함수를 통해 다음 순서의 연산자 탐색.
                    operators[cand] += 1;// 한 차례의 탐색이 끝나면 +1을 통해 사용한 연산자 복구. 
//                    selected[cand] =0;
                }
            }
        }
    }
    static void pro() throws IOException{
        input();
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        recur(1); // 1번째 자리부터 탐색
        sb.append(max).append('\n').append(min);
        System.out.println(sb);

    }
    public static void main(String[] args) throws IOException{
        pro();

    }

}
