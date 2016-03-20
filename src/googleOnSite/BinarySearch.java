package googleOnSite;

import java.util.*;

public class BinarySearch {
	//如果有target(只在array里出现一次), 返回它的index, 如果不存在这样的value, 返回第一个比它大的value, 如果没有这样的value, 返回-1
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
	//重写上面的function, left即指向第一个比target大的值，需要检查下是不是越界
	public int findFirstGreater1(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=0, right=A.length-1;
		while(left<=right){
			int mid=left+(right-left)/2;
			if(A[mid]==target) return mid;
			else if(A[mid]<target){
				left=mid+1;
			}
			else right=mid-1;
		}
		if(left>=A.length) return -1;
		return A[left];
	}
	
	//返回array里第一个大于等于target的index,如果target在array里出现好几次，返回index最大的那个
	public int findFirstGreater2(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=0, right=A.length-1;
		int index=-1;
		while(left<=right){
			int mid = left+(right-left)/2;
			if(A[mid]==target){
				index=mid; //记录跟target相等的位置
				left=mid+1;
			}
			else if(A[mid]<target){ //left指向大于target的index
				left=mid+1;
			}
			else right=mid-1;
		}
		if(index!=-1) return index;
		if(left>=A.length) return -1; //注意判断left是不是超出范围
		return left;
	}
	//找到第一个比target大的index位置
	public int findFirstGreaterInsertPosition(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=0, right=A.length-1;
		while(left<=right){
			int mid = left+(right-left)/2;
			if(A[mid]<=target){
				left=mid+1;
			}
			else{
				right=mid-1;//left<=right, right可以取mid-1，left指向有效的结果
			}
		}
		return left; //有可能left到了a.length的位置
	}
	//找到最后一个value==target的index, 如果没有，返回-1
	public int findLastTarget(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=0, right=A.length-1;
		while(left<right){
			int mid = left+(right-left+1)/2;//后面要进行left=mid, 可能会死循环，重写mid
			if(A[mid]<=target){
				left=mid; 
			}
			else{
				right=mid-1;
			}
		}
		if(A[right]==target) return right;//再看一下当前是不是target
		else return -1;
		
	}
	
	
	//找到第一个value==target的index, 如果没有，返回-1
	public int findFirstTarget(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=0, right=A.length-1;
		while(left<right){
			int mid = left+(right-left)/2;
			if(A[mid]>=target){
				right=mid; //因为处理的是right==mid不会有死循环的情况
			}
			else{
				left=mid+1;
			}
		}
		if(A[right]==target) return right;//再看一下当前是不是target
		else return -1;
		
	}
	//找到当前target, 返回index, 如果没有, 返回第一个小于它的value,如果没有，返回-1
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
	//重写上面的function,都假设只有一个mid时
	public int findFirstSmaller1(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=0, right=A.length-1;
		while(left<right){
			int mid = left+(right-left+1)/2;//防止left=mid造成死循环
			if(A[mid]==target)
				return mid;
			else if(A[mid]>target){
				right=mid-1;
			}
			else
				left=mid;
		}
		if(A[right]<=target) return A[right]; //最后的结果肯定是在array范围里的，再看看是不是最后结果
		return -1;
	}
	//找到第一个比target小的index, 如果没有，返回-1
	public int findFirstSmaller2(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=0, right=A.length-1;
		while(left<=right){
			int mid = left+(right-left)/2;
			if(A[mid]>=target){
				right=mid-1;
			}
			else{
				left=mid+1;
			}
		}
		if(right>=0) return right;
		else return -1;
	}
	
	//找到离最接近target的值
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
	//重写上面的function, 用一般的binary search
	public int findClosest1(int[] A, int target){
		if(A==null||A.length==0) return -1;
		int left=0, right=A.length-1;
		int diff = Integer.MAX_VALUE;
		int index=-1;
		while(left<=right){
			int mid = left+(right-left)/2;
			if(A[mid]==target) return mid;
			if(Math.abs(A[mid]-target)<diff){
				index=mid;
				diff=Math.abs(A[mid]-target);
			}
			if(A[mid]<target){
				left=mid+1;
			}
			else{
				right=mid-1;
			}
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
		int[] a = {0,1,7};
		BinarySearch test = new BinarySearch();
		
		//如果有target(只在array里出现一次), 返回它的index, 如果不存在这样的value, 返回第一个比它大的value, 如果没有这样的value, 返回-1
		//System.out.println(test.findFirstGreater(a, 4));
		//重写上面的function, left即指向第一个比target大的值，需要检查下是不是越界
		//System.out.println(test.findFirstGreater1(a, 4));
		
		//返回array里第一个大于等于target的index,如果target在array里出现好几次，返回index最大的那个
		//System.out.println(test.findFirstGreater2(a, 0));
		//找到第一个比target大的index位置
		//System.out.println(test.findFirstGreaterInsertPosition(a, 1));
		
		//找到最后一个value==target的index, 如果没有，返回-1
		//System.out.println(test.findLastTarget(a, 1));
		//找到第一个value==target的index, 如果没有，返回-1
		//System.out.println(test.findFirstTarget(a, 0));
		
		//找到当前target, 返回index, 如果没有, 返回第一个小于它的value,如果没有，返回-1
		//System.out.println(test.findFirstSmaller(a, 2));
		//重写上面的function，都假设target只出现一次
		//System.out.println(test.findFirstSmaller1(a, 2));
		//找到第一个比target小的index, 如果没有，返回-1
		//System.out.println(test.findFirstSmaller2(a, 6));
		
		//find the closes value to the target
		System.out.println(test.findClosest(a, 2));
		//用普通的binary search 做
		System.out.println(test.findClosest1(a, 2));
		
		//System.out.println(test.findWithinRange(a, 3, 6));
		
	}
}
