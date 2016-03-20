package facebook;

public class DPQuestion {
/*第二道题：
面试官说是道新题  finding ali baba
就是ali baba是个怪兽，他可能在[0，1, ..., numCaves-1]出现，他每隔一天就要换
一个地方，但他每次只能移左右一格。
然后给你一个strategy[]数组，数组里面是猜测每天ali baba的位置。如果里面有一个 
猜中了，这个strategy就是正确的。
问题就是让你写一个判断函数 alibaba(int numCaves, int[] strategy)来判别这个
strategy数组是不是稳赢的策略。写完后问时间复杂度，然后问了下大概怎么样可以优
化～～～. */
	public boolean alibaba(int numCaves, int[] strategy){
		int days=strategy.length;
		boolean[][] dp=new boolean[numCaves][days];
		for(int i=0;i<days;i++){
			dp[strategy[i]][i]=true;
		}
		for(int i=0;i<numCaves;i++){
			for(int j=1;j<days;j++){  //第j天抓住即第j天抓住或j-1天抓住
				if(i==0){
					dp[i][j]=dp[i][j]||dp[i+1][j-1]; 
				}
				else if(i==numCaves-1){
					dp[i][j]=dp[i][j]||dp[i-1][j-1];
				}
				else{
					dp[i][j]=dp[i][j]||(dp[i-1][j-1]&&dp[i+1][j-1]);//前一天的strategy在i+1和j+1都有抓
				}
			}
		}
		for(int i=0;i<numCaves;i++){
			if(dp[i][days-1]==false) return false; //看最后一天是不是在所有cave都会抓到
		}
		return true;
		
	}
}
