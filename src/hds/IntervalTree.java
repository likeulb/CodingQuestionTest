package hds;

public class IntervalTree {
	IntervalTreeNode root;
	public IntervalTreeNode insertInterval(Interval i){
		if(root==null){
			root=new IntervalTreeNode(i.start,i.end);
			return root;
		}
		return insertHelper(root,i);
	}
	public IntervalTreeNode insertHelper(IntervalTreeNode node, Interval i){
		if(node==null){
			node=new IntervalTreeNode(i.start,i.end);
			return node;
		}
		if(i.start==node.low&&i.end==node.high){
			node.count++;
		}
		else if(i.start<node.low){
			node.left=insertHelper(node.left,i);
		}
		else node.right=insertHelper(node.right,i);
		node.max=Math.max(i.end,node.max);
		return node;
	}
	public int countInterval(Interval i){
		IntervalTreeNode p=root;
		return countHelper(i,p);
	}
	
	//有问题！！
	public int countHelper(Interval i, IntervalTreeNode p){
		if(p==null) return 0;
		int res=0;
		if(i.start>=p.low&&i.end<p.high) return p.count;
		
		if(i.start<p.low) return countHelper(i,p.left);
		if(i.start>=p.low){
			if(i.start<p.high)
				res+=p.count;
		}
		if(p.left!=null&&p.left.max>=p.low) res+=countHelper(i,p.left);
		res+=countHelper(i,p.right);
		
		return res;
		
	}
	
	
	class IntervalTreeNode{
		int low;
		int high;
		int count=0;
		int max=0; //keep track of the maximum end point of sub trees
		IntervalTreeNode left, right;
		IntervalTreeNode(int low, int high){
			this.low=low;
			this.high=high;
			this.max=high;
			count=1;
		}
	}
	public static void main(String[] args){
		Interval i1=new Interval(3,5);
		Interval i2=new Interval(3,5);
		Interval i3=new Interval(5,8);
		Interval i4=new Interval(2,7);
		Interval i5=new Interval(6,7);
		IntervalTree test=new IntervalTree();
		test.insertInterval(i1);
		test.insertInterval(i2);
		test.insertInterval(i3);
		test.insertInterval(i4);
		test.insertInterval(i5);
		System.out.println(test.countInterval(new Interval(4,10)));
		
	}
}
