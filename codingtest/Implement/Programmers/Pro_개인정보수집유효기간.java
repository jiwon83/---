import java.util.*;
import java.util.stream.Stream;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // int[] answer = {};
        // int ansIdx=0;
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i=0; i< privacies.length; i++){
            
            String [] separator = privacies[i].split(" ");
            
            //1. 파기일을 구하고 맞는 terms를 찾는다.
            // System.out.println("separator[0] >> "+separator[0]);
            String [] startDay =  separator[0].split("\\.");
            int term = 0; //기간 달
            String this_term = separator[1];
            
            for(int j=0; j<terms.length; j++){
                String [] temp = terms[j].split(" ");
                if(temp[0].equals(this_term)){
                    term = Integer.parseInt(temp[1]);
                }
            }
            
            // 파기일
            String [] endDay = new String [3];
            
          
            int year_int = Integer.parseInt(startDay[0]);
            int month_int = Integer.parseInt(startDay[1]) + term ;
            if(month_int > 12){
                if(month_int %12==0){
                    year_int += month_int / 12 - 1;
                    month_int = 12;
                }else{
                    year_int += month_int / 12;
                    month_int = month_int % 12;
                }
                
            }
            
            endDay [0] = String.valueOf(year_int);
            // endDay [1] = String.valueOf(month_int);
            endDay [1] = String.format("%02d", month_int);
            endDay [2] = startDay[2];
            // System.out.println("폐기일 "+Arrays.toString(endDay));
                
            //2. 파기해야 한다면, answer에 담는다. 
            String [] toDay =  today.split("\\.");
            //비교
            // System.out.println("오늘 " +Arrays.toString(toDay));
            
            boolean Yes=false;
            int same = 0;
            Loop1: for(int w =0; w<3; w++){
                // System.out.println(toDay[w]+" compareTo "+ endDay[w]+" = "
                                   // + toDay[w].compareTo(endDay[w]));
                switch(w){
                    case 0:
                        if(toDay[w].compareTo(endDay[w]) > 0){ //폐기해야함
                            Yes=true;
                            break Loop1;
                        }else if(toDay[w].compareTo(endDay[w]) < 0){ //폐기안해야함.
                            break Loop1;
                        }
                        break;
                    case 1:
                        if(toDay[w].compareTo(endDay[w]) > 0){ //폐기해야함
                            Yes=true;
                            break Loop1;
                        }else if(toDay[w].compareTo(endDay[w]) < 0){ //폐기안해야함.
                            break Loop1;
                        }
                        break;
                    case 2:
                        if(toDay[w].compareTo(endDay[w]) > 0){ //폐기해야함
                            Yes=true;
                            break Loop1;
                        }else if(toDay[w].compareTo(endDay[w]) < 0){ //폐기안해야함.
                            break Loop1;
                        }
                        break;
                }
                if(toDay[w].equals(endDay[w])) same++;
            }
            if(Yes || same==3) {
                System.out.println("오늘 " +Arrays.toString(toDay));
                System.out.println("endDay " +Arrays.toString(endDay));
                list.add(i+1);
            }
        
        }   
        //3. 정렬
        int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
            
        return answer;
    }
}