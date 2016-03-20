package solution;

public class MaxNumber321 {
	
	    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        int len1=nums1.length;
	        int len2=nums2.length;
	        int[] result = new int[k];
	        for(int len=Math.max(0,k-len2);len<=k&&len<=len1;len++){
	            int[] arr1=maxArray(nums1,len);
	            int[] arr2=maxArray(nums2,k-len);
	            int[] cur=merge(arr1,arr2,k);
	            if(greater(cur,0,result,0)) result=cur;
	        }
	        return result;
	    }
	    public int[] maxArray(int[] nums, int k){
	        int[] res=new int[k];
	        int j=0;
	        for(int i=0;i<nums.length;i++){
	            while(j>0&&nums.length-i+j>k&&res[j-1]<nums[i]){
	                j--;
	            }
	            if(j<k){
	                res[j++]=nums[i];
	            }
	        }
	        return res;
	    }
	    public int[] merge(int[] arr1, int[] arr2, int k){
	        int i=0,j=0,m=0;
	        int[] res = new int[k];
	        while(m<k){
	            if(greater(arr1,i,arr2,j)){
	                res[m++]=arr1[i++];
	            }
	            else{
	                res[m++]=arr2[j++];
	            }
	        }
	        return res;
	    }
	    public boolean greater(int[] arr1, int i, int[] arr2, int j){
	        while(i<arr1.length&&j<arr2.length&&arr1[i]==arr2[j]){
	            i++;
	            j++;
	        }
	        return j==arr2.length||(i<arr1.length&&arr1[i]>arr2[j]);
	    }
	    
	    public static void main(String[] args){
	    	int[] a={4,9,5};
	    	int[] b={8,7,4};
	    	MaxNumber321 test=new MaxNumber321();
	    	test.maxNumber(a, b, 3);
	    }
	    
	}


