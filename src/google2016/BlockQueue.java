package google2016;

import java.util.LinkedList;

public class BlockQueue<E> {
	private LinkedList<E> que=new LinkedList<E>();
	private int limit=10;
	BlockQueue(int limit){
		this.limit=limit;
	}
	@SuppressWarnings("unchecked")
	public synchronized void enqueue(E item) throws InterruptedException{
		while(this.que.size()==this.limit){
			wait();
		}
		if(this.que.size()==0){
			notifyAll();
		}
		this.que.add(item);
	}
	public synchronized E deque() throws InterruptedException{
		while(this.que.size()==0){
			wait();
		}
		if(this.que.size()==this.limit){
			notifyAll();
		}
		return this.que.remove();
	}

}
