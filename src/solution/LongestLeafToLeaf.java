package solution;

public class LongestLeafToLeaf {
	int path;
	public int longestLeftToLeaf(TreeNode root){
		if(root==null) return 0;
		path=0;
		helper(root);
		return path;
	}
	public int helper(TreeNode root){
		if(root==null) return 0;
		int l=helper(root.left);
		int r=helper(root.right);
		path=Math.max(l+r+1, path);
		return Math.max(l,r)+1;
	}
}
