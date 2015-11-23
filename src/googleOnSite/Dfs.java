package googleOnSite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Dfs {

	
	/*给你一个整数数组{2,3,5}， 一个target 33, 求出所有能用数组中数组成的<=target的数
例如： 
2,3,5
22,23,25
32，33 
subset的变种， 在23,32的时候纠结了一会。*/
	public void allNumbers(int[] arr, int target){
		HashSet<Integer> result = new HashSet<>();
		List<Integer> items = new ArrayList<>();
		boolean[] checked = new boolean[arr.length];
		int n = getNum(target);
		dfs(result, items, n, target, arr);
		for(int i:result){
			System.out.println(i);
		}
		
	}
	public void dfs(HashSet<Integer> result, List<Integer> item, int n , int target, int[] arr){
		if(item.size()==n){
			int number = calNumber(item);
			if(number<=target)
				result.add(number);
			return;
		}
		for(int i=0;i<arr.length;i++){
			
				item.add(arr[i]);
				
				if(item.size()<n){
					int number = calNumber(item);
					result.add(number);
				}
				dfs(result, item, n, target,arr);
				item.remove(item.size()-1);
			}
		
		
		
	}
	public int calNumber(List<Integer> item){
		int num = 0;
		for(int i=0;i<item.size();i++){
			num=num*10+item.get(i);
		}
		return num;
	}
	public int getNum(int num){
		int n=0;
		while(num>0){
			num/=10;
			n++;
		}
		return n;
	}
	
	/*print出若有round和可能的组合。比如 有 ABCD四个人比*/
	public void printOutAll(String s){
		helperCompetator("",s, 0);
	}
	public void helperCompetator(String cur, String s, int index){
		if(cur.length()==2){
			System.out.println(cur);
			return;
		}
		for(int i=index;i<s.length();i++){
			helperCompetator(cur+s.charAt(i),s,i+1);
		}
	}
	
	public static void main(String[] args){
		Dfs Test = new Dfs();
		int[] a  = {2,3,5,1};
		//Test.allNumbers(a,33);
		String teams = "ABCD";
		Test.printOutAll(teams);
	}
}
