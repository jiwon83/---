import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main2 {
    static class Shark{
        int id, x, y, dir;
        int [][] priority = new int[5][5];

        public int getPriorDir(List<Integer> dirList){
            for (int i=1; i<=4; i++){ //우선순위 순서대로
                if( dirList.contains(priority[dir][i]) ) {
                    return priority[dir][i];
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "id=" + id+
                    ", x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    ", priority=" + Arrays.deepToString(priority) +
                    '}';
        }

        public Shark(){}
    }
    static int N, M, k;
    static int [][] map;
    static int [][] leftSmell;
    static int [][] smellOwner;
    static int [][] move ={{0,0}, {-1,0}, {1,0},{0,-1},{0,1}};
    static Map<Integer, Shark> sharks = new HashMap<>(); //index 0부터 시작

    public static void input() throws IOException{
        /*
        5 4 4
0 0 0 0 3
0 2 0 0 0
1 0 0 0 4
0 0 0 0 0
0 0 0 0 0
4 4 3 1
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); k =  Integer.parseInt(st.nextToken());
        map = new int[N][N];
        smellOwner = new int[N][N];
        leftSmell = new int[N][N];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0){
                    Shark shark = new Shark();
                    shark.id = map[i][j];
                    shark.x = i; shark.y = j;
                    sharks.put(shark.id, shark);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++){
            Shark s = sharks.get(i);
            s.dir = Integer.parseInt(st.nextToken());
        }
        //모든 상어들에 대한 우선순위
        for (int si = 1; si<=M; si++){
            Shark s = sharks.get(si);
            for(int i=1; i<=4; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=4; j++){
                    s.priority[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        spreadSmells();
//        System.out.println("---------map----");
//        for(int i=0; i<N; i++){
//            System.out.println(Arrays.toString(map[i]));
//        }
//        System.out.println("---------smellOwner----");
//        for(int i=0; i<N; i++){
//            System.out.println(Arrays.toString(smellOwner[i]));
//        }
//        System.out.println("---------leftSmell----");
//        for(int i=0; i<N; i++){
//            System.out.println(Arrays.toString(leftSmell[i]));
//        }
//        System.out.println("---------sharks----");
//        System.out.println(Collections.unmodifiableMap(sharks));

    }

    public static int solution(){
        int time=0;
        while (time++ < 1000){

            List<Integer> willRemoveShark = new ArrayList<>();

            for (Map.Entry<Integer,Shark> entry: sharks.entrySet()){
                Shark s = entry.getValue();
                //1. 이동 방향 결정 ( 빈칸과 자신의 냄새 칸 List를 구하고, 상어 자신에 맞게 결정)
                int nextDir;
                List<Integer> emptyDir = new ArrayList<>();
                List<Integer> mySmellDir = new ArrayList<>();
                //자신의 위치에서 상하좌우를 돌며 갱신
                for (int i=1; i<=4; i++){
                    int nx = s.x + move[i][0]; int ny = s.y + move[i][1];
                    if(nx < 0 || ny < 0 || nx >=N || ny >= N) continue;
                    if( smellOwner[nx][ny] == 0) emptyDir.add(i);
                    if ( smellOwner[nx][ny] == s.id ) mySmellDir.add(i);
                }
                if (!emptyDir.isEmpty()) nextDir = s.getPriorDir(emptyDir);
                else nextDir = s.getPriorDir(mySmellDir);

                if (nextDir==-1){
//                    System.out.println("nextdir을 못찾음>> ");
//                    System.out.println("time>> "+time);
//                    System.out.println("상어 id "+ s.id+" x: "+ s.x +" y: "+s.y);
//                    for(int i=0; i<N; i++){
//                        System.out.println(Arrays.toString(map[i]));
//                    }
//
//                    System.out.println(Collections.unmodifiableList(emptyDir));
//                    System.out.println(Collections.unmodifiableList(mySmellDir));
                }

                //2. 이동 (원래 위치를 0으로, 이동가능하다면 새로운 위치에 자신의 번호를 기입, 지울 상어를 갱신)
                map[s.x][s.y] = 0;
                s.dir = nextDir;
                int nextX = s.x + move[nextDir][0]; int nextY = s.y + move[nextDir][1];
                if( map[nextX][nextY] == 0 || map[nextX][nextY] > s.id ){ //자리 차지 가능!
                    //이동.
                    s.x = nextX; s.y= nextY;
                    map[s.x][s.y] = s.id;

                }else{
                    willRemoveShark.add(s.id);
                }
            }
            //3. 지울 상어를 지운다.
            for(int id : willRemoveShark){
                sharks.remove(id);
            }
            //4. 전체 냄새의 감소
            decreaseSmell();
            //5. 현재 상어들의 위치에 냄새를 뿌린다.
            spreadSmells();

//            System.out.println("...이동후 map...");
//            for(int i=0; i<N; i++){
//                System.out.println(Arrays.toString(map[i]));
//            }
            //종료 조건
            if(sharks.size()==1) return time;
//            System.out.println("...현재 남은 상어..."+sharks.size());

        }
        return -1;
    }
    public static void decreaseSmell(){
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if(leftSmell[i][j] == 0) continue;
                leftSmell[i][j]--;
                if(leftSmell[i][j]==0){
                    smellOwner[i][j]=0;
                }
            }
        }
    }
    public static void spreadSmells(){
        for (Map.Entry<Integer,Shark> entry: sharks.entrySet()){
            Shark shark = entry.getValue();
            smellOwner[shark.x][shark.y] = shark.id;
            leftSmell[shark.x][shark.y] = k;
        }
    }
    public static void main(String[] args) throws IOException{
        input();
        System.out.println(  solution() );
    }
}
