package solution;

import java.math.BigDecimal;
import java.util.*;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
	
	HashSet<Integer> set = new HashSet<Integer>();
    public boolean isHappy(int n) {
        if(n==1) return true;
        if(set.contains(n)) return false;
        int cur = 0;
        while(n>0){
            int digit = n%10;
            cur+=digit*digit;
            n/=10;
        }
        set.add(cur);
        return isHappy(cur);
        
    }
    
    public String addBinary(String a, String b) {
        // Write your code here
        
        if(a==null||a.length()==0) return b;
        if(b==null||b.length()==0) return a;
        int n=a.length();
        int m=b.length();
        
        StringBuilder sb = new StringBuilder();
        int i =n-1;
        int j=m-1;
        int num = 0;
        while(i>=0||j>=0){
            int sum = num;
            if(i>=0&&j>=0){
                sum+=a.charAt(i)-'0'+b.charAt(j)-'0';
                i--;
                j--;
            }
            else if(i>=0){
                sum+=a.charAt(i)-'0';
                i--;
            }
            else{
                sum+=b.charAt(j)-'0';
                j--;
            }
            sb.append(sum%2);
            num=sum/2;
        }
        if(num>0){
            sb.append(1);
        }
        return sb.reverse().toString();
        
    }
    
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Write your code here
        List<Integer> result = new ArrayList<Integer>();
        if(operators==null||operators.length==0)
            return result;
        int[] check = new int[n*m+1];
        Arrays.fill(check, -1);
        int[] xway = {0,0,1,-1};
        int[] yway = {-1,1,0,0};
        int num=0;
        for(Point p:operators){
            int i = p.x*n+p.y;
            int head = findHead(check, i);
            if(head==i){
                check[i]=i;
                num++;
            }
            for(int k=0;k<4;k++){
                int xnew = p.x+xway[k];
                int ynew = p.y+yway[k];
                if(0<=xnew&&xnew<n&&0<=ynew&&ynew<m){
                    int index = xnew*n+ynew;
                    int indexHead = findHead(check, index);
                    if(indexHead!=head){
                        num--;
                        check[index] = head;
                    }
                }
            }
            result.add(num);
        }
        return result;
    }
    public int findHead(int[] check, int i){
        if(check[i]==-1)
            return i;
        else
            return findHead(check, check[i]);
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> item = new ArrayList<Integer>();
        for(int i=1;i<=9-k+1;i++){
            dfs(item, result, 0, k, i, n);
        }
        return result;
    }
    public void dfs(List<Integer> item, List<List<Integer>> result, int sum, int k, int cur, int n){
        if(item.size()==k){
            if(sum!=n) return;
            result.add(new ArrayList<Integer>(item));
            return;
        }
        if(sum>n) return;
        for(int i=cur;i<=9;i++){
            item.add(i);
            dfs(item, result, sum+i, k, i+1, n);
            item.remove(item.size()-1);
        }
        
    }
    
    private int[] getPrimes(int n){
        int[] primes = new int[n];
        primes[0]=2;
        int num = 3;
        int i = 1;
        while(i<n){
            boolean isPrime = true;
            for(int j=2;j<=Math.sqrt(num);j++){
                if(num%j==0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                primes[i++] = num;
            }
            num+=2;
        }
        return primes;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        ListNode p=head;
        int count = 0;
        while(p!=null){
            p=p.next;
            count++;
        }
        return BST(head, 0, count-1);
    }
    public TreeNode BST(ListNode head, int l, int r){
        if(l>r) return null;
        int m=(l+r)/2;
        TreeNode left = BST(head, l, m-1);
        TreeNode root = new TreeNode(head.val);
        head = head.next;
        root.left = left;
        root.right=BST(head, m+1, r);
        return root;
        
    }
	
    public int uniquePaths(int m, int n) {
        int[][] mat = new int[m + 1][n + 1];
        mat[m - 1][n] = 1;
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                mat[r][c] = mat[r + 1][c] + mat[r][c + 1];
                } 
            
        }
        return mat[0][0];
    }
	public int rangeBitwiseAnd(int m, int n) {
		int x = m^n;
        int i=0;
        while(i<31&&x>0){
            x=x>>1;
        }
        int add = 1<<i;
        int result = add;
        i++;
        while(i<32){
            result=(result<<1)+add;
        }
        return result;
        
    }
	public boolean isOneEditDistance(String s, String t){
		int m=s.length(), n=t.length();
		if(m>n) return isOneEditDistance(t,s);
		if(n-m>1) return false;
		int i=0,diff = n-m;
		while(i<m&&s.charAt(i)==t.charAt(i)){
			i++;
		}
		if(i==m) return diff==1;
		if(diff==0) i++;
		while(i<m&&s.charAt(i)==t.charAt(i+diff)){
			i++;
		}
		return i==m;
	}
	public boolean wordBreak(String s, Set<String> dict) { 
        if(s==null||s.length()==0) return true; 
        if(dict==null||dict.size()==0) return false; 
        if(dict.contains(s)) return true; 
        return dfs(s, dict, 0); 
    } 
    public boolean dfs(String s, Set<String> dict, int start){ 
        if(start==s.length()) return true; 
        for(int i=start+1;i<=s.length();i++){ 
            String sub = s.substring(start, i); 
            if(dict.contains(sub)){ 
                if(dfs(s,dict,i)){
                	return true;
                }
            } 
        } 
        return false; 
    } 
    public double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length;
        int n = A.length;
        int sum = m+n;
        if(sum%2!=0) return find(A,0,m-1,B,0,n-1,sum/2+1);
        else return (find(A,0,m-1,B,0,n-1,sum/2)+find(A,0,m-1,B,0,n-1,sum/2+1))/2.0;
    }
    
    public double find(int A[], int al, int ar, int B[], int bl, int br, int k){
    	 if(br-bl<ar-al){
             return find(B,bl, br, A, al, ar, k);
         }
    	if(ar-al+1<=0) return B[bl+k-1];
        if(k==1){
            return Math.min(A[al],B[bl]);
        }
       
        
        //if(bl>br) return (Double)A[al+k-1];
        int alen = ar-al+1;
        int am = Math.min(k/2, alen)-1;
        int bm = k-am-1;
        if(A[am]==B[bm]) return A[am];
        else if(A[am]<B[bm]) return find(A,am+1,ar, B, bl, br, k-am-1);
        else return find(A, am, ar, B, bm+1, br, k-bm-1);
        
    }
   
        public List<String> findRepeatedDnaSequences(String s) {
            HashSet<String> result = new HashSet<String>();
            if(s==null||s.length()<10)  return new ArrayList<String>(result);
            
            long base = 7;
            long hashing = 0;
            long factor = 1;
            for(int i=0;i<10;i++){
                hashing = hashing*base + (int)s.charAt(i);
                factor*=base;
            }
            factor/=base;
            HashSet<Long> set = new HashSet<Long>();
            set.add(hashing);
            for(int i=1;i<=s.length()-10;i++){
                hashing = (hashing - (int)s.charAt(i-1)*factor)*base+(int)s.charAt(i+9);
                if(set.contains(hashing)){
                    result.add(s.substring(i,i+10));
                }
                else{
                    set.add(hashing);
                }
            }
            return new ArrayList<String>(result);
        }
    
	
	 public void setZeroes(int[][] matrix) {
	        if(matrix==null||matrix.length==0||matrix[0].length==0)
	                return;
	        boolean colZero=false;
	        boolean rowZero=false;
	        
	        for(int i=0;i<matrix[0].length;i++){
	            if(matrix[0][i]==0){
	                rowZero=true;
	                break;
	            }
	        }
	        for(int i=0;i<matrix.length;i++){
	            if(matrix[i][0]==0){
	                colZero=true;
	                break;
	            }
	        }
	        for(int i=1;i<matrix.length;i++){
	            for(int j=1;j<matrix[0].length;j++){
	                if(matrix[i][j]==0){
	                    matrix[i][0]=0;
	                    matrix[0][j]=0;
	                }
	            }
	        }
	        for(int i=0;i<matrix[0].length;i++){
	            if(matrix[0][i]==0){
	                for(int j=1;j<matrix.length;j++){
	                    matrix[j][i]=0;
	                }
	            }
	            else if(rowZero) matrix[0][i]=0;
	        }
	        for(int i=1;i<matrix.length;i++){
	            if(matrix[i][0]==0){
	                for(int j=1;j<matrix[0].length;j++){
	                    matrix[i][j]=0;
	                }
	            }
	            else if(colZero){
	                matrix[i][0]=0;
	            }
	        }
	        
	    }
	 public static ArrayList<Integer> mergeSortedArray(ArrayList<Integer> A, ArrayList<Integer> B) {
	        // write your code here
	        if(A==null||A.size()==0)
	            return B;
	        if(B==null||B.size()==0)
	            return A;
	        if(A.size()>B.size())
	            return mergeSortedArray(B, A);
	        int i=A.size()-1, j=B.size()-1;
	        int k=A.size()+B.size()-1;
	        for(int x=B.size();x<=k;x++){
	        	B.add(0);
	        }
	        while(i>=0&&j>=0){
	            if(A.get(i)<B.get(j)){
	                B.set(k, B.get(j));
	                j--;
	            }
	            else{
	                B.set(k, A.get(i));
	                i--;
	            }
	            k--;
	        }
	        while(i>=0){
	            B.set(k, A.get(i));
	            i--;
	            k--;
	        }
	        return B;
	    }
	 
	 public int maximalRectangle(char[][] matrix) {
	        
	        if(matrix==null||matrix.length==0||matrix[0].length==0) return 0;
	        int row = matrix.length;
	        int col = matrix[0].length;
	        
	        int max=0;
	        int[] num = new int[col];
	        for(int i=0;i<row;i++){
	            for(int j=0;j<col;j++){
	                if(matrix[i][j]==0){
	                    num[j]=0;
	                }
	                else num[j]++;
	            }
	            max=Math.max(max, findArea(num));
	        }
	        return max;
	        
	    }
	    
	    public int findArea(int[] num){
	        if(num.length==1) return num[0];
	        int area=0;
	        Stack<Integer> stak = new Stack<Integer>();
	        for(int i=0;i<num.length;i++){
	            while(!stak.isEmpty()&&num[i]<num[stak.peek()]){
	                int j=stak.pop();
	                int curArea = num[j]*(stak.isEmpty()?i:i-stak.peek()-1);
	                area = Math.max(area,curArea);
	            }
	            stak.push(i);
	        }
	        while(!stak.isEmpty()){
	            int j=stak.pop();
	            int curArea = num[j]*(stak.isEmpty()?num.length:num.length-stak.peek()-1);
	            area=Math.max(area,curArea);
	        }
	        return area;
	    }
    public boolean isNumber(String s) {
        if(s==null||s.length()==0) return false;
        s=s.trim();
        if(s.length()==0) return false;
        
        boolean hasE = false;
        boolean hasDot = false;
        boolean hasNum=false;
        int sign=0;
        
        
        for(int i=0;i<s.length();i++){
            if(!isValid(s.charAt(i)))   return false;
            
            if(s.charAt(i)>='0'&&s.charAt(i)<='9')
                hasNum=true;
            if(s.charAt(i)=='+'||s.charAt(i)=='-'){
                if(sign==2) return false;
                if(i==s.length()-1) return false;
                if(i>0&&s.charAt(i-1)!='e'&&s.charAt(i-1)!='E') return false;
                sign++;
            }
            if(s.charAt(i)=='.'){
                if(hasDot||hasE) return false;
                if(i==s.length()-1&&!hasNum) return false;
                hasDot=true;
            }
            if(s.charAt(i)=='E'||s.charAt(i)=='e'){
                if(hasE||!hasNum) return false;
                if(i==s.length()-1) return false;
                hasE=true;
            }
                
        }
        return true;
        
        
    }
    
    public boolean isValid(char c){
        if(c>='0'&&c<='9'||c=='e'||c=='E'||c=='.'||c=='+'||c=='-')
            return true;
        else
            return false;
    }
    
   
        public boolean isNumber1(String s) {
            if(s==null||s.length()==0) return false;
            s=s.trim();
            if(s.length()==0) return false;
            boolean hasE = false;
            boolean hasDigit = false;
            
            for(int i=0;i<s.length();i++){
                switch(s.charAt(i)){
                    case '+':
                    case '-': if((i==s.length()-1)||(i>0&&s.charAt(i-1)!='e'&&s.charAt(i-1)!='E')
                                ||(!(isNum(s.charAt(i+1))||s.charAt(i+1)=='.')))
                                return false;
                                break;
                    case 'e':
                    case 'E': if(hasE||i==0||i==s.length()-1)
                                return false;
                                hasE=true;
                                break;
                    case '.': if(hasE||hasDigit||(i==0||!isNum(s.charAt(i-1)))&&(i==s.length()-1||!isNum(s.charAt(i+1))))
                                return false;
                                hasDigit=true;
                                break;
                    default: if(!isNum(s.charAt(i)))    return false;
                                
                }
                    
                }
                return true;
        }
        
        public boolean isNum(char c){
            if(c>='0'&&c<='9')
                return true;
            else
                return false;
        }
    
        public List<String> fullJustify(String[] words, int L) {
            List<String> result = new ArrayList<String>();
            if(words==null||words.length==0) return result;
            
            int curLen=0;
            int start=0;
            for(int i=0;i<words.length;i++){
                if(curLen+words[i].length()+i-start<=L)
                    curLen+=words[i].length();
                else{
                    int eachspace=0;
                    int remain=0;
                    int numOfSpace = i-start-1;
                    if(numOfSpace>0){
                        eachspace=(L-curLen)/numOfSpace;
                        remain = (L-curLen)%numOfSpace;
                    }
                    StringBuilder item = new StringBuilder();
                    for(int k=start;k<i;k++){
                        item.append(words[k]);
                        if(k<i-1){
                        for(int j=0;j<eachspace;j++){
                            item.append(" ");
                        }
                        if(remain>0){
                            item.append(" ");
                            remain--;
                            }
                        }
                        
                    }
                    for(int k=item.length();k<L;k++){
                        item.append(" ");
                    }
                    result.add(item.toString());
                    start=i;
                    curLen=0;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i=start;i<words.length;i++){
                sb.append(words[i]);
                if(i<words.length-1)
                    sb.append(" ");
            }
            for(int k=sb.length();k<L;k++){
                sb.append(" ");
            }
            result.add(sb.toString());
            return result;
        }

        public List<String> fullJustify1(String[] words, int L) {
            
            int len = words.length;
           
            ArrayList<String> result = new ArrayList<String>();
            if(words==null||len==0) return result;
            
            int curLen = 0;
            int first = 0;
           
            for(int i=0;i<len;i++){
                
                if(curLen + words[i].length()+i-first>L){
                   int spaceNum=0;
                   int extraSpace=0;
                   
                   if(i-1-first>0){
                        spaceNum = (L-curLen)/(i-1-first);
                        extraSpace = (L-curLen)%(i-1-first);
                   }
                   StringBuffer item = new StringBuffer();
                    for(int k=first;k<i;k++){
                        item.append(words[k]);
                        if(k<i-1){
                           for(int j=0;j<spaceNum;j++){
                                item.append(" ");
                               }
                           if(extraSpace>0){
                                item.append(" ");
                               extraSpace--;
                           }
                        }
                   }
                   
                   for(int m=item.length();m<L;m++){
                       item.append(" ");         //for the situation that only one letter in one line, lie "boys", "girl" and L=5
                   }
                   
                   result.add(item.toString());
                   first = i;
                   curLen = 0;
                    
                }
                
                curLen+=words[i].length();
            }
            StringBuffer item_last = new StringBuffer();
            for(int i=first;i<len;i++){
                item_last.append(words[i]);
                if(i<len-1) item_last.append(" ");
            }
            for(int k=item_last.length();k<L;k++){
                item_last.append(" ");
            }
            result.add(item_last.toString());
            
            return result;
               
           }
        
        public void reversWords(char[] c){
        	reverse(c, 0,c.length-1);
        	for(int i=0,j=0;j<=c.length;j++){
        		if(j==c.length||c[j]==' '){
        			reverse(c,i,j-1);
        			i=j+1;
        		}
        	}
        	System.out.println(new String(c));
        	
        }
        private void reverse(char[] c, int left, int right){
        	while(left<right){
        		char tmp = c[left];
        		c[left] = c[right];
        		c[right]=tmp;
        		left++;
        		right--;
        	}
        }
        
        public String reverseWords(String s) {
            StringBuilder sb = new StringBuilder();
            int i=s.length()-1;
            int end = i;
            int start=0;
            while(i>=0){
                while(i>=0&&s.charAt(i)==' '){
                    i--;
                }
                end = i+1;
                while(i>=0&&s.charAt(i)!=' '){
                    i--;
                }
                start=i+1;
                String tmp = s.substring(start, end);
                sb.append(tmp);
                sb.append(" ");
            }
            if(sb.length()==0) return "";
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        }
        public void test(int i){
        	while(--i>0){
        		i*=5;
        	}
        }
        
        class bucket{
            int small;
            int big;
            public bucket(){
                small = Integer.MAX_VALUE;
                big = Integer.MIN_VALUE;
            }
        }
        
        public int maximumGap(int[] num) {
            if(num==null||num.length<=1) return 0;
            
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            
            for(int i:num){
                min = Math.min(i, min);
                max = Math.max(i, max);
            }
            
            int gap = (max-min)/(num.length-1)+1;
            int BucketNum = (max-min)/gap+1;
            
            bucket[] bucks = new bucket[BucketNum];
            for(int i=0;i<BucketNum;i++){
                bucks[i]=new bucket();
            }
            
            for(int i:num){
                if(i==min||i==max) continue;
                int bucketIndex = (i-min)/gap;
                bucks[i].big = Math.max(bucks[i].big, i);
                bucks[i].small = Math.min(bucks[i].small,i);
            }
            
            int preMax = min;
            int maxGap = 0;
            for(int i=0;i<BucketNum;i++){
                if(bucks[i].big==Integer.MIN_VALUE||bucks[i].small==Integer.MAX_VALUE) continue;
                maxGap = Math.max(maxGap, bucks[i].small-preMax);
                preMax = bucks[i].big;
            }
            return Math.max(maxGap, max-preMax);
        }
        
    public List<String> findMissingRange(int[] val, int start, int end){
    	List<String> result = new ArrayList<String>();
    	int pre = start - 1;
    	for(int i=0;i<=val.length;i++){
    		int cur = (i==val.length?end-1:val[i]);
    		if(cur<=start){
    			pre = cur;
    			continue;
    		}
    		
    		if(cur-pre>=2){
    			result.add(getRange(pre+1,cur-1));
    		}
    		pre = cur;
    		if(cur>=end) break;
    	}
    	for(String s:result){
    		System.out.println(s);
    	}
    	return result;
    	
    }
    
    
    
    private String getRange(int from, int to){
    	return from==to? String.valueOf(from):from+"->"+to; 
    }
    
    public String longestPalindrome(String s) {
        if(s==null||s.length()<=1) return s;
        int start = 0;
        int end = 0;
        for(int i=0;i<s.length();i++){
            int len1 = find(s, i, i);
            int len2 = find(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len>end-start+1){
                start = i-(len-1)/2;
                end = start+len-1;
            }
        }
        return s.substring(start, end+1);
    }
    private int find(String s, int left, int right){
        int l=left,r=right;
        while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        return r-l-1;
    }
        
    public int lengthOfLongestSubstringTwoDistinct(String s){
    	int i=0;
    	int j=-1;
    	int maxLen = 0;
    	for(int k=1;k<s.length();k++){
    		if(s.charAt(k)==s.charAt(k-1)) continue;
    		if(j>=0&&s.charAt(k)!=s.charAt(j)){
    			maxLen = Math.max(maxLen, k-i);
    			i=j+1;
    		}
    		j=k-1;
    	}
    	return Math.max(s.length()-i, maxLen);
    }
    
        public void sortColors(int[] A) {
            if(A==null||A.length==1) return;
            
            int i=0;
            int j=A.length-1;
            for(int k=i;k<=j;k++){
                if(A[k]==0){
                    swap(A, k, i);
                    i++;
                    k++;
                }
                else if(A[k]==2){
                    swap(A, k, j);
                    j--;
                }
                else k++;
            }
        }
        private void swap(int[] A, int left, int right){
            int tmp = A[left];
            A[left] = A[right];
            A[right] = tmp;
        }
    
    public int lengthOfLongestTwoDistinct(String s){
    	int[] count = new int[256];
    	int i=0, numDistinct = 0, maxLen = 0;
    	for(int j=0;j<s.length();j++){
    		if(count[s.charAt(j)]==0) numDistinct++;
    		count[s.charAt(j)]++;
    		while(numDistinct>2){
    			count[s.charAt(i)]--;
    			if(count[s.charAt(i)]==0) numDistinct--;
    			i++;
    		}
    		maxLen = Math.max(maxLen, j-i+1);
    	}
    	return maxLen;
    }
    
    public static int fastPower(int a, int b, int n) {
        // write your code here
        if(n==1)
            return a%b;
        int half = fastPower(a, b, n/2);
        if(n%2==0)
            return (half*half)%b;
        else
            return (half*half*a)%b;
    }
    
    public ListNode sortList(ListNode head) {  
        // write your code here
        if(head==null||head.next==null) return head;
        ListNode pre = new ListNode(0);
        pre.next=head;
        ListNode p = pre;
        ListNode fast=pre;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            p=p.next;
        }
        ListNode second = p.next;
        p.next=null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(second);
        
        return merge(l1,l2);
    }
    public ListNode merge(ListNode node1, ListNode node2){
        ListNode pre = new ListNode(0);
        pre.next=node1;
        ListNode p=pre;
        ListNode p2 = node2;
        while(p.next!=null&&p2!=null){
            if(p.next.val>p2.val){
                ListNode nxt = p2.next;
                ListNode pnext = p.next;
                p.next=p2;
                p2.next=pnext;
                p2=nxt;
                p=p.next;
            }
            else{
                p=p.next;
            }
        }
        if(p2!=null){
            p.next=p2;
        }
        return pre.next;
    }
    
   
        public int lengthOfLIS(int[] nums) {
            ArrayList<Integer> res = new ArrayList<>();
            
            for(int i=0;i<nums.length;i++){
                if(res.isEmpty()||res.get(res.size()-1)<nums[i])
                    res.add(nums[i]);
                else{
                    replaceFirstBigger(res, nums[i]);
                }
            }
            return res.size();
        }
        public void replaceFirstBigger(ArrayList<Integer> res, int target){
            int i=0, j=res.size()-1;
            while(i<j){
                int mid = i+(j-i)/2;
                if(res.get(mid)<target){
                    i=mid+1;
                }
                else
                    j=mid;
            }
            res.set(i, target);
        }
        public static double sqrt(double num, double p){
        	double x = num/2;
        	while(Math.abs(num-x*x)>p){
        	x = (x+num/x)/2;
        }
        return x;
        }
        
        
            public int threeSumSmaller(int[] nums, int target) {
                if(nums==null||nums.length<=2) return 0;
                Arrays.sort(nums);
                int count=0;
                int i=0;
                while(i<nums.length-2){
                    
                    int cur = nums[i];
                    int j=i+1, k=nums.length-1;
                    while(j<k){
                       
                        if(cur+nums[j]+nums[k]<target){
                            count+=k-j;
                            j++;
                            while(j<k&&nums[j]==nums[j-1]){
                                j++;
                            }
                        }
                        else if(cur+nums[j]+nums[k]>=target){
                            k--;
                            while(j<k&&nums[k]==nums[k+1]){
                                k--;
                            }
                        }
                       
                        
                    }
                    i++;
                    while(i<nums.length&&i>0&&nums[i]==nums[i-1]){
                        i++;
                    }
                }
                return count;
            }
        
            public boolean isAdditiveNumber(String num) {
                if(num==null||num.length()<3) return false;
                for(int i=1;i<=num.length()/2;i++){
                    for(int j=1;i+j<=num.length();j++){
                        if(check(i,j, num))
                            return true;
                    }
                }
                return false;
            }
            public boolean check(int i, int j, String num){
                if(num.charAt(i)=='0'&&j>1) return false;
                if(i+j==num.length()) return false;
                String sum;
                Long x1 = Long.parseLong(num.substring(0,i));
                Long x2 = Long.parseLong(num.substring(i,i+j));
                
                for(int start = i+j; start!=num.length();start+=sum.length()){
                    x2 = x2+x1;
                    x1 = x2-x1;
                    sum = x2.toString();
                    if(!num.startsWith(sum, start)) return false;
                }
                return true;
            }
            
            public String generate(int mask, String word){
                StringBuilder sb = new StringBuilder();
                int i=0;
                while(i<word.length()){
                    if((mask&(1<<i))==0){ //
                        sb.append(word.charAt(i));
                        i++;
                    }
                    else{
                        int cur=i;
                        while(cur<word.length()&&((mask&(1<<cur))>0)){
                            cur++;
                        }
                        sb.append(cur-i);
                        i=cur;
                    }
                }
                return sb.toString();
            }
     
            public String removeDuplicateLetters(String s) {
                if(s==null||s.length()<=1) return s;
                int[] count = new int[26];
                for(int i=0;i<s.length();i++){
                    count[s.charAt(i)-'a']++;
                }
                int min=0;
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i)<s.charAt(min)){
                        min=i;
                    }
                    count[s.charAt(i)-'a']--;
                    if(count[s.charAt(i)-'a']==0) break;
                }
                return s.charAt(min)+removeDuplicateLetters(s.substring(min+1).replaceAll(""+s.charAt(min),""));
            }
        
            public List<String> generateAbbreviations(String word) {
                List<String> result=new ArrayList<>();
                if(word==null||word.length()==0) return new ArrayList<String>();
                helper(result, "",0,word,0);
                return result;
            }
            public void helper(List<String> result, String cur, int index, String word, int count){
                if(index==word.length()){
                    if(count>0){
                        cur+=count;
                    }
                    result.add(cur);
                    return;
                }
                helper(result, cur+(count>0?count:"")+word.charAt(index),index+1,word,0);
                helper(result,cur,index+1,word,count+1);
            }
    
    public static void main(String[] args) {
    	
    	int[] test2={-1,1,-1,-1};
    	Solution sol = new Solution();
    	sol.generateAbbreviations("word");
    	System.out.println((5)%(-3));
    	System.out.println(sol.removeDuplicateLetters("bbcaac"));
    	
    	System.out.println(sol.generate(1, "word"));
    	
    	
    	
    	String[] board = {"..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."};
    	char[][] b = new char[9][9];
    	int y=0;
    	for(String s:board){
    		b[y++]=s.toCharArray();
    	}
    	
    	sol.isAdditiveNumber("111");
    	sol.threeSumSmaller(test2, -1);
    	double d = Solution.sqrt(45, 1);
    	String[] words={"foo","bar"};
    	
    	System.out.println(d);
    	//fastPower(3,7,5);
    	Solution Sol = new Solution();
    	int[] ttt = {3,5,6,2,5,4,19,5,6,7,12};
    	Sol.lengthOfLIS(ttt);
    	
    	ListNode head = new ListNode(0);
    	head.next = new ListNode(3);
    	head.next.next = new ListNode(1);
    	head.next.next.next=new ListNode(-1);
    	
    	Solution T = new Solution();
    	T.sortList(head);
    	
    	TreeNode root0 = new TreeNode(-1);
    	root0.left = new TreeNode(0);
    	root0.right=new TreeNode(1);
    	String splitTest = "a,a";
    	String atest[]=splitTest.split(",");
    	System.out.println(atest.length);
    	
    	ArrayList<Integer> A1 = new ArrayList<>();
    	A1.add(1);
    	ArrayList<Integer> B1 = new ArrayList<>();
    	B1.add(1);
    	//mergeSortedArray(A1,B1);
    	
    	
    	String astring = "100";
    	String bstring = "110010";
    	Solution lintCode = new Solution();
    	
    	
    	
    	double fval=3;
    	
    	double tdouble = (double)0.2;
    	String st=Double.toString(tdouble);
    	String fs=new BigDecimal(fval).toPlainString();
    	double tttt=tdouble*Double.parseDouble(st);
    	
    	//String fsf = Float.toString(fval);
    	System.out.println(fval);
    	System.out.println(fs);
    	System.out.println(Float.parseFloat(fs)*10000);
    	
    	ListNode root = new ListNode(3);
    	root.next = new ListNode(5);
    	root.next.next = new ListNode(8);
    	int[][] t = {{1},{0}};
    	int[] test = {1,0};
    	int[] A= {1};
    	int[] B = {1};
    	String[] ss={"a","b","c","d","e"};
    	String s = "aaabcccddderfffffdddfff";
    	Set<String> set = new HashSet<String>();
    	set.add("leet");
    	set.add("code");
    	set.add("more");
    	//char board[][]={"..4...63.",".........","5......9.","...56....","4.3.....1","...7.....","...5.....",".........","........."}
    	
    	Solution a = new Solution();
    	int[] testPrime = a.getPrimes(26);
    	a.uniquePaths(7,3);
    	a.sortColors(test);
    	List<List<Integer>>tt = a.combinationSum3(2, 6);
    	for(List<Integer> tmp:tt){
    		System.out.print("[");
    		for(Integer i: tmp){
    			System.out.print(i+" ");
    		}
    		System.out.println("]");
    	}
    	//a.findMissingRange(test,2,8);
   // 	System.out.println(a.isHappy(7));
   // 	a.sortedListToBST(root);
    	//System.out.println(a.isOneEditDistance("abccef","abcced"));
    	//System.out.println(a.lengthOfLongestSubstringTwoDistinct(s));
    	//System.out.println(a.lengthOfLongestTwoDistinct(s));
    	//a.findMedianSortedArrays(A,B);
   // 	a.reversWords(s.toCharArray());
    	
   // 	System.out.println(a.isNumber(s));
   // 	System.out.println(a.isNumber1(s));
   // 	System.out.println((int)(Math.pow(2, (int)(Math.log(17)/Math.log(2)))));
    	
   // 	System.out.println((-1)>>31&(0x01));
    	
    	
    	
    }
    
    class Point {
    	      int x;
    	      int y;
    	     Point() { x = 0; y = 0; }
    	      Point(int a, int b) { x = a; y = b; }
    	  }
        
}  
	
	
