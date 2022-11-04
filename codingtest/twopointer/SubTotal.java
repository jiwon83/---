import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
백준 부분합 https://www.acmicpc.net/problem/1806
시도: X
 */
public class SubTotal {

    public static int ans, left, N, S, sum;
    public static int count;
    public static int[] arr;

//    public static void recursion(int){
//
//    }


    public static void pros(){
        left =0;
        while (left<arr.length){ //왼쪽 포인터를 배열의 끝까지
//            System.out.println((pos-1)+"번째: sum="+sum+" ,ans="+ans);
            sum =0;
            count=0;

            for (int i = left; i<arr.length; i++){ //오른쪽 포인터
                if(sum>=S){
                    System.out.println("count"+count);
                    ans = Math.max(count,ans);
//                    System.out.println("sum="+sum + " pos"+pos);

                    left = i-1;
                    break;
                }
                sum += arr[i];
                count++;
                System.out.println(i);


            }

        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];// arr 초기화

        //배열 받기
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i]= Integer.parseInt(st.nextToken());
        }

    }
    public static void main(String[] args) throws IOException {
        input();
//        System.out.println(Arrays.toString(arr));
        pros();
        System.out.println(ans);
    }
}
