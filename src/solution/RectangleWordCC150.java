package solution;

import java.util.*;

public class RectangleWordCC150 {
	WordGroup[] group;
	int maxLength;
	Trie[] trieList;
	RectangleWordCC150(String[] list){
		group=createWordGroups(list);
		maxLength=group.length;
		trieList=new Trie[maxLength];
	}
	
	public Rectangle maxRectangle(){
		int maxSize=maxLength*maxLength;
		for(int z=maxSize;z>0;z--){
			for(int i=1;i<=maxLength;i++){
				if(z%i==0){
					int j=z/i;
					if(j<=maxLength){
						Rectangle rec=makeRectangle(i,j);
						if(rec!=null)
							return rec;
					}
				}
			}
		}
		return null;
	}
	Rectangle makeRectangle(int length, int height){
		if(group[length-1]==null||group[height-1]==null) return null;
		if(trieList[height-1]==null){
			List<String> words = group[height-1].getWords();
			trieList[height-1]=new Trie(words);
		}
		return makePartialRectangle(length,height, new Rectangle(length));
	}
	Rectangle makePartialRectangle(int l, int h, Rectangle rec){
		if(rec.height==h){
			if(rec.isComplete(l,h,group[h-1])){
				return rec;
			}
			else return null;
		}
		if(!rec.isPartialOK(l,trieList[h-1]))
			return null;
		for(String s:group[l-1].lookup){
			Rectangle cur=rec.append(s); //append one row, then recursively make the rectangle
			Rectangle test = makePartialRectangle(l,h,cur);
			if(test!=null) return test;
		}
		return null;
	}
	
	public WordGroup[] createWordGroups(String[] list){
		WordGroup[] grouplist;
		int maxLength=0;
		for(String s:list){
			maxLength=Math.max(maxLength, s.length());
		}
		grouplist=new WordGroup[maxLength];
		for(String s: list){
			int len=s.length()-1;
			if(grouplist[len]==null){
				grouplist[len]=new WordGroup();
			}
			grouplist[len].addWord(s);
		}
		return grouplist;
	}
	public class Trie{
		TrieNode root;
		Trie(List<String> words){
			for(String s:words){
				insertWord(s);
			}
		}
		void insertWord(String s){
			TrieNode p=root;
			for(char c:s.toCharArray()){
				int index=c-'a';
				if(p.next[index]==null){
					p.next[index]=new TrieNode();
				}
				p=p.next[index];
			}
		}
		boolean contains(String prefix){ 
			TrieNode p=root;
			for(char c:prefix.toCharArray()){
				int index=c-'a';
				if(p.next[index]==null) return false;
				p=p.next[index];
			}
			return true;
		}
	}
	public class TrieNode{
		TrieNode[] next;
		TrieNode(){
			next=new TrieNode[26];
		}
	}
	
	public class Rectangle{
		public int height, length;
		public List<String> matrix;
		public Rectangle(int l){ //length固定但height在变
			height=0;
			length=l;
		}
		//check if each column is a word from dictionary
		public boolean isComplete(int l, int h, WordGroup group){
			if(height==h){
				for(int i=0;i<l;i++){
					String col=getColString(i);
					if(!group.containsWord(col)) return false;
				}
				return true;
			}
			return false;
		}
		public String getColString(int i){
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<height;j++){
				sb.append(matrix.get(j).charAt(i));
			}
			return sb.toString();
		}
		public boolean isPartialOK(int l, Trie trie){
			if(height==0) return true;
			for(int i=0;i<l;i++){
				String col=getColString(i);
				if(!trie.contains(col)) return false;//check if the column string is valid prefix
			}
			return true;
		}
		public Rectangle append(String s){
			Rectangle rec = new Rectangle(length);
			for(String t:matrix){
				rec.matrix.add(t);
				rec.height++;
			}
			rec.matrix.add(s);
			rec.height++;
			return rec;
			
		}
	}
	public class WordGroup{
		private HashSet<String> lookup;
		public void addWord(String s){
			lookup.add(s);
		}
		
		public List<String> getWords(){
			return new ArrayList<String>(lookup);
		}
		public boolean containsWord(String s){
			return lookup.contains(s);
		}
		public int length(){
			return lookup.size();
		}
	}
}
