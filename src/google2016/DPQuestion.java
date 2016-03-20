package google2016;
import java.util.Arrays;
import java.util.*;

import commonClass.*;
public class DPQuestion {
	//matrix中有flower， statue，还有empty的地方。statue能挡住视野，
	//问站在哪个空的地方，能看到最多的花（只可以看上下左右，四个方向）
	public int maxFlower(int[][] matrix){
		int m=matrix.length, n=matrix[0].length;
		int[][] dp=new int[m][n];
		for(int i=m-1;i>=0;i--){
			for(int j=n-1;j>=0;j--){
				if(i==m-1&&j==n-1){
					dp[i][j]=0;
					continue;
				}
				if(i==m-1){
					dp[i][j]=matrix[i][j+1]<0?0:dp[i][j+1]+matrix[i][j+1];
				}
				else if(j==n-1){
					dp[i][j]=matrix[i+1][j]<0?0:dp[i+1][j]+matrix[i+1][j];
				}
				else{
					dp[i][j]=matrix[i][j+1]<0?0:dp[i][j+1]+matrix[i][j+1]+matrix[i+1][j]<0?0:dp[i+1][j]+matrix[i+1][j];
				}
			}
		}
		int result=dp[0][0];
		int up=0, left=0;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(i==0&&j==0) continue;
				if(i==0){
					left = matrix[i][j-1]<0?0:left+matrix[i][j-1];
					result=Math.max(result, left+dp[i][j]);
				}
				else if(j==0){
					up = matrix[i-1][j]<0?0:up+matrix[i-1][j];
					result=Math.max(result,  up+dp[i][j]);
				}
				else{
					left = matrix[i][j-1]<0?0:left+matrix[i][j-1];
					up = matrix[i-1][j]<0?0:up+matrix[i-1][j];
					result=Math.max(result, up+dp[i][j]+left);
					
				}
			}
		}
		return result;
	}
	//有3n个数围成一个环，取走其中一个的话会顺带去掉这个数相邻的两个数（这两个不计入总和），
	//剩下的继续围成环，问取走n个数构成总和的最大值。
	public int maxSum(int[] arr){
		int result=0;
		for(int i=0;i<3;i++){
			int cur=houseRobberHelper(arr, i, arr.length-3+i);
			result=Math.max(result, cur);
		}
		return result;
	}
	public int houseRobberHelper(int[] arr, int left, int right){
		
		int a=0;
		int b=arr[left];
		for(int i=left+1;i<=right;i++){
			int cur=Math.max(b, a+arr[i]);
			a=b;
			b=cur;
		}
		return b;
	}
	//给一个list 的时间start, end, profits, 让求出来不冲突的最大profit
	public int maxProfit(Interval[] lists){
		Arrays.sort(lists, new Comparator<Interval>(){
			public int compare(Interval l1, Interval l2){
				return l1.start-l2.start;
			}
		});
		
		int[] dp=new int[lists.length];
		dp[0]=lists[0].profit;
		
		
		for(int i=1;i<lists.length;i++){
			Interval cur=lists[i];
			int max=cur.profit;
			for(int j=0;j<i;j++){
				Interval l=lists[j];
				if(l.end<=cur.start){
					max=Math.max(dp[j]+cur.profit, max);
				}
				else max=Math.max(dp[j], max);
			}
			dp[i]=max;
			
		}
		int sum=dp[dp.length-1];
		List<Interval> path=new ArrayList<>();
		for(int i=dp.length-1;i>=0;i--){
			if(sum!=dp[i]) continue;
			if(i>0&&dp[i]!=dp[i-1]){
				sum-=lists[i].profit;
				path.add(lists[i]);
				if(sum==0) break;
			}
			if(i==0){
				path.add(lists[0]);
			}
			
		}
		Collections.reverse(path);
		for(Interval l:path){
			System.out.println(l.start+","+l.end);
		}
		
		return dp[lists.length-1];
		
	}
	public int longestPalindromeSubsequence(String s){
		int[][] dp=new int[s.length()][s.length()];
		
		for(int i=0;i<s.length();i++){
			dp[i][i]=1;
		}
		/*for(int len=2;len<=s.length();len++){
			for(int i=0;i<s.length()-len+1;i++){
				int j=i+len-1;
				if(s.charAt(i)==s.charAt(j)&&len==2){
					dp[i][j]=2;
				}
				else if(s.charAt(i)==s.charAt(j)){
					dp[i][j]=dp[i+1][j-1]+2;
				}
				else dp[i][j]=Math.max(dp[i][j-1], dp[i+1][j]);
			}
			
		}*/
		for(int i=1;i<s.length();i++){
			for(int j=i-1;j>=0;j--){
				if(i-j==1){
					if(s.charAt(i)==s.charAt(j)) dp[i][j]=2;
					else dp[i][j]=1;
				}
				else{
					if(s.charAt(i)==s.charAt(j)){
						dp[i][j]=dp[i-1][j+1]+2;
					}
					else dp[i][j]=Math.max(dp[i-1][j], dp[i][j+1]);
				}
			}
		}
		
		return dp[s.length()-1][0];
		//return dp[0][s.length()-1]; //when iterate through length first
		
	}
	
	//longestpalindrome using dp
	public String longestPalindrome(String s) {
        if(s==null||s.length()<=1) return s;
        
        int len=1, start=0;
        boolean[][] dp=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            for(int j=i;j>=0;j--){
                if(i==j){
                    dp[i][j]=true;
                }
                else{
                    if(s.charAt(i)==s.charAt(j)&&(i-j==1||dp[i-1][j+1])){
                        dp[i][j]=true;
                        int cur=i-j+1;
                        if(cur>len){
                            len=cur;
                            start=j;
                        }
                
                    }
                }
            }
            
        }
        return s.substring(start, start+len);
    }
	//有面值1, f1, f2, .., fn 的硬币无限个，组成给定的 k 最少需要多少硬币
	public int coinChange(int[] f, int target){
		int[] dp = new int[target+1];
		Arrays.fill(dp,Integer.MAX_VALUE);
		Arrays.sort(f); //denominator is sorted
		dp[0]=0;
		for(int i=1;i<=target;i++){
			
			for(int j=0;j<f.length;j++){
				if(i-f[j]>=0&&dp[i-f[j]]>=0){
					dp[i]=Math.min(dp[i], dp[i-f[j]]+1);
				}
				else if(i-f[j]<0) break;
			}
		}
		return dp[target];
	}
	
	public static void main(String[] args){
		DPQuestion test=new DPQuestion();
		
		//coin changing
		int[] f={1,5,7,9};
		System.out.println(test.coinChange(f, 16));
		
		System.out.println(test.longestPalindrome("bb"));
		int[] arr={1,2,3,9,8,3};
		System.out.println(test.maxSum(arr));
		
		Interval l1=new Interval(0,3,5);
		Interval l2=new Interval(9,12,9);
		Interval l3=new Interval(3,5,6);
		Interval l4=new Interval(4,10,11);
		Interval[] list=new Interval[4];
		list[0]=l1;
		list[1]=l2;
		list[2]=l3;
		list[3]=l4;
		System.out.println(test.maxProfit(list));
	}

}
