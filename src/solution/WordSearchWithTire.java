package solution;

import java.util.ArrayList;
import java.util.List;

public class WordSearchWithTire {

	 static class TrieNode{
	        TrieNode[] next;
	        boolean end;
	        int idx;
	        TrieNode(){
	            next = new TrieNode[26];
	        }
	        
	        private static void insertWords(TrieNode root, String[] words,  int index){
	            int i=0, len = words[index].length();
	            while(i<len){
	                char cur = words[index].charAt(i);
	                if(root.next[cur-'a']==null){
	                    root.next[cur-'a'] = new TrieNode();
	                }
	                i++;
	                root = root.next[cur-'a'];
	            }
	            root.end=true;
	            root.idx = index; 
	        }
	        public static TrieNode buildTree(String[] words){
	            TrieNode root = new TrieNode();
	            for(int i=0;i<words.length;i++){
	                insertWords(root, words, i);
	            }
	            return root;
	        }
	    }
	    
	    
	    
	    public List<String> findWords(char[][] board, String[] words) {
	        List<String> result = new ArrayList<String>();
	        if(words==null||words.length==0) return result;
	        if(board==null||board.length==0||board[0].length==0) return result;
	        
	        TrieNode root = TrieNode.buildTree(words);
	        boolean[][] checked = new boolean[board.length][board[0].length];
	        
	        for(int i=0;i<board.length;i++){
	        	for(int j=0;j<board[0].length;j++){
	        		checkWords(board, i, j, root, result, words,checked);
	        	}
	        }
	        return result;	        
	        
	    }
	    public void checkWords(char[][] board, int i, int j, TrieNode root, List<String> result, String[] words, boolean[][] checked){
	    	if(i<0||j<0||i>=board.length||j>=board[0].length) return;
	    	if(checked[i][j]) return;
	    	char c = board[i][j];
	    	TrieNode nxt = root.next[c-'a'];
	    	if(nxt==null) return;
	    	if(nxt.end){
	    		int index = nxt.idx;
	    		result.add(words[index]);
	    		nxt.end=false;
	    	}
	    	checked[i][j]=true;
	    	checkWords(board, i-1,j,nxt,result, words, checked);
	    	checkWords(board, i+1,j,nxt,result,words,checked);
	    	checkWords(board, i,j+1,nxt, result, words, checked);
	    	checkWords(board, i, j-1,nxt, result, words, checked);
	    	checked[i][j]=false;
	    	
	    }
	    
	    public static void main(String[] args){
	    	char[][] test = {{'a','b'},{'c','d'}};
	    	String[] s = {"ab","cd"};
	    	WordSearchWithTire a = new WordSearchWithTire();
	    	System.out.print(a.findWords(test, s));
	    }
}
