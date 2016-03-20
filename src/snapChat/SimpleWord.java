package snapChat;

import java.util.*;
//http://www.1point3acres.com/bbs/thread-166020-1-1.html



//http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=167411&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B5%5D%3D5%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D21%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
//http://www.1point3acres.com/bbs/thread-168299-1-1.html
//http://www.1point3acres.com/bbs/thread-169519-1-1.html
//http://www.1point3acres.com/bbs/thread-166984-1-1.html
//http://www.1point3acres.com/bbs/thread-167943-1-1.html
public class SimpleWord {
	public List<String> Simplewords(List<String> words){
		List<String> result=new ArrayList<>();
		if(words==null||words.size()==0) return result;
		HashSet<String> set=new HashSet<>();
		int len=Integer.MAX_VALUE;
		for(String s:words){
			set.add(s);
			len=Math.min(len,s.length());
		}
		for(String s:words){
			set.remove(s);
			
			if(s.length()<len*2||!wordBreak(s,set)){
				result.add(s);
			}
			set.add(s);
		}
		
		return result;
	}
	public boolean wordBreak(String s, HashSet<String> set){
		boolean[] dp=new boolean[s.length()+1];
		dp[0]=true;
		for(int i=1;i<=s.length();i++){
			/*for(String t:set){
				int len=t.length();
				if(i-len>=0&&dp[i-len]&&set.contains(s.substring(i-len,i))){
					dp[i]=true;
					break;
				}
			}*/
			for(int j=0;j<i;j++){
				if(dp[j]&&set.contains(s.substring(j,i))){
					dp[i]=true;
					break;
				}
			}
		}
		return dp[s.length()];
	}
	public static void main(String[] args){
		SimpleWord test=new SimpleWord();
		List<String> input=new ArrayList<>();
		/*input.add("whatsoever");
		input.add("so");
		input.add("ever");
		input.add("person");
		input.add("per");
		input.add("son");
		input.add("what");
		input.add("sales");
		input.add("salesperson");*/
		input.add("aaaaaaaaaaaaaaaaa");
		input.add("aaaaaaaaaaaaaaaa");
		input.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		input.add("a");
		input.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		input.add("aaaaa");
		List<String> out=test.Simplewords(input);
		for(String s:out){
			System.out.println(s);
		}
	}
	
	//word abbreviation
	//http://www.1point3acres.com/bbs/thread-162312-1-1.html
	//http://www.1point3acres.com/bbs/thread-167964-1-1.html
	
	
}
