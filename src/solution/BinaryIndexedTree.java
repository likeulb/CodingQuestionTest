package solution;

import java.util.Arrays;

public class BinaryIndexedTree {
	int[] tree;
	int[][] tree2;

    public BinaryIndexedTree(int[] nums) {
        if(nums==null||nums.length==0) return;
        tree=new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            updateInitial(i,nums[i]);
        }
    }
    void updateInitial(int i, int num){
        if(tree.length<=1) return;
        for(int k=i+1;k<tree.length;k+=(k&(-k))){
            tree[k]+=num;
        }
    }
    
    void update(int i, int val) {
        if(tree.length<=1) return;
        int old = sumRange(i,i);
        
        updateHelper(i,val-old);
    }
    void updateHelper(int i, int v){
        if(tree.length<=1) return;
        for(int k=i+1;k<tree.length;k+=(k&(-k))){
            tree[k]+=v;
        }
    }

    public int sumRange(int i, int j) {
        if(tree.length<=1) return 0;
        return sumHelper(j+1)-sumHelper(i); //sum range index to be processed in index binary tree is j+1 - j
    }
    public int sumHelper(int i){
        if(tree.length<=1) return 0;
        int sum=0;
        for(int k=i;k>0;k-=(k&(-k))){
            sum+=tree[k];
        }
        return sum;
    }
    
    

    public BinaryIndexedTree(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) return;
        tree2=new int[matrix.length+1][matrix[0].length+1];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                update(i,j,matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int old=sumRegion(row,col,row,col);
        int diff = val-old;
       // System.out.println(diff);
        for(int i=row+1;i<tree2.length;i+=(i&(-i))){
            for(int j=col+1;j<tree2[0].length;j+=(j&(-j))){
                tree2[i][j]+=diff;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(tree2.length==1||tree2[0].length==1) return 0;
        return sum(row2+1,col2+1)-sum(row2+1,col1)-sum(row1,col2+1)+sum(row1,col1);
    }
    public int sum(int row,int col){
        int result=0;
        for(int i=row;i>0;i-=(i&(-i))){
            for(int j=col;j>0;j-=(j&(-j))){
                result+=tree2[i][j];
            }
        }
        return result;
    }
    //count range sum, leetcode 327
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums==null||nums.length==0) return 0;
        long[] sum = new long[nums.length+1];
        for(int i=0;i<nums.length;i++){
            sum[i+1]=sum[i]+nums[i];
        }
        Arrays.sort(sum);
        long s=0;
        int[] tree = new int[sum.length+1];
        add(tree, Arrays.binarySearch(sum,0));
        int count = 0;
        for(int i:nums){
            s+=i;
            count+=getSum(tree,Arrays.binarySearch(sum,s-lower))-getSum(tree,Arrays.binarySearch(sum,s-upper));
            add(tree,Arrays.binarySearch(sum,s));
        }
        return count;
    }
    public void add(int[] tree, int index){
        for(int i=index+1;i<tree.length;i+=i&(-i)){
            tree[i]++;
        }
    }
    public int getSum(int[] tree, int index){
        int result=0;
        for(int i=index+1;i>0;i-=i&(-i)){
            result+=tree[i];
        }
        return result;
    }
    
    public static void main(String[] args){
    	int[] a ={1,3,5};
    	int[][] b={{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
    	BinaryIndexedTree test = new BinaryIndexedTree(b);
    	test.update(3,2,2);
    	int[] t = {-2,5,1};
    	test.countRangeSum(t, -2, 2);
    	System.out.println(Arrays.binarySearch(t, 3));
    	System.out.println(test.sumRegion(2,1,4,3));
    }
}
