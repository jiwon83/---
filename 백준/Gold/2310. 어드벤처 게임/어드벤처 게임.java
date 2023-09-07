import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static class Info{
        char type;
        int quantity;
        public Info(char type, int quantity){
            this.type = type;
            this.quantity = quantity;
        }

        public String toString(){
            return " [type = "+ type + " , quan = "+ quantity ;
        }
    }


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
3
E 0 2 0
L 10 3 0
T 15 1 2 0
4
E 0 2 3 0
L 201 2 3 0
L 10 4 0
T 15 2 3 1 0
0
     */
    static ArrayList<Integer> [] graph;
    static int N;
    static Info [] nodesInfo;
    static boolean clear;

    static int [] maxQ; //index 번호의 노드를 통과할 때

    static void input() throws IOException{

        graph = new ArrayList[N+1];
        nodesInfo = new Info[N+1]; // index노드가 트롤, 빈칸, 리프레셔인지  &  양
        clear = false;
        maxQ = new int[N+1];
        Arrays.fill(maxQ, -1);

        for (int i = 1; i<= N; i++){
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            int quantity = Integer.parseInt(st.nextToken());
            nodesInfo[i] = new Info(type, quantity);

            while (true){
                int num = Integer.parseInt(st.nextToken());
                if (num == 0 ) break;
                graph[i].add(num);
            }
        }
//        for (int i = 1; i<= N; i++){
//            System.out.println(i + " " +nodesInfo[i]);
//
//        }
//        for (int i = 1; i<=N; i++){
//            System.out.println(graph[i]);
//        }

    }
    public static void main(String[] args) throws IOException {

        while (true){

            N = Integer.parseInt(br.readLine());
            if (N == 0) return;

            input();

            if (nodesInfo[1].type == 'T'){
                System.out.println("No");
                return;
            }

            maxQ[1] = nodesInfo[1].quantity;
            dfs(1, nodesInfo[1].quantity);

            System.out.println(clear? "Yes" : "No");

        }

    }
    static void dfs(int x, int energy){
        if (clear) return;
        if (x == N){
            clear = true;
            return;
        }
        for (int next : graph[x]){

            Info nextInfo = nodesInfo[next];
            switch (nextInfo.type){
                case 'E':
                    if (maxQ[next] >= energy) break;
                    maxQ[next] = energy;
                    dfs(next, energy);
                    break;
                case 'L':
                    int nextEnergy = Math.max(energy, nextInfo.quantity);
                    if (maxQ[next] >= nextEnergy) break;
                    maxQ[next] = nextEnergy;
                    dfs(next, nextEnergy);
                    break;
                case 'T':
                    if ( nextInfo.quantity > energy || maxQ[next] >= energy - nextInfo.quantity) break;
                    maxQ[next] = energy - nextInfo.quantity;
                    dfs(next, energy - nextInfo.quantity);
                    break;

            }
        }


    }

}
