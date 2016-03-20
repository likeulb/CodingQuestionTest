package prepare;

import java.util.*;

public class AmazonOA {
	
	public static boolean isRotate(String s1, String s2){
		if(s1==null||s1.length()==0) return s2==null;
		if(s1.length()==s2.length()){
			String s = s1+s1;
			return isSubstring(s,s2);
		}
		return false;
	}
	
	public static boolean isGrayCode(byte term1,byte term2){
		byte x = (byte)(term1^term2);
		int count=0;
		while(x!=0){
			x=(byte)(x&(x-1));
			count++;
		}
		return count==1;
		
	}
	//removeVowel
	public String removeVowel(String s){
		String v = "aeiou";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length();i++){
			if(v.indexOf(s.charAt(i))!=-1)
				continue;
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	//shortest job first
	public static double shortestJobFirst(int[] request, int[] duration){
		int curTime=0;
		int waitTime=0;
		HashSet<Integer> hasRun = new HashSet<>();
		for(int i=0;i<request.length;i++){
			int minDuration = Integer.MAX_VALUE;
			int minIndex = -1;
			for(int j=0;j<request.length;j++){
				if(hasRun.contains(j))
					continue;
				if(duration[j]<minDuration){
					minDuration=duration[j];
					minIndex=j;
				}
				else{
					break;
				}			
			}
			if(minIndex==-1){
				minIndex=i;
				curTime=request[minIndex];
			}
			waitTime+=request[minIndex]-curTime;
			curTime+=duration[minIndex];
			hasRun.add(minIndex);
		}
		return waitTime*1.0/request.length;
	}
	
	
	public class Job{
		int start;
		int runTime;
		public Job(int s, int r){
			this.start=s;
			this.runTime=r;
		}
	}

	
		
	public double roundRobin(int[] arrival, int[] run, int q){
		if(arrival==null||run==null||arrival.length==0||run.length==0||arrival.length!=run.length)
			return 0;
		Queue<Job> que = new LinkedList<>();
		int curTime=0;
		int waitTime=0;
		int index = 0;
		while(!que.isEmpty()||index<arrival.length){
			if(!que.isEmpty()){
				Job cur = que.poll();
				if(cur.start<curTime){
					waitTime+=curTime-cur.start;
					curTime+=Math.min(q, cur.runTime);
				}
				else{
					curTime = cur.start+Math.min(q, cur.runTime);
				}
				while(index<arrival.length&&arrival[index]<curTime){
					que.offer(new Job(arrival[index],run[index]));
					index++;
				}
				if(cur.runTime>q){
					que.offer(new Job(curTime, cur.runTime-q));
				}
			}
			else{
				que.offer(new Job(arrival[index],run[index]));
				curTime = arrival[index++];
			}
		}
		return 1.0*waitTime/arrival.length;
	}
	
	
	public static boolean isSubstring(String s1,String s2){
		if(s1==null||s1.length()==0) return s2==null;
		if(s1.length()<s2.length()) return false;
		int i=0;
		int j=0;
		while(i<s1.length()){
			while(j<s2.length()&&s1.charAt(i)==s2.charAt(j)){
				i++;
				j++;
			}
			if(j==s2.length())
				return true;
			j=0;
			i++;
		}
		return false;
		
	}
	public boolean isInBST(TreeNode root, TreeNode p){
		if(root==null)
			return false;
		if(p.val==root.val)
			return true;
		else if(p.val<root.val)
			return isInBST(root.left, p);
		else
			return isInBST(root.right,p);
	}
	public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q){
		if(root==null||root.val==p.val||root.val==q.val)
			return root;
		if(root.val<p.val&&root.val<q.val)
			return lowestCommonAncestorBST(root.right, p, q);
		else if(p.val<root.val&&q.val<root.val)
			return lowestCommonAncestorBST(root.left, p, q);
		else
			return root;
	}



	public boolean isInTree(TreeNode root, TreeNode p){
		if(root==null) return false;
		if(root==p) return true;
		return isInTree(root.left, p)||isInTree(root.right, p);
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
		if(root==null||root==q||root==p) return root; //不是bst,所以有可能出现重复的node
		TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
		TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
		if(leftNode!=null&&rightNode!=null)
			return root;
		else
			return leftNode==null?rightNode:leftNode;
	}
	
	class TreeNode{
		TreeNode left, right;
		int val;
		TreeNode(int v){
			this.val=v;
		}
	}
	public static void main(String[] args){
		System.out.println(Integer.toBinaryString(-2));
		System.out.println((Integer.MIN_VALUE)^0>>1);
		System.out.println(Integer.toBinaryString(-1)+"\n");
		System.out.println(Integer.toBinaryString(127));
		System.out.println(AmazonOA.isGrayCode((byte)-1, (byte)127));
	}
}
