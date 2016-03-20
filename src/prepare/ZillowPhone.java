package prepare;

import java.util.*;

public class ZillowPhone {
	
	//1. find median in sorted array from a threshold
	public Integer findMedianWithDuplicate(int[] arr, int t){
		if(arr==null||arr.length==0) return null;
		int left=0, right=arr.length-1;
		while(left<=right){
			int mid = left+(right-left)/2;
			if(arr[mid]<=t){
				left=mid+1;
			}
			else{
				right=mid-1;
			}
		}
		if(left==arr.length) return null;
		int size = arr.length-left;
		if(size%2==1) return arr[left+size/2];
		else return (arr[left+size/2-1]+arr[left+size/2])/2; //考虑overflow, 返回double?
	}
	
	//2. 找到lowest common ancestor giving parent node
	//2.1不平衡树会是O(n)的time, O(n)的space
	public Node findCommonAncestor(Node node1, Node node2 ){
		HashSet<Node> set = new HashSet<>();
		if(node1==null) return node2;
		if(node2==null) return node1;
		
		/*while(node1!=null){
			set.add(node1);
			node1=node1.parent;
		}
		while(node2!=null){
			if(set.contains(node2))
				return node2;
			else
				node2=node2.parent;
		}
		return null;*/ //简单的方法，把其中一个node的parent都放入set
		
		//2.2 这个方法可以在两个node比较接近的情况得到比较好的running time
		while(node1!=null||node2!=null){ //这里应该用或，比如node1就是parent
			if(node1!=null){
				if(set.contains(node1))
					return node1;
				else{
					set.add(node1);
					node1=node1.parent;
				}
			}
			if(node2!=null){
				if(set.contains(node2)){
					return node2;
				}
				else{
					set.add(node2);
					node2=node2.parent;
				}
			}
		}
		return null;
	}
	//2.3 用constant memory就先算出每个node的level, 深的先走几步，然后大家一起走
	public Node findCommonAncestorConstantSpace(Node node1, Node node2){
		if(node1==null) return node2;
		if(node2==null) return node1;
		int level1=0;
		Node p1=node1;
		while(p1!=null){
			level1++;
			p1=p1.parent;
		}
		int level2=0;
		p1=node2;
		while(p1!=null){
			level2++;
			p1=p1.parent;
		}
		p1=node1;
		if(level1<level2){
			p1=node2;
		}
		int diff=Math.abs(level1-level2);
		while(diff>0){
			p1=p1.parent;
			diff--;
		}
		Node p2=node2;
		if(level1<level2){
			p2=node1;
		}
		while(p1!=null&&p2!=null){
			if(p1==p2)
				return p1;
			else{
				p1=p1.parent;
				p2=p2.parent;
			}
		}
		return null;
	}
	
	//3.1 递归计算string长度
	public int len(String s){
		if(s==null||s.isEmpty()) return 0;
		return 1+len(s.substring(1));
		
	}
	//3.2 不用substring, 好像找不到其他可以标记结尾的条件，只能用exception了。。
	public int lenWithoutSub(String s){
		if(s==null) return 0;
		StringBuilder sb = new StringBuilder(s);
		return lenHelper(sb);
	}
	public int lenHelper(StringBuilder sb){
		try{
			char c = sb.charAt(0);
		}
		catch (StringIndexOutOfBoundsException e){
			return 0;
		}
		return 1+lenHelper(sb.deleteCharAt(0));
	}
	
	//4.1 matching pair
	public List<Pair> matchingPair(int[][] arr){
		List<Pair> res = new ArrayList<>();
		if(arr==null||arr.length==0||arr[0].length==0) return res;
		HashMap<Integer, List<int[]>> map = new HashMap<>();
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				if(!map.containsKey(arr[i][j])){
					map.put(arr[i][j], new ArrayList<int[]>());
				}
				map.get(arr[i][j]).add(new int[]{i,j});
			}
		}
		for(int i:map.keySet()){
			if(map.get(i).size()>=2){
				List<int[]> list = map.get(i);
				for(int k=0;k<list.size()-1;k+=2){
					int[] first = list.get(k);
					int[] sec = list.get(k+1);
					res.add(new Pair(i,first[1],first[0],sec[1],sec[0]));//结果里面y是row坐标，x是col坐标
				}
			}
		}
		for(Pair p:res){
			System.out.println("value:"+p.val+" x1:"+p.pos1[0]+" y1:"+p.pos1[1]+" x2:"+p.pos2[0]+" y2:"+p.pos2[1]);
		}
		return res;
	}
	class Pair{
		int val;
		int[] pos1;
		int[] pos2;
		Pair(int v, int x1, int y1, int x2, int y2){
			this.val=v;
			pos1=new int[]{x1,y1};
			pos2=new int[]{x2,y2};
		}
	}
	//5.1 first non-repeating character
	public Character firstNonRepeat(String s){
		HashMap<Character, Integer> map = new HashMap<>();
		for(char c: s.toCharArray()){
			if(map.containsKey(c)){
				map.put(c, map.get(c)+1);
			}
			else
				map.put(c, 1);
		}
		for(char c:s.toCharArray()){
			if(map.get(c)==1){
				return c;
			}
		}
		return null;
	}
	public Character firstNonReportScanOnce(String s){
		HashMap<Character, Integer> count = new HashMap<>();
		HashMap<Character, Integer> pos = new HashMap<>();
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			if(count.containsKey(c)){
				count.put(c, count.get(c)+1);
			}
			else count.put(c, 1);
			if(!pos.containsKey(c)){
				pos.put(c, i);
			}
		}
		int min=s.length();
		for(char c:count.keySet()){
			if(count.get(c)==1){
				if(pos.get(c)<min){
					min=pos.get(c);
				}
			}
		}
		if(min==s.length()) return null;
		return s.charAt(min);
	}
	//6.1 find missing integer
	public void findMissing(int[] arr, int n){
		boolean[] checked = new boolean[n+1];
		for(int i:arr){
			checked[i]=true;
		}
		for(int i=1;i<checked.length;i++){
			if(!checked[i]){
				System.out.println(i);
			}
		}
	}
	//6.2 find missing integer if sorted，输入数组应该是1-n的值
	public void findMissingSorted(int[] arr, int n){
		int i=1;
		for(int j=0;j<arr.length;j++){
			while(i<arr[j]){
				System.out.println(i++);
			}
			i++;//注意有这些个corner case
		}
		while(i<n){
			System.out.println(i++);
			
		}
	}
	//用bitset处理大的数据？
	public void findMissingBigNum(int[] arr, int n, int blockSize, int numBlock){
		BitSet set = new BitSet(blockSize);
		int start = numBlock*blockSize;
		int end = (numBlock+1)*blockSize;
		for(int i:arr){
			if(i>=start&&i<end){
				set.set(i);
			}
		}
		for(int i=0;i<set.size();i++){
			if(!set.get(i)){
				System.out.println(start+i);
			}
		}
	}
	//7. find index, sum of its left == sum of its right, not include it self
	public int partitionArray(int[] a){
		if(a==null||a.length<=2) return -1; //suppose at least 3 elements
		
		/*int[] sum = new int[a.length];
		sum[0]=a[0];
		for(int i=1;i<a.length;i++){
			sum[i]=sum[i-1]+a[i];
		}
		int right = a[a.length-1];
		for(int i=a.length-2;i>=1;i--){
			if(right==sum[i-1]){
				return i;
			}
			else{
				right+=a[i];
			}
		}
		return -1;*/
		//不用多余空间, 维持两边的sum, 依次加或减
		int left = a[0];
		int right = 0;
		for(int i=2;i<a.length;i++){
			right+=a[i];
		}
		for(int i=1;i<a.length-1;i++){
			if(left==right) return i;
			else{
				left+=a[i];
				right-=a[i+1];
			}
		}
		return -1;
	}
	//8. check if bit representation of interger is palindrome
	//reverse再比较
	public boolean isBitPalidrome(int n){
		int copy = n;
		int reversed = 0;
		while(copy!=0){
			reversed<<=1;
			reversed|=(copy&1);
			copy>>>=1;//带着符号移动
		}
		return (reversed==n);
	}
	
	//9, calculate distance in 3D
	public Point closestToAll(Point[] points){
		int min=Integer.MAX_VALUE;
		Point res = new Point();
		for(int i=0;i<points.length;i++){
			Point cur = points[i];
			int dis = 0;
			for(int j=0;j<points.length;i++){
				if(i==j) continue;
				Point t = points[j];
				dis+=(t.x-cur.x)*(t.x-cur.x)+(t.y-cur.y)*(t.y-cur.y)+(t.z-cur.z)*(t.z-cur.z);
				if(dis>min) break;
			}
			if(dis<min){
				min=dis;
				res=cur;
			}
		}
		return res;
	}
	class Point{
		int x;
		int y;
		int z;
	}
	//fib sum recursive
	public int fib(int n){
		if(n==1||n==2) return 1;
		return fib(n-1)+fib(n-2);
	}
	public int sumfib(int n){
		if(n==1) return 1;
		return fib(n)+sumfib(n-1);
	}
	
	//fib sum dp
	public int fibsum(int n){
		if(n<=2) return n;
		int a=1,b=1;
		int sum=2;
		for(int i=2;i<n;i++){
			int c=a+b;
			a=b;
			b=c;
			sum+=c;
		}
		return sum;
	}
	
	public static void main(String[] args){
		ZillowPhone test = new ZillowPhone();
		
		//1. test with find median after threshold
		/*int[] a = {0,1,1,3};
		Integer x = test.findMedianWithDuplicate(a,1);
		if(x==null) System.out.println("out");
		else System.out.println(x);*/
		
		//3. test with count len with recursive
		//System.out.println(test.lenWithoutSub("abcddd"));
		
		//4. test with matching pair
		/*int[][] a = {{1,3,4,1},{6,1,7,8},{9,0,5,8},{2,2,1,2}};
		test.matchingPair(a);*/
		
		//5. test with first non repeating character
		/*Character c = test.firstNonReportScanOnce("hughug6");
		if(c==null) System.out.println("no such character");
		else System.out.printf("%c",c);*/
		
		//6. test with find missing integer
		/*int[] a = {1,5,10};
		test.findMissingSorted(a, 10);*/
		
		//7.test with partition array
		/*int[] a = {1, 2, -4, 8, 5, 9, -2};
		System.out.println(test.partitionArray(a));*/
		
		//8. test if bit representation of interger is palidrome
		System.out.println(test.isBitPalidrome(Integer.MIN_VALUE+1));
		
		System.out.println(((-5)%2+2)%2);
		System.out.println(test.fibsum(5));
		
	}
	public class Node{
		int val;
		Node parent;
		Node(int v){
			this.val=v;
		}
	}
}
