
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<PriorityQueue<Integer>> sellers;
    static HashMap<String,Integer> addMap;

    public static void main(String[] args) throws IOException {

        sellers = new ArrayList<>();
        addMap = new HashMap<>();
        long sum=0;
        //input
        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            String name = st.nextToken();
            int n = Integer.parseInt(st.nextToken());
            if(type == '1'){

                if (addMap.containsKey(name)){
                    for (int i = 0; i < n; i++) {
                        int info = Integer.parseInt(st.nextToken());
                        sellers.get(addMap.get(name)).add(info);
                    }

                }else{
                    addMap.put(name, sellers.size());
                    sellers.add(new PriorityQueue<>(Collections.reverseOrder()));
                    for (int i = 0; i < n; i++) {
                        int info = Integer.parseInt(st.nextToken());
                        sellers.get(addMap.get(name)).add(info);
                    }

                }
            }else if(type == '2'){
                if (!addMap.containsKey(name)) continue;
                int endIdx = Math.min(n, sellers.get(addMap.get(name)).size());
                for (int i= 0; i<endIdx; i++){
                    int num = sellers.get(addMap.get(name)).poll();
//                    System.out.println(" add > "+ num);
                    sum += num;
                }
            }
        }
        System.out.println(sum);


    }


}
