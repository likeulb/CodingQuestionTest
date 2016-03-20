package solution;

import java.util.Arrays;

public class WiggleSort {
	public void wiggleSort(int[] nums) {
        if(nums==null||nums.length<=1) return;
        Arrays.sort(nums);
        int len=nums.length;
        int mid=len/2;
        int[] res = new int[len];
        System.arraycopy(nums, 0, res, 0, res.length);
        int j=len-1;
        if(j%2==1){
            j--;
        }
        for(int i=0;i<len-mid;i++){
            nums[j]=res[i];
            j-=2;
        }
        j=len-1;
        if(j%2==0) j--;
        for(int i=len-mid;i<len;i++){
            nums[j]=res[i];
            j-=2;
        }
       
    }
	public static void main(String[] args){
		WiggleSort test=new WiggleSort();
		int[] a={1,5,1,1,6,4};
		test.wiggleSort(a);
		
	}
}
