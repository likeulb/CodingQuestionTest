package googleOnSite;

import java.util.*;

public class BinarySearch {
	public int findFirstGreater(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=-1;
		int right = A.length;
		while(left+1<right){
			int m = left+(right-left)/2;
			if(A[m]==target){
				return m;
			}
			else if(A[m]>target){
				right=m;
			}
			else
				left=m;
		}
		//if(left+1<A.length&&A[left+1]==target) return left+1;
		return right<A.length?A[right]:-1;
	}
	public int findFirstSmaller(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=-1;
		int right = A.length;
		while(left+1<right){
			int m = left+(right-left)/2;
			if(A[m]==target){
				return m;
			}
			else if(A[m]>target){
				right=m;
			}
			else
				left=m;
		}
		//if(right-1>=0&&A[right-1]==target) return right-1;
		return left>=0?A[left]:-1;
		
	}
	public int findClosest(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=-1;
		int right = A.length;
		int min = Integer.MAX_VALUE;
		int index = -1;
		while(left+1<right){
			int m = left+(right-left)/2;
			if(Math.abs(A[m]-target)<min){
				index=m;
				min = Math.abs(A[m]-target);
			}
			if(A[m]==target){
				return m;
			}
			
			else if(A[m]>target){
				
				right=m;
			}
			else
				
				left=m;
		}
		return A[index];
		
	}
	public int findWithinRange(int[] A, int lower, int higher){
		if(A==null||A.length==0) return 0;
		if(lower>A[A.length-1]||higher<A[0]) return 0;
		int left=0;
		int right = A.length-1;
		int index1=0;
		int index2=0;
		while(left+1<right){
			int mid = left+(right-left)/2;
			
			if(A[mid]<lower){
				left=mid;
			}
			else right=mid;
		}
		index1=right;
		left=0;
		right = A.length-1;
		while(left+1<right){
			int mid = left+(right-left)/2;
			
			if(A[mid]<=higher){
				left=mid;
			}
			else right=mid;
		}
		
			index2=left+1;
		
		
		return index2-index1+1;
	}
	public static void main(String[] args){
		int[] a = {0,0,1,3,4,5};
		BinarySearch test = new BinarySearch();
		System.out.println(test.findWithinRange(a, 3, 6));
		System.out.println(test.findFirstGreater(a, 2));
		System.out.println(test.findFirstSmaller(a, 2));
		System.out.println(test.findClosest(a, 15));
	}
}
