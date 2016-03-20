package facebook;

public class TreeToCirularList {
	//第二轮， coding第一题binary tree -> circular doubly LinkedList.
	public DoubleLinkedList convertToList(TreeNode root){
		if(root==null) return null;
		DoubleLinkedList left=convertToList(root.left);
		DoubleLinkedList right=convertToList(root.right);
		DoubleLinkedList cur=new DoubleLinkedList(root.val);
		if(left==null&&right==null){
			cur.next=cur;
			cur.pre=cur;
			return cur;
		}
		DoubleLinkedList left_tail=left==null?null:left.pre;
		DoubleLinkedList right_tail=right==null?null:right.pre;
		if(left_tail==null){  //跟左边join是插在左边的tail.next
			concat(right.pre,cur);
		}
		else{
			concat(left.pre,cur);
		}
		
		if(right_tail==null){ //跟右边join是把右边放到cur的next
			concat(cur,left);
		}
		else concat(cur,right);
		
		if(left_tail!=null&&right_tail!=null){ //两边都不空，右边tail.next=left
			concat(right_tail,left);
		}
		return left==null?cur:left; //返回最左边的node
		
		
	}
	public void concat(DoubleLinkedList node1, DoubleLinkedList node2){
		if(node1==null||node2==null) return;
		node1.next=node2;
		node2.pre=node1;
		
	}
	//BST to doubly linked list
	TreeNode pre;
	TreeNode head;
	public TreeNode BSTToList(TreeNode root){ 
		if(root==null) return null;
		pre=null;
		head=null;
		BSTToListHelper(root);
		return head;
	}
	public void BSTToListHelper(TreeNode root){
		if(root==null) return;
		BSTToListHelper(root.left);
		if(pre!=null){
			pre.right=root;
			root.left=pre;
		}
		else{
			head=root;
		}
		pre=root;
		BSTToListHelper(root.right);
		
		
	}

	
	public static void main(String[] args){
		TreeToCirularList test=new TreeToCirularList();
		TreeNode root=new TreeNode(5);
		TreeNode node10=new TreeNode(10);
		TreeNode node3=new TreeNode(3);
		root.left=node3;
		root.right=node10;
		
		TreeNode t=test.BSTToList(root);
		while(t!=null){
			if(t.left!=null) System.out.println(t.left.val);
			System.out.println(t.val);
			t=t.right;
		}
		
		/*
		TreeNode node1=new TreeNode(1);
		TreeNode node7=new TreeNode(7);
		TreeNode node8=new TreeNode(8);
		
		node10.left=node1;
		node10.right=node7;
		node3.left=node8;
		DoubleLinkedList res=test.convertToList(root);
		DoubleLinkedList p=res;
		do{
			System.out.println(p.val);
			p=p.next;
		}while(p!=res);*/
	}
}
