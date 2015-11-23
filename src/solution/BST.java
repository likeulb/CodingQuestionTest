package solution;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BST {
	public BSTNode root;
	
	public BSTNode create(){
		Scanner sc = new Scanner(System.in);
		BSTNode root = new BSTNode(sc.nextInt());
		BSTNode pre = root;
		while(sc.hasNextInt()){
			BSTNode node = new BSTNode(sc.nextInt());
			if(pre.left==null){
				pre.left = node;
			}
			else if(pre.right==null){
				pre.right=node;
			}
			else{
				pre=pre.left;
				pre.left=node;
			}
		}
		return root;
	}
	public BSTNode createBST(){
		Scanner sc = new Scanner(System.in);
		BSTNode root = new BSTNode(sc.nextInt());
		
		while(sc.hasNextInt()){
			insert(root, sc.nextInt());
		}
		return root;
	}
	public void printNode(BSTNode node){
		if(node==null) return;
		Queue<BSTNode> que = new LinkedList<BSTNode>();
		que.add(node);
		while(!que.isEmpty()){
			BSTNode cur = que.poll();
			System.out.println(cur.val);
			if(cur.left!=null){
				que.add(cur.left);
			}
			if(cur.right!=null){
				que.add(cur.right);
			}
		}
	}
	
	public int countLevelK(BSTNode node, int k){
		if(node==null||k==0) return 0;
		if(k==1) return 1;
		return countLevelK(node.left, k-1)+countLevelK(node.right, k-1);
	}
	
	public int countLeave(BSTNode node){
		if(node==null) return 0;
		if(node.left==null&&node.right==null)
			return 1;
		return countLeave(node.left)+countLeave(node.right);
	}
	
	public void insert(BSTNode node, int x){
		if(node.val==x) return;
		if(node.val<x){
			if(node.right==null){
				node.right=new BSTNode(x);
				return;
			}
			else{
				insert(node.right,x);
			}
		}
		else{
			if(node.left==null){
				node.left=new BSTNode(x);
				return;
			}
			else{
				insert(node.left,x);
			}
		}
	}
	
	public static void main(String[] args){
		BST tree = new BST();
		BSTNode root = tree.createBST();
		tree.printNode(root);
		System.out.println(tree.countLevelK(root, 2));
		tree.insert(root, 4);
		tree.printNode(root);
		System.out.println(tree.countLeave(root));
	}
	
public class BSTNode{
	int val;
	BSTNode left;
	BSTNode right;
	public BSTNode(int x){
		val=x;
		left=null;
		right=null;
	}
		
}

}

