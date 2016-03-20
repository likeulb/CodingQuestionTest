package ms;

public class StringRelated {
	//give a string A, abcd, and a string B, bc, and return ad.
	public static String removeOccurence(String A, String B){
		if(A==null||A.length()<B.length()) return A;
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<A.length();){
			int j=0;
			while(i+j<A.length()&&j<B.length()&&A.charAt(i+j)==B.charAt(j)){
				j++;
			}
			if(j==B.length()){
				i+=B.length();
			}
			else{
				sb.append(A.charAt(i));
				i++;
			}
		}
		return sb.toString();
	}
	//abc bbb ccc bbb abc 也算palindrom
	public static boolean isPalin(String s){
		if(s==null||s.length()<=1) return true;
		String[] t=s.split(" ");
		int i=0, j=t.length-1;
		while(i<j){
			while(i<j&&t[i].length()==0) i++;
			while(i<j&&t[j].length()==0) j--;
			if(t[i].length()!=t[j].length()) return false;
			if(!t[i].equals(t[j])) return false;
			i++;
			j--;
		}
		return true;
	}
	//把这个array里面带有这个字母开头的单次删掉，操作是要求in place
	public static String removeWordsWithBeginCharacter(char[] arr, char c){
		if(arr==null||arr.length==0) return "";
		int i=-1;
		int j=0;
		while(j<arr.length){
			if(arr[j]==c&&(j==0||arr[j-1]==' ')){
				while(j<arr.length&&arr[j]!=' '){
					j++;
				}
				if(j==arr.length){
					i--;
					break;
				}
			}
			else{
				i++;
				arr[i]=arr[j];
			}
			j++;
		}
		return new String(arr,0,i+1);
	}
	public static String replace(String s, String a, String b){
		if(s.length()<a.length()) return s;
		StringBuilder sb=new StringBuilder();
		int i=0;
		/*while(i<s.length()){
			if(s.startsWith(a,i)){
				sb.append(s.charAt(i));
				i++;
			}
			else{
				sb.append(b);
				i+=a.length();
			}
		}*/
		while(i<s.length()){
			if(s.charAt(i)!=a.charAt(0)){
				sb.append(s.charAt(i));
				i++;
			}
			else{
				int j=1;
				while(i+j<s.length()&&j<a.length()){
					if(s.charAt(i+j)!=a.charAt(j))
						break;
					j++;
				}
				if(j<a.length()){
					sb.append(s.charAt(i));
					i++;
				}
				else{
					sb.append(b);
					i+=a.length();
				}
			}
		}
		return sb.toString();
	}
	//convert n to 16 base
	static String itoa(int n){
		int base=16;
		String[] s = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		StringBuilder sb=new StringBuilder();
		while(n>0){
			int index=n%base;
			sb.append(s[index]);
			n/=base;
		}
		return sb.reverse().toString();
	}
	public static void main(String[] args){
		System.out.println(removeOccurence("adbcbbbbb","bbc"));
		System.out.println(isPalin("abc  a b  abc"));
		System.out.println(removeWordsWithBeginCharacter("a big ball is running".toCharArray(),'a'));
		System.out.println(replace("aabbccdedbcgaesdfr","bc","zzzz"));
		System.out.println(itoa(1749));
	}

}
