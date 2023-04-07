import java.util.*;
class Solution {
  
    class Song implements Comparable<Song>{
        int idx, playCnt;
        @Override
        public int compareTo(Song other){
            if(this.playCnt != other.playCnt){
                return other.playCnt - this.playCnt;
            }else{
                return this.idx - other.idx;
            }
        }
        public Song(int idx, int playCnt){
            this.idx = idx;
            this. playCnt = playCnt;
        }
    }
    
    class Genre implements Comparable<Genre>{
        String name;
        List<Song> songs;
        int playCnt;
        
        public Genre(String name, List<Song> songs){
            this.name = name;
            this.songs = songs;
            this.playCnt =0;
            this.calPlayCnt();

        }
        public void calPlayCnt(){
            for(Song s : songs){
                playCnt += s.playCnt;
            }
        }
        public List<Integer> getTopSongs(){
            Collections.sort(songs);
            List<Integer> list = new ArrayList<>();
            int idx = 0;
            while(idx < songs.size() && idx <2){
                list.add(songs.get(idx++).idx);
            }
            return list;
        }
        @Override
        public int compareTo(Genre other){
            return other.playCnt - this.playCnt;
        }
        
    }
    public List<Integer> solution(String[] genres, int[] plays) {

        List<Genre> genList = new ArrayList<>();
        
        HashMap<String, List<Song>> hm = new HashMap<>();
        
        //1. hm 채운다.
        for(int i=0; i<plays.length; i++){
            if(hm.containsKey(genres[i])){
                hm.get(genres[i]).add(new Song(i, plays[i]));
            }else{
                List<Song> list = new ArrayList<>();
                list.add(new Song(i, plays[i]));
                hm.put(genres[i], list);
            }
        }
        
        //2. hm 순회하며 genList 에 넣는다.
        for(String key : hm.keySet()){
            genList.add(new Genre(key, hm.get(key)));
        }
        
        //3. 장르 정렬
        Collections.sort(genList);
        //4. 정답 출력
        int idx = 0;
        List<Integer> answer = new ArrayList<>();
        for(Genre gen : genList){
            List<Integer> two = gen.getTopSongs();
            for( Integer i : two ){
                answer.add(i);
            }
        }
        
        return answer;
    }
}