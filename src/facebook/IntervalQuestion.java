package facebook;

import java.util.*;

public class IntervalQuestion {
	
	//给n个区间，一个点被最多的区间覆盖的点，这个点可能有多个，任意返回一个
	public int coveredByMostInterval(List<Interval> lists){
		Collections.sort(lists,new Comparator<Interval>(){
			public int compare(Interval l1, Interval l2){
				return l1.start-l2.start;
			}
		});
		PriorityQueue<Interval> que=new PriorityQueue<>(new Comparator<Interval>(){
			public int compare(Interval l1, Interval l2){
				return l1.end-l2.end;
			}
		});
		int res=0;
		int point=-1;
		for(int i=0;i<lists.size();i++){
			if(que.isEmpty()||que.peek().end>lists.get(i).start){
				que.add(lists.get(i));
				if(que.size()>res){
					res=que.size();
					point=lists.get(i).start;
				}
			}
			else{
				Interval cur=que.poll();
				cur.end=lists.get(i).end;
				que.add(cur);
			}
		}
		return point;
	}
	//1, 给n个区间，一个点被最多的区间覆盖的点，这个点可能有多个，任意返回一个
	//这个明显有O(n)办法，我们把区间的开始和结束都当做一个点，对2N个点排序，每遇到一个左边括号，当前的intersection数目加1，每遇到一个右边括号，当前intersection数目减一。
	//但是有些细节需要注意，如果两个点重合，应该谁在前？
	class Point{
		int val;
		boolean isLeft;
		Point(int val, boolean isLeft){
			this.val=val;
			this.isLeft=isLeft;
		}
	}
	
	public static void main(String[] args){
		IntervalQuestion test=new IntervalQuestion();
		Interval l1=new Interval(1,4);
		Interval l2=new Interval(5,8);
		Interval l3=new Interval(0,4);
		Interval l4=new Interval(1,2);
		List<Interval> list=new ArrayList<>();
		list.add(l1);
		list.add(l2);
		list.add(l3);
		list.add(l4);
		int t=test.coveredByMostInterval(list);
		System.out.println(t);
	}

}
