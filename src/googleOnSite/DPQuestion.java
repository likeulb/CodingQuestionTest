package googleOnSite;

public class DPQuestion {
	
	/*画了一个图， 两端是A和B， A，B之间有若干个stop points, 一个人要从A走到B， 中间可以任意停在stop点上， 
a. 这些点的间隔不一样， 例如（想象个一维坐标， e.g. A: 0, B, 1000), 我们有 stop 点 50,60，85， 100.。。。。
b. 50 penalty, 假如从一点到另外一点的距离是50， penalty为0， 否则penalty=Math.abs(50-distance);
找出来从A走到B最小的penalty. 
e.g. input{0, 50, 60, 85, 100} where 0 is A, 100 is B, 
so penalties are:
(0-50:0), (50-100:0) total=0
(0-50:), (50-60:10), (60-100:10) total=20 
....
(0-85:35), (85-100:15) total=50. 
So (0-50-100) is what we want, 只用返回最小值， 不用求序列， 可以用dp O(n^2) 解决。 */
	public int minCost(int[] arr){
		if(arr==null||arr.length<=1)
			return 0;
		int[] dp = new int[arr.length];
		for(int i=1;i<arr.length;i++){
			dp[i] = Integer.MAX_VALUE;
			for(int j=0;j<i;j++){
				dp[i] = Math.min(dp[i], dp[j]+(arr[i]-arr[j]==50?0:Math.abs(arr[i]-arr[j])));
			}
			
		}
		return dp[arr.length-1];
	}
	
	public static void main(String[] args){
		DPQuestion Test = new DPQuestion();
		int[] arr = {0,50,60,85,100};
		System.out.println(Test.minCost(arr));
	}

}
