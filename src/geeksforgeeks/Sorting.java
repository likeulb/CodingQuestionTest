package geeksforgeeks;

import java.util.Arrays;

public class Sorting {
	//heap sort http://geeksquiz.com/heap-sort/
	//use max heap, every time swap the max value to the last, and heaplify the root value
	public static void heapSort(int[] a){
		buildMaxHeap(a);
		int len=a.length;
		while(len>1){
			swap(a,0,len-1);
			len--;
			heaplify(a,0,len);
		}
		print(a);
	}
	public static void buildMaxHeap(int[] a){
		for(int i=a.length/2;i>=0;i--){
			heaplify(a,i,a.length);
		}
	}
	public static void heaplify(int[] a, int i, int len){
		while(i<len){
			int big=i;
			if(i*2+1<len&&a[i*2+1]>a[big]){
				big=i*2+1;
			}
			if(i*2+2<len&&a[i*2+2]>a[big]){
				big=i*2+2;
			}
			if(i==big) break;
			swap(a,i,big);
			i=big;
		}
	}
	//radix sort
	public static void radixSort(int[] a){
		int m=getMax(a);
		for(int i=1;m/i>0;i*=10){
			countSort(a,i);
		}
		print(a);
	}
	public static void countSort(int[] a, int k){
		int[] count=new int[10];
		for(int n:a){
			count[(n/k)%10]++;
		}
		for(int i=1;i<count.length;i++){
			count[i]+=count[i-1];
		}
		int[] output=new int[a.length];
		for(int i=a.length-1;i>=0;i--){
			output[count[(a[i]/k)%10]-1]=a[i];
			count[(a[i]/k)%10]--;
		}
		System.arraycopy(output, 0, a, 0, a.length);
	}
	public static int getMax(int[] a){
		int max=a[0];
		for(int i=1;i<a.length;i++){
			max=Math.max(max,a[i]);
		}
		return max;
	}
	//quick sort
	public static void quickSort(int[] a){
		quickSort(a,0,a.length-1);
		print(a);
	}
	public static void quickSort(int[] a, int left, int right){
		if(left>=right) return; //when left==right, no need to do sorting
		int p=partition(a,left,right);
		quickSort(a,left,p);
		quickSort(a,p+1,right);
	}
	public static int partition(int[] a, int left, int right){
		int p=a[left];
		int s=left;
		for(int i=left+1;i<=right;i++){
			if(a[i]<p){
				s++;
				swap(a,s,i);
			}
		}
		swap(a,s,left);
		return s;
	}
	//merge sort
	public static void mergesort(int[] a){
		mergesortHelper(a,0,a.length-1);
		print(a);
	}
	public static void mergesortHelper(int[] a, int left, int right){
		if(left<right){
			int mid=left+(right-left)/2;
			mergesortHelper(a,left,mid);
			mergesortHelper(a,mid+1,right);
			mergesortMerge(a,left,mid,right);
		}
	}
	public static void mergesortMerge(int[] a, int left, int mid, int right){
		int[] a1=new int[right-mid];
		int k=0;
		for(int i=mid+1;i<=right;i++){
			a1[k++]=a[i];
		}
		int i=mid,j=a1.length-1;
		k=right;
		while(i>=left&&j>=0){
			if(a[i]>a1[j]){
				a[k--]=a[i--];
			}
			else{
				a[k--]=a1[j--];
			}
		}
		while(j>=0){
			a[k--]=a1[j--];
		}
	}
	//insertion sort
	public static void insertionsort(int[] a){
		for(int i=1;i<a.length;i++){
			int key=a[i];
			int j=i-1;
			while(j>=0&&a[j]>key){
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=key;
		}
		print(a);
	}
	//buble sort
	public static void bublesort(int[] a){
		for(int i=0;i<a.length;i++){
			boolean swap=false;
			for(int j=1;j<a.length-i;j++){
				if(a[j]<a[j-1]){
					swap=true;
					swap(a,j-1,j);
				}
			}
			if(!swap) break;
		}
		print(a);
	}
	//selection sort
	public static void selectionSort(int[] a){
		for(int i=0;i<a.length;i++){
			int min=i;
			for(int j=i+1;j<a.length;j++){
				if(a[j]<a[min]){
					min=j;
				}
			}
			swap(a,i,min);
		}
		print(a);
	}
	public static void print(int[] a){
		for(int i:a){
			System.out.print(i+" ");
		}
		System.out.println("");
	}
	public static void swap(int[] a, int left, int right){
		int tmp=a[left];
		a[left]=a[right];
		a[right]=tmp;
	}
	public static void main(String[] args){
		int[] a = {10,1,4,90,5,6,6,6,7,0};
		radixSort(a);
	}

}
