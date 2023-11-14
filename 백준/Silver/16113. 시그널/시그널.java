                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                import java.util.HashMap;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                import java.util.Map;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                public class Main {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        static int R, C;
        static Map<Integer, char[][]> numbersMap;
        static StringBuilder sb = new StringBuilder();
        static String input;

        static void initNumbersMap(){
                numbersMap = new HashMap<>();
                for (int i = 0; i< 10; i++){
                        if(i == 1) continue;
                        numbersMap.put(i, makeMapByNumber(i));
                }

        }
        static char[][] makeMapByNumber(int num){
                char [][] result = new char[5][3];
                if (num == 0){
                        for (int i = 0; i<5; i++){
                                if (i == 0 || i == 4)  result[i] = "###".toCharArray();
                                else result[i] = "#.#".toCharArray();
                        }
                }
                if (num == 2){
                        for (int i = 0; i<5; i++){
                                if ((i & 1)==0) result[i] = "###".toCharArray();
                                else if(i == 1){
                                        result[i] = "..#".toCharArray();
                                }else{
                                        result[i] = "#..".toCharArray();
                                }
                        }
                }
                if (num == 3){
                        for (int i = 0; i<5; i++){
                                if ((i & 1)==0) result[i] = "###".toCharArray();
                                else{
                                        result[i] = "..#".toCharArray();
                                }
                        }
                }
                if (num == 4){
                        for (int i = 0; i<5; i++){
                                if (i<2) result[i] = "#.#".toCharArray();
                                else if(i==2) result[i] = "###".toCharArray();
                                else result[i] = "..#".toCharArray();
                        }

                }
                if (num == 5){
                        for (int i = 0; i<5; i++) {
                                if ((i & 1) == 0) result[i] = "###".toCharArray();
                                else if (i == 3) {
                                        result[i] = "..#".toCharArray();
                                } else {
                                        result[i] = "#..".toCharArray();
                                }
                        }
                }
                if (num == 6){
                        for (int i = 0; i<5; i++) {
                                if ((i & 1) == 0) result[i] = "###".toCharArray();
                                else if (i == 1) {
                                        result[i] = "#..".toCharArray();
                                } else {
                                        result[i] = "#.#".toCharArray();
                                }
                        }
                }
                if (num == 7){
                        for (int i = 0; i<5; i++) {
                                if ( i == 0) result[i] = "###".toCharArray();
                                else {
                                        result[i] = "..#".toCharArray();
                                }
                        }
                }
                if (num == 8){
                        for (int i = 0; i<5; i++) {
                                if ((i & 1) == 0) result[i] = "###".toCharArray();
                                else {
                                        result[i] = "#.#".toCharArray();
                                }
                        }

                }
                if (num == 9){
                        for (int i = 0; i<5; i++) {
                                if ((i & 1) == 0) result[i] = "###".toCharArray();
                                else if(i==1){
                                        result[i] = "#.#".toCharArray();
                                }else{
                                        result[i] = "..#".toCharArray();
                                }
                        }
                }
                return result;
        }

        static int findNumber(int c){
                numLoop: for (int i = 0; i < 10; i++) {
                        if (i == 1) continue;
                        char [][] stand = numbersMap.get(i);
                        for (int r = 0; r <5; r++){
                                for (int j=0; j < 3; j++){
                                        char ch = input.charAt(r * C + c+j);
                                        if (stand[r][j] != ch){
                                               continue numLoop;
                                        }
                                }
                        }

                        return i;
                }
//                System.out.println(" -1 ");
                return -1;
        }
        static boolean isNumberOne(int c){
                //맨 뒤라면 c 만 확인
                for (int r = 0; r < 5; r++) {
                        if (input.charAt(r * C + c)!= '#') return false;
                }
                if ( c != C-1){
                        for (int r = 0; r < 5; r++){
                                if (input.charAt(r * C + c+1)== '#') return false;
                        }
                }
                return true;
        }

        static boolean isEmptyCol(int c){
                for (int r = 0; r < 5; r++) {
                        if (input.charAt(r * C + c)== '#') return false;
                }
                return true;
        }
        public static void main(String[] args) throws IOException {
                initNumbersMap();

                int N = Integer.parseInt(br.readLine());
                R = 5;
                C = N/5;
                input = br.readLine();
                //1. 5행의 board로 변환
                for (int c = 0; c < C; c++){
//                        System.out.println( "c = "+c);
                        if (isEmptyCol(c)) continue;
                        if (isNumberOne(c)){
                                sb.append("1");
                                c +=1;
                        }else{
                             sb.append(findNumber(c));
                             c += 3;
                        }
                }
                System.out.println(sb);
                //2. 숫자 매칭 3 col 씩, 단 숫자 1은 1 col
                // 2-1. col==1 or col의 row 0이 #이라면 숫자 탐색 시작
                // 2-2. 1인지 확인
                // 2-3. 아니라면 다른 숫자들 중에 확인
                // 2-4. 숫자의 너비 + 1만큼 이동
                //3. 저장 후 출력



        }


}