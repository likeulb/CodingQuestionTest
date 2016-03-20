package solution;

import java.util.*;

public class WordBreak {
	//only need to return one possible break, dfs
	static String wordBreak(String s, HashSet<String> wordDict){
		if(s==null||s.length()==0||wordDict==null) return s;
		List<String> list=new ArrayList<>();
		dfs(s,0,list,wordDict);
		StringBuilder sb=new StringBuilder();
		for(String t:list){
			if(sb.length()>0){
				sb.append(" ");
			}
			sb.append(t);
		}
		return sb.toString();
	}
	static boolean dfs(String s, int index, List<String> result, HashSet<String> wordDict){
		if(index==s.length()){
			return true;
		}
		for(int i=index+1;i<=s.length();i++){
			String tmp = s.substring(index,i);
			if(wordDict.contains(tmp)){
				result.add(tmp);
				if(dfs(s,i,result,wordDict)){
					return true;
				}
				result.remove(result.size()-1);
			}
		}
		return false;
	}
	

}
