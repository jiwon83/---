import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, score, P;
    static List<Integer> list = new ArrayList<>(); // N개의 점수들 내림차순
    /*
3 90 10
100 90 80

5 75 4
90 80 70 70 60
// 3

5 60 4
90 80 70 70 60
// -1

5 60 5
90 80 70 70 60
//-1
5 60 6
90 80 70 70 60
//5

5 100 6
90 80 70 70 60
//1

5 90 6
90 80 70 70 60

5 80 6
90 80 70 70 60

5 81 6
90 80 70 70 60

//-1

     */

    static boolean flag = true;

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        score = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        if(N == 0 ){
            flag = false;
            return;
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            int num = Integer.parseInt(st.nextToken());
            list.add(num);
        }

    }
    public static void main(String[] args) throws IOException {
        input();


        if (!flag){
//            System.out.println(" N == 0 ");
            System.out.println(1);
            return;
        }

        int insertIdx = -1;
        while( insertIdx + 1 < list.size() && list.get(insertIdx+1) > score){
            insertIdx++;
        }
        insertIdx+=1;
//        System.out.println("score가 들어가는 첫번째 위치(순위) "+ insertIdx);

        if (insertIdx >= P){
//            System.out.println("첫번째 위치가 P의 용량을 넘어선다면");
            System.out.println(-1);
            return;
        }
        /**
         * p의 용량을 넘어서지 않는다면, if score ==4
         * case 1 : 첫번째 위치가 배열 밖에 있는 경우 [1234:]5 or [1234:4]4
         * case 2 : 첫번 째 위치가 배열 안에 있는 경우 [1234:4455] or [1234:56]
         * 조건 -> score는 이전 숫자보다 높을 때만 순서가 바뀔 수 있다 => 같으면 맨뒤에
         * [12344]4 or [12344455] 처럼 숫자가 첫번째 위치가 중복되어 범위를 벗어난다면? -1
         * 배열의 길이 < P이면서 시작점이 P의 범위를 벗어나지 않은 경우는 [123]4(불가능한 경우) [1234]5(가능한 경우) [1234]4
         * 배열의 길이 > P이면서 시작점이 P의 범위를 벗어나지 않은 경우는 [12345:6](가능한 경우) [1234:56][12344:456](불가능한 경우)
         * 따라서 MIN(배열의 길이, P)와 score가 똑같으면 안된다.
         *
         * 시작 지점이 P범위 내 인 경우
         * if( p > N)
         *  [1,2,4]_ (score == 인덱스 N-1 값 OK) [1,4,4]_ (score OK) [1,2,3]_ (OK)
         * if( p <= N)
         * [1,2,4,_,4] => p == score 이면 안됨. [1,2,2,2] [1,2,_], [1,2,4,_]
         */
        //
        //
//        int deadIdx = Math.max(P-1, list.size()-1); //[5432]1 => ok [5432]2 => No
        if (P <= N && list.get(P-1) == score){ //만약 sc  = 2, 122222 의 경우라면
//            System.out.println(" 만약 2222 처럼 범위의 마지막 칸까지 같은 숫자가 이어지는 경우");
            System.out.println(-1);
            return;
        }
        System.out.println((insertIdx+1));

        /*
        int insertIdx = -1;

        while (insertIdx + 1 < list.size() && list.get(insertIdx + 1) >= score){ //insertIdx => score > insertIdx+1
            insertIdx++;

        }

        insertIdx += 1;

        if (insertIdx < P){
            //등수를 확인
            int grade = insertIdx;

            while (grade -1 >= 0 && score == list.get(grade-1)){
                grade--;
            }
            System.out.println((grade+1));

        }else{
            System.out.println(-1);
        }
        */


    }

}
