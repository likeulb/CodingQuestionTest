package ms;

import java.util.*;

public class TreeRelated {
	//bst里找出所有在range里的数字
	static List<Integer> numberInRange(TreeNode root, int left, int right){
		List<Integer> result = new ArrayList<>();
		if(root==null) return result;
		numberInRangeDfs(result,root,left,right);
		return result;
	}
	static void numberInRangeDfs(List<Integer> result, TreeNode node, int left, int right){
		if(node==null) return;
		if(node.val<left){
			numberInRangeDfs(result,node.right,left,right);
		}
		else if(node.val>right){
			numberInRangeDfs(result,node.left,left,right);
		}
		else{
			result.add(node.val);
			numberInRangeDfs(result,node.left,left,right);
			numberInRangeDfs(result,node.right,left,right);
		}
	}

}
