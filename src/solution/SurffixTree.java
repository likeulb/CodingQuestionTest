package solution;

import java.util.ArrayList;
import java.util.HashMap;
//cc150
public class SurffixTree {
	SuffixTreeNode root=new SuffixTreeNode();
	public SurffixTree(String s){
		for(int i=0;i<s.length();i++){
			String suffix=s.substring(i);
			root.inserString(suffix, i);
		}
	}
	public ArrayList<Integer> search(String s){
		return root.search(s);
	}

	public class SuffixTreeNode{
		HashMap<Character, SuffixTreeNode> children = new HashMap<>();
		char value;
		ArrayList<Integer> indexes = new ArrayList<>();
		public SuffixTreeNode(){};
		
		public void inserString(String s, int index){
			indexes.add(index);
			if(s!=null&&s.length()>0){
				value=s.charAt(0);
				SuffixTreeNode child=null;
				if(children.containsKey(value)){
					child=children.get(value);
				}
				else{
					child=new SuffixTreeNode();
					children.put(value,  child);
				}
				String remainder = s.substring(1);
				child.inserString(remainder,index);
			}
			
		}
		public ArrayList<Integer> search(String s){
			if(s==null||s.length()==0) return indexes;
			char first=s.charAt(0);
			if(children.containsKey(first)){
				String remainder=s.substring(1);
				return children.get(first).search(remainder);
			}
			return null;
		}
	
	}
	public static void main(String[] args){
		SurffixTree test=new SurffixTree("abcaabc");
		ArrayList<Integer> s = test.search("bc");
		for(int i:s){
			System.out.println(i);
		}
	}
}
