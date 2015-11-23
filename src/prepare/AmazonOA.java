package prepare;

public class AmazonOA {
	
	public static boolean isRotate(String s1, String s2){
		if(s1==null||s1.length()==0) return s2==null;
		if(s1.length()==s2.length()){
			String s = s1+s1;
			return isSubstring(s,s2);
		}
		return false;
	}
	
	public static boolean isGrayCode(byte term1,byte term2){
		byte x = (byte)(term1^term2);
		int count=0;
		while(x!=0){
			x=(byte)(x&(x-1));
			count++;
		}
		return count==1;
		
	}
	
	public static boolean isSubstring(String s1,String s2){
		if(s1==null||s1.length()==0) return s2==null;
		if(s1.length()<s2.length()) return false;
		int i=0;
		int j=0;
		while(i<s1.length()){
			while(j<s2.length()&&s1.charAt(i)==s2.charAt(j)){
				i++;
				j++;
			}
			if(j==s2.length())
				return true;
			j=0;
			i++;
		}
		return false;
		
	}
	
	public static void main(String[] args){
		System.out.println(Integer.toBinaryString(-2));
		System.out.println((Integer.MIN_VALUE)^0>>1);
		System.out.println(Integer.toBinaryString(-1)+"\n");
		System.out.println(Integer.toBinaryString(127));
		System.out.println(AmazonOA.isGrayCode((byte)-1, (byte)127));
	}
}
