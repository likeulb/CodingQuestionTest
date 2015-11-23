package solution;

public class Searching {
	
	public static int woodCut(int[] L, int k) {
        // write your code here
        
        if(L==null||L.length==0) return 0;
        
        
        int left=0,right=Integer.MAX_VALUE;
        while(left+1<right){
            int mid = left+(right-left)/2;
            if(mid<=0) return 0;
            int num = numOfWood(L, mid);
            if(num<k){
                right=mid;
            }
            else{
                left=mid;
            }
        }
        return left;
    }
    public static int numOfWood(int[] L, int len){
        int num=0;
        for(int i:L){
            num+=i/len;
        }
        return num;
    }
    
    public int median(int[] nums) {
        // write your code here
        if(nums==null||nums.length==0)
            return -1;
        int n=nums.length;
        if(n%2==1){
            return findKth(nums, n/2+1, 0, n-1);
        }
        else{
            return findKth(nums, n/2,0,n-1);
        }
    }
    public int findKth(int[] nums, int k, int left, int right){
        int p = nums[left];
        int l=left+1, r=right;
        while(l<=r){
            while(l<=r&&nums[l]<p){
                l++;
            }
            while(l<=r&&nums[r]>=p){
                r--;
            }
            if(l<r) swap(nums, l, r);
           
        }
        swap(nums, left, r);
        if(r+1==k) return nums[l];
        
        else if(r+1>k)
            return findKth(nums, k, left, l);
        else
            return findKth(nums, k, l+1, right);
    }
    public void swap(int[] nums, int left, int right){
        int tmp = nums[left];
        nums[left]=nums[right];
        nums[right] = tmp;
    }

    public static void main(String[] args){
    	int[] a = {4,5,1,2,3};
    	Searching Test = new Searching();
    	Test.median(a);
    }

}
