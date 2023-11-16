import java.util.*;
class Solution {
    int [][] times; //모든 배열의 시작 시간과 끝 시간
    final int sec = 1000;
    final int min = sec*60;
    final int hour = min*60;
    final int day = 24*hour;
    
    int [] ch = new int[day+3*sec]; //인덱스 = ms 항상 3ms를 더해서 계산
    
    public int getRuntime(String runtime){
        // System.out.println(" getRuntime >> "+ runtime);
        //단위 miliseconds
        String runtimeStr = runtime.substring(0, runtime.length()-1);
        // System.out.println(" runtimeStr >> "+ runtimeStr);
        String temp [] = runtimeStr.split("\\.");
         // System.out.println(Arrays.toString(temp));
   
        return Integer.parseInt(temp[0])*1000 + ( temp.length > 1? Integer.parseInt(temp[1]) : 0 ) ;
      
    }
    public int getEndTime(String startTime){
        //단위 miliseconds
        String [] hms = startTime.split("\\.")[0].split("\\:");
        // System.out.println(Arrays.toString(hms));
        String  ms = startTime.split("\\.")[1];
        // System.out.println(ms);
        return  Integer.parseInt(hms[0])*hour
                + Integer.parseInt(hms[1])*min
                + Integer.parseInt(hms[2])*sec
                + Integer.parseInt(ms);
    }
    
    public int getStartTime(int endTime, int runtime){
        return endTime - runtime + 1;
        
    }
    
    public void initTimes(String[] lines){
        times = new int[lines.length][2]; //idx = 0 start-time, idx =1 end-time
        int idx = 0;
        for(String line : lines){
            int endTime = getEndTime(line.split(" ")[1]);
            int runTime = getRuntime(line.split(" ")[2]);
            int startTime = getStartTime(endTime, runTime);
            times[idx++] = new int[]{startTime, endTime};
            
        }
    }
    
    public int solution(String[] lines) {
        int answer = 0;
     
        // 모든 배열의 시작 시간과 끝 시간을 구한다.
        initTimes(lines);
        // System.out.println(Arrays.deepToString(times));
        
        for(int i = 0; i<lines.length; i++){
            int st = times[i][0] + 3*sec;
            int end = times[i][1] + 3*sec;
            // System.out.println("line "+ i+ " st = "+ st + " end = "+end);
            for(int j = st-999; j <=end; j++){
                if(j < 0 || j >=ch.length) continue;
                ch[j]++;
                answer = Math.max(answer, ch[j]);
            }
    
        }


        return answer;
    }
    
    
}