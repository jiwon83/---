import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static HashMap<String, Integer> hm = new HashMap<>();

    static class File implements Comparable<File> {
        String exe;
        int count;
        public File(String exe, int count){
            this.exe = exe;
            this.count = count;

        }
        @Override
        public int compareTo(File other){
            return this.exe.compareTo(other.exe);
        }


    }
    static void input() {
        N= sc.nextInt();
        for (int i=0; i<N; i++){
            String input = sc.nextLine().split("\\.")[1];
            hm.put(input, hm.getOrDefault(input, 0)+1);
        }

        //file로 변환
        List<File> files = new ArrayList<File>();
        for (Map.Entry<String, Integer> entry : hm.entrySet()){
            files.add(new File(entry.getKey(), entry.getValue()));
        }
        Collections.sort(files);

        for (File f : files){
            sb.append(f.exe).append(" ").append(f.count).append("\n");
        }
        System.out.println(sb);
    }
    static void pro() {

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