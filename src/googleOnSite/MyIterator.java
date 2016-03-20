package googleOnSite;

import java.util.*;

public class MyIterator implements Iterator {
	Iterator<Iterator<Integer>> i;
	Iterator<Integer> j;
	MyIterator(Iterator<Iterator<Integer>> input){
		i=input;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		while((j==null||!j.hasNext())&&i.hasNext()){
			j=(Iterator)i.next();
		}
		if(j==null||!j.hasNext()) return false;
		return true;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		if(hasNext()) return (Integer)j.next();
		return null;
	}

}
