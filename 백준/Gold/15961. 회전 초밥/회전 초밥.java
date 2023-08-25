import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Integer> linkedList = new ArrayList<>();
    static int [] visit;
    static int K, C, D, N;
    static int maxTypeCnt = 0;
    static int typeCnt=0;

    public static void main(String[] args) throws IOException{


        //입력
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        D = toInt(st.nextToken());
        K = toInt(st.nextToken());
        C = toInt(st.nextToken()); //쿠폰번호
        visit = new int[D+1]; // 먹은 초밥 수를 저장할 배열

        for (int i=0 ;i < N; i++){
            linkedList.add(toInt(br.readLine()));
        }

        int L = 0, R = 0;
        add(linkedList.get(R));
        for (int st = 0 ; st < N; st++){
            while( getLen(L, R, N) < K){
                R = ( R + 1 ) % N;
                add(linkedList.get(R));
            }
            add(C);
//            System.out.println(" L = "+L+" R = "+ R+" typeCnt = "+ typeCnt);
//            System.out.println(Arrays.toString(visit));
            maxTypeCnt = Math.max(typeCnt, maxTypeCnt);
            remove(C);
            remove(linkedList.get(L));
            L = ( L + 1 ) % N;

        }
        System.out.println(maxTypeCnt);
    }
    static void add(int selectType){
        if(visit[selectType] == 0){
            visit[selectType]++;
            typeCnt++;
        }else{
            visit[selectType]++;
        }
    }
    static void remove(int selectType){
        visit[selectType]--;
        if(visit[selectType] == 0){
            typeCnt--;
        }
    }
    static int toInt(String s){
        return Integer.parseInt(s);
    }
    static int getLen(int L, int R, int N){
        if( L <= R){
            return R - L + 1;
        }
        int endIdx = N - 1;
        return ( R + 1 ) + (endIdx - L + 1); //앞 부분 + 뒷부분
    }
}
