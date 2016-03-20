package facebook;

import java.util.*;

public class PaintHourse {
	
	public List<HashSet<Integer>> paintHourse(List<List<Integer>> house, int[] cost){
		List<HashSet<Integer>> group = new ArrayList<>();
		for(int i=0;i<cost.length;i++){
			group.add(new HashSet<Integer>());
		}
		HashMap<Integer,Integer> marked = new HashMap<>();
		Queue<Integer> que=new LinkedList<>();
		que.add(0);
		marked.put(0,0);
		group.get(0).add(0);
		
		while(!que.isEmpty()){
			int cur=que.poll();
			
			for(int j:house.get(cur)){
				boolean[] used=new boolean[cost.length];
				used[marked.get(cur)]=true;
				if(!marked.containsKey(j)){
					for(int k:house.get(j)){
						if(marked.containsKey(k)){
							used[marked.get(k)]=true;
						}
					}
					for(int i=0;i<used.length;i++){
						if(!used[i]){
							marked.put(j, i);
							group.get(i).add(j);
							que.add(j);
							break;
						}
					}
				}
			}
		}
		//maybe no need to sort
		Collections.sort(group,new Comparator<HashSet<Integer>>(){
			@Override
			public int compare(HashSet<Integer> o1, HashSet<Integer> o2) {
				return o2.size()-o1.size();
			}
		});
		Arrays.sort(cost);
		int minCost=0;
		for(int i=0;i<group.size();i++){
			if(group.get(i).size()==0) break;
			minCost+=group.get(i).size()*cost[i];
		}
		System.out.println(minCost);
		return group;
	}
	public static void main(String[] args){
		List<Integer> a1=new ArrayList<>(Arrays.asList(1,2,3,4));
		List<Integer> a2=new ArrayList<>(Arrays.asList(0,2,5,6));
		List<Integer> a3=new ArrayList<>(Arrays.asList(0,1,3,5,7));
		List<Integer> a4=new ArrayList<>(Arrays.asList(0,2,9,8));
		List<Integer> a5=new ArrayList<>(Arrays.asList(0,6,9));
		List<Integer> a6=new ArrayList<>(Arrays.asList(1,2));
		List<Integer> a7=new ArrayList<>(Arrays.asList(1,4));
		List<Integer> a8=new ArrayList<>(Arrays.asList(2,8));
		List<Integer> a9=new ArrayList<>(Arrays.asList(3,7,10));
		List<Integer> a10=new ArrayList<>(Arrays.asList(3,4));
		List<Integer> a11=new ArrayList<>(Arrays.asList(8));
		List<List<Integer>> house=new ArrayList<>();
		house.add(a1);
		house.add(a2);
		house.add(a3);
		house.add(a4);
		house.add(a5);
		house.add(a6);
		house.add(a7);
		house.add(a8);
		house.add(a9);
		house.add(a10);
		house.add(a11);
		PaintHourse test=new PaintHourse();
		int[] cost={20,3,4,5,6,7,8};
		List<HashSet<Integer>> res=test.paintHourse(house, cost);
		for(HashSet<Integer> set:res){
			for(int i:set){
				System.out.print(i+" ");
			}
			System.out.println("");
		}
	}
}
