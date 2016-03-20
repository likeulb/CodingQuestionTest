package geeksforgeeks;

public class AVLTree {
	TreeNode root;
	TreeNode rightRotate(TreeNode y){
		TreeNode x=y.left;
		TreeNode T2=x.right;
		
		x.right=y;
		y.left=T2;
		
		y.height=Math.max(height(y.left),height(y.right))+1;
		x.height=Math.max(height(x.left),height(x.right))+1;
		
		return x;
		
	}
	TreeNode leftRotate(TreeNode y){
		TreeNode x=y.right;
		TreeNode T2=x.left;
		
		x.left=y;
		y.right=T2;
		
		y.height=Math.max(height(y.left),height(y.right))+1;
		x.height=Math.max(height(x.left),height(x.right))+1;
		
		return x;
		
	}
	int height(TreeNode node){
		if(node==null) return 0;
		return node.height;
	}
	int getBalance(TreeNode node){
		if(node==null) return 0;
		return height(node.left)-height(node.right);
	}
	TreeNode insert(TreeNode node, int key){
		if(node==null){
			node=new TreeNode(key);
			return node;
		}
		if(node.val<key){
			node.right=insert(node.right,key);
		}
		else node.left=insert(node.left,key);
		
		node.height=Math.max(height(node.left),height(node.right))+1;
		
		int balance=getBalance(node);
		if(balance>1&&key<node.left.val){
			return rightRotate(node);
		}
		if(balance>1&&key>node.left.val){
			node.left=leftRotate(node.left);
			return rightRotate(node);
		}
		if(balance<-1&&key>node.right.val){
			return leftRotate(node);
		}
		if(balance<-1&&key<node.right.val){
			node.right=rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
		
		
	}
	TreeNode deleteNode(TreeNode node, int key){
		if(node==null) return null;
		if(key<node.val){
			node.left=deleteNode(node.left,key);
		}
		else if(key>root.val){
			node.right=deleteNode(node.right,key);
		}
		else{
			if(node.left==null&&node.right==null){
				node=null;
			}
			else if(node.left==null||node.right==null){
				if(node.left!=null){
					node.val=node.left.val;
					node.left=null;
				}
				else{
					node.val=node.right.val;
					node.right=null;
				}
			}
			else{
				TreeNode tmp=minNode(node.right);
				node.val=tmp.val;
				node.right=deleteNode(node.right,tmp.val);
			}
		}
		if(node==null) return node;
		node.height=Math.max(height(node.left),height(node.right))+1;
		int balance = getBalance(node);
		if(balance>1&&getBalance(node.left)>=0){
			return rightRotate(node);
		}
		if(balance>1&&getBalance(node)<0){
			node.left=leftRotate(node.left);
			return rightRotate(node);
		}
		if(balance<-1&&getBalance(root.right)<=0){
			return leftRotate(root);
		}
		if(balance<-1&&getBalance(root.right)>0){
			root.right=rightRotate(root.right);
			return leftRotate(root);
		}
		return root;
	}
	TreeNode minNode(TreeNode node){
		TreeNode p=node;
		while(p.left!=null){
			p=p.left;
		}
		return p;
	}
}
