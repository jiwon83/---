class Solution {
    public String solution(String new_id) {
        // System.out.println("...jg".replaceAll("..","\\."));
        String answer = "";
        String special = "-_.~!@#$%^&*()=+[{]}:?,<>/";
        String can = "-_.";
        
        // answer = answer.replaceAll("[^-_.a-z0-9]", ""); // 2단계
        // answer = answer.replaceAll("[.]{2,}", "."); // 3단계
        // answer = answer.replaceAll("^[.]|[.]$", "");    // 4단계
        //1. 모두 소문자로
        new_id= new_id.toLowerCase();
        // System.out.println(new_id);
        //2. 사용불가 문자는 제거
        StringBuilder step2 = new StringBuilder();
        for(int i=0; i< new_id.length(); i++){
            char c = new_id.charAt(i);
            if( (c>='0' && c<='9') || (c>='a' && c<='z') || can.contains(c+"" )){
                step2.append(c);
            }
        }
        new_id = step2.toString();
     
        // System.out.println("step 2. "+ new_id);
        //3. .여러개 연속은 1개만 => 정규식? 
        while(new_id.contains("..")){
            new_id= new_id.replace("..",".");
        }
        // System.out.println("step 3 "+new_id);
        
        //4. 처음 끝의 .는 제거
        if(new_id.length()>0){
            if(new_id.charAt(0)=='.') new_id = new_id.substring(1);
        }
        if(new_id.length()>0){
            if(new_id.charAt(new_id.length()-1)=='.') {
                new_id = new_id.substring(0,new_id.length()-1);
            }
        }
        // System.out.println("step 4 "+new_id);
        
        //5.빈 문자열이라면 'a'대입
        if(new_id.length() == 0){
            new_id="a";
        }
        // System.out.println("step 5 "+new_id);
        //길이는 15자로 제한
        if(new_id.length() >= 16){
            new_id = new_id.substring(0,15);
            // System.out.println("step 6 "+new_id);
            // 그랬는데 끝이 .이면 제거
            if(new_id.charAt(14)=='.') {
                new_id = new_id.substring(0,14);
            }
            // System.out.println("step 7 "+new_id);
        }
        while(new_id.length() < 3){
            new_id += new_id.charAt(new_id.length()-1);
        }
        //길이가 2자 이하라면 마지막 문자가 길이 3이될때까지 append
        return new_id;
    }
}