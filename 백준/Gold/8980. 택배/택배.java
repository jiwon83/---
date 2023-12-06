import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
  static class Box{
    int s , e , count;
    public Box(int s, int e, int count){
      this.s = s;
      this.e = e;
      this.count = count;
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
  static StringTokenizer st;
  static int N, K, M;
  static Box [] boxes;
  static void input() throws IOException{
    String [] str = br.readLine().split(" ");
    N = Integer.parseInt(str[0]);
    K = Integer.parseInt(str[1]);
    M = Integer.parseInt(br.readLine());
    boxes = new Box[M];
    for (int i = 0; i< M; i++){
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int count = Integer.parseInt(st.nextToken());
      boxes[i] = new Box(s,e,count);
    }
  }
  static int sol(int cap, Box [] boxes){
    int answer = 0;
    Arrays.sort(boxes, (o1, o2)->{
      if (o1.e != o2.e) return o1.e - o2.e;
      return o2.s - o1.s;
    });
    int [] left = new int[N+1];
    Arrays.fill(left, cap);
    for (int i = 0; i< M; i++){
      Box box = boxes[i];
      int size = getMin(left, box);
      answer += size;
      for (int j = box.s; j <box.e; j++){
        left[j] -= size;
      }
    }
    return answer;
  }
  static int getMin(int [] left, Box box){
    int minVal = box.count;
    for (int i = box.s; i < box.e; i++){
      minVal = Math.min(minVal, left[i]);
    }
    return minVal;
  }


  public static void main(String[] args) throws IOException {
    input();
    System.out.println(sol(K, boxes));
  }

}