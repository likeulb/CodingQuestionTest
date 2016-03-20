package facebook;

public class ArrayQuestion {
	//move zero the the right
	public void moveZero(int[] a){
		int i=0,j=a.length-1;
		while(i<j){
			while(i<j&&a[i]!=0){
				i++;
			}
			while(i<j&&a[j]==0){
				j--;
			}
			if(i<j){
				swap(a,i,j);
			}
		}
	}
	public void swap(int[] a, int i, int j){
		int tmp=a[i];
		a[i]=a[j];
		a[j]=tmp;
	}
	public static void main(String[] args){
		ArrayQuestion test=new ArrayQuestion();
		int[] a={0,2,3,5,0,9,0,6,0,0,0};
		test.moveZero(a);
		for(int i:a){
			System.out.print(i+" ");
		}
	}
}
