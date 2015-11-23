package solution;

public class copyBook {

	public static int copyBooks(int[] pages, int k) {
        // write your code here
		 if(pages==null||pages.length==0) return 0;
	        int num = Math.min(pages.length,k);
	        
	        int[][] dp = new int[num+1][pages.length+1];
	        
	        for(int i=1;i<=num;i++){
	            if(i==1){
	                for(int j=1;j<=pages.length;j++){
	                    dp[i][j] = dp[i][j-1]+pages[j-1];
	                }
	            }
	            else{
	                dp[i][1] = dp[1][1];
	                int left =1,right=2;
	                while(right<=pages.length){
	                    int cur = dp[1][right]-dp[1][left];
	                    if(dp[i][right]==0)
	                        dp[i][right] = Integer.MAX_VALUE;
	                    dp[i][right]= Math.min(dp[i][right],Math.max(dp[i-1][left],cur));
	                    if(left<right&&dp[i-1][left]<cur){
	                        left++;
	                    }
	                    else{
	                        right++;
	                        if(left>1){
	                            left--;
	                        }
	                    }
	                }
	                
	            }
	                
	                
	            
	        }
        return dp[num][pages.length];
        
    }
	public static void main(String[] args){
		int[] pages = {393,8306,2935,9673,3769,9181,4804,199,5305,9089,3522,9676,6083,5340,3634,55,2298,497,4892,977,5026,3253,8548,60,4016,6299,6812,7005,7047,6647,1961,8846,2064,8800,4873,1515,5526,1771,8045,1906,2015,8154,1107,4042,1549,9553,7278,2290,2378,3884,6901,6337,9797,5583,1435,2064,757,2313,4825,2102,5160,3916,4558,7694,2851,7149,8104,9531,2943,3490,5390,6865,122,2511,3567,7822,897,9675,7369,6949,1923,6926,7869,2205,961,2313,9166,5848,1568,8898,4921,9982,9025,529,1235,405,8847,3816,156,2416};
		int[] a = {3,2,4};
		System.out.println(copyBook.copyBooks(a,2));
	}
}
