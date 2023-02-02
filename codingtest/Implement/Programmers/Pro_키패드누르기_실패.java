import java.util.*;
class Solution {
    class Point{
        int x, y,dist;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public Point(int x, int y, int dist){
            this.x=x;
            this.y=y;
            this.dist = dist;
        }
        public Point(){}
    }
    public StringBuilder sb = new StringBuilder();
    public String hands[][] = new String[4][3];
    // public int phone[][] = new int[4][3];
    public int [] dx ={0,0,-1,1}, dy = {-1,1,0,0};
    public String Hand;
    public int leftX, leftY, rightX, rightY;
    public ArrayList<Integer> num0 =new ArrayList<>(Arrays.asList(1,4,7));
    public ArrayList<Integer> num1 =new ArrayList<>(Arrays.asList(2,5,8,0));
    public ArrayList<Integer> num2 =new ArrayList<>(Arrays.asList(3,6,9));
    public HashMap<Integer,Point> map = new HashMap<>();
    
    public void initMap(){
        map.put(0, new Point(3,2));
        map.put(1, new Point(0,0));
        map.put(2, new Point(0,1));
        map.put(3, new Point(0,2));
        map.put(4, new Point(1,0));
        map.put(5, new Point(1,1));
        map.put(6, new Point(1,2));
        map.put(7, new Point(2,0));
        map.put(8, new Point(2,1));
        map.put(9, new Point(2,2));
    }
    
    public void initHands(){
        
        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                hands[i][j]="";
            }
        }
        hands[3][0]="L";
        hands[3][2]="R";
        leftX = 3; leftY=0; rightX=3; rightY=2;
        //System.out.println(Arrays.deepToString(hands));
    }
    //ok
    public void moveFinger(int num, String hand){ 
        // num 이 있는 곳으로 손가락을 움직인다.
        Point p = map.get(num);
        //현재 leftX, leftY, rightX, rightY remove;
        if(hand.equals("L")){
            hands[leftX][leftY] ="";
            leftX = p.x;
            leftY = p.y;
            hands[leftX][leftY] = "L";
            sb.append("L");
        }else{
            hands[rightX][rightY] ="";
            rightX = p.x;
            rightY = p.y;
            hands[rightX][rightY] = "R";
            sb.append("R");
        }
        
    }
    public void moveFinger(Point p, String hand){
        
        //현재 leftX, leftY, rightX, rightY remove;
        if(hand.equals("L")){
            hands[leftX][leftY] ="";
            leftX = p.x;
            leftY = p.y;
            hands[leftX][leftY] = "L";
            sb.append("L");
        }else{
            hands[rightX][rightY] ="";
            rightX = p.x;
            rightY = p.y;
            hands[rightX][rightY] = "R";
            sb.append("R");
        }
        
    }
    
    public void bfs(int num, String hand){ //2 5 8 0
        //num에서 가까운 곳을 찾아야한다.hand는 오른손잡이 인지 왼손 잡이인지 여부
        //leftX, leftY, rightX, rightY remove;
        //만약 현재 위치가 가려는 곳이라면 
        Point test = new Point(leftX,leftY);
        Point test2 = new Point(rightX,rightY);
        if(map.get(num)==test || map.get(num)==test2){
            System.out.println("현재위치와 같았다.");
            if(map.get(num)==test) sb.append("L");
            else sb.append("R");
            return;
        }
        boolean[][] visit = new boolean[4][3];
        Point start = map.get(num);
        start.dist = 0;
        Queue<Point>  q = new LinkedList<>();
        q.add(start);
        
        
        String tarHand="";
        Point tarPoint= new Point();

        boolean finded=false;
        while(!q.isEmpty()){
            
            Point now = q.poll();
            System.out.println("가까운 hand 탐색중...."+now.x+" , "+ now.y);
            
            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || ny <0 || nx >=4 || ny >=3) continue;
                if(visit[nx][ny]) continue;
                
                if(hands[nx][ny].equals("L") || hands[nx][ny].equals("R") ){
                    
                    if(!finded){
                        System.out.println("가까운 hand를 찾았다!"+hands[nx][ny]);
                        tarPoint.x= nx;
                        tarPoint.y= ny;
                        tarPoint.dist = now.dist+1;
                        tarHand=hands[nx][ny];
                        // moveFinger(num, hands[nx][ny] );
                        finded=true;
                    } 
                    else if(hands[nx][ny].equals(hand) && tarPoint.dist >= now.dist+1){
                        System.out.println("동일한 거리의 hand를 찾았다!"+hands[nx][ny]);
                        tarPoint.x= nx;
                        tarPoint.y= ny;
                        tarPoint.dist = now.dist+1;
                        tarHand=hands[nx][ny];
                        // moveFinger(num, hands[nx][ny] );
                    }
                }else{
                    q.add(new Point(nx,ny,now.dist+1));
                }
                visit[nx][ny] = true;
       
            }
            
        } 
        if(tarHand.length()>0) {
            moveFinger(tarPoint, tarHand );   
            // break;
        }
    }

    
    public String solution(int[] numbers, String hand) {
        Hand = hand;
        if(hand.equals("left")) hand = "L";
        if(hand.equals("right")) hand = "R";
        String answer = "";
 
        initHands();
        initMap();
        for(int i=0; i<numbers.length; i++){
            //숫자 하나씩
            if(num0.contains(numbers[i])){
                moveFinger(numbers[i],"L");
                System.out.println("L 이동 >> "+numbers[i]);
                System.out.println(Arrays.deepToString(hands));
                
            }else if (num2.contains(numbers[i])){
                moveFinger(numbers[i],"R");
                System.out.println("R 이동 >> "+ numbers[i]);
                System.out.println(Arrays.deepToString(hands));
                
            }else{
                bfs(numbers[i], hand);
                System.out.println("2580이동 >> "+ numbers[i]);
                System.out.println(Arrays.deepToString(hands));
            }
            
        }
        return sb.toString();
    }
}