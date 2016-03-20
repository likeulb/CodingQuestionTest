package twitter;

import java.util.*;

public class OnSite {
	//看一个string可以用多少种方法变成另一个string，只允许去掉字母，
	public int waysToRemove(String s, String t){
		if(s==null||s.length()<t.length()) return 0;
		if(s.length()==t.length()){
			if(s.equals(t)) return 0;
			else return -1;
		}
		int[][] dp=new int[s.length()+1][t.length()+1];
		for(int i=0;i<=s.length();i++){
			dp[i][0]=1;
		}
		for(int i=1;i<=s.length();i++){
			for(int j=1;j<=t.length();j++){
				dp[i][j]=dp[i-1][j];
				if(s.charAt(i-1)==t.charAt(j-1)){
					dp[i][j]+=dp[i-1][j-1];
				}
			}
		}
		return dp[s.length()][t.length()];
	}
	//给4个数字，然后让你用+-*/算出24
	
	public static void main(String[] args){
		OnSite test=new OnSite();
		System.out.println(test.waysToRemove("aaab", "abc"));
	}
}
