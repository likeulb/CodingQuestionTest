package hulu;

import java.util.*;

public class XmlParse {
	int i;
	String s;
	public Map<String, Object> getMap(){
		Node cur = getNextNode();
		Stack<Map<String,Object>> stak = new Stack<>();
		Map<String,Object> m=null;
		while(cur!=null){
			if(cur.eventType.equals("openNode")){
				m =new HashMap<>();
				m.put(cur.text, new Object());
				stak.push(m);
			}
			else if(cur.eventType.equals("closeNode")){
				m=stak.pop();
				if(stak.isEmpty()) return m;
				stak.peek().put(cur.text, m);
			}
			else if(cur.eventType.equals("nodeValue")){
				m =new HashMap<>();
				m.put("value", cur.text);
				stak.peek().put(cur.text, m);
			}
			cur=getNextNode();
		}
		return m;
		
	}
	public void getString(String t){
		s=t;
		i=0;
	}
	public Node getNextNode(){
		int tmp=i;
		if(i<s.length()){
			if(s.charAt(i)=='<'){
				int j=s.indexOf(">",i);
				i=j+1;
				if(s.charAt(tmp+1)=='/'){
					return new Node("closeNode",s.substring(tmp+2,j));
				}
				else return new Node("openNode",s.substring(tmp+1,j));
			}
			else{
				int j=s.indexOf("<",i);
				i=j;
				return new Node("nodeValue",s.substring(tmp,j));
			}
			
		}
		return null;
	}
	
	class Node{
		String eventType;
		String text;
		Node(String s, String t){
			eventType=s;
			text=t;
		}
	}
	class TreeNode{
		String test;
		TreeNode next;
	}
	
	public static void main(String[] args){
		XmlParse test=new XmlParse();
		test.getString("<html><test>abc</test><tt>ddd</tt></html>");
		test.getMap();
	}
	

}
