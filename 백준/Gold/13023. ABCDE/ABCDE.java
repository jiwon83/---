import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] graph;
    static int [] childs;
    static ArrayList<Integer> roots;
    static boolean [] visit;
    static int N, M;
    static boolean flag=false;
    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        //1. 그래프를 그린다.
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        childs = new int[N];
        visit = new boolean[N];
        graph = new ArrayList[N];
        roots = new ArrayList<>();

        for (int i=0; i<N; i++) graph[i] = new ArrayList<>();

        //2. root들로 탐색한다.
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
            childs[from]++;
            childs[to]++;
        }
//        System.out.println("childs "+ Arrays.toString(childs));
        for (int i=0; i<N; i++){
            if (childs[i]==1) roots.add(i);
        }
//        System.out.println("roots "+ Collections.unmodifiableList(roots));

//        for (int r : roots){
//            visit[r]=true;
//            dfs(1,r, r+" ");
//        }

        for (int r=0; r < N; r++){
            if (visit[r]) continue;
            visit[r]=true;
            dfs(1,r);
            visit[r]=false;
        }


        if (flag) System.out.println(1);
        else System.out.println(0);

    }
    /*
    2000
    1999
    1998 => x

     */
    static void dfs(int depth, int x){
//        System.out.println("x = "+ x+ ", depth = "+depth+" : "+course);
        if (flag) return;
        if (depth == 5){
//            System.out.println(course);
            flag = true;
        } else {
            for (int y : graph[x]){
                if(visit[y]) continue;
                visit[y] = true;
                dfs(depth+1, y);
                visit[y] = false;

            }
        }

    }
}
