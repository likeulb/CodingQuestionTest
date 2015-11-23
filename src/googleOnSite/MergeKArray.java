package googleOnSite;

import java.util.*;

public class MergeKArray {
	public int[] mergek(List<int[]> kArray){
		if(kArray==null||kArray.size()==0) return new int[]{};
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>(){
			public int compare(int[] a, int[] b){
		return a[0]-b[0];
	}
	});
	int size = 0;
		for(int i=0;i<kArray.size();i++){
			int[] cur = kArray.get(i);
			if(cur==null||cur.length==0) continue;
			que.add(new int[]{kArray.get(i)[0], i,0});
			size+=kArray.get(i).length;
	}
	int[] result = new int[size];
	int i=0;
	while(!que.isEmpty()){
		int[] cur= que.poll();
		result[i++]=cur[0];
		cur[2]++;
		if(cur[2]<kArray.get(cur[1]).length){
		que.add(new int[]{kArray.get(cur[1])[cur[2]], cur[1], cur[2]});
	}
	}
	return result;

	}
	
	public static void main(String[] str){
		int[] a = {1,4,6,88,91};
		int[] b = {2};
		int[] c = {-1,4,57,89,99};
		int[] d = {};
		List<int[]> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		MergeKArray Test = new MergeKArray();
		int[] result=Test.mergek(list);
		for(int i:result){
			System.out.println(i);
		}
		
	}

}
