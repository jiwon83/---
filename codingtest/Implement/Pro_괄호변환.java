package implement;

public class Pro_괄호변환 {


    /*
    1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
  4-3. ')'를 다시 붙입니다.
  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
  4-5. 생성된 문자열을 반환합니다.

         */

//    static StringBuilder result = new StringBuilder();
    static String u, v;



    public static String solution(String p) {
        String answer = "";
        StringBuilder result = new StringBuilder();

        if (p=="" || p==null || p.length()==0) {
//            System.out.println("빈 문자열 입니다.");
            return ""; //**주의
        }

        String [] uv = distrubute(p);  /** u,v 분리 */
        if (isProper(uv[0])){
//            System.out.println("적절!");
//            System.out.println("u가 올바른 문자열이다. ");
            result.append(uv[0]).append(solution(uv[1]));
//            System.out.println("result "+result);
        }else{
            String vResult = solution(uv[1]);
//            System.out.println("u가 올바른 문자열이 아니다. ");
//            System.out.println(" ( + "+vResult+" + ) ");

            result.append("(").append(vResult).append(")");



            /** u의 앞뒤를 제거 */
//            System.out.println("uv[0] 제거 전 "+ uv[0]);
            uv[0] = uv[0].substring(1, uv[0].length()-1);
//            System.out.println("uv[0] 제거 후 "+ uv[0]);

            StringBuilder temp = new StringBuilder();
            //남은 u의 괄호를 reverse.
            for (int i=0; i<uv[0].length(); i++){
//                System.out.println("남은 u안의 괄호 처리");
                if (uv[0].charAt(i)=='(') temp.append(")");
                else temp.append("(");
            }
//            uv[0] = uv[0].replace("\\(","\\)").replace("\\)","\\(");
//            uv[0] = uv[0].replaceAll("\\(","\\)");
//            System.out.println("reverse 후 "+ uv[0]);
//            uv[0] = uv[0].replaceAll("\\)","\\(");
//            System.out.println("reverse 후 "+ temp);
            result.append(temp);

//            System.out.println("result = "+result);
        }
        return result.toString();
    }

    private static String[] distrubute(String p) {
        //u , v 분리
        int left=0,right=0;
        int index= 0; //전체일때

        for (int i=0; i<p.length(); i++){
            char c = p.charAt(i);

            if (c=='(') left++;
            if (c==')') right++;

            if (left==right && left >0 && right >0){ //만약 left와 right가 일치하는 순간이 오면
                index=i;
                break;
            }
        }
        //그때의 index로 split

        String u = p.substring(0,index+1);
        String v = p.substring(index+1);
        System.out.println("u"+u);
        System.out.println("v"+v);
        String [] result = new String[]{u,v};
        return result;
    }

    private static boolean isProper(String u) {
        //올바른 문자열 (순서가 맞는 지 확인)
        //)의 갯수로 판단
        int R=0;
        for (int i=0; i<u.length(); i++){
            
            char c = u.charAt(i);
            if (c == '(') R++;
            else R--;
            
            if (R < 0) return false; //)의 갯수가 음수가 될 때, )로 시작하거나 애초에 ()) 이런 경우
            
        }
        //만약 R==0이면 
        if (R==0) return true;
        else return false; // ()(((( 처럼 (가 엄청 많아서 0이 되지 못한 경우
    }


    public static void main(String[] args) {
//        System.out.println("()".replaceAll("\\(","\\)"));
//        System.out.println(solution("))(()("));
        System.out.println(solution(")(()"));

    }




}
