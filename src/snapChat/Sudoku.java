package snapChat;

public class Sudoku {
	public boolean isValidSudoku(String s){
		if(s.length()!=81) return false;
		//System.out.println(s.length());
		char[][] board=new char[9][9];
		int k=0;
		
		for(int i=0;i<9;i++){
			//StringBuilder sb=new StringBuilder();
			for(int j=0;j<9;j++){
				board[i][j]=s.charAt(k++);
				//sb.append(board[i][j]);
			}
			//System.out.println(sb.toString());
		}
		for(int i=0;i<9;i++){
			boolean[] checked=new boolean[9];
			for(int j=0;j<9;j++){
				if(!isValid(board[i][j],checked)) return false;
			}
		}
		for(int j=0;j<9;j++){
			boolean[] checked=new boolean[9];
			for(int i=0;i<9;i++){
				if(!isValid(board[i][j],checked)) return false;
			}
		}
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				boolean[] checked=new boolean[9];
				for(int m=0;m<3;m++){
					for(int n=0;n<3;n++){
						if(!isValid(board[3*i+m][3*j+n],checked)){
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	public boolean isValid(char a, boolean[] checked){
		if(a=='.') return true;
		if(a<'1'||a>'9') return false;
		int index=a-'1';
		if(checked[index]) return false;
		checked[index]=true;
		return true;
		
	}
	public static void main(String[] args){
		Sudoku test=new Sudoku();
		boolean t=test.isValidSudoku("123456789098765432345678909765432134266789074748394056097463523023457652283999999");
		System.out.println(t);
	}

}
