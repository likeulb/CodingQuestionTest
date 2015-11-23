package solution;

import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LintCode {
	public int maxTwoSubArrays(List<Integer> list) {
        // write your code
        if(list==null||list.size()==0) return 0;
        int[] left = new int[list.size()];
        int[] right = new int[list.size()];
        int local = list.get(0);
        int max = list.get(0);
        left[0]=max;
        for(int i=1;i<list.size();i++){
            local = Math.max(local+list.get(i), list.get(i));
            max = Math.max(max, local);
            left[i]=max;
        }
        int n=list.size();
        max = list.get(n-1);
        local = list.get(n-1);
        right[n-1] = max;
        for(int i=n-2;i>=0;i--){
            local = Math.max(local+list.get(i),list.get(i));
            max = Math.max(max, local);
            right[i]=max;
        }
        int result = Integer.MIN_VALUE;
        for(int i=0;i<n-1;i++){
            result=Math.max(result, left[i]+right[i+1]);
        }
        return result;
    }
	public int maxSubArray(List<Integer> list, int k) {
        // write your code
        if(list.size()<k) return 0;
        int[][] dp = new int[list.size()+1][k+1];
        
        for(int i=1;i<=list.size();i++){
            for(int j=1;j<=Math.min(i,k);j++){
                dp[i][j]=dp[i-1][j];
                int local = 0;
                for(int m=i;m>=j;m--){
                    local = Math.max(0, local)+list.get(m-1);
                    dp[i][j]=Math.max(dp[i][j],dp[m-1][j-1]+local);
                }
            }
        }
        return dp[list.size()][k];
    }
	
	
	    int minRemove;
	    public List<String> removeInvalidParentheses(String s) {
	        List<String> result = new ArrayList<>();
	        if(s==null) return result;
	        HashMap<Integer, List<String>> map = new HashMap<>();
	        int count = 0;
	        for(int i=0;i<s.length();i++){
	            if(s.charAt(i)=='('||s.charAt(i)==')')
	                count++;
	        }
	        minRemove = count;
	        helper(map, 0, s);
	        return map.get(minRemove);
	    }
	    public void helper(HashMap<Integer, List<String>> map, int numRemove, String cur){
	        if(numRemove==minRemove&&!isValid(cur))
	            return;
	        if(isValid(cur)){
	            minRemove = Math.min(minRemove, numRemove);
	            if(!map.containsKey(minRemove)){
	                map.put(minRemove, new ArrayList<String>());
	            }
	            map.get(minRemove).add(cur);
	            return;
	        }
	        for(int i=0;i<cur.length();i++){
	            if(cur.charAt(i)!='('&&cur.charAt(i)!=')') continue;
	            String removed = cur.substring(0,i)+cur.substring(i+1);
	            helper(map, numRemove+1, removed);
	        }
	    }
	    public boolean isValid(String s){
	        int count=0;
	        for(int i=0;i<s.length();i++){
	            if(s.charAt(i)=='(')
	                count++;
	            else if(s.charAt(i)==')'){
	                count--;
	                if(count<0) return false;
	            }
	        }
	        return count==0;
	    }
	    List<String> getAllAbbreviation(String s){
	    	if(s.length()==0) return new ArrayList<String>();
	    	if(s.length()==1) return Arrays.asList("1", s);
	    	List<String> pre = getAllAbbreviation(s.substring(0, s.length()-1));
	    	List<String> result = new ArrayList<>();
	    	char cur = s.charAt(s.length()-1);
	    	for(String p:pre){
	    		result.add(p+cur);
	    		int num=0;
	    		if(Character.isDigit(p.charAt(p.length()-1))){
	    			num = Character.getNumericValue(p.charAt(p.length()-1))+1;
	    			p=p.substring(0,p.length()-1);
	    		}
	    		result.add(p+num+"");
	    	}
	    return result;
	    }
	    
	   
	        public int strStr(String haystack, String needle) {
	            if(needle==null||needle.length()==0) return 0;
	            if(needle.length()>haystack.length()) return -1;
	            int[] next = getNext(needle);
	            int i=0;
	            while(i<=haystack.length()-needle.length()){
	                int j=0;
	                while(j<needle.length()&&(haystack.charAt(i+j)==needle.charAt(j))){
	                    j++;
	                }
	                if(j==needle.length()) return i;
	                else if(j==0) i++;
	                else i=i+j-next[j-1];
	            }
	            return -1;
	        }
	        
	        public int[] getNext(String s){
	            int[] next = new int[s.length()];
	            for(int i=1;i<s.length();i++){
	                int tmp = next[i-1];
	                while(tmp!=0&&s.charAt(i)!=s.charAt(tmp)){
	                    tmp = next[tmp-1];
	                }
	                if(s.charAt(i)==s.charAt(tmp)){
	                    tmp++;
	                }
	                next[i]=tmp;
	            }
	            return next;
	        }
	    
	
	
	public static void main(String[] args){
		LintCode Test=new LintCode();
		Test.strStr("bbaa", "aab");
		Integer[] a = {-1,-2,-3,-100,-1,-50};
		List<Integer> list= Arrays.asList(a);
		List<String> result = Test.getAllAbbreviation("abb");
		for(String s: result){
			System.out.println(s);
		}
		String s="(";
		System.out.println("test"+s.substring(0,0));
		
	}
}
