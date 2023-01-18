import java.awt.*;
import java.io.*;
import java.net.CookieHandler;
import java.util.*;

public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static ArrayList<String> list = new ArrayList<>();
    static ArrayList<String> result = new ArrayList<>();

    public static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++){
            String s = br.readLine();
            list.add(s);
        }
        Collections.sort(list);
        for (int i=0; i<M; i++){
            //not see에도 포함되어있다면 그대로 냅두고 아니면 삭제.
            String s = br.readLine();
            if (isContain(s)) result.add(s);
        }
        Collections.sort(result);
        sb.append(result.size()).append("\n");
        Iterator<String> iter = result.iterator();
        while (iter.hasNext()){
            sb.append(iter.next()).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isContain(String s) {
        int L =0, R=list.size()-1;
        while (L <= R){
            int mid = (L + R) / 2;
            if (list.get(mid).compareTo(s)==0){
//                System.out.println("find ! "+ s);
                return true;
            }else if (list.get(mid).compareTo(s) > 0){
                R = mid - 1;

            }else {
                L = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        input();

    }
}
