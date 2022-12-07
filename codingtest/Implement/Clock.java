package implement;

import java.util.Scanner;

public class Clock {
    static int count = 0;
    static int N;

    //숫자의 자릿수를 확인하는 함수.
    int sizeNum(int num) {
        int result = 0;
        while (num % 10 != num) { //10단위에서 멈춤.
            num = num / 10;
            result++;
        }

        return result + 1;

    }


    //3이 숫자에 존재하는 지 확인하는 함수
    static boolean is3(int num) {
        return num / 10 == 3 || num % 10 == 3;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i=0; i<=N; i++){
            for (int m=0; m<=59; m++){
                for (int s =0; s<=59; s++){
                    if (is3(i) || is3(m) || is3(s)){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
