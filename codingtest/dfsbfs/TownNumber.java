package DFSBFS;
/*
 * 백준 실버 1 단지번호 붙이기 https://www.acmicpc.net/problem/2667
 * 시도: X(푸는 방식은 맞았는데 결과가 나오지 않음. 꼼꼼하고 철저하게 로직을 세우자.), O
 * 체감 난이도: 중하
 * 참고: https://github.com/rhs0266/FastCampus/blob/main/%EA%B0%95%EC%9D%98%20%EC%9E%90%EB%A3%8C/02-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98/09~11-%EA%B7%B8%EB%9E%98%ED%94%84%20%ED%83%90%EC%83%89/%EB%AC%B8%EC%A0%9C%EB%B3%84%20%EC%BD%94%EB%93%9C/2667-%EB%8B%A8%EC%A7%80%EB%B2%88%ED%98%B8%20%EB%B6%99%EC%9D%B4%EA%B8%B0/solution.java
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N,cnt;
    public static boolean[][] visit;
    public static int[][] graph; //String[]

    public static ArrayList<Integer> group = new ArrayList<>();
    public static int[] dx={-1,1,0,0};
    public static int[] dy={0,0,-1,1};

    public static StringBuffer result = new StringBuffer();

    public static void dfs(int x, int y){
        cnt++;
        visit[x][y] = true;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

//            if (visit[nx][ny]) continue;
            if (nx <0 || ny <0 || nx >= N || ny >= N){
                continue;
            }
            if(!visit[nx][ny] && graph[nx][ny]==1){
                dfs(nx,ny);
            }
        }


    }
    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        visit= new boolean[N][N];
        graph= new int[N][N];

        for (int i=0; i<N; i++){
            String str = br.readLine();
            for (int j=0; j<N;j++){
                graph[i][j]= str.charAt(j)-'0';
            }
        }
//        System.out.println(Arrays.deepToString(graph));
    }

    public static void main(String [] args)throws IOException{
        input();
        for(int i=0; i<N; i++){
            for(int j=0; j<N;j++){
                if(!visit[i][j] && graph[i][j]==1){
                    cnt=0;
                    dfs(i,j);
                    group.add(cnt);

                }

            }
        }
        Collections.sort(group);
        result.append(group.size()+"\n");
        for (int i=0; i< group.size(); i++){
            if (i== group.size()){
                result.append(group.get(i));
            }
            result.append(group.get(i)+"\n");

        }
        System.out.println(result);

//        System.out.println(Arrays.deepToString(visit));
    }
}
