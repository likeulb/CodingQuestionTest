package googleOnSite;

import java.util.*;

public class CoinSum {
	public int minNumOfCoin(int[] d, int target){
		int[] dp = new int[target+1];
		for(int i=1;i<=target;i++){
			dp[i]=Integer.MAX_VALUE;
			for(int j=0;j<d.length;j++){
				if(i-d[j]>=0){
					dp[i]=Math.min(dp[i],dp[i-d[j]]+1);
				}
			}
		}
		return dp[target];
	}
	public List<Integer> coins(int[] d, int target){
		List<Integer> result = new ArrayList<Integer>();
		int[] dp = new int[target+1];
		for(int i=1;i<=target;i++){
			dp[i]=Integer.MAX_VALUE;
			for(int j=0;j<d.length;j++){
				if(i-d[j]>=0){
					dp[i]=Math.min(dp[i],dp[i-d[j]]+1);
				}
			}
		}
		int i=target;
		while(i>0){
			for(int k:d){
				if(i-k>=0&&dp[i-k]+1==dp[i]){
					result.add(k);
					i=i-k;
					break;
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		CoinSum test = new CoinSum();
		int[] d = {1,2,3,5,7};
		System.out.println(test.minNumOfCoin(d, 50));
		List<Integer> coins = new ArrayList<>();
		coins=test.coins(d, 4);
		for(int i:coins){
			System.out.print(i+" ");
		}
	}
}
