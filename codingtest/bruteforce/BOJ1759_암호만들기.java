package bruteforce;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1759_암호만들기 {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static String[] selected, alpha, least;
    static ArrayList<Integer> vowels, consnants; //이들의 인덱스를 저장
    static boolean[] used;

    static void input() {
        FastReader scan = new FastReader();
        M = scan.nextInt();
        N = scan.nextInt();

        selected = new String[M + 1];
        alpha = new String[N + 1];
        used = new boolean[N + 1];
        vowels = new ArrayList<>();
        consnants = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            alpha[i] = scan.next();
        }
        Arrays.sort(alpha, 1, N + 1); //정렬
        // 이걸 자음 모음으로 나눈다.
        for (int i = 1; i <= N; i++) {
            if (isVowel(alpha[i])) {
                vowels.add(i);
            } else {
                consnants.add(i);
            }
        }
        System.out.println(Collections.unmodifiableList(vowels));
        System.out.println(Collections.unmodifiableList(consnants));

    }

    static public boolean isVowel(String s) {
        boolean tf = false;
        if (s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u")) {
            tf = true;
        }
        return tf;
    }


    static void recur(int k, int beginIdx) {
        if (k == 4) { // 1 ~ 3 번째를 전부 다 골랐다!
//            System.out.println(Arrays.toString(selected));
            leftover(4);
        } else if ( k == 1 ){ //모음을 고른다.
            for (int i=0; i<vowels.size();i++){
                selected[k] = alpha[vowels.get(i)];//a
                used[vowels.get(i)]=true;
                recur(k+1,0); //항상 1이다. ArrayList이기 때문에 0이다.
                used[consnants.get(i)] = false;
            }
        } else if (k>1 && k<4) {
            //자음을 고른다.
            for (int i= beginIdx; i<consnants.size(); i++ ){
                selected[k] = alpha[consnants.get(i)]; //c,s,t,w
                used[consnants.get(i)] = true;
                recur(k+1, i+1);
                used[consnants.get(i)] = false;
            }

        }
    }
    static void leftover(int k) {
        if (k == M+1) { // 1 ~ 3 번째를 전부 다 골랐다!
            System.out.println(Arrays.toString(selected));
            String [] temp =new String[M];
            for (int i = 1; i <= M; i++) temp[i-1]=selected[i]; //sb.append(selected[i]);
            Arrays.sort(temp);
            for (int i=0; i<M; i++) sb.append(temp[i]);
            sb.append('\n');

        } else { //전체에서 방문하지 않은 것들을 또 조합한다.
            for (int i=1; i<=N; i++){
                if (!used[i]){
                    selected[k] = alpha[i];
                    used[i] = true;
                    leftover(k+1);
                    used[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        input();
        recur(1,1);
        System.out.println(sb);
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
