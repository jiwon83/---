class Solution {
    
    public String[] splitStr(String p){
        String u="",v="";
        //균형잡힌 괄호문자열 u추출 ( +1   ) -1
        int sum=0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<p.length(); i++){
            char c = p.charAt(i);
            if(c=='('){
                sum += 1;
            }else{
                sum -= 1;
            }
            sb.append(c);
            if(sum==0){
                u= sb.toString();
                break;
            }
        }
        v= p.substring(u.length());
        return new String[]{u,v};
    }
    public String recur(String p){
        if(p.equals("")) return p;
        //분리
        String [] result = splitStr(p);
        String u = result[0]; String v = result[1];
        System.out.println(p+" 분리 후 "+u+" , "+v );
        
        if(isCorrect(u)){
            System.out.println(u+" 올바른 문자열임. ");
            u += recur(v);
            System.out.println(u+" 완성 : u와 v의 합체 ");
            return u;
        }else{
            System.out.println(u+" 올바른 문자열 아님. ");
            StringBuilder sb = new StringBuilder("(");
            sb.append(recur(v)).append(")");
            u = u.substring(1,u.length()-1);
            String temp = "";
            for(int i=0; i<u.length(); i++){
                char c = u.charAt(i);
                if(c=='('){
                    sb.append(')');
                }else{
                    sb.append('(');
                }
            }
            return sb.toString();
        }

    }

    public boolean isCorrect(String u){
        // ( => + ) => - 
        int result=0;
        for(int i=0; i<u.length(); i++){
            char c = u.charAt(i);
            if(c=='('){
                result += 1;
            }else{
                result -= 1;
            }
            if(result < 0 ) return false;
        }
        if(result==0) return true;
        else return false;
    }

    public String solution(String p) {
        String answer = recur(p);

        return answer;
    }
}