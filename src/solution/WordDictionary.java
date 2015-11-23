package solution;

public class WordDictionary {
	 
    TrieNode root;
    WordDictionary(){
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(node.next[index]==null){
                node.next[index]=new TrieNode();
            }
            node = node.next[index];
        }
        node.end=true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        TrieNode node = root;
        return helper(word, 0, root);
    }
    
    public boolean helper(String word, int i, TrieNode node){
	        if(i==word.length()&&node.end) return true;
	        
	        if(i<word.length()){
	        	if(word.charAt(i)!='.'){
	        		int index = word.charAt(i)-'a';
	        		if(node.next[index]==null) return false;
	        		else return helper(word, i+1, node.next[index]);
	        	} 
	        	for(TrieNode nxt : node.next){
	        		if(nxt!=null){
	        			if(helper(word, i+1, nxt)){
	        				return true;
	        			}
	        		}
	        	}
	        	return false;
	        }
	        return false;
    }
    
    class TrieNode{
        TrieNode[] next = new TrieNode[26];
        boolean end=false;
        
        TrieNode(){
            
        }
    }
	    
	    public static void main(String[] args){
	    	WordDictionary w= new WordDictionary();
	    	w.addWord("at");
	    	w.addWord("and");
	    	w.addWord("an");
	    	w.addWord("add");
	    	w.search("a");
	    	w.search(".at");
	    	w.addWord("bat");
	    	w.search(".at");
	    	w.search("an.");
	    	w.search("a.d.");
	    	w.search("b.");
	    	w.search("a.d");
	    	w.search(".");
	    	
	    }
}
