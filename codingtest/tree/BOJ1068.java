package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
한 번 더 풀어볼 것  11/24 재귀로풀었는데 dp로 풀면 더 효율적
골드5 트리 : https://www.acmicpc.net/problem/1068
시도 : X X
풀이 : dp, bfs 이용 ** root노드를 삭제할 시 예외처리 해줘야함.
 */
public class BOJ1068 {
    static int N, D, root;
    static int[] parents;
    static ArrayList<Integer> [] graph_childs;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static void input() throws IOException {

        N = Integer.parseInt(br.readLine());
        parents = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            parents[i]= Integer.parseInt(st.nextToken());
            if (parents[i] == -1){
                root=i;
            }
        }
        D = Integer.parseInt(br.readLine());
        //graph_childs 초기화
        graph_childs = new ArrayList[N];
        for (int i=0; i<N; i++){
            graph_childs[i]= new ArrayList<>();
        }

    }
    static void deleteNode(int node){

        // -1로 처리해서 childs 그래프로 바꿀 때 부모가 없다고 처리해서 연결을 끊음.
        parents[node]=-1;
    }

    //parents그래프를 graph_childs로 변환해주는 함수
    static void parentsToChilds(){
        //parents[]에서 -1이면 패스 그렇지않으면 graph의 value 인덱스에 값을 i로 저장.
        for (int i=0; i<parents.length; i++){

            if (parents[i]==-1) continue;
            graph_childs[ parents[i] ].add(i);

        }
//        System.out.println(Arrays.toString(graph_childs));//ok
    }

    static int dfs(int node){
        if (graph_childs[node].size() ==0){
            return 1;
        }
        int cntLeaf=0;
        for (int i=0; i<graph_childs[node].size(); i++){
            cntLeaf += dfs(graph_childs[node].get(i));
        }
        return cntLeaf;

    }

    static void pro() throws IOException{
        input();
        deleteNode(D);
        parentsToChilds();
        if (D==root){
            System.out.println(0);
        }else{
            System.out.println(dfs(root));
        }


    }
    public static void main(String[] args) throws IOException{

        pro();
        br.close();
    }
}
