package facebook;

import java.util.*;

public class PostOrderIterator {
	Stack<TreeNode> stak=new Stack<>();
	
	PostOrderIterator(TreeNode root){
		pushLeaf(root);
	}
	public boolean hasNext(){
		return !stak.isEmpty();
	}
	public int next(){
		
		TreeNode cur=stak.pop();
		if(!stak.isEmpty()){
			TreeNode p=stak.peek();
			if(p.right!=cur){
				pushLeaf(p.right);
			}
		}
		return cur.val;
	}
	public void pushLeaf(TreeNode p){
		while(p!=null){
			stak.push(p);
			if(p.left!=null){
				p=p.left;
			}
			else p=p.right;
		}
	}
	public static void main(String[] args){
		
		TreeNode root=new TreeNode(5);
		TreeNode node10=new TreeNode(10);
		TreeNode node3=new TreeNode(3);
		//root.left=node3;
		root.right=node10;
		TreeNode node1=new TreeNode(1);
		TreeNode node7=new TreeNode(7);
		TreeNode node8=new TreeNode(8);
		
		//node10.left=node1;
		node10.right=node7;
		//node3.left=node8;
		PostOrderIterator test=new PostOrderIterator(root);
		while(test.hasNext()){
			System.out.println(test.next());
		}
	}

}
