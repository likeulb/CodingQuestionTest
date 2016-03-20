package googleOnSite;

import java.util.*;

public class Floodfill {
	/*给一个方阵，方阵里每一个正方形格都有一个颜色。针对方阵里的某一点A，求包含该点的最大同色区域的周长。5 u8 e5 Y+ n  Z" U

	input: 代表方阵的2D array, 点A的坐标(x, y).*/
	public void floodFill(int[][] arr, int newCol, int x, int y){
		int n =arr.length;
		int m = arr[0].length;
		boolean[][] checked = new boolean[n][m];
		int oldCol = arr[x][y];
		dfs(checked, arr, x, y, oldCol, newCol);
		return;
		
	}
	public void dfs(boolean[][] checked, int[][] arr, int x, int y, int old, int colour){
		if(x<0||x>=arr.length||y<0||y>=arr[0].length) return;
		if(checked[x][y]||arr[x][y]!=old)
			return;
		checked[x][y]=true;
		arr[x][y]=colour;
		int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
		for(int[] d:dir){
			int i = x+d[0];
			int j = y+d[1];
			if(i>=0&&i<arr.length&&j>=0&&j<arr[0].length&&!checked[i][j]&&arr[i][j]==old){
				dfs(checked, arr, i, j, old, colour);
			}
		}
	}
	
	//heap里放的是当前的边界，按照高度每次取最小高度
	//如果最小高度周围有没有访问过的点，该点的高度如果比这最小高度小，这该里可以注水，如果比它大，注的水就是0，所以max(h-h_pop,0)
	//对于该点，取较高的height放入que, 相当于扩展边界
	public int TrappingWaterIn2D(int[][] a){
		int n=a.length;
		int m=a[0].length;
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>(){
			public int compare(int[] a, int[] b){
				return a[0]-b[0];
			}
		});
		boolean[][] checked = new boolean[n][m];
		for(int i=0;i<n;i++){
			que.add(new int[]{a[i][0],i,0});  //放入周围一圈的height
			checked[i][0]=true;
			que.add(new int[]{a[i][m-1],i,m-1});
			checked[i][m-1]=true;
			
		}
		for(int i=1;i<=m-1;i++){
			que.add(new int[]{a[0][i],0,i});
			checked[0][i]=true;
			que.add(new int[]{a[n-1][i],n-1,i});
			checked[n-1][i]=true;
		}
		int[][] d = {{1,0},{0,1},{-1,0},{0,-1}};
		int water = 0;
		while(!que.isEmpty()){
			int[] cur = que.poll();//poll最小hight
			int h = cur[0];
			int x = cur[1];
			int y = cur[2];
			for(int[] dir : d){
				int i = x+dir[0];
				int j = y+dir[1];
				if(i>=0&&i<a.length&&j>=0&&j<a[0].length&&!checked[i][j]){
					water+=Math.max(h-a[i][j],0);
					que.add(new int[]{Math.max(a[i][j], h),i,j});//最较大的height放入queue
					checked[i][j]=true;
				}
				
			}
			
		}
		return water;
		
	}
	public static void main(String[] args){
		Floodfill Test2 = new Floodfill();
		int[][] b = {{12,13,0,12},
				{13,4,13,12},
						{13,8,10,12},
						{12,13,12,12},
						{13,13,13,13}};
		System.out.println(Test2.TrappingWaterIn2D(b));
		Floodfill Test  = new Floodfill();
		int[][] a ={{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 0, 0},
				{1, 0, 0, 1, 1, 0, 1, 1},
                {1, 3, 3, 3, 3, 0, 1, 0},
                {1, 1, 1, 3, 3, 0, 1, 0},
                {1, 1, 1, 3, 3, 3, 3, 0},
                {1, 1, 1, 1, 1, 3, 1, 1},
                {1, 1, 1, 1, 1, 3, 3, 1}};
		Test.floodFill(a, 2, 4, 4);
		for(int i=0;i<a.length;i++){
			String s = "";
			for(int j=0;j<a[0].length;j++){
				s+=a[i][j]+",";
			}
			System.out.println(s);
		}
		
		
		
	}

}
