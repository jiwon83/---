import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        HashMap<String, Integer> hm = new HashMap<>();

        for(int i=0; i<records.length; i++){
            String [] in = records[i].split(" ");
            if(in[2].equals("OUT")) continue; //In에 대한 것만 취급
            
            String [] time = in[0].split(":");
            
            int inTime = Integer.parseInt(time[0])*60+ Integer.parseInt(time[1]);
            String carNum = in[1];
            int outTime;
            
            //carNum의 out을 찾는다.  * 반드시 IN 다음에는 OUT이 온다. 
            String [] out={"23","59"}; //23:00 5961 OUT"
            for( int j=i+1; j<records.length; j++){
                if(records[j].substring(6,6+4).equals(carNum)){
                    //만약 번호가 같다면
                    out = records[j].split(" ")[0].split(":");
                    break;
                }
            }
            // System.out.println(carNum + "차량의 out 시간"+ Arrays.toString(out));
            outTime = Integer.parseInt(out[0])*60 + Integer.parseInt(out[1]);
            //주차 이용 시간
            int usingTime = outTime - inTime;
            //map 에 갱신
            hm.put(carNum, hm.getOrDefault(carNum, 0) + usingTime);
  
        }//records 순회
        
        answer = new int [hm.size()];
        int ansIdx=0;
        //hashmap 정렬 : keySet을 정렬
        Object [] mapKey = hm.keySet().toArray(); //keySet을 array로 변환해서 
        Arrays.sort(mapKey); //정렬
        for(Object key : mapKey){
            String num = String.valueOf(key);
            Integer time = hm.get(key);
            int usingFee = fees[1]; //기본 요금 
            if(time > fees[0]){ //기본시간 초과라면
                if( ( time - fees[0] ) % fees[2] > 0 ){
                    usingFee += ( ( time - fees[0] ) / fees[2] + 1 )* fees[3];
                }else{
                    usingFee += ( ( time - fees[0] ) / fees[2] )* fees[3];
                }
            }
            // System.out.println(num+"의 주차 기록 : "+""+ usingFee);
            answer[ansIdx++] = usingFee;
        }
        return answer;
    }
}