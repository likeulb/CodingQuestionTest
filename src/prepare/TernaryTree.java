package prepare;

import java.util.*;

public class TernaryTree {
	
	TernaryTreeNode root;
	
	public void insert(int v){

		if(root==null){
			root= new TernaryTreeNode(v);;
			return;
		}
		insertHelp(root,v);
		
	}
	
	public int findMin(TernaryTreeNode parent, TernaryTreeNode cur){
		int result = 0;
		if(cur.left==null){
			result=cur.val;
			if(parent.left==cur){
				parent.left=cur.right;
			}
			else{
				parent.right=cur.right;
			}
		}
		else
			result=findMin(cur, cur.left);
		
		return result;
		
		
	}
	public void insertHelp(TernaryTreeNode node, int v){
		if(v<node.val){
			if(node.left==null){
				node.left=new TernaryTreeNode(v);
			}
			else
				insertHelp(node.left,v);
		}
		else if(v>node.val){
			if(node.right==null){
				node.right=new TernaryTreeNode(v);
			}
			else
				insertHelp(node.right, v);
		}
		else{
			if(node.mid==null){
				node.mid=new TernaryTreeNode(v);
			}
			else
				insertHelp(node.mid, v);
		}
	}
	public void delete(int v){
		root = deleteHelp(root,v);
		
	}
	public TernaryTreeNode deleteHelp(TernaryTreeNode cur, int v){
		if(cur==null) return cur;
		if(v<cur.val){
			cur.left = deleteHelp(cur.left, v);
		}
		else if(v>cur.val){
			cur.right = deleteHelp(cur.right,v);
		}
		else{
			if(cur.mid!=null){
				cur.mid = deleteHelp(cur.mid,v);
			}
			else{
				if(cur.left==null){
					return cur.right;
				}
				else if(cur.right==null){
					return cur.left;
				}
				else{
					TernaryTreeNode min = findMinNode(cur.right);
					System.out.println(min.val);
					cur.val=min.val;
					cur.mid = min.mid;
					System.out.println(cur.mid.val);
					cur.right = deleteMin(cur.right, min);					
				}
			}
			
		}
		return cur;
	}
	
	TernaryTreeNode deleteMin(TernaryTreeNode p, TernaryTreeNode min){
		TernaryTreeNode node = p;
		if(p==min) return null;
		while(node.left!=min){
			node=node.left;
		}
		node.left=min.right;
		return p;
	}

	public TernaryTreeNode findMinNode(TernaryTreeNode node){
		while(node.left!=null){
			node=node.left;
		}
		return node;
	}
	public void printTree(){
		if(root==null){
			System.out.println("root is null");
			return;
		}
		Queue<TernaryTreeNode> que = new LinkedList<>();
		que.add(root);
		System.out.println(root.val);
		while(!que.isEmpty()){
			int size = que.size();
			for(int i=0;i<size;i++){
				TernaryTreeNode cur = que.poll();
				if(cur.left!=null){
					que.add(cur.left);
					System.out.print(cur.val+"left"+cur.left.val+";");
				}
				if(cur.mid!=null){
					que.add(cur.mid);
					System.out.print(cur.val+"mid"+cur.mid.val+";");
				}
				if(cur.right!=null){
					que.add(cur.right);
					System.out.print(cur.val+"right"+cur.right.val+";");
				}
			}
			System.out.println("");
		}
	}
	
	
	public class TernaryTreeNode{
		int val;
		TernaryTreeNode left;
		TernaryTreeNode mid;
		TernaryTreeNode right;
		TernaryTreeNode(int val){
			this.val=val;
			left=null;
			mid=null;
			right=null;
		}
	}
	public static void main(String[] args){
		TernaryTree test = new TernaryTree();
		int[] a = {2,2,1};
		for(int i:a){
			test.insert(i);
		}
		test.printTree();
		test.delete(3);
		test.printTree();
		test.delete(2);
		test.printTree();
		test.delete(3);
		test.printTree();
//		test.delete(7);
//		test.printTree();
//		test.delete(9);
//		test.printTree();
		
	}


}

