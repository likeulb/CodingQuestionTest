package google2016;
import commonClass.*;

public class SeralizeTree {
	
	public String serializeNode(Node root){
		if(root==null){
			return "";
		}
		String result=""+root.val+",";
		for(Node c:root.children){
			result+=serializeNode(c);
		}
		result+="#,";
		return result;
		
	}
	int index;
	public Node deseralize(String s){
		String[] arr=s.split(",");
		index=0;
		return deseralizeHelper(arr);
	}
	public Node deseralizeHelper(String[] arr){
		/*if(arr[index].equals("#")){
			index++;
			return null;
		}*/
		Node cur=new Node(Integer.parseInt(arr[index++]));
		while(!arr[index].equals("#")){
			cur.children.add(deseralizeHelper(arr));
		}
		index++;
		return cur;
	}
	public static void main(String[] args){
		String cur="1,2,3,#,4,5,#,#,#,6,#,7,8,#,9,#,10,#,11,#,#,#";
		SeralizeTree test=new SeralizeTree();
		Node a=test.deseralize(cur);
		System.out.println(test.serializeNode(a));
	}

}
