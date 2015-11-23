package solution;

import java.util.*;

public class NumIslands {
	
	
	    public List<Integer> numIslands2(int m, int n, int[][] positions) {
	        List<Integer> result = new ArrayList<>();
	        if(m==0||n==0) return result;
	        int[] head = new int[m*n];
	        Arrays.fill(head, -1);
	        int island = 0;
	        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	        int[][] board = new int[m][n];
	        for(int[] pos:positions){
	            int x = pos[0];
	            int y = pos[1];
	            if(board[x][y]==1) continue;
	            board[x][y]=1;
	            head[x*n+y]=x*n+y;
	            island++;
	            for(int[] d:dir){
	                int i=x+d[0];
	                int j=y+d[1];
	                
	                if(i>=0&&i<m&&j>=0&&j<n&&board[i][j]==1){
	                    int head1 = findHead(head, i*n+j);
	                    int head2 = findHead(head, x*n+y);
	                    if(head1!=head2){
	                        setHead(head, head1, head2);
	                        island--;
	                    }
	                }
	            }
	            System.out.println(x+","+y);
	            result.add(island);
	            
	        }
	        return result;
	    }
	    public int findHead(int[] head, int x){
	        if(head[x]==-1||head[x]==x) return x;
	        else return findHead(head, head[x]);
	    }
	    public void setHead(int[] head, int head1, int head2){
	        head[head1]=head2;
	    }
	
	public static void main(String[] args){
		NumIslands T = new NumIslands();
		int[][] input= {{0,1},{1,2},{2,1},{1,0},{0,2},{2,2},{1,1}};
		T.numIslands2(3, 3, input);
		}
}
