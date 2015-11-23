package googleOnSite;

public class ArrayQuestion {
	//找超过n/4的点
	public int find(int[] A){
		int n=A.length;
		int a = A[0];
		int b = A[n/4];
		int c = A[n/2];
		int d = A[n*3/4];
		if(a==b) return a;
		if(b==c) return b;
	if(b!=c){
			int range = seachRange(A, b);
			if(range>n/4) return b;
	}
	if(c==d) return c;
	if(c!=d){
		int range = seachRange(A, c);
		if(range>n/4) return c;
	}
	return d;
	}
	public int seachRange(int[] A, int target){
		int left= 0, right=A.length-1;
		while(left<right){
			int mid = left+(right-left)/2;
			if(A[mid]<target){
			left=mid+1;
	}
	else{
		right = mid;
	}
	}
	int leftside = left;
	left=0;
	right=A.length-1;
	while(left<right-1){
		int mid=left+(right-left)/2;
		if(A[mid]>target){
		right=mid-1;
	}
	else{
		left = mid;
	}
	}
	int rightside = right;
	return rightside-leftside+1;
	}
	
	public static void main(String[] args){
		ArrayQuestion Test = new ArrayQuestion();
		int[] a = {1,2,3,5,6,7,8,8,8,8,8,9,9,9,9,9,9,9,9,9};
		System.out.println(Test.find(a));
	}
}
