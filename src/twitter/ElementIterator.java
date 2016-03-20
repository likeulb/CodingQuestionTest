package twitter;

import java.util.*;

public class ElementIterator {
	List<Character> list;
	int index=-1;
	ElementIterator(Element e){
		list=new ArrayList<>();
		helper(e);
		if(list.size()>0) index=0;
		
	}
	void helper(Element e){
		if(e.next==null){
			list.add(e.ch);
			return;
		}
		for(Element n:e.next){
			helper(n);
		}
	}
	boolean hasNext(){
		if(index==-1||index==list.size()) return false;
		return true;
	}
	char next(){
		char res;
		res=(char)list.get(index);
		index++;
		return res;
		
	}
}
