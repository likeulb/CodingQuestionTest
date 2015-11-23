package googleOnSite;

import java.util.*;

public class Cal24 {
	
	static double[] nums;
	static String[] expression;
	static List<String> result;
	static double PRE=1E-6;
	
	public List<String> cal24(double[] input){
		nums=new double[4];
		expression = new String[4];
		result = new ArrayList<String>();
		for(int i=0;i<input.length;i++){
			nums[i]=input[i];
			expression[i] = input[i]+"";
	}
	dfs(4);
	return result;
		

	}
	public void dfs(int n){
		if(n==1&&Math.abs(nums[0]-24)<=PRE){
		result.add(expression[0]);
		return;
	}
	if(Math.abs(nums[0]-24)>24)
		return;

	for(int i=0;i<n;i++){
		for(int j=i+1;j<n;j++){
			double firstnum=nums[i];
			double secnum = nums[j];
			nums[j] = nums[n-1];
			String left = expression[i];
			String right = expression[j];
			expression[j]=expression[n-1];
		
			expression[i]="("+left+"+"+right+")";
			nums[i] = firstnum+secnum;
			dfs(n-1);
		
			expression[i]="("+left+"-"+right+")";
			nums[i]=firstnum-secnum;
			dfs(n-1);
		
			expression[i]="("+right+"-"+left+")";
			nums[i]=secnum-firstnum;
			dfs(n-1);

			expression[i]="("+left+"*"+right+")";
			nums[i]=firstnum*secnum;
			dfs(n-1);
		
		if(secnum!=0){
			expression[i]="("+left+"/"+right+")";
			nums[i]=firstnum/secnum;
			dfs(n-1);
		}

		if(firstnum!=0){
			expression[i]="("+right+"/"+left+")";
			nums[i]=secnum/firstnum;
			dfs(n-1);
		}
		
		nums[i]=firstnum;
		nums[j]=secnum;
		expression[i]=left;
		expression[j]=right;
		}
		}
	}
	public static void main(String[] args){
		Cal24 Test = new Cal24();
		double[] input = {2,4,5,2};
		List<String> result = Test.cal24(input);
		for(String s: result){
			System.out.println(s);
		}
	}

}
