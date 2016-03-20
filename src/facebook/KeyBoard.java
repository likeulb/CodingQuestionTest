package facebook;

import java.util.*;

public class KeyBoard {
  //给你两个数组: keySize[] 每个element代表能存放的最多character，
	//frequency[]每个element代表每个character出现的频率。要算出最少的按键次数。
	public int keyBoard(int[] keySize, int[] count){
		char c='a';
		List<List<Character>> keys=new ArrayList<>();
		int numKey=keySize.length;
		//int numChar=count.length;
		for(int i=0;i<numKey;i++){
			keys.add(new ArrayList<Character>());
		}
		//suppose count is sorted decreasingly
		int[] num=new int[keySize.length];
		int click=0;
		int j=0;
		for(int i=0;i<count.length;i++){
			while(num[j]==keySize[j]){
				j++;
				j%=numKey;
			}
			num[j]++;
			click+=num[j]*count[i];
			keys.get(j).add(c);
			c++;
			j++;
			j%=numKey;
		}
		for(List<Character> list:keys){
			for(char t:list){
				System.out.println(t+" ");
			}
			System.out.println("");
		}
		return click;
	}
	
	//follow up, the order cannot be messed up
	public int keyBoard2(int[] keySize, int[] count){
		int m=keySize.length, n=count.length;
		int[][] dp=new int[m][n+1];
		for(int j=1;j<=n;j++){
			if(j<=keySize[0]){
				dp[0][j]=cal(count,0,j);
			}
			else break;
			
		}
		int preMax=0;
		for(int i=1;i<m;i++){
			int k=keySize[i];
			preMax+=k;
			for(int j=1;j<=n;j++){
				if(j<=i) dp[i][j]=dp[i-1][j];
				else dp[i][j]=Integer.MAX_VALUE;
				for(int size=k;size>=1&&j-size>=0&&j-size<=preMax;size--){
					dp[i][j]=Math.min(dp[i][j],dp[i-1][j-size]+cal(count,j-size,size));
				}
			}
		}
		return dp[m-1][n];
		
	}
	public int cal(int[] count, int start, int num){
		int res=0;
		int click=1;
		while(num>0){
			res+=click*count[start];
			start++;
			click++;
			num--;
		}
		return res;
	}
	public static void main(String[] args){
		KeyBoard test=new KeyBoard();
		int[] keysize={3,3,3};
		int[] count={3,3,3,2,1,1};
		int a=test.keyBoard2(keysize, count);
		System.out.println(a);
		
	}
}
