class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int time = 0;

        m = edit(m);

        for (int inx = 0; inx < musicinfos.length; inx++) {

            String[] info = musicinfos[inx].split(",");

            int start = (60 * Integer.parseInt(info[0].substring(0, 2)) + Integer.parseInt(info[0].substring(3)));
            int end = (60 * Integer.parseInt(info[1].substring(0, 2)) + Integer.parseInt(info[1].substring(3)));
            int t = end - start;

            if (t > time) { //0
                info[3] = edit(info[3]);
                StringBuffer sb = new StringBuffer();
                for (int jnx = 0; jnx < t; jnx++) { //재생시간 t, 0~재생시간 
                    sb.append(info[3].charAt(jnx % (info[3].length()))); //wow,,, 나머지 기호를 사용해서 길이만큼 이어 붙인다. 
                }
                if (sb.toString().indexOf(m) >= 0) { //멜로디가 존재한다면
                    answer = info[2]; //제목을 answer에 넣고
                    time = t; //time에 재생시간를 대입.
                }
            }
        }

        return answer;
    }

    public String edit(String m) {

        m = m.replaceAll("C#", "V");
        m = m.replaceAll("D#", "W");
        m = m.replaceAll("F#", "X");
        m = m.replaceAll("G#", "Y");
        m = m.replaceAll("A#", "Z");

        return m;
    }
}