package solution;

import java.util.Stack;

public class MyQueue {
public Stack<Integer> stak = new Stack<Integer>();
    
    public void push(int x) {
        if(stak.isEmpty()){
            stak.push(x);
        }
        else{
            Stack<Integer> tmp = new Stack<Integer>();
            while(!stak.isEmpty()){
                tmp.push(stak.pop());
            }
            stak.push(x);
            while(!tmp.isEmpty()){
                stak.push(tmp.pop());
            }
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(!stak.isEmpty()){
            stak.pop();
            return;
        }
        throw new IllegalArgumentException("queue is empty");
        
    }

    // Get the front element.
    public int peek() {
        if(!stak.isEmpty()){
            return stak.peek();
        }
        throw new IllegalArgumentException("queue is empty");
        
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stak.isEmpty();
    }
    public static void main(String[] args){
    	MyQueue my = new MyQueue();
    	my.push(1);
    	my.pop();
    	my.peek();
    }
}
