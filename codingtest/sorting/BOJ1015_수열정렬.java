package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BOJ1015_수열정렬 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int [] A,select;
    static int N;

    static void input() {
        N = sc.nextInt();
        A = new int[N];
        select = new int[N];
        for (int i=0; i<N; i++){
            A[i] = sc.nextInt();
        }
    }
    static void pro() {
        //select를 채운다. select[i] = A[i]의 값과 같은 B의 값의 index이다.

        //0. 정렬된 B를 생성
        List<Integer> B = Arrays.stream(A).boxed().collect(Collectors.toList());
        Collections.sort(B);

        //1. 0~n-1 까지 A를 기준으로 순회한다.
        for (int i=0; i<N; i++){
            //2. A[i]의 값을 B에서 찾는다.
            //3. B에서 찾은 값의 index를 select[i]에 저장한다.
            select[i] = B.indexOf(A[i]); //indexOf는 가장 앞의 인덱스를 반환한다.
            B.set( select[i], -1); //중복인 경우를 위해서 찾은 것은 -1로 바꿔준다.
            sb.append(select[i]).append(" ");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) {
        input();
        pro();
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

