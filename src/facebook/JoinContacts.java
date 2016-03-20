package facebook;

import java.util.*;

public class JoinContacts {

	static class Contacts{
		String name;
		List<String> emals;
		Contacts(String s, List<String> t){
			this.name=s;
			this.emals=t;
		}
	}
	
	static void join(List<Contacts> input){

		HashMap<String,List<String>> map=new HashMap<>();
		HashMap<String, Integer> names=new HashMap<>();
		for(int i=0;i<input.size();i++){
			for(String s:input.get(i).emals){
				if(!map.containsKey(s)){
					map.put(s,new ArrayList<String>());
				}
				map.get(s).add(input.get(i).name);
			}
			names.put(input.get(i).name, i);
		}
		int[] head=new int[input.size()];
		HashMap<String,String> heads=new HashMap<>();
		Arrays.fill(head, -1);
		for(List<String> l:map.values()){
			for(int i=0;i<l.size()-1;i++){
				String first=l.get(i);
				String sec=l.get(i+1);
				String head1=find2(heads,first);
				String head2=find2(heads,sec);
				if(!head1.equals(head2)){
					heads.put(head1, head2);
				}
				//int head1=find(head,names.get(first));
				//int head2=find(head,names.get(sec));
				//if(head1!=head2){
					//head[head1]=head2;
				//}
			}
		}
		//boolean[] checked =new boolean[input.size()];
		//HashMap<Integer, List<String>> result=new HashMap<>();
		HashMap<String, List<String>> result=new HashMap<>();
		for(int i=0;i<input.size();i++){
			String head0=find2(heads,input.get(i).name);
			//int head0=find(head,i);
			if(!result.containsKey(head0)){
				result.put(head0,new ArrayList<String>());
			}
			result.get(head0).add(input.get(i).name);
		}
		for(List<String> l:result.values()){
			System.out.println(l);
		}
	}
	public static String find2(HashMap<String,String> heads, String s){
		if(!heads.containsKey(s)){
			heads.put(s, s);
			return s;
		}
		if(heads.get(s).equals(s)){
			return s;
		}
		return find2(heads,heads.get(s));
	}
	public static int find(int[] head, int i){
		if(head[i]==-1||head[i]==i) return i;
		return find(head,head[i]);
	}
	public static void main(String[] args){
		List<String> john=new ArrayList<>(Arrays.asList("1","2","3"));
		List<String> mary=new ArrayList<>(Arrays.asList("4","5","2"));
		List<String> tom=new ArrayList<>(Arrays.asList("6"));
		List<String> jerry=new ArrayList<>(Arrays.asList("5"));
		List<Contacts> input=new ArrayList<>();
		input.add(new Contacts("john",john));
		input.add(new Contacts("mary",mary));
		input.add(new Contacts("tom",tom));
		input.add(new Contacts("jerry",jerry));
		join(input);
	}
}
