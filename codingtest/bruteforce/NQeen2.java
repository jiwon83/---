package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/*
골드 4 N-Queen https://www.acmicpc.net/problem/9663
시도: X (잘 안됨. 강의와 접근을 아예 다르게 했음 수정필요**, X(메모리 초과 값은 잘 나옴)
 */
public class NQeen2 {
    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int result,N;
//    static int [] Queens;
//    static ArrayList<Integer> Queens ;
    static ArrayList<Point> Queens ;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        //퀸 배열은 N*2만큼임.
        Queens= new ArrayList<>(N*2);

    }
    static boolean isPossible(int x, int y){

        boolean tf = true;
        //Queens 배열과 모두 비교 한다.
        for (int i=0; i<Queens.size(); i++){
            int vx = Queens.get(i).x;
            int vy = Queens.get(i).y;


            //같은 x에 있을 때
            if (x==vx) return false;
            //같은 y에 있을 때

            if (y==vy) return false;
            //같은 \에 있을 떄
            if (x-y == vx-vy) return false;
            //같은 /에 있을 때
            if(x+y == vx+vy) return false;
        }
        return tf;

    }
    static void recur(int k){
        if (k==N+1){
            result++;
        }else {
            for (int col=1; col<=N; col++){

                //퀸을 row마다 배정
                if (isPossible(k,col)){
                    //이 행 k와 열 col이 이전 queen 들과 비교했을 때 놓아도 된다면,
                    //Queens에 추가해주고
                    Queens.add(new Point(k,col));
                    recur(k+1);
                    //Queens에 값을 빼준다.
                    Queens.remove(k-1);

                }
            }
        }
    }
    static void pro() throws IOException{
        input();
        recur(1);
        System.out.println(result);
    }
    public static void main(String[] args) throws IOException{
        Runtime.getRuntime().gc();//메모리 정리

        pro();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print("메모리 사용량>>"+usedMemory + " bytes");

        String retFormat = "";

        String[] strArr = {"byte", "KB", "MB", "GB"};

        if(usedMemory != 0) {
            int idx = (int)Math.floor(Math.log(usedMemory)/ Math.log(1024));
            DecimalFormat df = new DecimalFormat("#,###.##");
            double ret = usedMemory / Math.pow(1024, Math.floor(idx));
            retFormat = df.format(ret) + " " + strArr[idx];
        }else {
            retFormat += " "+ strArr[0];
        }
        System.out.print("메모리 사용량>>"+retFormat);
    }
}
