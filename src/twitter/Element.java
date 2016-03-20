package twitter;

import java.util.*;

public class Element {
	char ch;
	List<Element> next;
	
	Element(char c){
		ch=c;
		next=null;
	}
	Element(List<Element> list){
		next=list;
		ch='*';
	}
	
}
