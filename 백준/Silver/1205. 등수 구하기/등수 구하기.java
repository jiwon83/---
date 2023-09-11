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
            System.out.println(1);
            return;
        }

        int insertIdx = -1;

        while (insertIdx + 1 < list.size() && list.get(insertIdx + 1) >= score){ //insertIdx => score > insertIdx+1

            insertIdx++;
//            System.out.println("inserIdx > "+insertIdx);
        }

//        System.out.println("list.size() -1 까지 확인 , insertIdx = "+ insertIdx);
//        if (insertIdx == list.size()-1 && list.get(list.size()-1) >= score) insertIdx++;

        insertIdx += 1;
//        System.out.println("insertIdx = "+ insertIdx);

        if (insertIdx < P){
            //등수를 확인
            int grade = insertIdx;

            while (grade -1 >= 0 && score == list.get(grade-1)){
                grade--;
            }
            System.out.println((grade+1));
//            System.out.println("등수 : "+ (grade+1));

        }else{
            System.out.println(-1);
        }


//        int insertion = Collections.binarySearch(list, score);
//        System.out.println("score = "+ score + " "+ insertion);

    }

}
