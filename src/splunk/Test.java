package splunk;

import java.util.*;

public class Test {
	public void getCoin(int[] d, int target){
		List<int[]> result=new ArrayList<>();
		int[] count=new int[d.length];
		
		
		helper(result,d,target,count,0);
		for(int[] i:result){
			for(int k=0;k<i.length;k++){
				if(i[k]>0)
					System.out.println(d[k]+": "+i[k]);
			}
			System.out.println("==========");
		}
		
		
	}
	public void helper(List<int[]> result, int[] d, int target, int[] count,int index){
		if(target==0){
			result.add(count.clone());
			return;
		}
		if(target<0) return;
		for(int i=index;i<d.length;i++){
			if(d[i]<=target){
				
				count[i]++;
				helper(result,d,target-d[i],count,i);
				count[i]--;
			}
		}
	}
	public static void main(String[] args){
		Test t=new Test();
		int[] d={1,5,10,20,25,100};
		t.getCoin(d,20);
	}
}
