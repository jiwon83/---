import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringBuilder ans = new StringBuilder();
        static int N, M;

        public static void main(String[] args) throws IOException {

//
                String input = br.readLine();
                int [] pos = new int[]{0,0,1,0};


                ArrayDeque<Character> queue = new ArrayDeque<>();
                ArrayDeque<Character> buffer = new ArrayDeque<>();
                Stack<Character> stack = new Stack<>();
                String answer = "PPAP";

                int idx = 0;
                for (int i = 0; i < input.length(); i++) {
                        stack.push(input.charAt(i));
                        StringBuilder sb = new StringBuilder();

                        if (stack.size() < 4) continue;

                        for (int j = 0; j < 4; j++) {
                                sb.append( stack.get(stack.size()-1-j) );
                        }
                        if (sb.toString().equals("PAPP")){
                                int cnt = 0;
                                while (++cnt <= 3) stack.pop();
                        }
                }
                System.out.println((stack.size()==1 && stack.get(0)=='P')? "PPAP" : "NP");

//                System.out.println(check(input)?"PPAP": "NP");
                //KMP, O(N), 한 번 돌면서
                // PPAP
                // PPPAPAP
                // PPAP
                //PPAP PPAP PPAP
                // P  P P => fail
                // PPAP PPAP A PPAP => P



        }
//        public static boolean check(String s){
//                String target = "PPAP";//=> p
//                StringBuilder sb = new StringBuilder();
//                int pointer = 0;
//                int idx = 0;
//                for(int i = 0; i <= s.length(); i++){
//                        if (target.charAt(idx++)==s.charAt(i)){
//
//                                sb.append(s.charAt(i));
//                                if (idx == 4){
//
//                                        sb.setLength(sb.length()-4);
//                                        sb.append("P");
//                                        pointer++;
//                                        idx -=3;
//
//                                }
//                        }
//                        else return false;
//
//                }
//                return tarI == 4;
//        }


}