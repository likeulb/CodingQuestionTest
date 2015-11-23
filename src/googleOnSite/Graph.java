package googleOnSite;

import java.util.*;

public class Graph {

	
	/*给你一个无向图edge list, 求里面的三角形， 南美人不友好， 不给hint, 就自己在那边干活， 而且最后我就做出来个暴力解， 他没留意， 还讲了半天才明白， 我了个去。 
a. 
0-1
1-2
3-2
4-3. From 1point 3acres bbs
1-0
自己想如何设计数据结构来表示input. .  
b. 求里面的三角形
我会求cycle, 但是三角形， 没想到太好的解法， 说完题目都过了20分钟了， 因为之前还扯了别的， 最后给了个最暴力的解法
i. 对于每个node, 找出他们的邻居. more info on 1point3acres.com
ii. 看邻居之间有没有相连， 有则找到， 没有继续*/
	
	public boolean hasTriangle(int[][] edges){
		HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
		for(int[] e:edges){
			if(!map.containsKey(e[0])){
				map.put(e[0],new HashSet<Integer>());
			}
			if(!map.containsKey(e[1])){
				map.put(e[1], new HashSet<Integer>());
			}
			map.get(e[0]).add(e[1]);
			map.get(e[1]).add(e[0]);
		}
		for(int[] e:edges){
			HashSet<Integer> first = map.get(e[0]);
			HashSet<Integer> second = map.get(e[1]);
			for(int i:first){
				if(second.contains(i))
					return true;
			}
		}
		return false;
		
		
	}
	
}
