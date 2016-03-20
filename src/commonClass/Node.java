package commonClass;

import java.util.*;

public class Node {
	public int val;
	public List<Node> children;
	public Node(int v){
		this.val=v;
		children=new ArrayList<>();
	}
}
