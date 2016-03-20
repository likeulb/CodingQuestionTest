package facebook;

public class TrieNode {
	TrieNode[] next;
	boolean end;
	int index;
	public TrieNode(){
		next=new TrieNode[26];
		end=false;
		index=-1;
	}
}
