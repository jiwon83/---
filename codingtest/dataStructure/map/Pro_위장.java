import java.util.*;
class Solution {
    final int max_cate_idx =30;
    ArrayList<String> [] cate_graph = new ArrayList [max_cate_idx+1];    //1부터 사용
    Map<String, Integer> elements = new HashMap<>();
    boolean [] visit;
    Map<String, Integer> cate = new HashMap<>();
    int ans=0;
    int idx_cate=0;
    int idx_element=0;
    String [] select;

    
    public void combi(int k, String [] select){
        if(k == idx_cate+1){
            int notFill = 0; //내가 코드 짤 때 신경쓰는 부분 (구현 목적에 부합하는 명시적인 변수명과 흐름인가.index 가 1로 시작할 때 for(int i=1)로 명시, i와 j보다는 r와 c으로 ) + 주석
//             for(int i=1; i<=idx_cate; i++){
                
//                 if(select[i] == null || select[i].length()==0) notFill++;
//             }
            //다 0이 아니라면 ans++;
            if(notFill < idx_cate) {
                System.out.println(Arrays.toString(select));
                ans++;
            }
        }else{
            combi(k+1, select);//비포함
            for(String c : cate_graph[k]){
                // visit[elements.get(c)] = true; //각 카테고리 마다 선택하기 때문에 필요 X
                select[k] = c;
                combi(k+1, select);
                select[k] = "";
            }
         
        }
    }
    public int solution(String[][] clothes) {
        
        for(int i=1; i<=max_cate_idx ; i++){
            cate_graph[i] = new ArrayList<>();
        }
         //elements cate cate_graph를 채운다.
        for(int i=0; i< clothes.length; i++){
            String ele = clothes[i][0]; String cat = clothes[i][1]; 
            if(!cate.containsKey(cat)){ //1. cate가 이미 존재하지 않는다면
                cate.put(cat, ++idx_cate);
            }
            elements.put(ele, ++idx_element);
            cate_graph[cate.get(cat)].add(ele);
        }
      
        //visit, select 초기화
        // visit = new boolean[idx_element+1];
        select = new String [idx_cate+1];
        
        combi(1, select);
        int answer = 0;
        return ans-1;
    }
}