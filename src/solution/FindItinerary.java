package solution;

import java.util.*;

public class FindItinerary {
	public List<String> findItinerary(String[][] tickets) {
        List<String> result=new ArrayList<>();
        if(tickets==null||tickets.length==0||tickets[0].length==0) return result;
        HashMap<String, PriorityQueue<String>> map=new HashMap<>();
        for(String[] s:tickets){
            if(!map.containsKey(s[0])){
                map.put(s[0],new PriorityQueue<String>());
            }
            map.get(s[0]).add(s[1]);
        }
        Stack<String> stak=new Stack<>();
        stak.push("JFK");
        while(!stak.isEmpty()){
            
            while(map.containsKey(stak.peek())&&!map.get(stak.peek()).isEmpty()){
                stak.push(map.get(stak.peek()).poll());
            }
            result.add(0,stak.pop());
        }
        return result;
    }
	public static void main(String[] args){
		FindItinerary test=new FindItinerary();
		String[][] a = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		test.findItinerary(a);
		System.out.print("abc"+"\n");
		System.out.print("ff");
	}
}
