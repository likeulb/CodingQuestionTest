package snapChat;

import java.util.*;

public class WordAbbrivation {
	HashMap<String,TrieNode> map=new HashMap<>();
	
	public void wordAbbrivation(List<String> input){
		for(String s:input){
			String key=""+s.charAt(0)+s.length()+s.charAt(s.length()-1);
			if(!map.containsKey(key)){
				TrieNode node=new TrieNode();
				map.put(key, node);
			}
			insert(map.get(key),s);
		}
		for(String s:input){
			String key=""+s.charAt(0)+s.length()+s.charAt(s.length()-1);
			print(map.get(key),s,key);
		}
		
	}
	public void print(TrieNode node, String s,String key){
		char last=s.charAt(s.length()-1);
		StringBuilder sb=new StringBuilder();
		if(node.next[last-'a'].count==1){
			if(key.length()<s.length()) System.out.println(key);
			else System.out.println(s);
		}
		else{
			TrieNode p=node;
			p=p.next[last-'a'];
			for(int i=0;i<s.length();i++){
				if(p.next[s.charAt(i)-'a'].count>1){
					sb.append(s.charAt(i));
					p=p.next[s.charAt(i)-'a'];
				}
				else{
					sb.append(s.charAt(i));
					break;
				}
			}
			sb.append(s.length()).append(last);
			if(sb.length()>=s.length()) System.out.println(s);
			else{
				System.out.println(sb.toString());
			}
		}
		
	}
	public void insert(TrieNode node, String s){
		TrieNode p=node;
		char last=s.charAt(s.length()-1);
		if(p.next[last-'a']==null){
			p.next[last-'a']=new TrieNode();
		}
		p.next[last-'a'].count++;
		p=p.next[last-'a'];
		for(int i=0;i<s.length();i++){
			int index=s.charAt(i)-'a';
			if(p.next[index]==null){
				p.next[index]=new TrieNode();
				
			}
			p.next[index].count++;
			p=p.next[index];
		}
		p.end=true;
	}
	
	

	class TrieNode{
		TrieNode[] next;
		boolean end;
		int count;
		TrieNode(){
			next=new TrieNode[26];
			end=false;
			count=0;
		}
	}
	public static void main(String[] args){
		List<String> input=new ArrayList<>();
		input.add("look");
		input.add("god");
		input.add("internal");
		input.add("me");
		input.add("internet");
		input.add("interval");
		input.add("intension");
		input.add("face");
		input.add("intrusion");
		WordAbbrivation test=new WordAbbrivation();
		test.wordAbbrivation(input);
	}
}
