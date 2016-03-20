package googleOnSite;

import java.util.*;

public class RectangleAllOne {
	static int up;
	static int down;
	static int left;
	static int right;
	public List<int[]> rectangleAllOne(int[][] matrix){
		int n =matrix.length;
		int m =matrix[0].length;
		up = n;
		down = 0;
		left = m;
		right=0;
		boolean[][] checked = new boolean[n][m];
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				if(!checked[i][j]&&matrix[i][j]==1){
					dfs(checked, matrix, i, j);
				}
			}
			
		}
		List<int[]> result = new ArrayList<>();
		result.add(new int[]{up,left});
		result.add(new int[]{down, right});
		return result;
		
	}
	public void dfs(boolean[][] checked, int[][] matrix, int i, int j){
		if(matrix[i][j]==0||checked[i][j]) return;
		int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
		checked[i][j]=true;
		up = Math.min(up, i);
		down = Math.max(down, i);
		left = Math.min(left, j);
		right = Math.max(right, j);
		for(int[] d:dir){
			int x = i+d[0];
			int y = j+d[1];
			if(x>=0&&x<matrix.length&&y>=0&&y<matrix[0].length&&!checked[x][y]&&matrix[x][y]==1){
				dfs(checked, matrix, x, y);
			}
		}
	}
	public static void main(String[] args){
		int[][] a = {{0,0,1},{0,0,1},{0,0,1}};
		List<int[]> result= new ArrayList<>();
		RectangleAllOne test = new RectangleAllOne();
		result=test.rectangleAllOne(a);
		for(int[] d:result){
			System.out.println(d[0]+","+d[1]);
		}
	}
}
