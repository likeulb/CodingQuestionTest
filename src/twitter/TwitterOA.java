package twitter;

import java.util.*;

public class TwitterOA {
	//找到同时在等差数列和等比数列里的数
	public List<Integer> getSame(int a0, int d, int b0, int q, int limit){
		List<Integer> result = new ArrayList<>();
		int b = b0;
		while(b<limit){
			int cur = (b-a0)%d;
			if(cur<0){
				cur = (cur+d)%d;
			}
			if(cur==0){
				result.add(b);
			}
			b=b*q;
		}
		return result;
	}
	//砍树
	public List<Integer> cutTree(int[] height){
		PriorityQueue<Integer> que = new PriorityQueue<>();
		List<Integer> result = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>();
		for(int h:height){
			que.add(h);
		}
		while(!que.isEmpty()){
			int cur = que.peek();
			int size = que.size();
			result.add(size);
			for(int i=0;i<size;i++){
				int h = que.poll();
				h-=cur;
				if(h>0){
					tmp.add(h);
				}
			}
			que.addAll(tmp);
			tmp.clear();
		}
		return result;
	}
	
	//delete t from s and return how many times we can do this
	public int deleteString(String s, String t){
		if(s==null||s.length()<t.length()) return 0;
		HashMap<String, Integer> map = new HashMap<>();
		helperDelete(s, t, map);
		if(map.containsKey(s)) return map.get(s);
		return 0;
	}
	public int helperDelete(String s, String t, HashMap<String, Integer> map){
		if(s==null||s.length()<t.length()) return 0;
		if(map.containsKey(s)) return map.get(s);
		List<Integer> index = matchString(s,t);
		int num = 0;
		for(int i:index){
			String left = s.substring(0,i);
			String right = s.substring(i+t.length());
			num = Math.max(num, helperDelete(left+right, t, map)+1);
		}
		map.put(s, num);
		return num;
		
	}
	public List<Integer> matchString(String s, String t){
		List<Integer> result = new ArrayList<>();
		if(s==null||s.length()<t.length()) return result;
		int i=0;
		int[] next = getKMPNext(t);
		while(i<=s.length()-t.length()){
			int j=0;
			while(j<t.length()&&s.charAt(i+j)==t.charAt(j)){
				j++;
			}
			if(j==t.length()){
				result.add(i);
				i++;
				
			}
			else{
				if(j==0) i++;
				else i=i+j-next[j-1];
			}
			
		}
		return result;
	}
	public int[] getKMPNext(String t){
		int[] next = new int[t.length()];
		for(int i=1;i<t.length();i++){
			int tmp = next[i-1];
			while(tmp>0&&t.charAt(tmp)!=t.charAt(i)){
				tmp = next[tmp-1];
			}
			if(t.charAt(tmp)==t.charAt(i))
				tmp++;
			next[i]=tmp;
		}
		return next;
	}
	//find the first duplicate character
	public char firstDuplicates(String s){
		
		HashMap<Character, Integer> map = new HashMap<>();
		char result=s.charAt(0);
		for(char c:s.toCharArray()){
			if(map.containsKey(c)){
				map.put(c, map.get(c)+1);
			}
			else{
				map.put(c, 1);
			}
		}
		for(char c:s.toCharArray()){
			if(map.get(c)>1){
				result = c;
				break;
			}
		}
		return result;
	}
	public char firstNonDuplicate(String s){
		HashMap<Character, Integer> map = new HashMap<>();
		char result = 'a';
		for(char c: s.toCharArray()){
			if(map.containsKey(c)){
				map.put(c,  map.get(c)+1);
			}
			else{
				map.put(c,1);
			}
		}
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(map.get(c)==1){
				result=c;
				return result;
			}
		}
		return result;
	}
	//(x-1)!%x==(x-1)
	public int findPrime(int n){
		if(n<=0) return 0;
		boolean[] checked = new boolean[n+1];
		int result = 1;
		for(int i=2;i<=n;i++){
			if(!checked[i]){
				System.out.println(i);
				result++;
				for(int k=i;k<=n;k+=i){
					checked[k]=true;
				}
			}
		}
//		for(int i=2;i<=n;i++){
//			int j=2;
//			boolean found = false;
//			for(;j<=Math.sqrt((double)i)+1;j++){
//				if(i%j==0&&j<i){
//					found=true;
//					break;
//				}
//			}
//			if(!found) {
//				result++;
//				System.out.println(i);
//			}
//		}
		return result;
	}
	//utopian tree高度
	public int[] UtopianTree(int[] c){
		if(c==null||c.length==0) return new int[]{};
		
		int[] result = new int[c.length];
		for(int i=0;i<c.length;i++){
			int cur = c[i];
			int h=1;
			for(int j=0;j<cur;j++){
				if(j%2==0){
					h*=2;
				}
				else{
					h+=1;
				}
			}
			result[i]=h;
		}
		return result;
	}
	//出现重复的元素个数
	public int countDuplicate(int[] arr){
		HashMap<Integer, Integer> map =new HashMap<>();
		for(int i:arr){
			if(map.containsKey(i)){
				map.put(i, map.get(i)+1);
			}
			else{
				map.put(i, 1);
			}
		}
		int count=0;
		for(int key:map.keySet()){
			if(map.get(key)>1){
				count++;
			}
		}
		return count;
	}
	//求coefficent
	public double coefficent(double[] x, double[] y){
		double avgx = 0;
		double avgy = 0;
		for(double i:x){
			avgx+=i;
		}
		avgx = avgx/x.length;
		for(double i:y){
			avgy+=i;
		}
		avgy = avgy/y.length;
		double sumx  = 0;
		for(int i=0;i<x.length;i++){
			x[i]-=avgx;
			if(x[i]==0) return 0;
			sumx+=x[i]*x[i];
		}
		double sumy = 0;
		for(int i=0;i<y.length;i++){
			y[i]-=avgy;
			if(y[i]==0) return 0;
			sumy+=y[i]*y[i];
		}
		double div = Math.sqrt(sumx*sumy);
		double result = 0;
		for(int i=0;i<x.length;i++){
			result+=x[i]*y[i];
		}
		return result/div;
		
		
	}
	//前n个fibonacci数
	public int[] fibonacci(int n){
		if(n<0) return new int[]{};
		int[] d = new int[n];
		int a = 0, b=1;
		for(int i=0;i<n;i++){
			if(i==0) d[i]=a;
			else if(i==1) d[i]=b;
			else{
				d[i]=a+b;
			
				a=b;
				b=d[i];
			}
			System.out.println(d[i]);
		}
		return d;
		
	}
	//数字是不是在前面或后面出现过
	public List<String> isNumRepeat(int[] a){
		List<String> result = new ArrayList<>();
		if(a==null||a.length==0) return result;
		StringBuilder sb = new StringBuilder();
		HashSet<Integer> set = new HashSet<>();
		for(int i=0;i<a.length;i++){
			if(set.contains(a[i])){
				sb.append(1);
			}
			else{
				set.add(a[i]);
				sb.append(0);
			}
			
		}
		System.out.println(sb.toString());
		result.add(sb.toString());
		set.clear();
		sb = new StringBuilder();
		for(int i=a.length-1;i>=0;i--){
			if(set.contains(a[i])){
				sb.append(1);
			}
			else{
				set.add(a[i]);
				sb.append(0);
			}
		}
		System.out.println(sb.reverse().toString());
		result.add(sb.reverse().toString());
		return result;
	}
	//欧拉公式求互质的数
	public int[] interPrimeNum(int[] a){
		if(a==null||a.length==0) return new int[]{};
		int max = 0;
		for(int i:a){
			max = Math.max(max, i);
		}
		boolean[] primes = new boolean[max+1];
		for(int i=2;i<primes.length;i++){
			if(!primes[i]){
				for(int k=i+i;k<primes.length;k+=i){
					primes[k]=true;
				}
			}
		}
		int[] p = new int[primes.length];
		for(int i=2;i<p.length;i++){
			p[i]=i;
		}
		for(int i=2;i<p.length;i++){
			if(!primes[i]){
				for(int k=i;k<p.length;k+=i){
					p[k]=p[k]/i*(i-1);
				}
			}
		}
		int[] result = new int[a.length];
		for(int i=0;i<a.length;i++){
			result[i]=p[a[i]];
		}
		return result;
		
	}
	
	//segment tree find range min
	public int[] rangeMin(int[] num, int[][] index){
		int[] tree = new int[num.length*3];
		build(tree, num, 0, num.length-1,  0);
		int[] result = new int[index.length];
		int k=0;
		for(int[] d:index){
			int i=d[0];
			int j=d[1];
			result[k++]=query(0,num.length-1,i,j,0,tree);
		}
		return result;
	}
	public int query(int left, int right, int i, int j, int index,int[] tree){
		if(i<=left&&j>=right){
			return tree[index];
		}
		if(i>right||j<left){
			return Integer.MAX_VALUE;
		}
		int mid = left+(right-left)/2;
		return Math.min(query(left,mid,i,j,index*2+1,tree), query(mid+1,right, i,j,index*2+2,tree));
	}
	public int build(int[] tree, int[] num, int left, int right,int index){
		if(left==right){
			tree[index]=num[left];
			return tree[index];
		}
		int mid = left+(right-left)/2;
		tree[index] = Math.min(build(tree,num,left,mid,2*index+1), build(tree,num,mid+1,right, 2*index+2));
		return tree[index];
	}
	//flip to get most 1
	public int[] flip(int[] a){
		if(a==null||a.length==0) return new int[]{};
		int ones = 0;
		int num=0;
		int start =0;
		int end = 0;
		int curStart = 0;
		int curEnd = 0;
		int max = 0;
		for(int i=0;i<a.length;i++){
			if(a[i]==0){
				num++;
				if(num>max){
					max=num;
					end=curEnd;
					start = curStart;
				}
				curEnd++;
			}
			else {
				num--;
				curEnd++;
				ones++;
			}
			if(num<0){
				curStart=i+1;
				curEnd=i+1;
				num=0;
			}
			
		}
		System.out.println(ones+max);
		System.out.println(start);
		System.out.println(end);
		return new int[]{start, end};
	}
	
	public int[] rationalSum(int[][] a){
		int[] result = new int[2];
		for(int[] num:a){
			result = add(result, num);
			System.out.println(result[0]);
			System.out.println(result[1]);
		}
		return result;
		
	}
	public int[] add(int[] a, int[] b){
		if(a[0]==0) return b;
		if(b[0]==0) return a;
		int lcm = lcm(a[1],b[1]);
		int[] result = new int[2];
		result[0]=a[0]*lcm/a[1]+b[0]*lcm/b[1];
		result[1]=lcm;
		int gcd = gcd(result[0],result[1]);
		if(gcd>1){
			result[0]/=gcd;
			result[1]/=gcd;
		}
		return result;
	}
	public int gcd(int a, int b){
		if(a==b) return a;
		if(a<b) return gcd(a,b-a);
		else return gcd(a-b,b);
	}
	public int lcm(int a, int b){
		int g = gcd(a,b);
		return a*b/g;
	}
	//由1，0组成能带队n的最小数字
	public void minMultiple(int n){
		int size = 2000;
		long[] pow = new long[size];
		long[] val = new long[size];
		int t=0,x=0;
		
		for(t=1, x=1;x<size;x++){
			val[x]=t;
			for(int j=0;j<size;j++){
				if(pow[j]!=0&&pow[(j+t)%n]==0&&pow[j]!=x)
					pow[(j+t)%n]=x;
			}
			if(pow[t]==0)
				pow[t]=x;
			t=(10*t)%n;
			if(pow[0]!=0) break;		
		}
		x=n;
		long count=0;
		StringBuilder sb = new StringBuilder();
		if(pow[0]!=0){
			while(x!=0){
				while(--count>pow[x%n]-1)
					sb.append(0);
				count=pow[x%n]-1;
				sb.append(1);
				x=(int) ((n+x-val[(int) pow[x%n]])%n);
				
			}
			while(count-->0)
				sb.append(0);
		}
		System.out.println(sb.toString());
	}
	public int twoOperation(int a){
		if(a==0) return 0;
		if(a<=2) return a;
		int[] dp = new int[a+1];
		dp[0]=0;
		dp[1]=1;
		dp[2]=2;
		for(int i=2;i<=a;i++){
			if(i%2==0){
				dp[i]=Math.min(dp[i/2],dp[i-1])+1;
			}
			else{
				dp[i]=dp[i-1]+1;
			}
		}
		System.out.println(dp[a]);
		return dp[a];
	}
	public int twoOperationBinary(int a){
		int result = 0;
		while(a>0){
			if((a&1)==1){
				a--;
			}
			else{
				a=(a>>1);
			}
			result++;
		}
		System.out.println(result);
		return result;
	}
	public int findSet(int[] a){
		
		int max = 1;
		boolean[] checked = new boolean[a.length];
		for(int i=0;i<a.length;i++){
			if(!checked[i]){
				int k=i;
				int num=0;
				while(!checked[k]){
					checked[k]=true;
					num++;
					k=a[k];
				}
				max = Math.max(max, num);
			}
		}
		System.out.println(max);
		return max;
	}
	HashMap<Integer,Double> map = new HashMap<>();
	double precision = 1E-5;
	public int numCubic(int A, int B){
		int count=0;
		for(int i=1;i<=A;i++){
			for(int j=1;j<=B;j++){
				double first = 0;
				double second = 0;
				if(map.containsKey(i)){
					first = map.get(i);
				}
				else{
					first = Math.cbrt(i);
					map.put(i,first);
				}
				if(map.containsKey(j)){
					second = map.get(j);
				}
				else{
					second = Math.cbrt(j);
					map.put(j,second);
				}
				double test = Math.pow((first+second),3);
				long r = Math.round(test);
				if(Math.abs(test-r)<=precision){
					count++;
					System.out.println(i+","+j);
					
				}
			}
			
		}
		System.out.println(count);
		return count;
	}
	
	public static void main(String[] args){
		TwitterOA test = new TwitterOA();
//		List<Integer> result=test.getSame(1, 3, 1, 2, 100);
//		for(int a:result){
//			System.out.println(a);
//		}
//		int[] height = {3,2,3,4,5,2};
//		List<Integer> result2 = test.cutTree(height);
//		for(int a:result2){
//			System.out.println(a);
//		}
//		int[] cycle = {2,3,5,1,0};
//		int[] h = test.UtopianTree(cycle);
//		for(int i:h){
//			System.out.println(i);
//		}
		
//		int[] a = {1,2,4,6,7,4,5,6,6,5};
//		System.out.println(test.countDuplicate(a));
//		int[][] b = {{2,3,4},{5,6,7},{1,7,9},{3,9,0}};
//		double[] x = new double[b.length];
//		double[] y = new double[b.length];
//		double[] z = new double[b.length];
//		for(int i=0;i<b.length;i++){
//			for(int j=0;j<b.length;j++){
//				switch(j){
//				case 0: x[i]=b[i][j];
//					break;
//				case 1: y[i]=b[i][j];
//					break;
//				case 2: z[i]=b[i][j];
//					break;
//				}
//			}
//		}
//		System.out.format("%.4f%n",test.coefficent(z, x));
		
		//test.fibonacci(7);
//		int[] a = {1,3,2,3,4,1,5,4};
//		test.isNumRepeat(a);
//		
//		int[] a = {2,5,8,9,10};
//		int[] b = test.interPrimeNum(a);
//		for(int i:b){
//			System.out.println(i);
//		}
		//System.out.println(test.findPrime(50));
		
//		int[] a = {10,20,3,44,55,11};
//		int[][] d = {{0,2},{1,5},{2,4}};
//		int[] result = test.rangeMin(a, d);
//		for(int i:result){
//			System.out.println(i);
//		}
		
//		int[] a = {1,0,1,0,1,0,1,1};
//		test.flip(a);
		
//		int[][] a = {{4,2},{2,4},{2,4},{2,3}};
//		test.rationalSum(a);
		
		//test.minMultiple(101);
		
		//test.twoOperationBinary(15);
		
//		int[] a = {2,3,1,0};
//		test.findSet(a);
		test.numCubic(100000,100000);
		System.out.println(test.deleteString("bababbbab", "bab"));
		System.out.println(test.deleteString("bbbabab", "bab"));
		System.out.println(test.firstNonDuplicate("abcdedbacfacg"));
	}
}
