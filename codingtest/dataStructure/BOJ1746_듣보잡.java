import java.awt.*;
import java.io.*;
import java.util.*;

public class Main2 {
    static StringBuilder sb = new StringBuilder();
    static int N, M;

    public static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<String> set = new HashSet<>();

        ArrayList<String> list = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++){
            String s = br.readLine();
            set.add(s);
        }
        for (int i=0; i<M; i++){
            //not see에도 포함되어있다면 그대로 냅두고 아니면 삭제.
            String s = br.readLine();
            if (set.contains(s)) list.add(s);
        }
        Collections.sort(list);
        sb.append(list.size()).append("\n");

        Iterator<String> iter = list.iterator();
        while (iter.hasNext()){
            sb.append(iter.next()).append("\n");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException {
        input();

    }
}
