package googleOnSite;

import java.util.*;

public class RandomMaze {
	int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
	public void randomMaze(int n){
		String[][] ver = new String[n][n+1];
		for(int i=0;i<n;i++){
			for(int j=0;j<n+1;j++){
				if(j==n){
					ver[i][j]="|";
				}
				
				else{
					ver[i][j]="|  ";
				}
			}
		}
		String[][] hor = new String[n+1][n+1];
		for(int i=0;i<=n;i++){
			for(int j=0;j<=n;j++){
				if(j==n){
					hor[i][j]="+";
				}
				else{
					hor[i][j]="+--";
				}
			}
		}
	//	print(ver, hor, n);
		
		
		
		Random rand = new Random();
		int x = (int)rand.nextInt(n);
		int y=(int)rand.nextInt(n);
		boolean[][] checked = new boolean[n][n];
		dfs(x,y, checked, ver, hor);
//		//for(int i=0;i<maze.length;i++){
//			for(int j=0;j<maze[0].length;j++){
//			System.out.print(maze[i][j]);
//			}
//			System.out.println("");
//		}
		print(ver, hor, n);
	}
	public void print(String[][] ver, String[][] hor, int n){
		String out = "";
		for(int i=0;i<n;i++){
			for(int j=0;j<=n;j++){
				out+=hor[i][j];
			}
			out+="\n";
			for(int j=0;j<=n;j++){
				out+=ver[i][j];
			}
			out+="\n";
		}
		for(int j=0;j<=n;j++){
			out+=hor[n][j];
		}
		out+="\n";
		System.out.println(out);
	}
	public void dfs(int x, int y, boolean[][] checked, String[][] ver, String[][] hor){
		if(x>=checked.length||x<0||y>=checked[0].length||y<0) return;
		if(checked[x][y]) return;
		checked[x][y]=true;
		Integer[] a = {0,1,2,3};
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(a));
		
		Collections.shuffle(list);
		
		for(int i:list){
			int k= x+dir[i][0];
			int j =y+dir[i][1];
			if(k>=0&&k<checked.length&&j>=0&&j<checked[0].length&&!checked[k][j]){
				if(k==x){
					hor[Math.max(j,y)][k]="+  ";
				}
				else if(j==y){
					ver[y][Math.max(k,x)]="   ";
				}
				dfs(k,j,checked, ver, hor);
			}
		}
		
}
	public static void main(String[] args){
		RandomMaze Test = new RandomMaze();
		Test.randomMaze(16);
	}
	}
