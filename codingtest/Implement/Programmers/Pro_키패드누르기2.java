import java.util.*;
class Solution {
    class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y=y;
        }
    }
    int L,R;
    HashMap<Integer, Point> map = new HashMap<>();
    
    public int getDist(int num1, int num2){
        //num과 왼쪽 , 오른쪽의 거리를 구한다. 
        Point p1 = map.get(num1);
        Point p2 = map.get(num2);
        return Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y);   
    }
    public String solution(int[] numbers, String hand) {
        String answer = "";
        
        if(hand.equals("left")) hand="L";
        else hand="R";
        //map 초기화, 처음 손 위치 초기화 
        L = -1; R= -2;
        map.put(1,new Point(0,0));
        map.put(2,new Point(0,1));
        map.put(3,new Point(0,2));
        map.put(4,new Point(1,0));
        map.put(5,new Point(1,1));
        map.put(6,new Point(1,2));
        map.put(7,new Point(2,0)); 
        map.put(8,new Point(2,1));
        map.put(9,new Point(2,2));
        map.put(0,new Point(3,1));
        map.put(-1,new Point(3,0));
        map.put(-2,new Point(3,2));
        
        //숫자에 따라 손위치를 변경하고 값을 저장한다.
        for(int i=0; i<numbers.length; i++){
            int num = numbers[i];
            if(num == 1 || num == 4 || num == 7 ){
                L = num;
                answer += "L";
            }
            if(num == 3 || num == 6 || num == 9 ){
                R = num;
                answer += "R";
            }
            if(num == 2 || num == 5 || num == 8 || num==0 ){
                int left = getDist(num, L);
                int right = getDist(num, R);
                
                if( left < right){
                    L = num;
                    answer += "L";
                }else if(right < left){
                    R = num;
                    answer += "R";
                }else{
                    if( hand.equals("L")){
                        L = num;
                    }else{
                        R = num;
                    }
                    answer += hand;
                }
         
      
            }//if
        }//for
        return answer;
    }//sol
}