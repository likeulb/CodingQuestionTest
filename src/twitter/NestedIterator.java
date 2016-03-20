package twitter;

import java.util.*;

public class NestedIterator {
	Stack<Iterator<Element>> stak;
    Iterator<Element> cur;
    Character num;

    NestedIterator(Element e){
        if(e.next!=null){
            cur=e.next.iterator();
            stak=new Stack<>();
            num=null;
        } 
        else num=e.ch;
   }
   boolean hasNext(){
       if(num!=null) return true;
       if(cur!=null&&cur.hasNext()){
             Element e=(Element)cur.next();
             if(e.next==null){
                   num=e.ch;
                   return true;
             }
             else{
                 
                 stak.push(cur);
                 cur=e.next.iterator();
                  return hasNext();
                  
              }

       }
       
       if(stak.isEmpty()) return false;
       cur=stak.pop();
       return hasNext();
       
       
  }
    char next(){
         char result=0;
         hasNext();
         result=num;
         num=null;
         return result;
    }
    public static void main(String[] arg){
    	//{'a','b',{'c','d',{'e'}},{f,g}};
    	Element a=new Element('a');
    	Element b=new Element('b');
    	Element c=new Element('c');
    	Element d=new Element('d');
    	List<Element> listc=new ArrayList<>();
    	listc.add(c);
    	listc.add(d);
    	Element e=new Element('e');
    	List<Element> liste=new ArrayList<>();
    	liste.add(e);
    	Element upe=new Element(liste);
    	listc.add(upe);
    	Element upc=new Element(listc);
    	List<Element> list = new ArrayList<>();
    	list.add(a);
    	list.add(b);
    	list.add(upc);
    	Element f=new Element('f');
    	Element g=new Element('g');
    	List<Element> listf=new ArrayList<>();
    	listf.add(f);
    	listf.add(g);
    	Element upf=new Element(listf);
    	list.add(upf);
    	Element h=new Element('h');
    	list.add(h);
    	Element root=new Element(list);
    	NestedIterator test=new NestedIterator(root);
    	while(test.hasNext()){
    		System.out.println(test.next());
    	}
    }
}
