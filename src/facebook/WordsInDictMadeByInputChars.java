package facebook;

import java.util.*;

public class WordsInDictMadeByInputChars {
	/*Find all words [A-Z] in a dictionary (about 1M words) that are made of a subset (in any order) of the chars in the input parameter [A-Z].
	ex: input "ACRAT" (10 to 20 chars, up to 30 worst case).
	matching words: "A", "CAR", "ACA", "ART", "RAC"
	non-matching words: "BAR", "AAA"*/
	TrieNode root=new TrieNode();
	public List<String> wordsMadeByInputCharsCallMultipleTime(List<String> dic, String input){
		for(int i=0;i<dic.size();i++){
			insert(root,dic.get(i),i);
		}
		HashMap<Character, Integer> map=new HashMap<>();
		for(char c:input.toCharArray()){
			if(map.containsKey(c)){
				map.put(c, map.get(c)+1);
			}
			else map.put(c,1);
		}
		List<String> result=new ArrayList<>();
		dfs(result,root,map,input.length(),dic);
		return result;
	}
	public void dfs(List<String> result, TrieNode p, HashMap<Character,Integer> map, int len,List<String> dic){
		if(len<0) return;
		if(p.end==true){
			result.add(dic.get(p.index));
		}
		for(char c:map.keySet()){
			if(map.get(c)>0){
				if(p.next[c-'A']!=null){
					map.put(c, map.get(c)-1);
					dfs(result,p.next[c-'A'],map,len-1,dic);
					map.put(c,map.get(c)+1);
				}
			}
		}
		
	}
	public void insert(TrieNode p, String s,int index){
		for(int i=0;i<s.length();i++){
			if(p.next[s.charAt(i)-'A']==null){
				p.next[s.charAt(i)-'A']=new TrieNode();
			}
			p=p.next[s.charAt(i)-'A'];
		}
		p.end=true;
		p.index=index;
	}
	public static void main(String[] args){
		WordsInDictMadeByInputChars test=new WordsInDictMadeByInputChars();
		List<String> dic=new ArrayList<>(Arrays.asList("A","CAR","ACA","ART","RAC","BAR","AAA"));
		List<String> res=test.wordsMadeByInputCharsCallMultipleTime(dic, "ACRT");
		for(String s:res){
			System.out.println(s);
		}
		
	}

}
