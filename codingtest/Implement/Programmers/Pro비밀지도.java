package implement;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/17681
 */
public class Pro비밀지도 {


    public String[] solution(int n, int[] arr1, int[] arr2) {
        
        String[] answer = new String[n];


        for (int i=0; i<arr1.length; i++){//한 줄 씩

            //1. 배열의 값들을 2진수로 변환한다.
            String binary1 = String.format("%0"+n+"d", Long.parseLong(Integer.toBinaryString(arr1[i])));

            String binary2 = String.format("%0"+n+"d", Long.parseLong(Integer.toBinaryString(arr2[i])));


            StringBuilder sb = new StringBuilder();
            for (int j=0; j<n; j++){

                if ( (binary1.charAt(j)-'0' | binary2.charAt(j)-'0') ==1) sb.append("#"); //비트 연산 후 값에 따라 문자열로 변환해준다.
                else sb.append(" ");
            }
            answer[i] = sb.toString();
            sb.setLength(0);


        }
//        System.out.println(Arrays.toString(answer));
        return answer;
    }


    public static void main(String[] args) {
        Pro비밀지도 s = new Pro비밀지도();
        System.out.println(s.solution(5, new int []{9, 20, 28, 18, 11}, new int []{30, 1, 21, 17, 28}));

    }
}
