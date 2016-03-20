package hulu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class HuluPhone {
	//can we use subset of s to form p?
	public boolean canPermute(String s, String p){
		HashMap<Character, Integer> map =new HashMap<>();
		for(char c:p.toCharArray()){
			if(map.containsKey(c)){
				map.put(c,map.get(c));
			}
			else map.put(c, 1);
		}
		for(char c:s.toCharArray()){
			if(map.containsKey(c)){
				map.put(c, map.get(c)-1); 
			}
		}
		for(char c:map.keySet()){
			if(map.get(c)>0) return false;
		}
		return true;
	}
	public void printTree(TreeNode root){
		if(root==null) return;
		helperPrint(root, "node1", 0);
		
	}
	public void helperPrint(TreeNode root, String cur,int level){
		if(root==null) return;
		System.out.println(cur);
		if(root.left!=null){
			if(level==0)
				helperPrint(root.left, "|-"+cur+".1",level+1);
			else helperPrint(root.left, "|  "+cur+".1",level+1);
		}
		if(root.right!=null){
			if(level==0)
				helperPrint(root.right, "|-"+cur+(root.left==null?".1":".2"),level+1);
			else helperPrint(root.right, "|  "+cur+(root.left==null?".1":".2"),level+1);
		}
	}
	public int calculator(String s){
		if(s==null||s.length()==0) return 0;
		int result=0;
		int num=0;
		int preNum=0;
		char preChar='+';
		for(int i=0;i<s.length();i++){
			char cur=s.charAt(i);
			if(Character.isDigit(cur)){
				num=num*10+Character.getNumericValue(cur);
			}
			else{
				if(cur=='+'){
					if(preChar=='*'){
						result+=preNum*num;
						preNum=0;
						
					}
					else{
						result+=num;
						
					}
				}
				else{
					if(preChar=='*'){
						preNum*=num;
						
					}
					else{
						preNum=num;
						
					}
				}
				num=0;
				preChar=cur;
			}
			
		}
		if(preNum!=0||num!=0){
			if(preChar=='+'){
				result+=num;
			}
			else{
				result+=preNum*num;
			}
		}
		return result;
		
	}
	public class TreeNode{
		int val;
		TreeNode left,right;
		TreeNode(int v){
			val=v;
		}
	}
	public void print(TreeNode root){
		if(root==null) return;
		Queue<TreeNode> que = new LinkedList<>();
		que.add(root);
		while(!que.isEmpty()){
			int size=que.size();
			for(int i=0;i<size;i++){
				TreeNode cur = que.poll();
				System.out.print(cur.val+",");
				if(cur.left!=null) que.add(cur.left);
				if(cur.right!=null) que.add(cur.right);
			}
			System.out.println("");
		}
	}
	public static void main(String[] args){
		HuluPhone test = new HuluPhone();
		System.out.println(test.calculator("2*22+4*5+1*5"));
		TreeNode node1=test.new TreeNode(2);
		TreeNode node2=test.new TreeNode(5);
		TreeNode node3=test.new TreeNode(9);
		node1.left=test.new TreeNode(3);
		node1.right=node2;
		node1.left.right=node3;
		test.print(node1);
		test.printTree(node1);
	
	}
}
