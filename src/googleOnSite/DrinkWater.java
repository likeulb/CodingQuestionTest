package googleOnSite;

import java.util.*;

public class DrinkWater {
	static int maxWater;
	static int minTimes;
	HashMap<Integer,List<List<Integer>>> map;
	public List<Integer> getWater(int[] d, int target){
		List<Integer> cur = new ArrayList<>();
		map= new HashMap<>();
		maxWater = Integer.MIN_VALUE;
		minTimes = Integer.MAX_VALUE;
		dfs(d, target, 0, cur);
		if(!map.containsKey(maxWater)) return new ArrayList<>();
		List<List<Integer>> res= map.get(maxWater);
		if(res.size()==0) return res.get(0);
		int index=0;
		for(int i=1;i<res.size();i++){
			if(res.get(i).size()<res.get(index).size()){
				index = i;
			}
		}
		return res.get(index);
		
		
	}
	public void dfs(int[] d, int target, int water, List<Integer> cur){
		if(water>target) return;
		if(water>=maxWater&&cur.size()>0){
			maxWater=water;
			if(!map.containsKey(maxWater)){
				map.put(maxWater, new ArrayList<List<Integer>>());
			}
			map.get(maxWater).add(new ArrayList<>(cur));
		}
		for(int i=0;i<d.length;i++){
			cur.add(d[i]);
			dfs(d, target, water+d[i],cur);
			cur.remove(cur.size()-1);
		}
	}
	public static void main(String[] args){
		int[] d = {2,6,8};
		DrinkWater test = new DrinkWater();
		List<Integer> re= test.getWater(d, 17);
		for(int i=0;i<re.size();i++){
			System.out.println(re.get(i));
		}
	}

}
