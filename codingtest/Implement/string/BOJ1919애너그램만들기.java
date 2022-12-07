package implement.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ1919애너그램만들기 {

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static HashMap<String,Integer> mapA = new HashMap<>();
    static HashMap<String,Integer> mapB = new HashMap<>();
    static int interCount;
    static String A, B;


    static void input() {
        A = sc.nextLine();
        B = sc.nextLine();
    }
    static void pro() {
        // Hash map 넣기
        for (int i=0; i<A.length(); i++){

            String key =  A.charAt(i)+"";

            if ( mapA.containsKey( key ) ){
                int val = mapA.get(key);
                mapA.put(key, val+1);
            }else{
                mapA.put( A.charAt(i)+"", 1 );
            }
        }
        for (int i=0; i<B.length(); i++){

            String key =  B.charAt(i)+"";

            if ( mapB.containsKey( key ) ){
                int val = mapB.get(key);
                mapB.put(key, val+1);
            }else{
                mapB.put( B.charAt(i)+"", 1 );
            }
        }
//        System.out.println(Collections.unmodifiableMap(mapA));
//        System.out.println(Collections.unmodifiableMap(mapB));
        Iterator<String> keysA = mapA.keySet().iterator();
        Iterator<String> keysB = mapB.keySet().iterator();
        while(keysA.hasNext()){
            String keyA = keysA.next();
            int valueA = mapA.get(keyA);
            if (mapB.containsKey(keyA)){    //만약, b에도 같은 키가 있다면
                interCount += Math.min( valueA, mapB.get(keyA));
            }
            
        }
//        System.out.println("A 교 B = "+ interCount);
        System.out.println(A.length() + B.length() - 2 * interCount);



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
