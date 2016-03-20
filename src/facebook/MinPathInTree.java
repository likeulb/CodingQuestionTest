package facebook;

import java.util.*;

public class MinPathInTree {
	/*第二道题是 给个Tree 不一定是平衡的， 要求 把所有路径排序后  按字符串那样的比较大小方法 找出最小的路径  时间要求线性的。 比如  
    5. 
 /     \
10      3
/   \   /
1    7  8
路径有  5 10 1 ； 5 10 7 ； 5 3 8
排序后  1 5 10 ； 5 7 10 ； 3 5 8；.
所以按字符串类型排序 为  1 5 10 < 3 5 8 < 5 7 10；*/
	
	public List<Integer> treePath(TreeNode root){
		if(root==null) return null;
		Wrapper res=helper(root);
		return res.path;
		
	}
	public Wrapper helper(TreeNode node){
		if(node==null) return new Wrapper();
		Wrapper l=helper(node.left);
		Wrapper r=helper(node.right);
		Wrapper cur=new Wrapper();
		if(l.min<=r.min){
			cur.path.addAll(l.path);
		}
		else cur.path.addAll(r.path);
		cur.min=Math.min(Math.min(node.val,r.min),l.min);
		cur.path.add(node.val);
		return cur;
	}
	class Wrapper{
		int min;
		List<Integer> path;
		Wrapper(){
			min=Integer.MAX_VALUE;
			path=new ArrayList<>();
		}
	}
	public static void main(String[] args){
		MinPathInTree test=new MinPathInTree();
		TreeNode root=new TreeNode(5);
		TreeNode node10=new TreeNode(10);
		TreeNode node3=new TreeNode(3);
		TreeNode node1=new TreeNode(1);
		TreeNode node7=new TreeNode(7);
		TreeNode node8=new TreeNode(8);
		root.left=node10;
		root.right=node3;
		node10.left=node1;
		node10.right=node7;
		node3.left=node8;
		List<Integer> res=test.treePath(root);
		for(int i:res){
			System.out.println(i);
		}
	}
}
