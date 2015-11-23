package solution;

import java.util.Arrays;


public class SegmentTree {
	int[] tree;
	int size;
	int[] arr;

	public SegmentTree(int[] nums) {
        if(nums==null||nums.length==0) return;
        size = nums.length;
        tree = new int[nums.length*3];
        arr = new int[nums.length];
        arr=Arrays.copyOf(nums, nums.length);
        buildTree(nums, 0, nums.length-1, 0);
    }
    public int buildTree(int[] nums, int left, int right, int index){
        if(left==right){
            tree[index]=nums[left];
            return nums[left];
        }
        int mid = left+(right-left)/2;
        int first = buildTree(nums, left, mid, 2*index+1);
        int sec = buildTree(nums, mid+1, right, 2*index+2);
        tree[index] = first+sec;
        return tree[index];
    }

    void update(int i, int val) {
        if(tree==null||tree.length==0) return;
        int diff = val-arr[i];
        arr[i]=val;
        updateHelp(i,diff,0,size-1,0);
    }
    public void updateHelp(int i, int val, int left, int right, int index){
        if(i<left||i>right){
            return;
        }
        tree[index]+=val;
        if(left!=right){
            int mid = left+(right-left)/2;
            updateHelp(i,val, left, mid, index*2+1);
            updateHelp(i,val, mid+1, right, index*2+2);
        }
    }

    public int sumRange(int i, int j) {
        if(tree==null||tree.length==0) return -1;
        return sumHelp(i,j,0,size-1,0);
    }
    public int sumHelp(int i, int j, int left, int right,int index){
        if(i<=left&&j>=right){
            return tree[index];
        }
        else if(i>right||j<left)
            return 0;
        int mid = left+(right-left)/2;
        return sumHelp(i,j,left,mid, index*2+1)+sumHelp(i,j,mid+1,right, index*2+2);
    }
    
    public static void main(String[] args){
    	int[] a = {9,-8};
    	SegmentTree test = new SegmentTree(a);
    	test.update(0, 3);
    	
    	test.sumRange(1, 1);
    	test.sumRange(0, 1);
    	test.update(1, -3);
    	test.sumRange(0, 1);
    }

}
