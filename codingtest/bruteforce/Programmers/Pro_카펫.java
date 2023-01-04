class Solution {
    public int[] solution(int brown, int yellow) {
        
        int[] answer = new int [2];
        //1. yellow를 보고 두 자연수의 곱으로 표현
        for(int i=1; i<= Math.sqrt(yellow); i++){
            // System.out.println("i="+i);
            if(yellow % i ==0){
                int x = yellow / i + 2; //가로
                int y = i + 2;  //세로
                if(brown + yellow == x * y){
                    answer[0] = x;
                    answer[1] = y;
                }
                
            }
        }
        //2. 이를 통해 가로세로 길이를 추측하고 전체 수와 맞는지 체크해서
        //3. 맞다면 결과를 저장.
        
        return answer;
    }
}