package solution;

public class ValidNumber {
	public boolean isValidNumber(String s){
		if(s==null||s.length()==0) return false;
		s=s.trim();
		if(s.length()==0) return false;
		int i=0;
		boolean isNum=false;
		if(s.charAt(i)=='-'||s.charAt(i)=='+'){
			i++;
		}
		while(i<s.length()&&Character.isDigit(s.charAt(i))){
			i++;
			isNum=true;
		}
		if(i<s.length()&&s.charAt(i)=='.'){
			i++;
			while(i<s.length()&&Character.isDigit(s.charAt(i))){
				i++;
				isNum=true;
			}
		}
		if(isNum&&i<s.length()&&(s.charAt(i)=='e'||s.charAt(i)=='E')){
			i++;
			isNum=false;
			if(i<s.length()&&(s.charAt(i)=='-'||s.charAt(i)=='+')){
				i++;
			}
			while(i<s.length()&&Character.isDigit(s.charAt(i))){
				i++;
				isNum=true;
			}
			
		}
		return isNum&&i==s.length();
	}
	public static void main(String[] args){
		ValidNumber test=new ValidNumber();
		System.out.println(test.isValidNumber("+1.2e-06"));
	}
}
