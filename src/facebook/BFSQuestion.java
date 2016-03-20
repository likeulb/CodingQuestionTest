package facebook;

import java.util.*;

public class BFSQuestion {
	//一面：给一个board，上面有0 和1，1不可以走，0 可以走。
	//任意给一个start一个end，让输出最短的步数。follow up输出路径。就是BFS
	public int shortestPath(int[][] board, int[] start, int[] end){
		List<int[]> path=new ArrayList<>();
		int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
		//但是每次都是可以纵向两步横向一步，或者横向一步纵向两部
		//int[][] dir={{2,1},{-1,2},{-2,-1},{1,-2},{1,2},{-2,1},{-1,-2},{2,-1}};
		if(start[0]==end[0]&&start[1]==end[1]) return 0;
		Queue<int[]> que=new LinkedList<>();
		que.add(start);
		HashMap<Integer,List<int[]>> map=new HashMap<>();
		int m=board.length,n=board[0].length;
		boolean[][] checked=new boolean[m][n];
		int step=0;
		while(!que.isEmpty()){
			int size=que.size();
			for(int k=0;k<size;k++){
				int[] cur=que.poll();
				int i=cur[0];
				int j=cur[1];
				for(int[] d:dir){
					int x=i+d[0];
					int y=j+d[1];
					if(x==end[0]&&y==end[1]){
						path.add(end);
						buildPath(path,map,cur,start,n);
						Collections.reverse(path);
						for(int[] t:path){
							System.out.println(t[0]+","+t[1]);
						}
						return step+1;
					}
					while(x>=0&&x<m&&y>=0&&y<n&&!checked[x][y]&&board[x][y]==0){
						int key=x*n+y;
						if(!map.containsKey(key)){
							map.put(key,new ArrayList<int[]>());
						}
						map.get(key).add(cur);
						checked[x][y]=true;
						que.add(new int[]{x,y});
						
					
					}
				}
			}
			step++;
		}
		return step;
	}
	public boolean buildPath(List<int[]> path, HashMap<Integer,List<int[]>> map, int[] cur, int[] start,int n){
		if(cur[0]==start[0]&&cur[1]==start[1]){
			path.add(start);
			return true;
		}
		int key=cur[0]*n+cur[1];
		path.add(cur);
		if(map.containsKey(key)){
			for(int[] nxt:map.get(key)){
				if(buildPath(path,map,nxt,start,n)){
					return true;
				}
			}
		}
		path.remove(cur);
		return false;
		
	}
	public static void main(String[] args){
		int[][] board={{0,0,1},{0,0,0},{0,0,0}};
		int[] start={0,0};
		int[] end={2,2};
		BFSQuestion test=new BFSQuestion();
		int t=test.shortestPath(board, start, end);
		System.out.println(t);
	}
}
