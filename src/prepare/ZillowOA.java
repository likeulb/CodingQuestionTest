package prepare;

public class ZillowOA {
	public long stringToLong(String s) throws Exception{
		if(s==null||s.length()==0) return 0;
		int i=0;
		int flag=1;
		while(i<s.length()&&s.charAt(i)==' '){
			i++;
		}
		if(i==s.length()) return 0;
		if(s.charAt(i)=='+'||s.charAt(i)=='-'){
			flag = s.charAt(i)=='-'?-1:1;
			i++;
		}
		long result = 0;
		while(i<s.length()){
			char c = s.charAt(i);
			if(c==' ') continue;
			if(!Character.isDigit(c)) break;
			int cur  = Character.getNumericValue(c);
			if(flag==1&&result>(Long.MAX_VALUE-cur)/10||(flag==-1&&result>(Long.MIN_VALUE+cur)/(-10))){
				throw new Exception("overflow");
			}
			result=result*10+cur;
			i++;
		}
		System.out.println(result*(flag));
		return result*flag;
		
	}
	
	public static void main(String[] args){
		ZillowOA test = new ZillowOA();
		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.MIN_VALUE);
		
		try {
			test.stringToLong("   -0928653a9937");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
