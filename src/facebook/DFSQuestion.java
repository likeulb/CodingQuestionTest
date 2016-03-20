package facebook;

import java.util.*;

public class DFSQuestion {
	//Permutation using swap, no duplicates in the array
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null||nums.length==0) return result;
        Integer[] arr=new Integer[nums.length];
        for(int i=0;i<nums.length;i++){
            arr[i]=nums[i];
        }
        helper(arr,result,0);
        return result;
    }
    public void helper(Integer[] arr, List<List<Integer>> result, int index){
        if(index>=arr.length){
            result.add(new ArrayList<>(Arrays.asList(arr)));
            return;
        }
        for(int i=index;i<arr.length;i++){
            swap(arr,index,i);
            helper(arr,result,index+1);
            swap(arr,index,i);
            
        }
    }
    public void swap(Integer[] arr, int i, int j){
        Integer a=arr[i];
        arr[i]=arr[j];
        arr[j]=a;
    }
	
	public static void main(String[] args){
		int[] n={0,1};
		DFSQuestion test=new DFSQuestion();
		List<List<Integer>> res=test.permute(n);
		for(List<Integer> a:res){
			for(int b:a){
				System.out.println(b+" ");
			}
			System.out.println("");
		}
		Integer[] arr=new Integer[3];
		arr[0]=0;
		arr[1]=1;
		arr[2]=2;
		List<Integer> a=Arrays.asList(arr);
		for(int i:a){
			System.out.println(i);
		}
	}

}
