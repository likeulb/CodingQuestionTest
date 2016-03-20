package google2016;

import java.util.*;


public class NextSmallestIterator {
	//给一个List<Iterator>，写一个method next()，
	//每次调用这个method，输出所有iterator里的最小值, 本质就是merge k sorted list。用heap
	PriorityQueue<Integer> que=new PriorityQueue<>();
	HashMap<Integer,List<Iterator>> map=new HashMap<>();
	public NextSmallestIterator(List<Iterator<Integer>> lists){
		for(Iterator<Integer> l:lists){
			add(l);
		}
		
	}
	public void add(Iterator<Integer> l){
		if(l.hasNext()){
			int n=l.next();
			if(!map.containsKey(n)){
				map.put(n,new ArrayList<Iterator>());
			}
			map.get(n).add(l);
			que.add(n);
		}
	}
	public int next(){
		int res=que.poll();
		Iterator<Integer> iter=map.get(res).remove(map.get(res).size()-1);
		if(map.get(res).size()==0){
			map.remove(res);
		}
		add(iter);
		return res;
		
	}
	public boolean hasNext(){
		return !que.isEmpty();
	}
	public static void main(String[] args){
		List<Integer> l1=new ArrayList<>(Arrays.asList(1,2,6,3));
		List<Integer> l2=new ArrayList<>(Arrays.asList(5,26,1,7));
		List<Iterator<Integer>> iter=new ArrayList<>();
		iter.add(l1.iterator());
		iter.add(l2.iterator());
		NextSmallestIterator a=new NextSmallestIterator(iter);
		while(a.hasNext()){
			System.out.println((int)a.next());
		}
	}
}
