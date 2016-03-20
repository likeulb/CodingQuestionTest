package google2016;
import commonClass.*;
public class TreeRelated {
	
	//然后给定一个Node，给出他的inorder predecessor. 
	public TreeNode inorderPredecessor(TreeNode p){
		if(p==null) return null;
		if(p.left!=null){
			TreeNode t=p.left;
			while(t!=null&&t.right!=null){
				t=t.right;
			}
			return t;
		}
		else{
			TreeNode parent=p.parent;
			while(parent!=null&&parent.right!=p){
				p=parent;
				parent=parent.parent;
			}
			return parent;
		}
	}
	//然后给定一个Node，给出他的inorder successor.
	public TreeNode inorderSuccessor(TreeNode p){
		if(p==null) return null;
		if(p.right!=null){
			p=p.right;
			while(p.left!=null){
				p=p.left;
			}
			return p;
		}
		else{
			TreeNode parent=p.parent;
			while(parent!=null&&parent.left!=p){
				p=parent;
				parent=parent.parent;
			}
			return parent;
		}
	}
	//postorder的遍历下返回下一个值
	public TreeNode postoderNext(TreeNode p){
		if(p==null||p.parent==null) return null;
		if(p.parent.left==p){
			TreeNode r=p.parent.right;
			if(r==null) return p.parent;
			while(r.left!=null||r.right!=null){
				if(r.left!=null){
					r=r.left;
				}
				else if(r.right!=null){
					r=r.right;
				}
			}
			return r;
		}
		else{
			return p.parent;
		}
	}
	//remove a subtree if sum of itself and all nodes below It is zero
	public  Wrapper removeSubtree(TreeNode root){
		if(root==null) return new Wrapper(null, 0);
		Wrapper l=removeSubtree(root.left);
		Wrapper r=removeSubtree(root.right);
		root.left=l.node;
		root.right=r.node;
		int sum=l.sum+r.sum+root.val;
		if(sum==0){
			return new Wrapper(null, 0);
		}
		else return new Wrapper(root, sum);
	}
	public void printTree(TreeNode root) {
        if (root == null) {
                return;
        }
        printTree(root.left);
        System.out.print(root.val + " ");
        printTree(root.right);
}
	
	public static void main(String[] args){
		TreeRelated test=new TreeRelated();
		
		TreeNode root1 = new TreeNode(5);
		root1.left = new TreeNode(-7);
		root1.right = new TreeNode(6);
		root1.left.left = new TreeNode(5);
		root1.left.right = new TreeNode(2);
		root1.right.left = new TreeNode(5);
		root1.right.left.left = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        root1.right.right.left = new TreeNode(1);
        root1.right.right.right = new TreeNode(-8);
        
        test.removeSubtree(root1);
        test.printTree(root1);

		/*
		TreeNode root=new TreeNode(1);
		TreeNode node2=new TreeNode(2);
		root.left=node2;
		node2.parent=root;
		TreeNode node3=new TreeNode(3);
		node2.left=node3;
		node3.parent=node2;
		TreeNode node4=new TreeNode(4);
		node2.right=node4;
		node4.parent=node2;
		TreeNode node5=new TreeNode(5);
		root.right=node5;
		node5.parent=root;
		TreeNode node6=new TreeNode(6);
		node5.left=node6;
		node6.parent=node5;
		System.out.println(test.postoderNext(node6).val);*/
		
	}
	class Wrapper{
		TreeNode node;
		int sum=0;
		Wrapper(TreeNode node, int s){
			this.node=node;
			this.sum=s;
		}
	}

}
