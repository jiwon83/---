package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16506_CPU {
    static StringBuilder ans = new StringBuilder();
    static FastReader sc = new FastReader();
    static List<String> opcodeASS = Arrays.asList("ADD","SUB","MOV","AND","OR","NOT","MULT","LSFTL","LSFTR","ASFTR","RL","RR");
    static List<String> opcodeMAC = Arrays.asList("0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011");


    static void input() {
        //한줄씩 pro를 통해 변환한다.
        int N = sc.nextInt();
        while (N-- >0){
            String str = sc.nextLine();
            ans.append(pro(str)).append("\n");
        }

    }
    static String pro( String assemble ) {

        StringBuilder result = new StringBuilder();
        String opcode="";
        int bit4=0;
        String rD,rA,BC="";

        /**assemble을 기계어로 변환.*/
        String [] arr = assemble.split(" ");

        //1. 명령어 변환 4bit + 0 or 1 -> 명령어 끝에 c가 붙으면 4bit = 1;
        if ( arr[0].charAt(arr[0].length()-1) == 'C'){
            bit4=1;
            String assem = arr[0].substring(0,arr[0].length()-1);
            opcode= opcodeMAC.get( opcodeASS.indexOf(assem) );

        }else{
            opcode= opcodeMAC.get( opcodeASS.indexOf(arr[0]) );
        }

        //2. 나머지 숫자들 rD, rA 변환 3bit
        rD = String.format("%3s", Integer.toBinaryString(Integer.parseInt(arr[1])) ).replace(' ','0');

        rA = String.format("%3s", Integer.toBinaryString(Integer.parseInt(arr[2])) ).replace(' ','0');

        //3. idx4의 값에 따라 rB(3bit) or #C(4bit) 변환
        if (bit4==0){
            BC = String.format("%3s", Integer.toBinaryString(Integer.parseInt(arr[3])) ).replace(' ','0')+"0";
        }else {
            BC = String.format("%4s", Integer.toBinaryString(Integer.parseInt(arr[3])) ).replace(' ','0');
        }


        result.append(opcode).append(bit4).append(0).append(rD).append(rA).append(BC);

        return result.toString();

    }
    public static void main(String[] args) {

        input();
        System.out.println(ans);
    }
    static class FastReader {
            BufferedReader br;
            StringTokenizer st;

            public FastReader() {
                br = new BufferedReader(new InputStreamReader(System.in));

            }
            String next(){
                while (st == null || !st.hasMoreTokens()){  //현재 남아 있는 토큰이 없다면 새로 받아온다.
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }

            int nextInt(){
                return Integer.parseInt(next());
            }
            long nextLong(){return Long.parseLong(next()); }

            double nextDouble(){return Double.parseDouble(next());}

            String nextLine(){
                String str ="";
                try {
                    str = br.readLine();

                }catch (IOException e){
                    e.printStackTrace();
                }
                return str;
            }
            void close() {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

}

