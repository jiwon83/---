
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N = 9;
    static ArrayList<Point> blanks = new ArrayList<>();
    static int [][] map = new int[9][9];
    static boolean success = false;
    static StringBuilder sb = new StringBuilder();

    public static void input() throws IOException{
        for (int i = 0; i < N; i++){
            String temp = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j] = temp.charAt(j)-'0';
                if(map[i][j]==0){
                    blanks.add(new Point(i, j));
                }
            }
        }
    }
    static int [] dx = {0,0,-1,1}, dy ={-1,1,0,0};
    public static void main(String[] args) throws IOException{
        input();
        //스토쿠를 채워준다.
        recur(0);

        System.out.println(sb);

    }
    static void recur(int k){

        // k 번째 빈칸에 넣을 수 있는 숫자를 넣는다.
        if(success) return;
        if( k == blanks.size()){
            success = true;
            printSave();
            return;
        }
        Point blank = blanks.get(k);
        boolean [] impossible = getPossible(blank);
        for (int i = 1; i < 10; i++){
            if(!impossible[i]){
                map[blank.x][blank.y] = i;
                recur(k+1);
                map[blank.x][blank.y] = 0;
            }
        }
    }
    static void printSave(){
        for (int i = 0; i<N; i++){
            for (int j = 0; j < N; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
    }
    static boolean[] getPossible(Point p){
        boolean [] impossible = new boolean[10];
        //열에서 확인
        for (int i = 0; i < N; i++){
            if(map[i][p.y] != 0) impossible[map[i][p.y]] = true;
            if(map[p.x][i] != 0) impossible[map[p.x][i]] = true;
        }

        //행 확인

        // 3* 3 확인
        //기준점
        int i = p.x / 3;
        int j = p.y / 3;
        for (int nx = i*3; nx < i*3+3; nx++){
            for(int ny = j*3; ny < j*3+3; ny++){
                if(map[nx][ny]!=0) impossible[map[nx][ny]] = true;
            }
        }
        return impossible;
    }

}
