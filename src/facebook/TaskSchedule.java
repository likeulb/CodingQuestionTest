package facebook;

import java.util.*;

public class TaskSchedule {
	/*第二题是Task Schedule, 地里有面经。大致意思是每种task都有冷却时间，比如task1执行后，要经过interval时间后才能再次执行，求总共所需时间。
	Sample 1 
	tasks: 1, 1, 2, 1.  recovery interval: 2
	output: 7  (order is 1 _ _ 1 2 _ 1)

	Sample 2
	tasks: 1, 2, 3, 1, 2, 3.  recovery interval: 3
	output: 7  (order is 1 2 3 _ 1 2 3)

	一开始写了个bug，三姐让我描述一遍test case, 自己改了过来。followup是tasks是无序的，大致讲了一下思路，期间也让三姐提示了一下，时间就到了。问了几个问题结束。
*/
	/*第一道题意思 大概是这样  相同工作不能在K的时间内重复干 比如工作A 和下一个工作A必须相隔K的时间 。
	 * 给你一个Arraylist 和K 求出完成所有的最短时间。 时间复杂要求为O（n) 空间为O1；
	 * */
	
	//tasks is sorted by arriving time
	public int smallestTimeToFinishAllTask(List<Character> tasks, int k){
		Deque<Character> que=new LinkedList<>();
		int time=0;
		int i=0;
		while(i<tasks.size()){
			if(que.size()>k){
				que.removeFirst();
			}
			if(!que.isEmpty()&&que.contains(tasks.get(i))){
				que.add(null);
				time++;
				continue;
			}
			else{
				que.add(tasks.get(i));
				time++;
				i++;
			}
		}
		return time;
		
	}
	public int smallestTimeToFinishAllTaskNotSorted(List<Character> tasks, int k){
		HashMap<Character,Integer> map=new HashMap<>();
		int most=0;
		Character mostChar=null;
		int dup=1;
		for(char c:tasks){
			if(map.containsKey(c)){
				map.put(c, map.get(c)+1);
			}
			else map.put(c, 1);
		}
		for(char c:map.keySet()){
			if(map.get(c)>most){
				most=map.get(c);
				mostChar=c;
				dup=1;
			}
			else if(map.get(c)==most){
				dup++;
			}
		}
		int count=(most-1)*k+dup;
		return Math.max(count,tasks.size());
		//if((most-1)*k+dup<=tasks.size()) return tasks.size();
		
	}
	public static void main(String[] args){
		TaskSchedule test=new TaskSchedule();
		List<Character> tasks=new ArrayList<>(Arrays.asList('1','1','2','1','2','3','4','5'));
		int t=test.smallestTimeToFinishAllTask(tasks, 2);
		System.out.println(t);
		int m=test.smallestTimeToFinishAllTaskNotSorted(tasks, 2);
		System.out.println(m);
	}

}
