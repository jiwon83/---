import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
class Main
{
    FastReader sc = new FastReader();
    static int ans ;
    static int [] a;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        a = new int[N];
        ans = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, K, N, 0, 0, false);
        System.out.println(ans);

    }
    static void dfs(int seq, int K, int N, int energy, int file_eg, boolean con){

        if(seq == N){
            file_eg += energy - K;
            ans = Math.max(ans, file_eg);
            return;
        }
        if (con){
            if(energy < K )
                dfs(seq+1, K, N, energy+a[seq], file_eg, con);
            else if(energy >= K){
                //먹어도 되고 안먹어도 됨.
                file_eg += energy - K;
                energy = 0;
                dfs(seq+1, K, N, energy+a[seq], file_eg, true);
                dfs(seq+1, K, N, energy, file_eg,false );
            }
            return;
        }
        //con = false
        dfs(seq+1, K, N, energy+a[seq], file_eg, true);
        dfs(seq+1, K, N, energy, file_eg, false);

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
