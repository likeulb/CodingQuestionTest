package googleOnSite;

import java.util.*;

public class TreeNodeArray {
	TreeNode[] arr;
	HashMap<TreeNode, List<TreeNode>> map;
	public TreeNodeArray(int[] a){
		arr=new TreeNode[a.length];
		for(int i=0;i<a.length;i++){
			arr[i]=new TreeNode(i*7+1,a[i]);
		}
		map=new HashMap<>();
		isTree();
		sum(arr[3]);
		System.out.println(bottomUpSom(3));
	}
	public boolean isTree(){
		TreeNode root = null;
		int countRoot=0;
		for(TreeNode d: arr){
			if(d.parent==-1&&countRoot>=1)
				return false;
			if(d.parent==-1){
				root = d;
				countRoot=1;
			}
			if(d.parent==-1) continue;
			TreeNode par= arr[d.parent];
			if(!map.containsKey(par)){
				map.put(par, new ArrayList<TreeNode>());
			}
			map.get(par).add(d);
		}
	if(countRoot==0) return false;
	HashSet<TreeNode> checked = new HashSet<>();
	if(dfs(checked, root))
		return false;
	return true;

	}
	boolean dfs(HashSet<TreeNode> checked, TreeNode p){
		if(checked.contains(p)) return false;
		checked.add(p);
		if(map.containsKey(p)){
			for(TreeNode c: map.get(p)){
				if(!dfs(checked, c))
					return false;
			}
		}
		return true;
	}

	public void remove(TreeNode toRemove){
		removeHelp(toRemove);
		int j=0;
		for(int i=0;i<arr.length;i++){
			if(arr[i].parent!=-2){
				arr[j]=arr[i];
				j++;
			}
		}
	}
	public void removeHelp(TreeNode toRemove){
		toRemove.parent = -2;
		if(map.containsKey(toRemove)){
		for(TreeNode d:map.get(toRemove)){
			removeHelp(d);
			}
		}
	}
	public int bottomUpSom(int pindex){
		HashMap<Integer, Integer> treeLevel = new HashMap<>();
		int maxLevel = 0;
		for(int i=0;i<arr.length;i++){
			
				int level = countLevel(i);
				System.out.println(level);
				treeLevel.put(i, level);
				maxLevel = Math.max(maxLevel, level);
			
		}
		int targetLevel=treeLevel.get(pindex);
		HashMap<Integer, List<Integer>> LevelNode = new HashMap<>();
		for(int index:treeLevel.keySet()){
			int level  = treeLevel.get(index);
			if(!LevelNode.containsKey(level)){
				LevelNode.put(level,new ArrayList<Integer>());
			}
			LevelNode.get(level).add(index);
		}
		
		HashMap<Integer,Integer> indexSum = new HashMap<>();
		for(int index: LevelNode.get(maxLevel)){
			indexSum.put(index, arr[index].val);
		}
		int curLevel=maxLevel-1;
		while(curLevel>=targetLevel){
			List<Integer> nodes = LevelNode.get(curLevel);
			for(int i:nodes){
				if(!indexSum.containsKey(i)){
					indexSum.put(i, arr[i].val);
				}
				for(int j:LevelNode.get(curLevel+1)){
					if(indexSum.containsKey(arr[j].parent)){
						indexSum.put(arr[j].parent, indexSum.get(arr[j].parent)+indexSum.get(j));
					}
				}
				curLevel--;
			}
		}
		return indexSum.get(pindex);
		
	}
	public int countLevel(int index){
		if(arr[index].parent==-1)
			return 0;
		return countLevel(arr[index].parent)+1;
	}
	public int sum(TreeNode p){
		int[] s = new int[1];
		sumHelper(p, s);
		System.out.println(s[0]);
		return s[0];
	}
	public void sumHelper(TreeNode p, int[] s){
		System.out.println(p.val);
		s[0]+=p.val;
		if(map.containsKey(p)){
		for(TreeNode d: map.get(p))
			sumHelper(d, s);
		}
	}

	class TreeNode{
		int val;
		int parent;
		TreeNode(int v, int p){
		this.val =v;
		this.parent = p;
		}
	}
	public static void main(String[] arg){
		int[] arr = {-1,0,0,2,1,2,5,3,4,3};
		TreeNodeArray Test = new TreeNodeArray(arr);
		System.out.println(Test.isTree());
		
		
	}

}
