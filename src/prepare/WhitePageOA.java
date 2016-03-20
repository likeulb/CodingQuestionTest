package prepare;

import java.io.*;
import java.util.*;

public class WhitePageOA {
	//用类似BFS的想法，能当前层的值最多可以extend到多远的元素
	private static String useBFSOnePath(List<Integer> arr){
		int max=0, cur=0, i=0;
		List<Integer> res = new ArrayList<>();
		while(cur-i+1>0){
			int index=i;
			for(;i<=cur;i++){
				if(i<arr.size()&&i+arr.get(i)>=max){ //注意i<arr.size()的条件，否则exception
					max=i+arr.get(i);
					index=i;
					if(max>=arr.size()){
						res.add(index);
						break;
					}
				}
			}
			if(max>=arr.size())
				break;
			res.add(index);
			cur=max;
			
		}
		if(max<arr.size()) return "failure";
		StringBuilder sb = new StringBuilder();
		for(int k:res){
			sb.append(k+" ");
		}
		sb.append("out");
		return sb.toString();
	}
	
	//greedy的做法假设到最后一个index就算结束了, 对于4,3,3,0,2,0 会有问题
	private static String ArrayHopper(List<Integer> arr){
		StringBuilder sb = new StringBuilder();
		if(arr==null||arr.size()==0||arr.get(0)==0) return "failure";
		
		int max=0;
		int curJump=0;
		
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<arr.size();i++){
			
			if(i>curJump){
				curJump=max;
				sb.append(map.get(max)+" ");
				
			}
			if(max<=i&&arr.get(i)==0) break;
			if(i+arr.get(i)>max){
				max=i+arr.get(i);
				map.put(max, i);
			}
			if(max>=arr.size()-1){
				sb.append(i);
				if(max==arr.size()-1){
					sb.append(" "+(arr.size()-1));
				}
				sb.append(" out");
				break;
			}
			
			
		}
		if(max<arr.size()-1) return "failure";
		return sb.toString();
	}
	
	
	
	//返回所有jump次数最少的path
	static int minJump;
	public static List<List<Integer>> allpath(List<Integer> arr){
		List<List<Integer>> result = new ArrayList<>();
		if(arr==null||arr.size()==0||arr.get(0)==0) return result;
		
		minJump=Integer.MAX_VALUE;
		List<Integer> item = new ArrayList<>();
		item.add(0);
		
		helper(0,arr.get(0),item,result,arr);
		return result;
	}
	public static void helper(int left, int max, List<Integer> cur, List<List<Integer>> result, List<Integer> arr){
		if(left>=arr.size()-1){
			if(cur.size()>minJump) return;
			if(cur.size()<minJump){
				result.clear();
				minJump=cur.size();
			}
			result.add(new ArrayList<Integer>(cur));
			return;
		}
		
		if(cur.size()>minJump) return;
		for(int i=left+1;i<=max;i++){
			if(i<arr.size()){
				if(i+arr.get(i)>=max){
					cur.add(i);
					helper(i,i+arr.get(i), cur, result, arr);
					cur.remove(cur.size()-1);
				}
				if(i==max&&arr.get(i)==0) return;
			}
			else if(i==arr.size()){
				cur.add(i-1);
				helper(i-1,max,cur,result,arr);
				cur.remove(cur.size()-1);
			}
			else break;
		}
		
	}
	//用类似上面的方法只返回一个jump最少的path
	static List<Integer> result;
	public static String allpathReturnOneResult(List<Integer> arr){
		result = new ArrayList<>();
		if(arr==null||arr.size()==0||arr.get(0)==0) return "failure";
		
		minJump=Integer.MAX_VALUE;
		List<Integer> item = new ArrayList<>();
		item.add(0);
		helper2(0,arr.get(0),item,arr);
		if(result.size()==0){
			return "failure";
		}
		StringBuilder sb = new StringBuilder();
		for(int i:result){
			sb.append(i+" ");
		}
		sb.append("out");
		return sb.toString();
	}
	public static void helper2(int left, int max, List<Integer> cur, List<Integer> arr){
		if(cur.size()>=minJump) return;
		if(left>=arr.size()){
			//if(cur.size()>=minJump) return;
			//if(cur.size()<minJump){
				result=new ArrayList<Integer>(cur);
				minJump=cur.size();
			//}
			return;
		}
		
		
		for(int i=left+1;i<=max;i++){
			if(i<arr.size()){
				if(arr.get(i)==0) continue;
				if(i+arr.get(i)>=max){
					cur.add(i);
					helper2(i,i+arr.get(i), cur, arr);
					cur.remove(cur.size()-1);
				}
				
			}
			else if(i==arr.size()){
				if(cur.size()<minJump){
					result=new ArrayList<Integer>(cur);
					minJump=cur.size();
				}
				return;
			}
			else break;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		int[] test = {4,3,3,0,2,0};
		List<Integer> arr = new ArrayList<>();
		for(int i:test){
			arr.add(i);
		}
		System.out.println(ArrayHopper(arr));
		System.out.println(useBFSOnePath(arr));
		System.out.println(allpathReturnOneResult(arr));
		
		/*Scanner in =new Scanner(System.in);
		String s=in.nextLine();
		BufferedReader reader = new BufferedReader(new FileReader(s));
		
		String line = null;
		while((line=reader.readLine())!=null){
			arr.add(Integer.parseInt(line));
		}
		reader.close();
		System.out.println(ArrayHopper(arr));*/
		
	}
}
