package prepare;

import java.util.*;

public class CountOfRangeSum327 {
	public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums==null||nums.length==0) return 0;
        int[] sum=new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            sum[i+1]=sum[i]+nums[i];
        }
        return countMerge(sum,lower,upper,0,sum.length-1);
    }
    public int countMerge(int[] sum, int lower, int upper, int left, int right){
        if(left>=right){
            return 0;
        }
        int mid=left+(right-left)/2;
        int count = countMerge(sum,lower,upper,left,mid)+countMerge(sum,lower,upper,mid+1,right);
        int i=mid+1, j=mid+1;
        int k=mid+1;
        int[] cache = new int[right-left+1];
        int index=0;
        for(int start=left;start<=mid;start++){
            while(i<=right&&sum[i]-sum[start]<lower) i++;
            while(j<=right&&sum[j]-sum[start]<=upper) j++;
            count+=j-i;
            while(k<=right&&sum[k]<sum[start]) cache[index++]=sum[k++];
            cache[index++]=sum[start];
        }
        System.arraycopy(cache,0,sum,left,index);
        return count;
    }
    
    public int countRangeSumSegmentTree(int[] nums, int lower, int upper) {
        if(nums==null||nums.length==0) return 0;
        HashSet<Long> set = new HashSet<>();
        long sum=0;
        for(int i:nums){
            sum+=(long)i;
            set.add(sum);
        }
        Long[] sums= set.toArray(new Long[0]);
        Arrays.sort(sums);
        SegmentTreeNode root=buildTree(sums,0,sums.length-1);
        int res=0;
        for(int i=nums.length-1;i>=0;i--){
            updateTree(root,sum);
            sum-=nums[i];
            res+=getCount(root,(long)lower+sum, (long)upper+sum);
        }
        return res;
    }
    public SegmentTreeNode buildTree(Long[] num, int left, int right){
        if(left>right) return null;
        SegmentTreeNode root=new SegmentTreeNode(num[left],num[right]);
        if(left==right) return root;
        int mid = left+(right-left)/2;
        root.left=buildTree(num,left,mid);
        root.right=buildTree(num,mid+1,right);
        return root;
    }
    public void updateTree(SegmentTreeNode p, long val){
        if(p==null) return;
        if(val<p.min||val>p.max) return;
        p.count++;
        updateTree(p.left,val);
        updateTree(p.right,val);
    }
    public int getCount(SegmentTreeNode p, long lower, long upper){
        if(p==null) return 0;
        if(p.min>upper||p.max<lower) return 0;
        if(p.min>=lower&&p.max<=upper) return p.count;
        return getCount(p.left, lower, upper)+getCount(p.right,lower,upper);
    }
    class SegmentTreeNode{
        SegmentTreeNode left,right;
        int count;
        long min,max;
        SegmentTreeNode(long min, long max){
            this.min=min;
            this.max=max;
        }
    }
    public int countRangeSumBinaryIndexTree(int[] nums, int lower, int upper) {
    	if(nums==null||nums.length==0) return 0;
        HashSet<Long> set = new HashSet<>();
        long sum=0;
        for(int i:nums){
            sum+=(long)i;
            set.add(sum);
        }
        List<Long> sums= new ArrayList<>(set);
        Collections.sort(sums);
        int[] tree=new int[sums.size()+1];
        
        int res=0;
        for(int i=nums.length-1;i>=0;i--){
        	updateBinaryIndexTree(tree,Collections.binarySearch(sums, sum),1);
        	sum-=nums[i];
        	res+=queryBinaryIndexTree(tree, Collections.binarySearch(sums, sum+upper));
        	res-=queryBinaryIndexTree(tree, Collections.binarySearch(sums,sum+lower-1));
        	
        }
        return res;
    }
    public int queryBinaryIndexTree(int[] tree, int index){
    	int res=0;
    	for(;index>0;index-=index&(-index)){
    		res+=tree[index];
    	}
    	return res;
    }
    public void updateBinaryIndexTree(int[] tree, int index, int num){
    	for(;index<tree.length;index+=index&(-index)){
    		tree[index]+=num;
    	}
    }
    
    public static void main(String[] args){
    	CountOfRangeSum327 test = new CountOfRangeSum327();
    	int[] a={-2,5,-1};
    	System.out.println(test.countRangeSumBinaryIndexTree(a, -2, 2));
    }
}
