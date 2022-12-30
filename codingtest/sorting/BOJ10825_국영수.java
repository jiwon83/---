package sorting;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class BOJ10825_국영수 {
    static StringBuilder sb= new StringBuilder();
    static int N;
    static Elem [] elems;
    static class Elem implements Comparable<Elem> {
        int kor,eng,math;
        String name;

        public Elem() {}

        public Elem(int kor, int eng, int math, String name) {
            this.kor = kor;
            this.eng = eng;
            this.math = math;
            this.name = name;
        }

        @Override
        public int compareTo(Elem other) {
            //kor 내림차순, eng 오름, math  내림, name 오름
            if (kor != other.kor) return other.kor-kor;
            if (eng != other.eng) return eng - other.eng;
            if (math != other.math) return other.math - math;
            return name.compareTo(other.name);

        }
    }
    static void input() {

        FastReader sc = new FastReader();
        N = sc.nextInt();
        elems = new Elem[N];
        for (int i=0; i<N; i++){
            //Junkyu 50 60 100
            String name = sc.next();
            int kor = sc.nextInt();
            int eng = sc.nextInt();
            int math = sc.nextInt();
            elems[i] = new Elem(kor,eng,math,name);

        }
        sc.close();//스트림을 닫아 종료된 작업에 대한 메모리 확보.

    }
    static void pro() {
        //elems 정렬
        Arrays.sort(elems);
        //elems name만 sb에 inpu
        for (int i=0; i< elems.length; i++){
            sb.append(elems[i].name).append('\n');
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
