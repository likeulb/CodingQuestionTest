package solution;

import java.util.HashMap;

public class SubMatrixSumLintCode {
	
	    /**
	     * @param matrix an integer matrix
	     * @return the coordinate of the left-up and right-down number
	     */
	    public int[][] submatrixSum(int[][] matrix) {
	        // Write your code here
	        int[][] result = new int[2][2];
	        if(matrix==null||matrix.length==0||matrix[0].length==0) return result;
	        int m=matrix.length, n=matrix[0].length;
	        
	        for(int i=0;i<m;i++){
	            int[] sums = new int[n];
	            for(int k=i;k<m;k++){
	                for(int j=0;j<n;j++){
	                    sums[j]+=matrix[k][j];
	                }
	            
	                HashMap<Integer,Integer> map =new HashMap<>();
	                map.put(0,-1);
	                int lastSum=0;
	                for(int t=0;t<sums.length;t++){
	                    lastSum+=sums[t];
	                    if(map.containsKey(lastSum)){
	                        result=new int[][]{{i,map.get(lastSum)+1},{k,t}};
	                        return result;
	                    }
	                    map.put(lastSum,t);
	                }
	            }
	            
	        }
	        return result;
	    }
	    
	public static void main(String[] args){
		int[][] a = {{1,5,7},{3,7,-8},{4,-8,9}};
		SubMatrixSumLintCode test = new SubMatrixSumLintCode();
		test.submatrixSum(a);
	}

}
