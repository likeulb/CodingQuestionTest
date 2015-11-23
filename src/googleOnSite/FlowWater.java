package googleOnSite;

import java.util.*;

public class FlowWater {
	
	
	
	public List<Point> flowingWater(int[][] matrix){
		List<Point> result = new ArrayList<>();
		int n = matrix.length;
		HashSet<Point> setPac = new HashSet<>();
		for(int i=0;i<n;i++){
			Point p = new Point(0, i);
			setPac.add(p);
			dfs(setPac, 0, i, matrix);
	}
	for(int i=0;i<n;i++){
		Point p = new Point(i, 0);
		setPac.add(p);
		dfs(setPac, i, 0, matrix);
	}
	HashSet<Point> setAta = new HashSet<>();
	for(int i=0;i<n;i++){
		Point p=new Point(i,n-1);
		setAta.add(p);
		dfs(setAta, i, n-1, matrix);
	}
	for(int i=0;i<n;i++){
		Point p= new Point(n-1,i);
		setAta.add(p);
		dfs(setAta, n-1, i, matrix);
	}
	
	for(Point p:setPac){
		if(setAta.contains(p))
			result.add(p);
	}
	return result;
	}
	public void dfs(HashSet<Point> set, int x, int y, int[][] matrix){
		int[][] dir ={{-1,0},{1,0},{0,1},{0,-1}};
		for(int[] d: dir){
		int i = x+d[0];
		int j = y+d[1];
		Point cur = new Point(i, j);
		if(i>=0&&i<matrix.length&&j>=0&&j<matrix.length&&matrix[i][j]>=matrix[x][y]&&!set.contains(cur)){
		set.add(cur);
		dfs(set, i, j, matrix);
	}
	}
	}
	class Point{
		int i;
		int j;
		Point(int x, int y){
			this.i=x;
			this.j=y;
		}
		@Override
		public int hashCode() {
			Integer ix = new Integer(i);
			Integer jy = new Integer(j);
			return ix.hashCode()+jy.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}
		private FlowWater getOuterType() {
			return FlowWater.this;
		}
		
	}
	
	public static void main(String[] args){
		FlowWater Test = new FlowWater();
		int[][] matrix ={{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
		
		List<Point> result = Test.flowingWater(matrix);
		for(Point p:result){
			System.out.println(p.i+","+p.j);
		}
		
				

	}

}
