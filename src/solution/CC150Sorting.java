package solution;

import java.util.Arrays;
import java.util.Comparator;

public class CC150Sorting {
	
	public void mergeSort(int[] arr){
		//int[] helper = new int[arr.length];
		mergesort(arr, 0, arr.length-1);
	}
	
	public void mergesort(int[] arr, int left, int right){
		if(left<right){
			int mid = left+(right-left)/2;
			mergesort(arr, left, mid);
			mergesort(arr, mid+1, right);
			merge(arr, left, mid, right);
			
		}
	}
	public void merge(int[] arr, int left, int mid, int right){
		int[] helper = new int[right-left+1];
		int j=0;
		for(int i=left;i<=right;i++){
			helper[j++]=arr[i];
		}
		int i=0;
		j=mid-left+1;
		int index = left;
		while(i<=mid-left&&j<=right-left){
			if(helper[i]<=helper[j]){
				arr[index++]=helper[i++];
			}
			else{
				arr[index++]=helper[j++];
			}
		}
		while(i<=mid-left){
			arr[index++]=helper[i++];
		}
		
		
	}
	
	public void quickSort(int[] arr){
		quicksort(arr, 0, arr.length-1);
	}
	public void quicksort(int[] arr, int left, int right){
		int index = partition(arr, left, right);
		//int index = LomutoPartition(arr, left, right);
		if(left<index-1)
			quicksort(arr, left, index-1);
		
		if(index<right)
			quicksort(arr, index, right);
	
		
	}
	public int partition(int[] arr, int left, int right){
		int p = arr[(left+right)/2];
		while(left<=right){
			while(left<=right&&arr[left]<p){  //it has to be smaller, then greater, not equal
				left++;   //if equal, when there are same elements, there will have infinit loop
			}
			while(left<=right&&arr[right]>p){
				right--;
			}
			if(left<=right){
				int tmp = arr[left];
				arr[left]=arr[right];
				arr[right] = tmp;
				left++;
				right--;
				
			}
		}
		return left;
	}
	
	public int LomutoPartition(int[] arr, int left, int right){
		int p = arr[left];
		int s = left;
		for(int i=left+1;i<=right;i++){
			if(arr[i]<p){
				s++;
				swap(arr, s, i);
			}
		}
		swap(arr, left, s);
		return s+1;
		
	}
	
	public void swap(int[] arr, int left, int right){
		int tmp = arr[left];
		arr[left]=arr[right];
		arr[right]=tmp;
	}
	
	public class sortAnagram implements Comparator<String>{
		public String sortChars(String s){
			char[] c=s.toCharArray();
			Arrays.sort(c);
			return new String(c);
		}
		public int compare(String s1, String s2){
			return sortChars(s1).compareTo(sortChars(s2));
		}
	}
	
	
	
	public static void main(String[] args){
		CC150Sorting Test = new CC150Sorting();
		int[] test2 = {3,1,3,2,3};
		//int[] test2 = {3, 5,5,5,5, 8,9,0 ,5};
		//int[] test = {2,35,7,55};
		//Test.mergeSort(test2);
		Test.quickSort(test2);
		for(int i=0;i<test2.length;i++){
			System.out.print(test2[i]);
			System.out.print(" ");
		}
		System.out.println("");
		String[] ss = {"abc","cdc","ccd","acb"};
		Arrays.sort(ss, Test.new sortAnagram());
		for(String s:ss){
			System.out.println(s);
		}
		
	}

}
