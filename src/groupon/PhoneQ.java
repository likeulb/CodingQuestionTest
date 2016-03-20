package groupon;

import java.util.*;

public class PhoneQ {
	
	/*Balanced String
	  
	  Q: Given a String s, determine whether it is balanced or not.
	  
	  Definition of balanced:
	  1) "" or ":"
	  2) Balanced String + Balanced String
	  3) "(" + Balanced String + ")"
	  4) ":)" or ":("
	  */
	public boolean isBalancedString(String s){
		if(s==null||s.length()==0) return true;
		if(s.length()==2){
			if(s.equals("::")||s.equals(":)")||s.equals(":(")||s.equals("()"))
					return true;
			else return false;
		}
		if(s.length()==1){
			if(s.equals(":")) return true;
			return false;
		}
		
		if(s.charAt(0)=='('&&s.charAt(s.length()-1)==')'){
			if(isBalancedString(s.substring(1,s.length()-1)))
				return true;
		}
		for(int i=1;i<s.length();i++){
			if(isBalancedString(s.substring(0,i))&&isBalancedString(s.substring(i))) 
				return true;
		}
		return false;
	}
	public boolean isBalancedStringMemorizing(String s){
		if(s==null||s.length()==0) return true;
		HashMap<String, Boolean> map = new HashMap<>();
		map.put(":", true);
		map.put("::",true);
		map.put(":)", true);
		map.put(":(",true);
		map.put("()", true);
		isBalancedStringMemorizingHelper(s,map);
		if(map.containsKey(s)){
			return map.get(s);
		}
		return false;
	}
	public boolean isBalancedStringMemorizingHelper(String s, HashMap<String, Boolean> map){
		if(s==null||s.length()==0) return true;
		if(map.containsKey(s)) return map.get(s);
		if(s.length()==2||s.length()==1) return false;
		
		boolean res=false;

		if(s.charAt(0)=='('&&s.charAt(s.length()-1)==')'){
			if(isBalancedStringMemorizingHelper(s.substring(1,s.length()-1),map)){
				res=true;
				map.put(s, res);
			}
		}
		if(!map.containsKey(s)){
			for(int i=1;i<s.length();i++){
				if(isBalancedStringMemorizingHelper(s.substring(0,i),map)&&isBalancedStringMemorizingHelper(s.substring(i),map)) 
					res=true;
					map.put(s, res);
			}
		}
		if(!map.containsKey(s)){
			map.put(s,false);
		}
		return map.get(s);
	}
	
	
	public boolean isBalancedStringDP(String s){
		int n=s.length();
		boolean[][] dp=new boolean[n+1][n+1];
		dp[0][0]=true;
		for(int i=1;i<=s.length();i++){
			for(int j=i;j>=0;j--){
				String test = s.substring(j,i);
				if(test.length()==0||test.equals(":")||test.equals(":)")||test.equals(":(")){
					dp[j][i]=true;
				}
				else{
					if(i-j>=2){
						if(s.charAt(j)=='('&&s.charAt(i-1)==')'&&dp[j+1][i-1])
							dp[j][i]=true;
						else{
							for(int k=j+1;k<=i;k++){
								if(dp[j][k]&&dp[k][i]){
									dp[j][i]=true;
									break;
								}
							}
						}
					}
				}
			}
			
		}
		return dp[0][s.length()];
	}
	
	/*零钱问题

	1. 给一个整数值的金额(n cents)，返回最少总硬币数，用(quarter, dime, 5 cents,
	penny)
	解：直接用贪心策略。先算用多少quarter，再dime，再5 cents，再penny

	2. 还是一个金额(n cents)，但是硬币用自己定义的额度，比如[10, 7, 1]
	解：这个问题存在无解情况。比如给个额度3，但是硬币面值只有2的，这种情况fail，
	返回-1*/
	
	public int numOfCoinGreedy(int n){
		//use 25,10,5,1
		int num=0;
		int[] d = {25,10,5,1};
		
		for(int i:d){
			num+=n/i;
			n%=i;
			if(n==0) break;
		}
		System.out.println(num);
		return num;
		
	}
	
	public int numOfCoin(int[] d, int n){
		Arrays.sort(d);
		int[] dp = new int[n+1];
		for(int i=1;i<=n;i++){
			dp[i]=Integer.MAX_VALUE;
			for(int k=0;k<d.length;k++){
				if(d[k]<=i){
					dp[i]=Math.min(dp[i],dp[i-d[k]]+1);
				}
				else break;
			}
		}
		
		//print the coins based on dp array
		int num=n;
		List<Integer> list = new ArrayList<>();
		while(num>0){
			for(int i:d){
				if(num-i>=0&&dp[num-i]+1==dp[num]){
					list.add(i);
					num-=i;
					System.out.println(i);
				}
			}
		}
		
		return dp[n];
	}
	//use memorizing to print out coin choices
	public List<Integer> printCoints(int[] d, int n){
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		printCointsHelper(map, d, n);
		return map.get(n);
	}
	public int printCointsHelper(HashMap<Integer, List<Integer>> map, int[] d, int n){
		if(map.containsKey(n)){
			return map.get(n).size();
		}
		if(n==0){
			map.put(0, new ArrayList<Integer>());
			return 0;
		}
		int count=Integer.MAX_VALUE;
		List<Integer> cur = null;
		for(int i:d){
			if(i<=n){
				int tmp = printCointsHelper(map,d,n-i)+1;
				if(tmp<count){
					count=tmp;
					cur=new ArrayList<Integer>(map.get(n-i));
					cur.add(i);
				}
			}
		}
		map.put(n,cur);
		return count;

	}
	/*问如果每个buckets的上下届是给定的，怎么来求每个
buckets里面的count的。
例如，array A is
1, 200, 52, 2, 4, 1003

bucket的范围为，
1---100

101-- 1000

1001--2000
那么就等价于给你一个range vector, 表示每个bucket的下界， i.e. {1, 101, 1001}

问此时怎么求每个bucket里面元素的个数。
*/
	public int[] searchRange(int[] bucket, int[] input){
		//if sort input first;mlogm+logn(input size is m, bucket size is n, suppose m>n)
		//if check each element in the input array mlogn, maybe this one is better
		//suppose bucket is already sorted like {1,101,1001,2001};
		if(bucket==null||bucket.length==1) return new int[]{};
		int[] count = new int[bucket.length-1];
		for(int i:input){
			if(i<bucket[0]||i>=bucket[bucket.length-1]) continue;
			int index = findRange(bucket, i);
			System.out.println(index);
			count[index]++;
		}
		return count;
	}
	public int findRange(int[] bucket, int x){
		int left=0, right=bucket.length-1;
		while(left<right){
			int mid = left+(right-left)/2;
			if(x>=bucket[mid]&&x<bucket[mid+1]){
				return mid;
			}
			else if(x<bucket[mid]){
				right=mid;
			}
			else{
				left=mid+1;
			}
		}
		return -1;
	}
	//输入一浮点数，返回浮点数开方
	public double sqrt(double x, double p){
		double left=0,right=0;
		if(x>=1){
			left=1;
			right=x/2+1;
		}
		else{
			left=x;
			right=1;
		}
		while(left<=right){
			double mid = left+(right-left)/2;
			if((x-p)/mid<=mid&&(x+p)/mid>=mid) return mid;
			else if((x-p)/mid>mid){
				left=mid;
			}
			else right=mid;
		}
		return x;
	}
	//输出phone number， 要求是第一位不是0和1，前三位不能是911,611和411.
	public List<String> phoneNumber(int n){
		List<String> result = new ArrayList<>();
		if(n==0) return result;
		phoneNumberHelper(n, "", result);
		return result;
	}
	public void phoneNumberHelper(int n, String cur, List<String> result){
		if(cur.length()==n){
			if(n>=3){
				String test = cur.substring(0,3);
				if(test.equals("911")||test.equals("611")||test.equals("411"))
					return;
			}
			System.out.println(cur);
			result.add(cur);
			return;
		}
		for(int i=0;i<=9;i++){
			if(cur.length()==0&&(i==0||i==1)) continue;
			phoneNumberHelper(n,cur+i,result);
		}
		
	}
	//输出比n小的Fibonacci数列，用iteration和recursion的方法
	public List<Integer> fib(int n){
		List<Integer> result = new ArrayList<>();
		if(n<=1) return result;
		result.add(1);
		while(true){
			int cur=1;
			if(result.size()>1){
				int size=result.size();
				cur = result.get(size-1)+result.get(size-2);
			}
			if(cur<n){
				result.add(cur);
			}
			else
				break;
			
		}
		for(int i:result){
			System.out.println(i);
		}
		return result;
	}
	
	public List<Integer> fibRecursive(int n){
		List<Integer> result = new ArrayList<>();
		for(int i=1;i<n;i++){
			int cur = fibHelper(i);
			if(cur<n) result.add(cur);
			else break;
		}
		for(int i:result){
			System.out.println(i);
		}
		return result;
	}
	public int fibHelper(int n){
		if(n==1||n==2) return 1;
		else{
			return fibHelper(n-1)+fibHelper(n-2);
		}
	}
	//2. 给一个tree（不一定是binary tree） 还有一个数字n，改变tree的结构，使得除了一个node外的每个node都只有0或n个child，剩下的那个node有0-n个child
	public class TreeNode{
		int val;
		List<TreeNode> children;
		TreeNode(int v){
			this.val=v;
			children=new ArrayList<>();
		}
	}
	//????
	public TreeNode reOrder(TreeNode root,int n){
		if(root==null||root.children==null) return root;
		List<TreeNode> all = new ArrayList<>();
		traverse(root, all);
		int i=0;
		TreeNode result = all.get(i);
		i++;
		int index=i;
		TreeNode cur = result;
		while(i<all.size()){
			if(cur.children==null){
				cur.children=new ArrayList<>();
			}
			int k=0;
			if(i<all.size()&&k<n){
				cur.children.add(all.get(i++));
				k++;
			}
			i++;
			index++;
			cur=all.get(index);
		}
		return result;
	}
	
	private void traverse(TreeNode root, List<TreeNode> all){
		if(root==null) return;
		all.add(root);
		for(TreeNode c:root.children){
			traverse(c,all);
		}
	}
	public int findKth(int[] a, int k) throws Exception{
		if(a==null||a.length==0||k==0) 
			throw new Exception("");
		return findKthHelper(a, 0, a.length-1, k);
	}
	public int findKthHelper(int[] a, int left, int right, int k){
		int index = partition(a,left,right);
		if(k==index+1) return a[index];
		else if(k>index+1) return findKthHelper(a, index+1, right,k);
		else return findKthHelper(a, left, index-1,k);
	}
	public int partition(int[] a, int left, int right){
		int p=a[left];
		int s=left;
		for(int i=left+1;i<=right;i++){
			if(a[i]<p){
				s++;
				swap(a, s, i);
			}
		}
		swap(a, left, s);
		return s;
	}
	public void swap(int[] a, int i, int j){
		int tmp = a[i];
		a[i]=a[j];
		a[j]=tmp;
	}

	
	
	public static void main(String[] args) throws Exception{
		PhoneQ test = new PhoneQ();
		
		//test balanced string
		System.out.println(test.isBalancedStringDP("(:))"));
		
		//test coin pick
		test.numOfCoinGreedy(39);
		/*int[] d = {10,7,5,1};
		System.out.println(test.numOfCoin(d, 19));
		List<Integer> res = test.printCoints(d, 19);
		for(int i:res){
			System.out.println(i);
		}*/
		
		/*int[] bucket={1,101,1001,2001};
		int[] input = {2,3,10000};
		test.searchRange(bucket, input);*/
		
		//System.out.println(test.sqrt(0.26, 0.001));
		
		//test.phoneNumber(4);
		
		//test.fibRecursive(6);
		
		int[] a = {3,4,6,3,4,96,5};
		System.out.println(test.findKth(a, 7));
	}

}
