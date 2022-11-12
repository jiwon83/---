import java.io.*;
import java.util.*;
/*
골드5 두 용액 https://www.acmicpc.net/problem/2470
시도: X
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int [] arr; //담을 배열

    static void input(){
        FastReader sc = new FastReader();
        N = sc.nextInt();
        arr = new int[N];
        for (int i=0; i<N; i++){
            arr[i]= sc.nextInt();
        }
        sc.close();


    }
    static void pro() throws IOException{
        Arrays.sort(arr); //1.오름차순 정렬

        int L =0, R= arr.length-1;
        int a1=0,a2=0; //절댓값이 가장 작은 합의 두 특성값을 저장할 변수들
        int best_sum=Integer.MAX_VALUE; // 최소합

        while (L!=R){ //L<R로 해도 동일.

            int sum = arr[L] + arr[R];
            if (best_sum>Math.abs(sum)) {
                best_sum= Math.abs(sum);
                a1 = arr[L];
                a2 = arr[R];
            }
            if (sum<0) L++;
            else if (sum>0) R--;
            else{
                //만약 sum=0이라면
                a1 = arr[L];
                a2= arr[R];
                break;
            }
        }
        sb.append(a1).append(' ').append(a2);
        //출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();

//        bw.write(sb.toString());
//        bw.flush();//FileWriter 내부 버퍼의 내용을 파일에 writer합니다
//        bw.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.
//        br.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.

    }
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

        }
        String next(){
            while (st == null || !st.hasMoreTokens()){  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){return Long.parseLong(next()); }

        double nextDouble(){return Double.parseDouble(next());}

        String nextLine(){
            String str ="";
            try {
                str = br.readLine();

            }catch (IOException e){
                e.printStackTrace();
            }
            return str;
        }
        void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

