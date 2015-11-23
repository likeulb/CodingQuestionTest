package solution;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

public class TopK {
	
	public static int k;
	public PriorityQueue<Entry> que;
	public HashMap<Integer, Integer> map;
	
	
	TopK(int k){
		this.k=k;
		map=new HashMap<Integer, Integer>();
		que=new PriorityQueue<Entry>(k,com);
	}
	
	
	public Comparator<Entry> com = new Comparator<Entry>(){
		public int compare(Entry en1, Entry en2){
				return en1.frequency-en2.frequency;
		}
	};
	
	//assume whenever input is called, the related frequency of the inputkey is increased by 1
	//since the value k is given, I maintain a priorityque with size k for the
	//top k <key, frequency> pair 
	
	//the priority queue is a min heap that the smallest frequency 
	//among those top k <key, frequency> pair is on the top 
	
	//maintain a hashmap for all of the <key frequency> pair
	
	public void input(int inputkey){
		int count = 1;
		if(map.containsKey(inputkey)){
			count = map.get(inputkey)+1;
		}
		map.put(inputkey,count);
		Entry input = new Entry(inputkey, count);
		
		if(que.isEmpty()){
			que.add(input);
			return;
		}
		
		
		//check if the key already exists in the PriorityQue
		boolean exists = que.contains(input);
		if(exists){
			que.remove(input);
			que.add(input);
		}
		else if(que.size()<k){
			que.add(input);
		}
		else if(que.peek().frequency<count){
			que.poll();
			que.add(input);
			
		}
	}
	
	public void printTopK(){
		for(Entry e:que){
			System.out.println(e.key+":"+e.frequency);
		}
	}
	public void printAll(){
		for(Integer i:map.keySet()){
			System.out.println(i+":"+map.get(i));
		}
	}
	
	
	class Entry{
		int key;
		int frequency;
		
		Entry(int x, int y){
			this.key=x;
			this.frequency=y;
		}
		public boolean equals(Object e){
			if(e==this)
				return true;
			if(!(e instanceof Entry)){
				return false;
			}
			Entry cur = (Entry)e;
			return cur.key==this.key;
		}
		public int hashCode(){
			Integer k = new Integer(this.key);
			return k.hashCode();
		}

	}
	public static void main(String[] args){
		
		TopK test = new TopK(5);
		Random ra = new Random();
		for(int i=0;i<40;i++){
			int tmp = ra.nextInt(10);
			test.input(tmp);
		}
		test.printAll();
		System.out.println("*****");
		test.printTopK();
		
		
		
		
	}

}
