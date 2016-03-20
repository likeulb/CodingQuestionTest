package hds;

public class SegmentTree {
	SegmentTreeNode root;
//	public SegmentTreeNode insert(Interval i){
//		if(root==null){
//			root=new SegmentTreeNode(i.start, i.end, 1);
//			return root;
//		}
//		return insertHelper(root, i);
//	}
	//有问题！
//	public SegmentTreeNode insertHelper(SegmentTreeNode p, Interval i){
//		if(p==null) return new SegmentTreeNode(i.start, i.end, 1);
//		if(i.start<=p.start&&i.end<p.end){
//			p.count++;
//			return p;
//		}
//		if(i.start>=p.end){
//			p.right=insertHelper(p.right,i);
//		}
//		else if(i.end<p.start){
//			p.right=insertHelper(p.left,i);
//		}
//		else{
//			
//		}
//		
//	}
	class SegmentTreeNode{
		SegmentTreeNode left, right;
		int start, end;
		int count;
		SegmentTreeNode(int start, int end, int c){
			this.start=start;
			this.end=end;
			count=c;
		}
	}
}
