
import java.io.*;
import java.security.cert.PolicyNode;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static String s;
    static String t;

    static void pro() {
        int sIdx=0;
        boolean tf = false;
        for (int i=0; i<t.length(); i++){
            if (s.charAt(sIdx)==t.charAt(i)) sIdx++;
            if (sIdx==s.length()) {
                tf=true;
                break;
            }

        }
        if (tf) sb.append("Yes").append("\n");
        else sb.append("No").append("\n");

    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp;
        StringTokenizer st;
        while((temp = br.readLine()) != null){
            st = new StringTokenizer(temp);
            s = st.nextToken();
            t = st.nextToken();
            pro();
        }
        System.out.println(sb);

    }

}
