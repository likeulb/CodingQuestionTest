package solution;

public class quickSelect {
	public static int quickSelect(int[] A, int k){
		return quick(A, 0, A.length-1, k);
	}
	private static int quick(int[] A, int l, int r, int k){
		int index = partition(A, l, r);
		if(index==k-1)
			return A[index];
		else if(index<k-1)
			return quick(A,index+1, r, k);
		else
			return quick(A,l, index-1,k);
	}
	
	public static int quickSelectNotRecursive(int[] A, int k){
		int l=0, r=A.length-1;
		k=k-1;
		while(l<=r){
			int index = partition(A,l,r);
			if(index==k) return A[index];
			else if(index<k) l=index+1;
			else r = index-1;
		}
		return -1;
	}
	
	private static int partition(int[] A, int l, int r){
		int p = A[l];
		int i=l+1,s=l;
		while(i<=r){
			if(A[i]<p){
				s++;
				int tmp = A[s];
				A[s]=A[i];
				A[i]=tmp;
			}
			i++;
		}
		A[l]=A[s];
		A[s]=p;
		return s;
		
	}
	
	public static void main(String[] args){
		int[] list = { 3, 5,5,5,5, 8,9,0 ,5};
        int k = 9;
        int x = quickSelect(list,k);
        int y = quickSelectNotRecursive(list, k);
        System.out.println(x);
        System.out.println(y);
	}
}
