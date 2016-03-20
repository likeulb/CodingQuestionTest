package hds;

public class Buffer {
	Integer[] arr;
	int size;
	int capacity;
	int index;
	Buffer(int capacity){
		this.capacity=capacity;
		arr=new Integer[capacity];
		size=0;
		index=0;
	}
	public void input(int x){
		arr[index++]=x;
		if(index==capacity) index=0;
		if(size<capacity)
			size++;
	}
	public Integer getOldest(){
		if(size==0) return null;
		Integer result = null;
		if(size<capacity){
			int i=0;
			for(;i<size;i++){
				if(arr[i]!=null)
					break;
			}
			result=arr[i];
			arr[i]=null;
		}
		else{
			int k=index;  //current index supposed to be the oldest value, if it is null?
			while(arr[k]==null&&k<capacity){
				k++;
			}
			if(k<capacity){
				result=arr[k];
				arr[k]=null;
				
			}
			else{
				k=0;
				while(arr[k]==null&&k<index){
					k++;
				}
				if(k==index) return null;
				else{
					result=arr[k];
					arr[k]=null;
				}
			}
		}
		return result;
	}
	public void print(){
		for(Integer i:arr){
			if(i==null){
				System.out.print("null ");
			}
			else System.out.print(i+" ");
		}
		System.out.println("");
	}
	public static void main(String[] args){
		System.out.println(Math.log(243));
		System.out.println(Math.log(3));
		System.out.println(Math.log(243)/Math.log(3));
		System.out.println(Math.log10(243));
		System.out.println(Math.log10(3));
		System.out.println(Math.log10(243)/Math.log10(3));
		Buffer test=new Buffer(5);
		test.input(3);
		test.input(7);
		test.input(8);
		System.out.println(test.getOldest());
		test.input(2);
		System.out.println(test.getOldest());
		test.input(9);
		test.input(1);
		System.out.println(test.getOldest());
		test.print();
		
	}
}
