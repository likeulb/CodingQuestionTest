package yahoo;

public class Oncampus {
	public boolean findInSortedString(String[] arr, String target){
		if(arr==null||arr.length==0) return false;
		int left=0,right=arr.length-1;
		for(int i=0;i<target.length();i++){
			char cur = target.charAt(i);
			left = binarySearch(arr,left,right,cur,true,i);
			right = binarySearch(arr,left,right,cur,false,i);
			if(left==right){
				if(arr[left].equals(target)) return true;
				else return true;
			}
			if(left>right) return false;
		}
		return false;
	}
	public int binarySearch(String[] arr, int left, int right, char cur,boolean isLeft,int index){
		if(isLeft){
			while(left<right){
				int mid = left+(right-left+1)/2;
				if(arr[mid].charAt(index)<cur){
					left=mid;
				}
				else
					right=mid+1;
			}
		}
		else{
			while(left<right){
				int mid = left+(right-left)/2;
				if(arr[mid].charAt(index)>cur){
					right=mid;
				}
				else
					left=mid+1;
			}
		
		}
		if(isLeft) return left+1;
		else return right-1;
	}
	public int flipHTtogetLongest(char[] a){
		if(a==null||a.length==0) return 0;
		
		int i=0;
		int len=0;
		int j=-1;
		int start=0;
		for(;i<a.length;i++){
			if(a[i]=='T'){
				if(j>=0){
					len=Math.max(len,i-start);
					start=j+1;
					j=-1;
				}
				else{
					j=i;
					len=Math.max(len, i-start+1);
				}
			}
			else{
				len=Math.max(len,i-start+1);
			}
		}
		return len;
	}
	public static void main(String[] args){
		Oncampus test=new Oncampus();
	}
}
