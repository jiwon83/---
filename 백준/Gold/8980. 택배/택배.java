import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static class Box implements Comparable<Box>{

    int s, e, count;

    public Box(int s, int e, int count) {
      this.s = s;
      this.e = e;
      this.count = count;
    }

    @Override
    public int compareTo(Box o) {
      return this.e - o.e;
    }

    @Override
    public String toString() {
      return "Box{" +
              "s=" + s +
              ", e=" + e +
              ", count=" + count +
              '}';
    }
  }

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int N, C, M; //M = 박스 정보의 갯수
  static Box [] boxes;

  static void input() throws IOException {
    StringTokenizer st;
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(br.readLine());
    boxes = new Box[M];
    for (int i = 0; i < M ; i++){
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      boxes[i] =new Box(s, e, c);
    }

  }

  static void sortBoxes(){
    // 1. 박스들 출발점, 도착점 오름차순 정렬
    Arrays.sort(boxes, (o1, o2) -> {
      if (o1.s != o2.s) return o1.s - o2.s;
      return o1.e - o2.e;
    });
  }

  static int pro(){
    sortBoxes();
    int served = 0;
    int boxesIdx = 0;
    int qSize = 0; //큐 내 현재 박스의 갯수
    List<Box> q = new ArrayList<>();
    for (int st = 1; st <= N; st++){ //역마다
//      System.out.println(" ======= station : "+ st+" ==============");
      //q정렬
      Collections.sort(q);
      //1. 배송할 게 있다면 배송한다
      while ( !q.isEmpty() && q.get(0).e == st ){
        Box box = q.remove(0);
        served += box.count;
        qSize -= box.count;

//        System.out.println("배송 "+ box.e + " ,count =  "+ box.count);
      }
      //2. 현재 가지고 있는 것보다 이득이거나, 빈자리가 있다면 담는다.
      while (boxesIdx < M && boxes[boxesIdx].s == st){
        Box cand = boxes[boxesIdx];
//        System.out.println("승차 후보 : "+ cand);
        if ( qSize < C ){
//          System.out.println(" 빈공간 있음 "+ q.size()+"/"+C);
          int possSize = Math.min(C - qSize, cand.count);
          q.add(new Box(cand.s, cand.e, possSize));
          qSize += possSize;
          if(possSize != cand.count){ //남은 공간이 부족해서 다 못 넣었다면
            cand.count -= possSize;
          }else{
            boxesIdx++;
          }

        }else if(q.get(q.size()-1).e > cand.e){ //이미 큐에는 가득찼다면
//          System.out.println(" 빈공간 없음"+q.size()+"/"+C);

          if(q.get(q.size()-1).e > cand.e){
//            System.out.println(" 이득임 cand : "+ cand.e + " , 현재 bigger: "+ q.get(q.size()-1).e);
            Box bigger = q.remove(q.size()-1);
            if (bigger.count > cand.count){
              q.add(q.size()-1, new Box(st, bigger.e, bigger.count - cand.count));
              q.add(0, new Box(st, cand.e, cand.count));
              boxesIdx++;
            } else if (bigger.count == cand.count) {
              q.add(0,cand);
              boxesIdx++;
            }else if(bigger.count < cand.count){
              q.add(0,new Box(st, cand.e, bigger.count));
              cand.count -= bigger.count;
            }
          }
        }else {
          boxesIdx++;
          break;
        }
      }
//      System.out.println("--현재 큐 --");
//      System.out.println(q);

    }
    return served;


  }
  public static void main(String[] args) throws IOException{
    input();
    System.out.println(pro());
  }
}
