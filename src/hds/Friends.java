package hds;

import java.util.*;

public class Friends {
	public List<String> getClosestFriends(String s){
		return new ArrayList<>();
	}
	public int distance(String s, String t){
		if(s.equals(t)) return 0;
		int dis=0;
		HashSet<String> visited = new HashSet<>();
		Queue<String> que= new LinkedList<>();
		que.add(s);
		visited.add(s);
		while(!que.isEmpty()){
			int size=que.size();
			for(int i=0;i<size;i++){
				String cur = que.poll();
				for(String nxt:getClosestFriends(cur)){
					if(visited.contains(nxt)) continue;
					visited.add(nxt);
					if(nxt.equals(t)) return dis+1;
					que.add(t);
				}
			}
			dis++;
		}
		if(dis==0) return -1;
		return dis;
	}
	public boolean isAnagram(String s1, String s2){
		if(s1==null||s1.length()==0) return s2==null||s2.length()==0;
		if(s1.length()!=s2.length()) return false;
		HashMap<Character, Integer> map = new HashMap<>();
		for(int i=0;i<s1.length();i++){
			char c1=s1.charAt(i);
			char c2=s2.charAt(i);
			if(map.containsKey(c1)){
				map.put(c1,map.get(c1)+1);
			}
			else{
				map.put(c1,1);
			}
			if(map.containsKey(c2)){
				map.put(c2, map.get(c2)-1);
			}
			else
				map.put(c2,-1);
		}
		for(int v: map.values()){
			if(v!=0) return false;
		}
		return true;
	}
}
