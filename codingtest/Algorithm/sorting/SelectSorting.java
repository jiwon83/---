package sorting;

import java.util.ArrayList;
import java.util.Collections;
/*
선택 정렬: 찾아야 하는 남은 수들 중 가장 작은 값을 찾아서 정해진 위치에 insert해주는 방식
 */
public class SelectSorting {

    static ArrayList<Integer> array = new ArrayList<>();

    static ArrayList sort( ArrayList<Integer> dataList){
        //선택 정렬
        int minIndex;
        for (int left =0 ; left< dataList.size()-1; left++){
            minIndex=left;
            for (int right = left+1; right<dataList.size(); right++){
                if (dataList.get(right) < dataList.get(minIndex)){
                    minIndex= right;
                }
            }
//            System.out.println("가장 작은 값은 "+dataList.get(minIndex)+ " 넣어질 위치 = "+ left);
            Collections.swap(dataList, minIndex, left);
//            System.out.println(Collections.unmodifiableList(dataList));
        }
        return dataList;
    }

    public static void main(String[] args) {
        //랜덤으로 리스트를 생성 => arrayList
        for (int i=0; i<10; i++) {
            array.add( (int)(Math.random()*100) + 1 );

        }

        System.out.println(Collections.unmodifiableList(array));

        sort(array);

        System.out.println(Collections.unmodifiableList(array));
    }

}
