package solution;

public class CoinGame {
	
	public static int[][] p;
	
	public static int maxMoney(int[] A){
		int len = A.length;
		p = new int[len][len];
	//	for(int i=0;i<len-1;i++){
		//	p[i][i]=A[i];
	//	}
		int a, b, c;
		for(int i=0;i<len;i++){
			for(int m=0,n=i;n<len;m++,n++){
				a = (m+2<len)?p[m+2][n]:0;
				b = (m+1<len&&n-1>=0)?p[m+1][n-1]:0;
				c = (n-2>=0)? p[m][n-2]:0;
				p[m][n] = Math.max(A[m]+Math.min(a,b),
						A[n]+Math.min(b,c));
			}
		}
		traceback(A);
		return p[0][len-1];
	}
	
	public static void traceback(int[] A){
		int i=0, j=p.length-1;
		boolean myturn = true;
		String out ="";
		while(i<=j){
			int p1 = p[i][j-1];
			int p2 = p[i+1][j];
			out+= myturn? "My turn: ": "Your turn: ";
			if(p1<=p2){
				out+=A[j];
				j--;
			}
			else {
				out+=A[i];
				i++;
			}
			myturn = !myturn;
			out+="\n";
		}
		System.out.println(out);
	}
	
	
	public static void main(String[] args){
		int[] A = {3,2,2,3,1,2};
		int x = maxMoney(A);
		System.out.println(x);
		
	}

}

