package prepare;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GoogleTest {
	
	//find missing range, {0,1,99},0,99 output 2-98, {1,3,86},0,99, output 0,2,4-85,87-99
	public String findMissingNumber(int[] nums, int start, int end){
		StringBuilder sb = new StringBuilder();
		int last=start-1;
		for(int i=0;i<nums.length;i++){
			if(nums[i]!=last+1){
				int st = last+1;
				sb.append(st+ (nums[i]==st+1?"":"-"+(nums[i]-1)));
				sb.append(",");
				
			}
			last = nums[i];
		}
		
		if(last!=end){
			sb.append(last==(end-1)?end:(last+1)+"-"+end);
		}
		else{
			sb.deleteCharAt(sb.length()-1);
		}
		
		return sb.toString();
	}
	
	//return the minimum number of squares to add to n, like 9=3^2, return 1, 19=9+9+1, return 3, not 16+1+1+1
	public int minNumber(int n){
		if(n<=0) return 0;
		int[] dp = new int[n+1];
		dp[1]=1;
		for(int i=2;i<=n;i++){
			int min = Integer.MAX_VALUE;
			int j=1;
			while(j*j<=i){
				int cur = dp[i-j*j]+1;
				min = Math.min(min, cur);
				j++;
			}
			dp[i]=min;
		}
		return dp[n];
	}
	
	//generate random for given probability
	public int generateRandomGivenProbability(double[] arr, int[] id){
		Random ran = new Random();
		int i=(int)(ran.nextDouble()*100);
		double end=arr[0];
		double start=0;
		
		for(int j=0;j<arr.length;j++){
			
			if(i>=(int)(start*100)&&i<(int)(end*100))
				return id[j];
			start=end;
			end=j==arr.length-1?1:end+arr[j+1];
			
		}
		return 0;
	}
	public int generateRandom(double[] arr, int[] id){
		Random ran = new Random();
		double r = ran.nextDouble();
		double start = 0;
		double end = arr[0];
		
		for(int i=0;i<arr.length;i++){
			if(r>=start&&r<end){
				return id[i];
			}
			start=end;
			end=i==arr.length-1?1:end+arr[i+1];
		}
		return 0;
	}
	
	//validate utf8

	public boolean validate(Byte[] bytes){
		if(bytes.length==0) return false;
		for(int i=0;i<bytes.length;){
			int t = testNext(bytes, i);
			if(t==-1)
				return false;
			i=t;

		}
		return true;

	}

	public int testNext(Byte[] bytes, int start){
		if(start>=bytes.length)
			return -1;
		if((bytes[start]&0b10000000)==0b00000000) return start+1;
		else if((bytes[start]&11100000)==0b11000000)
			return test(bytes, start+1, 1);
		else if((bytes[start]&0b11110000)==0b11100000)
			return test(bytes, start+1, 2);
		else if((bytes[start]&0b11111000)==0b11110000)
			return test(bytes, start+1, 3);
		else if((bytes[start]&0b11111100)==0b11111000)
			return test(bytes, start+1, 4);
		else if((bytes[start]&0b11111110)==0b11111100)
			return test(bytes, start+1, 4);
		else return -1;
	}

	public int test(Byte[] bytes, int i, int len){
		int j=0;
		while(j<len){
			if((bytes[i+j]&0b11000000)==0b10000000)
				j++;
			else
				break;
		}
		return j==len?i+len-1:-1;
	}
	//O(n) is palidrome
	public String longestPalindrome(String s) {
        // Write your code here
        if(s==null||s.length()==0) return "";
        if(s.length()==1) return s;
        String tmp = "&|";
        for(int i=0;i<s.length();i++){
            tmp+=s.charAt(i)+"|";
        }
        tmp+="^";
        int[] p = new int[tmp.length()];
        int c=0, r=0;
        for(int i=1;i<tmp.length()-1;i++){
            int i_mirror=2*c-i;
            if(r>i){
                p[i]=Math.min(r-i,p[i_mirror]);
            }
            else{
                p[i]=0;
            }
            while(tmp.charAt(i+p[i]+1)==tmp.charAt(i-p[i]-1)){
                p[i]++;
            }
            if(p[i]+i>r){
                r=p[i]+i;
                c=i;
            }
        }
        int maxLen=0;
        int center=0;
        for(int i=1;i<tmp.length()-1;i++){
            if(p[i]>maxLen){
                maxLen=p[i];
                center=i;
            }
        }
        int start=(center-1-maxLen)/2;
        return s.substring(start, start+maxLen);
    }
	//add to the right of the given string s
	public String shortestPalindrome(String s) {
        if(s==null||s.length()<=1) return s;
        int j=s.length()-1;
        for(int i=0;i<s.length();i++){
            if(s.charAt(j)==s.charAt(i))
                j--;
        }
        if(j<0) return s;
        /*int j=0;
        for(int i=s.length()-1;i>=0;i--){
        	if(s.charAt(j)==s.charAt(i)){
        		j++;
        	}
        }
        if(j==s.length()) return s;*/
        String first = s.substring(0,j+1);
       
        return first+shortestPalindrome(s.substring(j+1))+new StringBuilder(first).reverse().toString();
        
    }
	
	public boolean wordBreak(String s, Set<String> dict) {
        // write your code here   
        
        if(s==null||s.length()==0)
            return true;
        if(dict.contains(s))
            return true;
        
        boolean[] dp = new boolean[s.length()+1];
        dp[0]=true;
        int len=0;
        for(String ss:dict){
        	System.out.append(ss);
            Math.max(len, ss.length());
        }
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                if(i-j>len) continue;
                if(dp[j]&&dict.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
    
	public int shortestPalindromeRandomInsert(String s) {
		int n=s.length();
		int[][] dp = new int[n][n];
		for(int i=n-2;i>=0;i--){
			for(int j=i+1;j<n;j++){
				if(s.charAt(i)==s.charAt(j)){
					if(j-i<=2){
						dp[i][j]=0;
					}
					else{
						dp[i][j]=dp[i+1][j-1];
					}
				}
				else{
					dp[i][j]=Math.min(dp[i][j-1], dp[i+1][j])+1;
				}
			}
		}
		return dp[0][n-1];
	}
    
    public String hasValid(String s, int left, int right){
        int i=1;
        while(left-i>=0){
            if(s.charAt(left-i)!=s.charAt(right+i)){
                break;
            }
            i++;
        }
        if(left-i>=0)
            return null;
        StringBuilder sb = new StringBuilder(s.substring(right+i));
        sb.reverse();
        return sb.toString()+s;
    
    }
    
    
    
	
	public static void main(String[] args){
		GoogleTest test = new GoogleTest();
		//test missing range
		//int[] a = {1,3,86};
		//System.out.println(test.findMissingNumber(a,0,99));
		//test min number of squares
		//System.out.println(test.minNumber(13));
		//test random
		/*int[] id={1,2,3,4};
		double[] p={0.1,0.3,0.2,0.4};
		int[] testp=new int[4];
		int i=0;
		while(i<10000){
			int k=test.generateRandom(p, id);
			testp[k-1]++;
			i++;
		}
		for(int m:testp){
			System.out.println(m);
		}*/
		String s="acaa";
		System.out.println(test.shortestPalindromeRandomInsert(s));
		System.out.println(test.shortestPalindrome(s));
		HashSet<String> set = new HashSet<>();
		set.add("a");
		set.add("b");
		test.wordBreak("ab", set);
	}
}
