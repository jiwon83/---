import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/*
12 10 4 7
2 4
7 3
3 1
5 6
4 7
12 10
8 6
 */

public class Main {
    static class Star implements Comparable<Star>{
        int x, y;
        public Star(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Star other){
            //x 좌표 기준 정렬

            if (x !=  other.x){
                return x - other.x; //오름차순, 가까운순
            }else{
                return 0;
            }

        }
        public String toString(){
            return " ( "+ x + " , "+ y + " ) ";
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M,L,K, defense;
    static List<Star> stars = new ArrayList<>();

//    static int [][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); //별동별 갯수
        defense = 1;
        for(int i = 1; i<= K ; i++){
            st =new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Star(x, y));
        }

        Collections.sort(stars); //x좌표기준 정렬

        for (int i = 0; i<K; i++){ //기준 좌표
            Star star1 = stars.get(i);
            for(int j = i+1; j<K; j++){
                int count = 1; //기준 별 1개 count
                Star star2 = stars.get(j);
                if(Math.abs(star1.y- star2.y) > L) continue; //두 점을 모서리로 갖지 못한다.
                int startX = star1.x;
                int endX = star1.x + L;
                int startY, endY;
                if(star1.y >= star2.y){ //1사분면에 위치하면
                    startY = star2.y;
                    endY = star2.y+L;
                }else{ //4사분면에 위치하면
                    startY = star2.y - L ;
                    endY = star2.y;
                }
//                System.out.println("탐색 x : "+startX + " ~ "+ endX );
//                System.out.println("탐색 y : "+startY + " ~ "+ endY );
                for (int w = i+1; w<K; w++){
                    Star star = stars.get(w);
                    if(star.x >= startX && star.x <=endX && star.y >= startY && star.y <= endY) count++;
                }
                defense = Math.max(count, defense);

            }

        }
        
        System.out.println(stars.size() - defense); //지구에 떨어지는 별동별의 양

    }
    static boolean isInDistL(Star s1, Star s2, int L){
        return Math.abs(s1.x - s2.x) <= L && Math.abs(s1.y - s2.y) <=L;
    }



}
