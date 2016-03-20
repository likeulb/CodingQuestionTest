package solution;

public class NumMatrix {
    int[][] tree;
    int m;
    int n;

    public NumMatrix(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) return;
        m=matrix.length;
        n=matrix[0].length;
        tree=new int[m+1][n+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                update(i,j,matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if(m==0||n==0) return;
        int old = sumRegion(row,col,row,col);
        updateHelper(row,col,val-old);
    }
    public void updateHelper(int row, int col, int diff){
        if(m==0||n==0) return;
        for(int i=row+1;i<=m;i +=i&(-i)){
            for(int j=col+1;j<=n;j +=j&(-j)){
                tree[i][j]+=diff;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(m==0||n==0) return 0;
        return sum(row2+1, col2+1)-sum(row1, col2+1)-sum(row2+1,col1)+sum(row1,col1);
    }
    public int sum(int row, int col){
        int sum=0;
        for(int i=row;i>0;i-=i&(-i)){
            for(int j=col;j>0;j-=j&(-j)){
                sum+=tree[i][j];
            }
        }
        System.out.println(sum);
        return sum;
    }
    public static void main(String[] args){
    	int[][] matrix={{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
    	NumMatrix test=new NumMatrix(matrix);
    	test.sumRegion(2,1,4,3);
    	test.update(3,2,2);
    	test.sumRegion(2,1,4,3);
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);
