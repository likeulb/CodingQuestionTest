package googleOnSite;

import java.util.*;

public class Google2016Phone {
	
	static boolean hasRectangle(char[][] c){
		int[] bit =new int[c.length];
		for(int i=0;i<c.length;i++){
			int cur=0;
			for(int j=0;j<c[0].length;j++){
				if(c[i][j]=='b'){
					cur|=1;
				}
				if(j<c[0].length-1)
					cur<<=1;
			}
			bit[i]=cur;
		}
		for(int i:bit){
			System.out.println(Integer.toBinaryString(i));
		}
		for(int i=0;i<bit.length;i++){
			
			for(int j=i+1;j<bit.length;j++){
				int tmp=bit[i];
				tmp&=bit[j];
				if(tmp>0){
					if((tmp&(tmp-1))>0)
						return true;
				}
			}
		}
		
		return false;
	}
	static void tortoiseTurn(int[] dir){
		if(dir[0]==0){
			dir[0]=dir[1];
			dir[1]=0;
		}
		else{
			dir[1]=-dir[0];
			dir[0]=0;
		}
	}
	static void tortoiseMove(int n, int[] position, int[] dir){
		position[0]+=n*dir[0];
		position[1]+=n*dir[1];
	}
	static int[] moveCommand(String s, int[] position, int[][] board, int[] dir){
		int i=0;
		while(i<s.length()){
			char cur=s.charAt(i);
			if(Character.isDigit(cur)){
				int n=cur-'A';
				i++;
				if(s.charAt(i)=='F'){
					tortoiseMove(n,position, dir);
				}
				else{
					for(int j=0;j<n%4;j++){
						tortoiseTurn(dir);
					}
				}
				
			}
			else if(cur=='F'){
				tortoiseMove(1,position, dir);
			}
			else if(cur=='R'){//turn right;
				tortoiseTurn(dir);
			}
			i++;
		}
		return position;
		
	}
	// 给定一个一元二次方程式y = ax^2 + bx + c, 一个sorted array X, 将X中所有元素代入方程中，返回sorted的Y～
	static List<Integer> calculation(int a, int b, int c, List<Integer> X){
		List<Integer> Y=new ArrayList<>();
		if(a==0){
			if(b>0){
				for(int x:X){
					Y.add(x*b+c);
				}
			}
			else{
				for(int x:X){
					Y.add(0,x*b+c);
				}
			}
		}
		else{
			int mid = -b/2*a;
			List<Integer> Y1=new ArrayList<>();
			List<Integer> Y2=new ArrayList<>();
			for(int x:X){
				int n=a*x*x+b*x+c;
				if(x<mid){
					if(a>0) Y1.add(0,n);
					else Y1.add(n);
				}
				else{
					if(a>0) Y2.add(n);
					else Y2.add(0,n);
				}
			}
			Y=merge(Y1,Y2);
		}
		return Y;
	}
	public static List<Integer> merge(List<Integer> l1, List<Integer> l2){
		int i=0,j=0;
		List<Integer> result =  new ArrayList<>();
		while(i<l1.size()&&j<l2.size()){
			if(l1.get(i)<l2.get(j)){
				result.add(l1.get(i++));
			}
			else{
				result.add(l2.get(j++));
			}
		}
		while(i<l1.size()){
			result.add(l1.get(i++));
		}
		while(j<l2.size()){
			result.add(l2.get(j++));
		}
		return result;
	}
	//给定一个array，只包含0，1， 找到一个分割位置，使左侧0出现的个数和右侧1出现的个数之和最大化
	public static int partiton(int[] a){
		int count=0;
		for(int i=1;i<a.length;i++){
			if(a[i]==1){
				count++;
			}
		}
		int index=0;
		int max=count;
		for(int i=0;i<a.length-1;i++){
			if(a[i]==0){
				count++;
			}
			else{
				count--;
			}
			if(count>max){
				max=count;
				index=i+1;
			}
		}
		return index;
	}
	/* floodfill，给一个2维矩阵，里面每一个int代表了那个坐标位置的海拔，然后给一个水平面高度，当水平面
高度>=海拔高度的时候算作那个地方被水淹没（flooded）。现在有一艘船只能走被水淹没的地方。
问：给定一个海拔矩阵，和一个水平面高度，求船从矩阵左上角划到右下角的path。*/
	static List<int[]> floodfill(int[][] a, int level){
		List<int[]> result=new ArrayList<>();
		if(a==null||a.length==0||a[0].length==0) return result;
		boolean[][] checked=new boolean[a.length][a[0].length];
		dfs_fooldfill(result,0,0,a,level,checked);
		return result;
	}
	static boolean dfs_fooldfill(List<int[]> result, int i, int j, int[][] a, int level, boolean[][] checked){
		int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
		if(i==a.length-1&&j==a[0].length-1){
			result.add(new int[]{i,j});
			return true;
		}
		if(checked[i][j]||a[i][j]<=level) return false;
		checked[i][j]=true;
		result.add(new int[]{i,j});
		for(int[] d:dir){
			int x=i+d[0];
			int y=j+d[1];
			if(x>=0&&x<a.length&&y>=0&&y<a[0].length){
				if(dfs_fooldfill(result,x,y,a,level,checked)){
					return true;
				}
			}
		}
		checked[i][j]=false;
		result.remove(result.size()-1);
		return false;
	}
	//有一排停车位，只有一个empty slot，其他车位都停满了车。每一次操作，只能将某一辆
	public static void printPath(String start, String end){
		char[] c1=start.toCharArray();
		char[] c2=end.toCharArray();
		/*HashMap<String, HashSet<String>> map=new HashMap<>();
		HashSet<String> visited=new HashSet<>();
		Queue<char[]> que=new LinkedList<>();
		que.add(c1);
		while(!que.isEmpty()){
			int size=que.size();
			HashMap<>
		}*/
		int i=0;
		for(;i<c1.length;i++){
			if(c1[i]==' ')
				break;
		} //i always point to the empty lot
		while(true){
			if(c1[i]==' '&&c1[i]==c2[i]){ //when c2 is also an empty lot, swap with any unmatching one, otherwise it has been finished
				int j=0;
				for(;j<c1.length;j++){
					if(j==i) continue;
					if(c1[j]!=c2[j]) break;
				}
				if(j==c1.length) break;
				swap(c1,i,j);
				System.out.println(new String(c1));
				i=j;
			}
			else if(c1[i]==' '&&c1[i]!=c2[i]){ //else, find the corret value, and swap
				int j=0;
				for(;j<c1.length;j++){
					if(c1[j]==c2[i])
						break;
				}
				swap(c1,i,j);
				System.out.println(new String(c1));
				i=j;
			}
			
		}
		
	}
	public static void swap(char[] a, int i, int j){
		char tmp=a[i];
		a[i]=a[j];
		a[j]=tmp;
	}
	
//insert value into a sorted linkedlist which has its end point to head;
	public static ListNode insert(ListNode head, ListNode p){
		ListNode t=head;
		
		while(t.next!=head){
			if(p.val>=t.val&&p.val<t.next.val){
				ListNode nxt=t.next;
				t.next=p;
				p.next=nxt;
				return head;
			}
			else{
				t=t.next;
			}
		}
		t.next=p;
		p.next=head;
		return p;
		
		
	}
	//return all the actual combinations of hours: minues
	public static void printAllCombinations(){
		boolean[] checked=new boolean[10];
		printHelper(checked,0,0);
		
	}
	public static void printHelper(boolean[] checked, int num, int index){
		if(num==3){
			int hour=0;
			int min=0;
			for(int i=0;i<=3;i++){
				hour<<=1;
				if(checked[i]){
					hour|=1;
				}
			}
			for(int i=4;i<=9;i++){
				min<<=1;
				if(checked[i]){
					min|=1;
				}
			}
			System.out.println(hour+":"+min);
			return;
		}
		if(num>3) return;
		for(int i=index;i<10;i++){
			checked[i]=true;
			printHelper(checked,num+1,i+1);
			checked[i]=false;
		}
		
	}
	//给一个sorted的array, 返回squar后sorted的array
	public static int[] squarSortedArray(int[] a){
		int[] result=new int[a.length];
		int i=0,j=a.length-1;
		int k=a.length-1;
		while(i<=j){
			if(Math.abs(i)>Math.abs(j)){
				result[k--]=i*i;
				i++;
			}
			else{
				result[k--]=j*j;
				j--;
			}
		}
		for(int m:result){
			System.out.println(m);
		}
		return result;
	}
	public static String printmatching(int n){
		String[] team=new String[n];
		for(int i=1;i<=n;i++){
			team[i-1]=i+"";
		}
		while(n>1){
			for(int i=0;i<n/2;i++){
				team[i]=match(team[i],team[n-i-1]);
			}
			n/=2;
		}
		System.out.println(team[0]);
		return team[0];
		
		
	}
	public static String match(String s1, String s2){
		return "("+s1+":"+s2+")";
	}
	public static void main(String[] args){
		char[][] c={{'w','w','w','b','w'},{'w','b','w','b','b'},{'b','w','b','b','w'}};
	//	System.out.println(Google2016Phone.hasRectangle(c));
		int[] a={0,0,1,1,1,0,0};
		System.out.println(Google2016Phone.partiton(a));
		Google2016Phone.printPath("a bc", "b ca");
	//	Google2016Phone.printAllCombinations();
		int[] b={-5,-3,-1,0};
		Google2016Phone.squarSortedArray(b);
		Google2016Phone.printmatching(8);
	}

}
