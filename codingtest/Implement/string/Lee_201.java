class Solution {
    public int pivotIndex(int[] nums) {
        //
        int N = nums.length;
        int [] left = new int [nums.length];
        int [] right = new int [nums.length];
        right[0] = nums[0];
        left[N-1] = nums[N-1];
        int L=nums[0], R=nums[N-1];
        for(int i=1; i<N ; i++){
            right[i] = right[i-1] + nums[i]; 
            
        }
        for(int i=N-2; i>=0 ; i--){
            left[i] = left[i+1] + nums[i];
        }
        
        System.out.println(Arrays.toString(right));
        System.out.println(Arrays.toString(left));
        for(int i=0; i<N; i++){
            if(left[i]==right[i]){
                return i;
            }
        }
        return -1;
    }
}