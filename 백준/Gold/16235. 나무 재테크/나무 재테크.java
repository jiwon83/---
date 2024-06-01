import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 실수한 부분: queue에서 poll 해주어야 하는 부분 poll 안하고 반복분 돌기만 하는 것 주의 !!
 */
class Main {
    static class Tree{
        int x, y, age, isDead;

        public Tree(int x, int y, int age, int isDead){
            this.x = x;
            this.y = y;
            this.age = age;
            this.isDead = isDead;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int [][] extra ; // 지속적으로 업데이트되는 양분
    static int [][] map; //양분의 양 정보

    static Deque<Tree> deathTrees = new ArrayDeque<>();

    static Deque<Tree> willSpreadTrees = new ArrayDeque<>();

    static Deque<Tree> treesInfoMap = new ArrayDeque<>(); // 좌표 정보, 나무들의 나이

    static int N, M, K;

    static int [][] dirs = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0, -1}, {-1,-1}};


    public static void main(String[] args) throws IOException {

        init();
        for (int k = 1; k <=K; k++) sol();
        System.out.println(countOfTrees());

    }
    private static void init() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        extra = new int[N][N];
        map = new int[N][N];
        for (int i = 0; i <N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <N; j++){
                extra[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        // treesInfoMap
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            treesInfoMap.addLast(new Tree(x, y, age, 0));
        }
    }
    private static void sol(){
        spring();
        summer();
        fall();
        winter();
    }

    private static void summer() {
        while (!deathTrees.isEmpty()){
            Tree tree = deathTrees.pollFirst();
            int x = tree.x;
            int y = tree.y;
            map[x][y] += tree.age / 2;
        }
    }

    private static void fall() {
        int qSize = willSpreadTrees.size();
        for (int s = 0; s < qSize; s++){
            Tree tree = willSpreadTrees.pollFirst();
            // 8 방향에 age 1인 나무 생성
            for (int d = 0; d < 8; d++){
                int nx = tree.x + dirs[d][0];
                int ny = tree.y + dirs[d][1];
                if (!inArea(nx, ny)) continue;
                treesInfoMap.addFirst(new Tree(nx, ny, 1, 0));
            }
        }
    }

    private static void winter(){
        for (int i = 0; i <N; i++){
            for(int j = 0; j< N; j++){
                map[i][j] += extra[i][j];
            }
        }
    }

    private static void spring() {

        int qSize = treesInfoMap.size();
        for (int i = 0; i < qSize; i++){
            //어린 나무부터 양분을 먹는다.  -> 항상 큐에 어린나이 나무를 앞으로 넣어주면 됨., 항상 만약 같은 x,y좌표라면 어린나이가 앞에 존재함을 보장
            Tree tree = treesInfoMap.pollFirst();
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;

            int food = map[x][y];
            if (food >= age){
                map[x][y] -= age;
                treesInfoMap.addLast(new Tree(x, y, age+1, 0));
                if ((age+1) % 5 == 0){
                    willSpreadTrees.addLast(new Tree(x, y, age+1, 0));
                }
            }else{
                deathTrees.addLast(new Tree(x, y, age, 1));
            }
        }
    }

    private static int countOfTrees(){
        return treesInfoMap.size();
    }

    private static boolean inArea(int x , int y){
        return x >= 0 && x < N && y >=0 && y < N;
    }


}