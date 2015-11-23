package solution;

import java.util.Arrays;

public class TestStatic {
	private static int[] a = new int[10];
	
	public static void add(){
		a[1]+=11;
	}
	
	
	public static void main(String args[]){
		Arrays.fill(a,10);
		add();
		for(int i=0;i<10;i++){
			System.out.println(a[i]);
		}
	}
}
