package ms;

public class ArrayRelated {
	//Partition Positive & Negative Numbers。keep the relative order
	static void partition(int[] arr){
		partitionHelper(arr,0,arr.length-1);
		for(int k:arr){
			System.out.print(k+" ");
		}
		System.out.println("");
	}
	static void partitionHelper(int[] arr, int left,int right){
		if(left>=right) return;
		int mid=left+(right-left)/2;
		partitionHelper(arr,left,mid);
		partitionHelper(arr,mid+1,right);
		int i=left;
		while(i<=mid&&arr[i]<0){
			i++;
		}
		int j=right;
		while(j>mid&&arr[j]>=0){
			j--;
		}
		if(i<=mid&&j>mid){
			reverse(arr,i,mid);
			reverse(arr,mid+1,j);
			reverse(arr,i,j);
		}
		
	}
	static void reverse(int[] arr, int i, int j){
		if(i==j) return;
		while(i<j){
			int tmp=arr[i];
			arr[i]=arr[j];
			arr[j]=tmp;
			i++;
			j--;
		}
	}
	static void swap(int[] arr, int i, int j){
		int tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
	static int[] maximumSubarraySum(int[] arr){
		int local=0;
		int result=Integer.MIN_VALUE;
		int start=0,end=0;
		int curstart=0,curend=0;
		for(int i=0;i<arr.length;i++){
			if(local+arr[i]<=arr[i]){
				local=arr[i];
				curstart=i;
				curend=i;
			}
			else{
				local+=arr[i];
				curend=i;
			}
			if(result<local){
				result=local;
				start=curstart;
				end=curend;
			}
			
		}
		System.out.println(start+" "+end);
		return new int[]{start,end};
	}
	public static void main(String[] args){
		int[] a={-1,2,3,-4,0,5,-2,0,4,-1};
		//ArrayRelated.partition(a);
		ArrayRelated.maximumSubarraySum(a);
	}
}
