import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        //원하는 단어를 중복되지 않도록 그 횟수를 return
        String str = br.readLine();
        String pattern = br.readLine();
        System.out.println(findPattern(str, pattern));
    }
    public static int findPattern(String str, String pattern){
        int count = 0;
        for (int i =0; i + pattern.length() -1 < str.length(); i++){
            if (pattern.equals(str.substring(i,i+pattern.length()))){
                i += pattern.length() - 1;
                count++;
            }
        }
        return count;
    }
}
