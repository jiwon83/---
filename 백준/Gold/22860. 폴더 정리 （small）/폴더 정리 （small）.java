import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
   static class Folder{
      String name;
      int childCnt; //file count;
      public Folder(String name, int cnt){
         this.name = name;
         this.childCnt = cnt;
      }
   }
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static StringTokenizer st;
   static StringBuilder sb = new StringBuilder();
   //126MB = 126*1000KB = 126*1000*1000
   static HashMap<String, List<String>> graphFolder = new HashMap<>();
   static HashMap<String, Set<String>> fileTypes = new HashMap<>();
   static HashMap<String, Integer> fileCnt = new HashMap<>();
   static HashMap<String, Boolean> folderVisit = new HashMap<>();
   static HashSet<String> folderNames = new HashSet<>();

   static int N, M, Q;

   static void input() throws IOException{

      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      for (int i = 0; i< N+M; i++){
         String [] tmp = br.readLine().split(" ");
         if (tmp[2].equals("1")){ //is folder
            folderNames.add(tmp[0]);
            folderNames.add(tmp[1]);
            addGraphFolder(tmp[0], tmp[1], graphFolder);
         }else{
            folderNames.add(tmp[0]);
            addFileTypes(tmp[0], tmp[1], fileTypes);
         }
      }

      for(String name: folderNames){
//         System.out.println("name: "+name);
//         System.out.println(graphFolder.get(name));
         if (!fileTypes.containsKey(name)) fileTypes.put(name, new HashSet<>());
//         System.out.println(fileTypes.get(name));
         if (!fileCnt.containsKey(name)) fileCnt.put(name,0);
//         System.out.println(fileCnt.get(name));
         folderVisit.put(name, false);
      }

      int Q = Integer.parseInt(br.readLine());
      for (int q = 0; q < Q; q++){
         String [] query = br.readLine().split("/");
         String folderName =  query[query.length-1];
         dfs(folderName);
         folderVisit.put(folderName, true);
         sb.append(fileTypes.get(folderName).size()).append(" ");
         sb.append(fileCnt.get(folderName)).append("\n");
      }

   }
   private static void dfs(String folder){
      if (folderVisit.get(folder)){
         return;
      }
      for(String childFolder : graphFolder.get(folder)){
         dfs(childFolder);
         folderVisit.put(childFolder, true);
         if (!fileTypes.containsKey(childFolder)) continue;
         for (String childFile : fileTypes.get(childFolder))
            fileTypes.get(folder).add(childFile);
         fileCnt.put(folder, fileCnt.getOrDefault(folder, 0)+ fileCnt.get(childFolder));
      }

   }

   private static void addGraphFolder(String par, String child, HashMap<String, List<String>> graphFolder) {
      if (!graphFolder.containsKey(par)) graphFolder.put(par, new ArrayList<>());
      if (!graphFolder.containsKey(child)) graphFolder.put(child, new ArrayList<>());
      graphFolder.get(par).add(child);
   }
   private static void addFileTypes(String folder, String file, HashMap<String, Set<String>> fileTypes){
      if (!fileTypes.containsKey(folder)) fileTypes.put(folder, new HashSet<>());
      fileTypes.get(folder).add(file);
      fileCnt.put(folder, fileCnt.getOrDefault(folder,0)+1);

   }
   public static void main(String[] args) throws IOException{
      input();
      System.out.println(sb);

   }
}