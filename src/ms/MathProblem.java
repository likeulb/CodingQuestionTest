package ms;

public class MathProblem {
	//在32位程序里实现和可以到达64位的加法
	static int[] cal64(int num1, int num2){
		int n1=0, n2=0;
		int carry=0;
		int tmp1=num1,tmp2=num2;
		int x=1;
		int count=0;
		while(tmp1>0||tmp2>0){
			int cur1=(tmp1&1);
			int cur2=(tmp2&1);
			int cur=carry+cur1+cur2;
			carry=cur/2;
			cur%=2;
			
			
			if(cur==1)
				n1+=x;
			x<<=1;
			
			tmp1>>=1;
			tmp2>>=1;
			
		}
		if(carry==1){
			n2=1;
		}
		
		if(n2==0) System.out.println(n1);
		else{
			long display=Integer.MAX_VALUE;
			System.out.println(display+1+n1);
		}
		return new int[]{n2,n1};
		
	}
	public static void main(String[] args){
		System.out.println(Integer.MAX_VALUE);
		cal64(Integer.MAX_VALUE,555);
	}
}
