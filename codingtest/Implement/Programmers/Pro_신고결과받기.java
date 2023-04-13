https://school.programmers.co.kr/learn/courses/30/lessons/92334

import java.util.*;
class Solution {
    Map<String, Integer> attacked = new HashMap<>();
    Map<String, HashSet<String>> attack = new  HashMap<>();
    Set<String> stopList = new HashSet<>();
    Map<String, Integer> idIndex = new HashMap<>();
    
    
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        for(int i=0; i<id_list.length; i++){
            attack.put(id_list[i], new HashSet<>());
            idIndex.put(id_list[i], i);
        }
        int [] result = new int[id_list.length];
        
        Set<String> temp = new  HashSet<>();
        for(int i=0; i<report.length; i++){
            temp.add(report[i]);
        }
        report = temp.toArray(new String[temp.size()]);
        
        //1. report로 생신 "muzi frodo"
        for(int i=0; i<report.length; i++){
            String rep [] = report[i].split(" ");
            attacked.put(rep[1], attacked.getOrDefault(rep[1],0)+1);
            attack.get(rep[0]).add(rep[1]);
        }
        //2. 정지자 목록 갱신
        for( Map.Entry<String, Integer> entry : attacked.entrySet() ){
            
            // System.out.println("공격 당한 "+entry.getKey()+" 이 "+ entry.getValue()+"번");
            if(entry.getValue() >= k) {
                stopList.add(entry.getKey());
            }
        }
        int resIdx=0;
        //3. 메일 횟수 갱신
        for(Map.Entry<String, HashSet<String>> entry : attack.entrySet() ){
            String id = entry.getKey();
            int cnt =0;
            for(String person : entry.getValue()){
                if(stopList.contains(person)){
                    cnt++;
                }
            }
            
            result[idIndex.get(id)] = cnt;
        }
        
        return result;
    }
}