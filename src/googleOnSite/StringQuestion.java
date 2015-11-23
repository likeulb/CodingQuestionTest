package googleOnSite;

public class StringQuestion {
	public String encode(String s){
		int num=1;
		if(s==null||s.length()==0) return "";
		if(s.length()<3) return s;
		int i=1;
		char cur = s.charAt(0);
		int j=0;
		StringBuilder sb = new StringBuilder();
	while(i<s.length()){
		cur = s.charAt(j);
		while(i<s.length()&&s.charAt(i)==cur){
			num++;
			i++;
		}
	if(num<3){
		while(num!=0){
			sb.append(s.charAt(j));
			j++;
			num--;
		}
	}
	else{
		sb.append("#"+num+"x"+cur);
		
	}
	
		j=i;
		i++;
		num=1;
	}
		return sb.toString();
	}
	public String decode(String s){
		StringBuilder sb = new StringBuilder();
		int i=0, j=0;
		while(j<s.length()){
			if(s.charAt(j)=='#'){
				j++;
				int num=0;
				while(j<s.length()&&Character.isDigit(s.charAt(j))){
					num=num*10+(int)(s.charAt(j)-'0');
					j++;
				}
				j++;
				while(num!=0&&j<s.length()){
					sb.append(s.charAt(j));
					num--;
				}
			}
			else
				sb.append(s.charAt(j));
			j++;
		}
		return sb.toString();
		
	}
	//ABCAABBCCAAAABBBBCCCC, 求第n个char
	public char nthCharacter(int n){
		int i = 1;
		int k = 0;
		//String test ="";
		while(k+3*i<n){
			
			k+=3*i;
			
//			for(int t=0;t<3;t++){
//				char c = 'A';
//				if(t==1) c='B';
//				else if(t==2) c='C';
//				for(int m=0;m<i;m++){
//					test+=c;
//				}
//			}
			i=(i<<1);
			//System.out.println(test);
			
		}
		
		int diff = n-k;
		if(diff<=i)
			return 'A';
		else if(diff>2*i)
			return 'C';
		else return 'B';
	}
	
	public static void main(String[] str){
		StringQuestion test = new StringQuestion();
		System.out.println(test.nthCharacter(30));
		System.out.println(test.encode("ddd"));
		System.out.println(test.decode(test.encode("ffff665566g6666bb1aaaa")));
	}

}
