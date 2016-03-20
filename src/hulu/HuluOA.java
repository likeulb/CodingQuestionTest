package hulu;

import java.io.*;
import java.util.*;


public class HuluOA {
	public static void buildEmployee(String s){
		HashMap<String, String> employee = new HashMap<>();
		HashMap<String, List<String>> boss = new HashMap<>();
		String[] e = s.split("--");
		HashSet<String> ceos = new HashSet<>();
		for(int i=0;i<e.length;i++){
			String cur[] = e[i].split(",");
			String em = cur[0];
			String bo = cur[1];
			if(bo.equals("NULL")){
				ceos.add(em);
			}
			if(!boss.containsKey(bo)){
				boss.put(bo, new ArrayList<String>());
			}
			boss.get(bo).add(em);
			employee.put(em, "("+cur[2]+")"+cur[3]);
		}
		for(String ceo:ceos){
			printEmployeeRelation(employee, boss, ceo, "");
		}
		
	}
	public static void printEmployeeRelation(HashMap<String, String> employee, HashMap<String, List<String>> boss, String curEmp, String dash){
		System.out.println(dash+curEmp+employee.get(curEmp));
		if(boss.containsKey(curEmp)){
			for(String s:boss.get(curEmp)){
				printEmployeeRelation(employee, boss, s, dash+"-");
			}
		}
	}
	
	
	public static String rearrangeString(String s){
		if(s==null||s.length()==0) return "";
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>(){
			public int compare(int[] a, int[] b){
				if(a[1]!=b[1]) return b[1]-a[1];
				else
					return a[0]-b[0];
			}
		});
		int[] chars = new int[256];
		for(char c:s.toCharArray()){
			chars[c]++;
		}
		for(int i=0;i<chars.length;i++){
			if(chars[i]>0){
				que.add(new int[]{i,chars[i]});
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!que.isEmpty()){
			int fre = que.peek()[1];
			while(!que.isEmpty()&&que.peek()[1]==fre){
				char curChar = (char)que.poll()[0];
				for(int i=0;i<fre;i++){
					sb.append(curChar);
				}
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	public static boolean isPalidrome(String word){
		if(word==null||word.length()<=1) return true;
		int i=0,j=word.length()-1;
		while(i<j){
			if(word.charAt(i)!=word.charAt(j)){
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		//rearrangeString("dgetdvaAAAAdeG");
		//System.out.println(isPalidrome("A"));
		BufferedReader reader = new BufferedReader(new FileReader("/Users/yingying/Desktop/sum.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/yingying/Desktop/testout"));
		int numCases = Integer.parseInt(reader.readLine());
		for(int i=1;i<=numCases;i++){
			String[] cur = reader.readLine().split(" ");
			int x=Integer.parseInt(cur[0]);
			int y=Integer.parseInt(cur[1]);
			int sum=x+y;
			writer.write("Case #"+i+"\n");
			writer.write(sum+"\n");
		}
		writer.close();
		reader.close();
		//buildEmployee(s);
		
	}
}
