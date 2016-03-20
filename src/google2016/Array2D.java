package google2016;
import commonClass.*;
public class Array2D {
	//一个含有interger 的matrix， 找出一个点使得到左上角的submatrix 和 到右下角的matrix的和相等
	public int[] findPoint(int[][] matrix){
		int m=matrix.length, n=matrix[0].length;
		int[][] sum=new int[m+1][n+1];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				sum[i+1][j+1]=sum[i][j+1]+sum[i+1][j]-sum[i][j]+matrix[i][j];
			}
		}
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				int upper=sum[i+1][j+1];
				int lower = sum[m][n]-sum[i+1][n]-sum[m][j+1]+upper;
				if(upper==lower) return new int[]{i,j};
			}
		}
		return new int[]{};
	}
	public static void main(String[] args){
		Array2D test=new Array2D();
		int[][] arr={{1,2,3,1},{4,5,6,1},{7,8,9,1},{1,1,1,1}};
		test.findPoint(arr);
	}

}
