import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        while (true){
            String temp = br.readLine();
            if (temp.charAt(0)=='0') break;
            String res = isPalindrome(temp)? "yes": "no";
            bw.write(res+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static boolean isPalindrome(String s){
        int L, R;
        if ((s.length() & 1) == 1){
            L = R = s.length() / 2;
            L++; R--;
        }else{ //0123
            L = s.length() / 2;
            R = s.length() / 2 - 1;
        }
        while (L - 1 >= 0 && R + 1 <s.length()){
            if (s.charAt(--L) != s.charAt(++R)) return false;
        }
        return true;
    }
}