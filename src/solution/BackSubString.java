package solution;

import java.util.Arrays;

public class BackSubString {
	public static void test(String s){
		String[] back = new String[s.length()];
		for(int i=0;i<s.length();i++){
			back[i]=s.substring(i);
		}
		Arrays.sort(back);
		for(int k=0;k<back.length;k++){
			System.out.println(back[k]);
		}
		
	}
	
	public static int longestCommonSubstring(String A, String B) {
        // write your code here
        if(A==null||A.length()==0||B==null||B.length()==0)
            return 0;
        int[][] dp = new int[A.length()+1][B.length()+1];
        int max = 0;
        for(int i=1;i<A.length();i++){
            for(int j=1;j<B.length();j++){
                if(A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                    
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
        
    }
	
	public static int longestCommonSubstring2(String A, String B) {
        // write your code here
        if(A==null||B==null||A.length()==0||B.length()==0)
            return 0;
        String s = A+"#"+B;
        String[] arr = new String[s.length()];
        for(int i=0;i<s.length();i++){
            arr[i]=s.substring(i);
        }
        Arrays.sort(arr);
        int max = 0;
        for(int j=1;j<arr.length;j++){
            int comLen = getComLen(arr[j],arr[j-1]);
            max = Math.max(comLen,max);
        }
        return max;
    }
    public static int getComLen(String s1, String s2){
        int len = 0;
        int i=0;
        boolean left=false, right=false;
        boolean finished=false;
        while(i<s1.length()||i<s2.length()){
            if(i<s1.length()&&i<s2.length()&&!finished){
            	if(s1.charAt(i)==s2.charAt(i))
            		len++;
            	else
            		finished=true;
            }
            
            
            if(i<s1.length()&&s1.charAt(i)=='#')
                left=true;
            if(i<s2.length()&&s2.charAt(i)=='#')
                right=true;
            i++;
        }
        if(left^right){
            return len;
        }
        else
            return 0;
    }
	
	
	public static void main(String[] args){
		BackSubString.test("banana");
		System.out.println(BackSubString.longestCommonSubstring2("banana", "cianaic"));
	}
}
