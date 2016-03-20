package hackRank;

import java.util.*;
import java.util.Scanner;

//indeed oa, calculate quantile
public class Solution {
	static class Pair{
		int value;
		int count;
		Pair(int v, int c){
			this.value=v;
			this.count=c;
		}
		
	}
	public static int binarySearch(int[] c, int target){
		int i=0, j=c.length-1;
		while(i<j){
			int mid=i+(j-i)/2;
			if(c[mid]==target){
				return mid;
			}
			else if(c[mid]>target){
				j=mid;
			}
			else
				i=mid+1;
		}
		return i;
	}
	
   
   
	public static void main(String[] args) {
        //Calculating quantile
		//Solution s=new Solution();
		
        Scanner in=new Scanner(System.in);
        int Q=in.nextInt();
		//int Q=2;
        int num=in.nextInt();
        Pair[] arr=new Pair[num];
        for(int j=0;j<num;j++){
        	int v=in.nextInt();
        	int c=in.nextInt();
        	arr[j]=new Pair(v,c);
        }
        Arrays.sort(arr,new Comparator<Pair>(){

				@Override
				public int compare(Pair o1, Pair o2) {
					return o1.value-o2.value;
				}
        	});
        int[] c = new int[num];
        c[0]=arr[0].count;
       	for(int j=1;j<num;j++){
       		c[j]=c[j-1]+arr[j].count;
       	}
       	int total=c[num-1];
        for(int i=1;i<Q;i++){
        	int index=(int)Math.ceil((double)total*i/Q);
        	//System.out.println(index);
        	int rankIndex=binarySearch(c,index);
        	System.out.println(arr[rankIndex].value);
        }
    }
	
    

}
