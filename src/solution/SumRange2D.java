package solution;

public class SumRange2D {
	int[][] sum;

    public SumRange2D(int[][] matrix) {
        if(matrix==null||matrix.length==0) return;
        sum = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            if(i>0){
                for(int j=0;j<matrix[0].length;j++){
                    sum[i][j]=sum[i-1][j];
                }
            }
            for(int j=0;j<matrix[0].length;j++){
                sum[i][j]+=matrix[i][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        if(row>=sum.length||row<0||col>=sum[0].length||col<0) return;
        int diff=0;
        if(row==0) {
        	diff=val-sum[row][col];
        	sum[row][col]=val;
        }
        else{
            int pre = sum[row-1][col];
            diff = val-(sum[row][col]-pre);
            sum[row][col]=pre+val;
        }
        for(int i=row+1;i<sum.length;i++){
            sum[i][col]+=diff;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum1=0;
        for(int i=col1;i<=col2;i++){
            sum1+=sum[row2][i];
        }
        if(row1==0) return sum1;
        int sum2=0;
        for(int i=col1;i<=col2;i++){
            sum2+=sum[row1-1][i];
        }
        return sum1-sum2;
    }
    public static void main(String[] args){
    	int[][] a = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
    			
    	SumRange2D test = new SumRange2D(a);
    	test.sumRegion(2, 1, 4, 3);
    	test.update(3, 2, 2);
    	test.sumRegion(2, 1, 4, 3);
    }
}
