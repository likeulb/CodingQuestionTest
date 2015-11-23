package googleOnSite;

import java.util.*;

public class TicTacToe {
	static boolean finished=false;
	public List<String> ticTacToe(int n){
		List<String> result = new ArrayList<>();
		char[][] board = new char[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				board[i][j]='_';
			}
		}
		int[][] checkWin = new int[2][2*n+2];
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				helper(result, board, i, j, 0,checkWin,n);
			}
		}
	
		return result;
	}
	public void helper(List<String> result, char[][] board, int i, int j,int cur, int[][] check,int n){
		if(i<0||i>=board.length||j<0||j>=board[0].length) return;
		if(board[i][j]=='X'||board[i][j]=='O')
			return;
		
		if(cur==0){
			board[i][j]='X';
			check[cur][i]++;
			check[cur][n+j]++;
			if(i==j) check[cur][2*n]++;
			if(i+j==n-1) check[cur][2*n+1]++; 
			if(win(check,cur)){
				System.out.println("X wins");
				print(board);
				return;
			}
			System.out.println("after twice");
			print(board);
			helper(result,board, i+1, j, 1,check,n);
			helper(result, board, i-1, j, 1,check,n);
			helper(result, board, i, j+1, 1,check,n);
			helper(result, board, i, j-1, 1,check,n);
			board[i][j]='_';
			check[cur][i]--;
			check[cur][n+j]--;
			if(i==j) check[cur][n]--;
			if(i+j==n-1) check[cur][n+1]--; 
			return;
		}
		if(cur==1){
			board[i][j]='O';
			check[cur][i]++;
			check[cur][n+j]++;
			if(i==j) check[cur][n]++;
			if(i+j==n-1) check[cur][n+1]++; 
			if(win(check,cur)){
				return;
			}
			helper(result,board, i+1, j, 0,check,n);
			helper(result, board, i-1, j, 0,check, n);
			helper(result, board, i, j+1, 0,check, n);
			helper(result, board, i, j-1, 0,check,n);
			board[i][j]='_';
			board[i][j]='_';
			check[cur][i]--;
			check[cur][n+j]--;
			if(i==j) check[cur][n]--;
			if(i+j==n-1) check[cur][n+1]--; 
		}
		
	}
	public void print(char[][] board){
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
		
	}
	public boolean win(int[][] check, int cur){
		for(int i=0;i<check[0].length;i++){
			if(check[cur][i]==3)
				return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		TicTacToe Test = new TicTacToe();
		Test.ticTacToe(3);
	}

}
