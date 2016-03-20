package googleOnSite;

import java.util.*;

public class ReorderString {
	public String reorder(String s, int k){
		if(s==null||s.length()==0)
			return "";
		HashMap<Character, Integer> map = new HashMap<>();
		for(char c:s.toCharArray()){
			if(map.containsKey(c)){
				map.put(c, map.get(c)+1);
			}
			else map.put(c,1);
		}
		PriorityQueue<charsAndFre> que = new PriorityQueue<>(new Comparator<charsAndFre>(){
			public int compare(charsAndFre c1, charsAndFre c2){
				return c2.count-c1.count;
			}
		});
		int countMost = 1;
		int most = 0;
		for(char c:map.keySet()){
			que.add(new charsAndFre(c,map.get(c)));
			if(map.get(c)>most){
				most=map.get(c);
				countMost=1;
			}
			else if(map.get(c)==most){
				countMost++;
			}
		}
		if((most-1)*k+countMost>s.length()) return "";
		int i=1;
		int j=0;
		char[] result = new char[s.length()];
		while(!que.isEmpty()){
			charsAndFre cur  = que.poll();
			int num = cur.count;
			char ch = cur.c;
			while(num>0){
				
				result[j]=ch;
				j+=k;
				if(j>=result.length){
					j=i;
					i++;
				}
				num--;
			}
			
		}
		return new String(result);
		
	}
	class charsAndFre{
		char c;
		int count;
		charsAndFre(char c, int co){
			this.c=c;
			this.count=co;
		}
	}
	public static void main(String[] args){
		ReorderString test = new ReorderString();
		System.out.println(test.reorder("aaabbbcccdd", 3));
	}
}
