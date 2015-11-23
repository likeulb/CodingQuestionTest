package googleOnSite;

/*实现一个动态数组， 可以自动扩容那种， 用了loadfactor. */

public class DynamicArray {
	int[] arr;
	int threshold;
	int index=-1;
	DynamicArray(int n){
		arr=new int[n];
		threshold = (int)(n*0.6);
	}
	DynamicArray(int n, double factor){
		arr = new int[n];
		threshold = (int)(n*factor);
	}
	
	void add(int elem){
		if(index+1>=threshold){
			int[] newarray = new int[arr.length*2];
			System.arraycopy(arr, 0, newarray, 0, arr.length-1);
			arr=newarray;
			threshold*=2;
		}
		arr[++index]=elem;
	}
	
	public static void main(String[] args){
		DynamicArray Test = new DynamicArray(4);
		Test.add(1);
		Test.add(2);
		Test.add(3);
	}
}
