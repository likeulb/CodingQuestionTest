package facebook;

//import java.util.*;
import java.util.*;

public class Phone {
   //calculator with +-*/^   1-2+3*4^2
	public int calculator(String s){
		int num=0;
		
		Stack<Integer> res=new Stack<>();
		Stack<Character> ope=new Stack<>();
		
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)==' ') continue;
			char cur=s.charAt(i);
			if(Character.isDigit(cur)){
				num=num*10+Character.getNumericValue(cur);
			}
			else{
				if(ope.isEmpty()){
					ope.push(cur);
					res.push(num);
					num=0;
				}
				else{
					res.push(num);
					num=0;
					if(cur=='+'||cur=='-'){
						while(!ope.isEmpty()){
							cal(res,ope.pop());
						}
						ope.push(cur);
					}
					else if(cur=='*'||cur=='/'){
						while(ope.peek()!='+'&&ope.peek()!='-'){
							cal(res,ope.pop());
						}
						ope.push(cur);
					}
					else if(cur=='^'){
						ope.push(cur);
					}
				}
			}
		}
		res.push(num);
		while(!ope.isEmpty()){
			cal(res,ope.pop());
		}
		return res.pop();
	}
	public void cal(Stack<Integer> res, char c){
		int sec=res.pop();
		int fir=res.pop();
		switch(c){
		case '+': res.push(fir+sec);
			break;
		case '-': res.push(fir-sec);
			break;
		case '*': res.push(fir*sec);
			break;
		case '/': res.push(fir/sec);
			break;
		case '^': res.push((int)Math.pow(fir,sec));
			break;
		}
	}
	//123456789=100， 在等式左边任意位置加上“-”或者“+”使得等式成立。Print all possible combinations
	public void cal100(String input){
		helpercal100(0,input,0,"");
	}
	public void helpercal100(int i,String s, long sum,String t){
		if(i==s.length()){
			if(sum==100){
				System.out.println(t);
			}
			return;
		}
		for(int j=i+1;j<=s.length();j++){
			long cur=Long.parseLong(s.substring(i,j));
			if(t.length()==0){
				helpercal100(j,s,sum+cur,t+cur);
			}
			else{
				helpercal100(j,s,sum+cur,t+"+"+cur);
				helpercal100(j,s,sum-cur,t+"-"+cur);
			}
		}
	}
	//反向输出一个链表的值
	public void printList(ListNode head){
		System.out.println(helperPrintList(head));
		
	}
	public String helperPrintList(ListNode head){
		if(head==null) return "";
		if(head.next==null) return head.val+"";
		ListNode nxt=head.next;
		return helperPrintList(nxt)+" "+head.val;
	}
	static class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val=val;
		}
	}
	public List<String> printCombination(String s){
		HashMap<String,String> map=new HashMap<>();
		map.put("1", "a");
		map.put("11", "b");
		map.put("111", "c");
		List<String> result=new ArrayList<>();
		if(s.length()==0) return result;
		
		combinationhelper(result, 0, s,"",map);
		for(String t:result){
			System.out.println(t);
		}
		return result;
	}
	public void combinationhelper(List<String> result, int index, String s, String cur,HashMap<String,String> map){
		if(index==s.length()){
			result.add(cur);
			return;
		}
		for(int i=index+1;i<=s.length();i++){
			if(map.containsKey(s.substring(index,i))){
				combinationhelper(result,i,s,cur+map.get(s.substring(index,i)),map);
			}
			if(i-index>=3&&s.charAt(i-1)==s.charAt(i-2)&&s.charAt(i-1)==s.charAt(i-3)){
				combinationhelper(result,i,s,cur,map);
			}
		}
	}
	
	public static void main(String[] args){
		Phone test=new Phone();
		System.out.println(test.calculator("1-2+3*4/1^2+1"));
		//test.cal100("123456789");
		ListNode head=new Phone.ListNode(2);
		head.next=new Phone.ListNode(6);
		head.next.next=new Phone.ListNode(7);
		//test.printList(head);
		test.printCombination("1111");
		
	}
}
