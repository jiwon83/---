                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringBuilder ans = new StringBuilder();

        /**
         * P PPAP AP
         * P PPAPPAP AP
         * PPAPPAP
         * PP >=2이면 A P를 확인
         * 있다면 P를 하나 minus
         * 없다면 P 추가
         * @param args
         * @throws IOException
         */

        public static void main(String[] args) throws IOException {

//
                String input = br.readLine();
                System.out.println(check2(input)?"PPAP":"NP");

        }
        static boolean check2(String s){
               int cntP = 0;
               for (int i =0; i<s.length(); i++){
                       if (cntP >= 2){
                               if (s.charAt(i)=='A'){
                                       if(i+1 < s.length() && s.charAt(i+1)=='P'){
                                               cntP--;
                                               i++;
                                       }else{
                                               return false;
                                       }

                               }else{
                                       cntP++;
                               }

                       }else{
                               if (s.charAt(i)=='P') cntP++;
                               else return false;
                       }
               }
               return cntP==1;
        }
        static boolean check(String s){
                String answer = "PPAP";
                int pointer =0;
                for (int i = 0; i < s.length(); i++) {
//                        System.out.println("pointer : "+pointer+ "  i: "+i );
                        if (answer.charAt(pointer)=='A' && s.charAt(i)!='A'){
                                return false;
                        }else if(answer.charAt(pointer) == 'P'){
                                if(s.charAt(i)!='P') return false;
                                if (isPPAP(s, i)){
//                                        System.out.println("PPAP이다..");
                                        i+= 3;
                                }
                        }
                        pointer++;
                        if (pointer == 4){
                                pointer = 0;
                        }

                }
                return pointer==0;
        }
        static boolean isPPAP(String s, int start){
                if (start + 3 >= s.length()){
                        return false;
                }
                String answer = "PPAP";
                for (int j = 0; j < 4; j++) {
                        if (s.charAt(start+j) != answer.charAt(j)){
                                return false;
                        }
                }
                return true;
        }

}