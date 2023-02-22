import java.util.*;
import java.io.*;

public class Main3 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int k;

    static void pro() {
        int k = sc.nextInt();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        while (k-- >0){
            String [] command = sc.nextLine().split(" ");
            int key = Integer.parseInt(command[1]);
            if (command[0].charAt(0)=='I'){
                treeMap.put( key , treeMap.getOrDefault(key,0)+1 );

            }else{ //D
                if (!treeMap.isEmpty()){
                    if (key == -1){ //lowest
                        int num = treeMap.firstKey();
                        if (treeMap.get(num)>1){
                            treeMap.put( num , treeMap.get(num)-1 );
                        }else{
                            treeMap.remove(treeMap.firstKey());
                        }

                    }else{
                        int num = treeMap.lastKey();
                        if (treeMap.get(num)>1){
                            treeMap.put( num , treeMap.get(num)-1 );
                        }else{
                            treeMap.remove(treeMap.lastKey());
                        }
                    }
                }

            }

//            System.out.println(Collections.unmodifiableSortedMap(treeMap));
        }
        if (treeMap.isEmpty())sb.append("EMPTY");
        else sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey());
        sb.append("\n");


    }
    public static void main(String[] args) {
        int T = sc.nextInt();
        while (T-- >0){
            pro();
        }
        System.out.println(sb);

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
