package solution;

public class SortColor {
	public void sortColors2(int[] colors, int k) {
        // write your code here
        int r=colors.length-1;
        for(int i=k;i>=1;i--){
            r=partition(colors, 0, r, i-1);
        }
    }
    public int partition(int[] colors, int left, int right, int p){
        int i=left, j=right;
        while(i<j){
            while(i<right&&colors[i]<=p) i++;
            while(j>0&&colors[j]>p) j--;
            if(i<j) {
                swap(colors, i, j);
            }
            
        }
        return i-1;
    }
    
    public void swap(int[] colors, int i, int j){
        int tmp=colors[i];
        colors[i]=colors[j];
        colors[j]=tmp;
    }
    public void sortColors(int[] nums) {
        if(nums==null||nums.length==0) return;
        int index0=0, index2=nums.length-1;
        for(int i=0;i<=index2;i++){
            while(i<index2&&nums[i]==2){
                swap(nums, i, index2);
                index2--;
            }
            while(i>index0&&nums[i]==0){
                swap(nums, i, index0);
                index0++;
            }
        }
    }
    public static void main(String[] args){
    	int[] a={1,1,3,3,3,3,3,3,3,3};
    	SortColor test=new SortColor();
    	test.sortColors2(a, 3);
    	int[] b = {0,1,0};
    	test.sortColors(b);
    }
}
