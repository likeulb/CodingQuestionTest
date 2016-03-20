package groupon;

import java.util.*;

public class ConstructTreeFromPair {
	
	public TreeNode constructTree(Pair[] arr){
		if(arr==null||arr.length==0) return null;
		HashMap<Integer, List<Integer>> adj= new HashMap<>();
		HashMap<Integer,Integer> degree = new HashMap<>();
		for(Pair p:arr){
			if(!adj.containsKey(p.i)){
				adj.put(p.i, new ArrayList<Integer>());
			}
			adj.get(p.i).add(p.j);
			if(!degree.containsKey(p.j)){
				degree.put(p.j,1);
			}
			else degree.put(p.j, degree.get(p.j)+1);
			
			if(!degree.containsKey(p.i)){
				degree.put(p.i, 0);
			}
		}
		Queue<Integer> que=new LinkedList<>();
		for(int i:degree.keySet()){
			if(degree.get(i)==0){
				que.add(i);
			}
		}
		if(que.size()>1) return null;
		TreeNode root=new TreeNode(que.peek());
		Queue<TreeNode> nodes = new LinkedList<>();
		nodes.add(root);
		while(!que.isEmpty()){
			for(int i=0;i<que.size();i++){
				int cur=que.poll();
				TreeNode c=nodes.poll();
				if(adj.containsKey(cur)){
					for(int j:adj.get(cur)){
						degree.put(j,degree.get(j)-1);
						if(degree.get(j)!=0) return null;
						TreeNode tmp=new TreeNode(j);
						que.add(j);
						c.next.add(tmp);
						nodes.add(tmp);
					}
				}
			}
		}
		return root;
		
	}
	public boolean subSetSumTo0(int[] arr){
		HashMap<Integer,Integer> map = new HashMap<>();
		int min=1;
		for(int i:arr){
			if(!map.containsKey(i)){
				map.put(i, 1);
			}
			else map.put(i, map.get(i)+1);
			min=Math.min(i,min);
		}
		if(min>0) return false;
		if(map.containsKey(0)) return true;
		return dfs(map,0);
	}
	public boolean dfs(HashMap<Integer,Integer> map, int sum){
		
		if(map.containsKey(-sum)&&map.get(-sum)>0) return true;

		for(int i:map.keySet()){
			if(map.get(i)>0){
				map.put(i,map.get(i)-1);
				if(dfs(map,sum+i))
					return true;
				map.put(i, map.get(i)+1);
			}
		}
		return false;
	}
	public List<List<Integer>> pathSumfromAnyNode(TreeNode2 root, int target){
		List<List<Integer>> result = new ArrayList<>();
		if(root==null) return result;
		Queue<TreeNode2> que=new LinkedList<>();
		que.add(root);
		while(!que.isEmpty()){
			TreeNode2 cur = que.poll();
			pathSum(result, new ArrayList<Integer>(),root,0,target);
			if(cur.left!=null){
				que.add(cur.left);
			}
			if(cur.right!=null){
				que.add(cur.right);
			}
		}
		
		return result;
	}
	public void pathSum(List<List<Integer>> result, List<Integer> item, TreeNode2 p,int sum, int target){
		if(sum==target){
			result.add(new ArrayList<>(item));
			return;
		}
		if(sum>target){
			return;
		}
		if(p==null) return;
		pathSum(result,item,p.left,sum+p.val,target);
		pathSum(result,item,p.right,sum+p.val,target);
	}
	
	public List<List<Integer>> pathSumfromAnyNode2(TreeNode2 root, int target){
		List<List<Integer>> result = new ArrayList<>();
		if(root==null) return result;
		pathSum2(result, new ArrayList<Integer>(),root,0,target); //path can not from bottom to top
		return result;
	}
	public void pathSum2(List<List<Integer>> result, List<Integer> item, TreeNode2 p,int sum, int target){
		if(sum==target){
			result.add(new ArrayList<Integer>(item));
			return;
		}
		if(p==null){
			return;
		}
		item.add(p.val);
		pathSum2(result,item,p.left,sum+p.val,target);
		pathSum2(result,item,p.right,sum+p.val,target);
		item.remove(item.size()-1);
		if(item.size()>1)
			pathSum2(result,new ArrayList<Integer>(),p,0,target);
	}
	public Point findIntersection(Point p11,Point p12, Point p21, Point p22){
		int gcd1=gcd(p11.x-p12.x,p11.y-p12.y);
		int gcd2=gcd(p21.x-p22.x,p22.y-p22.y);
		int k11=1,k12=1,k21=1,k22=1;
		if(gcd1!=0){
			k11=(p11.y-p12.y)/gcd1;
			k12=(p11.x-p12.x)/gcd1;
		}
		if(gcd2!=0){
			k21=(p22.y-p22.y)/gcd2;
			k22=(p21.x-p22.x)/gcd2;
		}
		if(gcd1==0&&gcd2==0){
			if(p11.x==p21.x) return p11;
		}
		int b1=0;
		if(gcd1!=0){
			b1=p11.y-k11/k12*p11.x;
		}
		int b2=0;
		if(gcd2!=0){
			b2=p21.y-k21/k22*p21.x;
		}
		if(gcd1==0){
			int y=k21/k22*p11.x+b2;
			return new Point(p11.x,y);
		}
		else if(gcd2==0){
			int y=k11/k12*p21.x+b1;
			return new Point(p21.x,y);
		}
		else{
			int x=(b1-b2)/((k11/k12)-(k21/k22));
			int y=(k11/k12)*x+b1;
			return new Point(x,y);
		}
		
	}
	public int gcd(int a, int b){
		if(b==0) return a;
		return gcd(b,a%b);
	}
	class Point{
		int x;
		int y;
		Point(int x,int y){
			this.x=x;
			this.y=y;
		}
	}

	public class Pair{
		int i;
		int j;
		Pair(int i, int j){
			this.i=i;
			this.j=j;
			}
	}
	public class TreeNode{
		int val;
		List<TreeNode> next;
		TreeNode(int i){
			val=i;
			next=new ArrayList<>();
		}
	}
	public class TreeNode2{
		int val;
		TreeNode2 left, right;
		TreeNode2(int i){
			val=i;
			
		}
	}
	public static void main(String[] args){
		ConstructTreeFromPair test=new ConstructTreeFromPair();
		int[] a={-6,1,1,4,5};
		System.out.println(test.subSetSumTo0(a));
	}

}

