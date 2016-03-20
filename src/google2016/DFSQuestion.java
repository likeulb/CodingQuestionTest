package google2016;
import java.util.*;

import commonClass.*;
public class DFSQuestion {
	
	//打印队伍比赛对手，把所有队伍放在叶子
	public List<TreeNode> print(TreeNode root){
		List<TreeNode> result=new ArrayList<>();
		if(root==null) return result;
		List<TreeNode> left=print(root.left);
		List<TreeNode> right=print(root.right);
		
		if(left.isEmpty()&&right.isEmpty()){
			result.add(root);
			return result;
		}
		else if(left.isEmpty()||right.isEmpty()){
			return left.isEmpty()?right:left;
		}
		else{
			for(TreeNode l:left){
				for(TreeNode r:right){
					System.out.println(l.val+"-"+r.val);
				}
			}
			left.addAll(right);
			return left;
		}
	}
	//安卓手机锁屏共多少可能性
	int count;
	public void phone(int min){
		List<Integer> item=new ArrayList<>();
		count=0;
		HashSet<Integer> used=new HashSet<>();
		phonehelper(item,min,used);
		System.out.println(count);
	}
	public void phonehelper(List<Integer> item, int min, HashSet<Integer> used){
		if(item.size()>9) return;
		if(item.size()>=min){
			count++;
		}
		for(int i=1;i<10;i++){
			if(isValid(i,used,item)){
				used.add(i);
				item.add(i);
				phonehelper(item,min,used);
				used.remove(i);
				item.remove(item.size()-1);
			}
		}
	}
	public boolean isValid(int i, HashSet<Integer> used,List<Integer> item){
		if(used.contains(i)) return false;
		if(item.size()>0){
			int pre=item.get(item.size()-1);
			if((i+pre)%2==1) return true;
			int mid=(i+pre)/2;
			if(mid==5||pre%3==i%3||(pre-1)/3==(i-1)/3) return used.contains(mid);
		}
		return true;
	}
	//给一些要求，像1必须在5前面，3 必须在4 前面。 给一个N的范围，
	//让写出一个合法的序列。后来follow up 也忘了。边交流边写，印度哥每次我一动笔写就要提示我。
	public void printValidSequence(int n){
		boolean[] used=new boolean[n+1];
		printdfs(n, "", used);
		
	}
	public void printdfs(int n, String cur, boolean[] used){
		if(cur.length()==n){
			System.out.println(cur);
			return;
		}
		for(int i=1;i<=n;i++){
			if(used[i]) continue;
			if(i==5&&used[1]||i==4&&used[3]||i!=5&&i!=4){
				used[i]=true;
				printdfs(n,cur+i,used);
				used[i]=false;
			}
		}
		
	}
	//给一个数组，平均分成 k 份，从 k 个子数组中每个抽一个数出来，输出所有可能的排列
	public void combinationfromkgroup(int[] arr, int k){
		int n=arr.length/k; //假设是可以平均分的
		dfsCombinationKgroup("",0,n,k,arr);
	}
	public void dfsCombinationKgroup(String cur, int level, int n, int k, int[] arr){
		if(cur.length()==k){
			System.out.println(cur);
			return;
		}
		for(int i=level*n;i<level*n+n;i++){
			dfsCombinationKgroup(cur+arr[i],level+1,n,k,arr);
		}
		
	}
	//perfect square 返回具体项
		public List<Integer> perfectsquare(int n){
			HashMap<Integer,List<Integer>> map=new HashMap<>();
			return perfectsquarehelper(map,n);
		}
		public List<Integer> perfectsquarehelper(HashMap<Integer,List<Integer>> map, int n){
			if(n==0) return new ArrayList<Integer>();
			List<Integer> result=new ArrayList<>();
			if(n==1){
				result.add(1);
				map.put(1, result);
				return result;
			}
			int size=Integer.MAX_VALUE;
			
			for(int i=1;i*i<=n;i++){
				
				List<Integer> pre= map.containsKey(n-i*i)? map.get(n-i*i):perfectsquarehelper(map,n-i*i);
				if(pre.size()<size){
					size=pre.size();
					result=new ArrayList<>(pre);
					result.add(i*i);
					if(pre.size()==0){
						map.put(n, result);
						return result;
					}
					
				}
			}
			map.put(n, result);
			return result;
			
		}
		/*给一个5*5的矩阵， 把数字1到25填进不同的格子， 要求相同行相同列的数字递增
		求有多少种放法*/
		public long countWays(int n){
			long[] count=new long[1];
			int[][] board=new int[n][n];
			int[] row=new int[n];
			countHelper(n,1,count,  board, row);
			return count[0];
		}
		public void countHelper(int n, int num, long[] count, int[][] board, int[] row){
			if(num>25){
				count[0]++;
				return;
			}
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(board[i][j]==0){
						if((j==0||board[i][j-1]==0||board[i][j-1]<num)&&(i==0||board[i-1][j]==0||board[i-1][j]<num)&&(i==0||row[i]<row[i-1])){
							board[i][j]=num;
							row[i]++;
							countHelper(n,num+1,count,board, row);
							board[i][j]=0;
							row[i]--;
						}
					}
				}
			}
		}
		//用dp优化
		/*解释一下：这里是逐个填入1到25的，dp是用在了 “只考虑count，不记录结果” 这一点上面。其中countMap是一个把vector映到int的map，vector就表示当前的选取方法，而int是选取方法对应的count数，
比如vector是 [4,3,2,1,1] 就表示第一行填了前4个，第二行填了前三个这样这样。（因为4+3+2+1+1=11，所以当前一定是在处理i=11这个数）每次一个大循环的loop结束后，newCount里面就存了填入第i个数后，所有的填法以及每个填法对应的count。所有可能的状态数大概是 5! 种（也就是非严格单减序列的个数，比 5!稍多，不过差不太多，）每个状态数会用常数时间（严格来说是 O(5)）处理，所以整体的时间比最终的结果数小很多。
*/
		//好像下一行个数不能比上一行多
		//java实现上还是有问题
		public long countWaysDp(int n){
			HashMap<ArrayAsKey,Long> map=new HashMap<>();
			int[] a=new int[5];
			ArrayAsKey arr=new ArrayAsKey(a);
			map.put(arr,(long)1);
			for(int i=1;i<=n*n;i++){
				HashMap<ArrayAsKey,Long> curMap=new HashMap<>();
				for(ArrayAsKey key:map.keySet()){
					int[] cur=key.arr;
					long count=map.get(key);
					for(int j=0;j<n;j++){
						if((j==0||cur[j]<cur[j-1])&&cur[j]<n){
							cur[j]++;
							ArrayAsKey tmp=new ArrayAsKey(cur);
							if(curMap.containsKey(tmp)){
								curMap.put(tmp, curMap.get(tmp)+count);
							}
							else{
								curMap.put(tmp,count);
							}
							cur[j]--;
						}
					}
					
				}
				map=curMap;
			}
			return map.get(arr);
		}
		/*Unique Paths 变体
		m * n的矩阵， 每一格有一个数字. 1point 3acres 璁哄潧
		求从左上角到右下角，和最大的路径
		followup： 如果两个人一起从左上角走， 每个人到了一个格子之后会把该格子的数清零。 求两个人走的路径和的最大值*/
		public List<int[]> maxSumPath(int[][] board){
			int m=board.length, n=board[0].length;
			int[][] dp=new int[m+1][n+1];
			for(int i=1;i<=m;i++){
				for(int j=1;j<=n;j++){
					dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1])+board[i-1][j-1];
				}
			}
			List<int[]> result=new ArrayList<>();
			int i=board.length-1, j=board[0].length-1;
			result.add(new int[]{i,j});
			while(true){
				int cur=dp[i+1][j+1]-board[i][j];
				if(cur==dp[i][j+1]){
					result.add(new int[]{i-1,j});
					i--;
				}
				else{
					result.add(new int[]{i,j-1});
					j--;
				}
				if(i==0&&j==0){
					break;
				}
			}
			
			Collections.reverse(result);
			return result;
		}
		
	//just used to generate a tree for testing
	public TreeNode buildTree(int[] a){
		int count=1;
		int num=1;
		while(num<a.length){
			num<<=1;
			count++;
		}
		TreeNode root=new TreeNode(0);
		Queue<TreeNode> que=new LinkedList<>();
		que.add(root);
		int level=1;
		int index=0;
		while(!que.isEmpty()){
			int size=que.size();
			for(int i=0;i<size;i++){
				TreeNode cur=que.poll();
				if(level<count){
					cur.left=new TreeNode(0);
					que.add(cur.left);
					cur.right=new TreeNode(0);
					que.add(cur.right);
				}
				else{
					if(index<a.length){
						cur.left=new TreeNode(a[index++]);
					}
					if(index<a.length){
						cur.right=new TreeNode(a[index++]);
					}
				}
				
			}
			level++;
			
		}
		return root;
	}
	//说你有三台接啤酒的机器，分别是small，medium, large. 这三种size的机器按一次button一次分别能distribute, say 100 - 150ml, 200 - 250ml, 300 - 350ml的啤酒，
	//每台机器出来的啤酒量是区间里的任意一个数不确定
	//找出如何按能得到min-max范围的量
	public void fillCup(int[][] machine, int min, int max){
		List<List<Integer>> result=new ArrayList<>();
		fillCuphelper(result, min, max, machine, 0,0, 0, new ArrayList<Integer>());
		for(List<Integer> s:result){
			for(int i:s){
				System.out.print(i+",");
			}
			System.out.println("");
		}
		
		
	}
	public void fillCuphelper(List<List<Integer>> result, int min, int max, int[][] machine, int curMin, int curMax, int index, List<Integer> item){
		if(curMin<=min&&curMax>=max){
			result.add(new ArrayList<Integer>(item));
			return;
		}
		if(curMin>max) return;
		for(int i=index;i<machine.length;i++){
			item.add(i);
			fillCuphelper(result,min,max,machine,curMin+machine[i][0],curMax+machine[i][1],i,item);
			item.remove(item.size()-1);
		}
	}
	public static void main(String[] args){
		DFSQuestion test=new DFSQuestion();
		
		int[][] machine={{100,150},{200,250},{300,350}};
		test.fillCup(machine, 300, 400);
		
		/*int[][] board={{1,9},{4,3}};
		List<int[]> re=test.maxSumPath(board);
		for(int[] p:re){
			System.out.println(p[0]+","+p[1]);
		}*/
		
		//??????
		//System.out.println(test.countWaysDp(5));
		
		/*List<Integer> result=test.perfectsquare(90);
		for(int i:result){
			System.out.println(i);
		}*/
		
		/*int[] a={34,56,66,77,99};
		TreeNode root =test.buildTree(a);
		test.print(root);*/
		int[] a={1,2,3,4,5,6};
		//test.combinationfromkgroup(a, 3);
		
	//	test.phone(4);
		
		//test.printValidSequence(5);
	}
	class ArrayAsKey{
		int[] arr=new int[5];
		
		ArrayAsKey(int[] a){
			this.arr=a;
			
		}
		@Override
		public int hashCode() {
			int result=0;
			for(Integer a:arr){
				result+=a.hashCode();
			}
			return result;
			
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ArrayAsKey other = (ArrayAsKey) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			for(int i=0;i<arr.length;i++){
				if(arr[i]!=other.arr[i])
					return false;
			}
			return true;
		}
		private DFSQuestion getOuterType() {
			return DFSQuestion.this;
		}
		
	}
}
