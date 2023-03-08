import java.util.*;
class Solution {
    class Value{
        String val;
        int g_id;
        public Value(String val, int g_id){
            this.val = val;
            this.g_id = g_id;
        }
        @Override
        public String toString(){
            return "( "+ val+" , "+g_id+")";
        }
    }
    public int group_cnt=1;
    public Value [][] map = new Value[51][51];
    public ArrayList<String> ans = new ArrayList<>();
    
    public void alterValueOfGroup(int groupId, String value, int toGroupId){
        for(int i=1; i<=50; i++){
            for(int j=1; j<=50; j++){
                if(map[i][j].g_id == groupId){
                    map[i][j].g_id = toGroupId;
                    map[i][j].val = value;
                }
            }
        }
    }
    
    public void update(int r, int c, String value ){
        map[r][c].val = value;
        if(map[r][c].g_id !=0){
            //그 그룹의 값을 다 바꿔주어야 한다. 
            alterValueOfGroup(map[r][c].g_id, value,map[r][c].g_id);
        }
    }
    public void update(String val1, String val2){
        for(int i=1; i<=50; i++){
            for(int j=1; j<=50; j++){
                if(map[i][j].val.equals(val1)){
                    map[i][j].val = val2;
                }
            }
        }         
    }
    public void merge(int r1, int c1, int r2, int c2){ //2,3 3,3
        if(r1==r2 && c1==c2) return;
        //이전에 그룹에 속해있지 않다면 새로운 그룹으로 만들어주고,
        //이미 그룹에 속해있다면 해당 그룹에 포함. 
        //** 병합 예외
        String willValue="EMPTY";
        int newGroup = group_cnt++;
        
        ArrayList<Integer> willAlterGroup = new ArrayList<>();
        if(map[r1][c1].g_id != 0) willAlterGroup.add(map[r1][c1].g_id); // 그룹에 속해있다면
        if(map[r2][c2].g_id != 0) willAlterGroup.add(map[r2][c2].g_id); // 그룹것들도 업데이트
        
        if(!map[r1][c1].val.equals("EMPTY")){
            willValue = map[r1][c1].val;
        }
        else if(!map[r2][c2].val.equals("EMPTY")){
            willValue = map[r2][c2].val;   
        }
        
        //새로운 그룹아이디로 변경
        map[r1][c1] = new Value(willValue, newGroup);
        map[r2][c2] = new Value(willValue, newGroup);
            
        //그룹에 속해있던 것들도 처리
        for( int g : willAlterGroup){
            for(int i=1; i<=50; i++){
                for(int j=1; j<=50; j++){
                    if(map[i][j].g_id==g){
                        map[i][j] = new Value(willValue, newGroup); 
                    }
                }
            }
        }
    }
    public void unmerge(int r,int c){
        //r,c만 빼고 group_id에 속한 모든 값 empty 처리
        //r,c의 group_id =0;
        int target_group_id = map[r][c].g_id;
        map[r][c].g_id=0;
        System.out.println("unmerge>> "+ target_group_id);
        if(target_group_id != 0 ) alterValueOfGroup(target_group_id,"EMPTY",0);
        
    }
    public void print(int r, int c){
        ans.add(map[r][c].val);
    }
    
    public String[] solution(String[] commands) {
        String[] answer = {};
        //초기화
        for(int i=1; i<=50; i++){
            for(int j=1; j<=50; j++){
                map[i][j]=new Value("EMPTY",0);
            }
        }
        for(String command : commands){
            String [] com = command.split(" ");
            switch(com[0]){
                case "UPDATE":
                    if(com.length==4){
                        update(Integer.parseInt(com[1]),Integer.parseInt(com[2]),com[3]);
                    }else{
                        update(com[1],com[2]);
                    }
                    break;
                case "MERGE":
                    merge(Integer.parseInt(com[1]),Integer.parseInt(com[2]),Integer.parseInt(com[3]),Integer.parseInt(com[4]));
                    break;
                case "UNMERGE":
                    unmerge(Integer.parseInt(com[1]), Integer.parseInt(com[2]));
                    break;
                case "PRINT":
                    print( Integer.parseInt(com[1]), Integer.parseInt(com[2]) );
                    break;
            }//switch
        }//for
        return ans.toArray(new String[ans.size()]);
    }
}