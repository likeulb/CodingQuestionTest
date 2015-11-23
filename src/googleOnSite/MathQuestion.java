package googleOnSite;

public class MathQuestion {
	
	//convert 10 based to 16 based
	public void convertToHexi(int n){
		if(n==0)
			System.out.println("0");
		boolean neg = false;
		long abs_n = n;
		if(n<0){
			neg=true;
			abs_n=Math.abs(abs_n);
		}
		StringBuilder sb = new StringBuilder();
		while(abs_n>0){
			long digit = (int)(abs_n%16);
			if(digit<=9){
				sb.append(digit);
			}
			else{
				sb.append((char)('A'+digit-10));
			}
			abs_n/=16;
		}
		if(neg){
			System.out.print("-");
		}
		System.out.println(sb.reverse().toString());
	}
	
	public static void main(String[] args){
		MathQuestion Test = new MathQuestion();
		Test.convertToHexi(984);
		Test.convertToHexi(-20);
		Test.convertToHexi(Integer.MIN_VALUE);
	}

}
