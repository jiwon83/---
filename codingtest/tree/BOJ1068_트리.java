
import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, root, remove;
    static int [] A;
    static ArrayList<Integer> [] graph;
    
    static void input() {
        N = sc.nextInt();
        graph = new ArrayList[N];
        A = new int[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            if (A[i]==-1) root = i;
        }
        remove = sc.nextInt();
        A[remove] = -1; //romove의 부모 삭제

        //그래프 작성
        for(int i=0; i<N; i++){
            if (A[i]==-1) continue;
            int child = i;
            int parent = A[i];
            graph[parent].add(child);
        }
    }
    static int recur(int x){
        boolean cango=false;
        int result =0;
        for (int y : graph[x]){
            result += recur(y);
            cango = true;
        }
        if(!cango) return 1;
        return result;
    }
    static void pro() {
        if(root==remove) System.out.println(0);
        else{
            System.out.println(recur(root));
        }

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

        String next() {
            while (st == null || !st.hasMoreTokens()) {  //현재 남아 있는 토큰이 없다면 새로 받아온다.
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

        void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
