import java.util.*;
import java.io.*;
//System.out.println();
//System.out.prinltn(Arrays.toString(   ));
//System.out.prinltn(Arrays.deepToString(   ));
//for(int i=0; i< ; i++)
class Solution
{
    static int sum;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String args[]) throws Exception
	{
		int T=Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++)
		{
            sum=0;
            int K = Integer.parseInt(br.readLine());
            int N = (int)Math.pow(2, K); // leaf 노드의 갯수 = 2^ 이진트리의 depth
            int [] score = new int[N]; 
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<score.length; i++) score[i] = Integer.parseInt(st.nextToken());
            recur(K, 0, score.length-1, score);
            System.out.printf("#%d %d%n", t, sum);
     
		}
	}//main
    static int recur(int k, int sIdx, int eIdx, int [] score){
        if(k==0) return score[sIdx];
        else{
         	//2개로 나눈다.
            int sp = ( sIdx + eIdx )/2;
            int front = recur(k-1, sIdx, sp,score ); int back = recur( k-1, sp+1, eIdx, score);
            sum+= Math.abs( front - back ); //차를 합산
           // System.out.println(k+" 라운드 합산: "+ Math.abs( front - back ));
            //System.out.println(k+" 라운드 승: "+ Math.max( front,back ) );
            return Math.max( front , back ); //우승자를 return.
        }
    }
}