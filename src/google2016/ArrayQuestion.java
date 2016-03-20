package google2016;

public class ArrayQuestion {
	//就是一个sorted array,若干位置是空的，给一个target求第一个比target小的position
	public int findFirstSmaller(Integer[] arr, int target){
		int left=0, right=arr.length-1;
		while(left<right){
			int mid=left+(right-left+1)/2;
			if(arr[mid]==null){
				int i=mid+1;
				while(i<right&&arr[i]==null){
					i++;
				}
				if(i<right){
					mid=i;
				}
				else{
					i=mid-1;
					while(i>left&&arr[i]==null){
						i--;
					}
					if(i>left) mid=i;
					else{
						if(arr[left]==null&&arr[right]==null){
							return -1;
						}
						else if(arr[right]!=null&&arr[right]<target){
							return right;
						}
						else if(arr[left]!=null&&arr[left]<target){
							return left;
						}
						else return -1;
					}
				}
			}
			if(arr[mid]>=target){
				right=mid-1;
			}
			else{
				left=mid;
			}
		
		
		}
		return arr[left]<target?left:-1;
	}
	public static void main(String[] args){
		ArrayQuestion test=new ArrayQuestion();
		Integer[] t={1,2,null,null,6,8,9,null};
		System.out.println(test.findFirstSmaller(t, 1));
	}

}
