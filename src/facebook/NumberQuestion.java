package facebook;

public class NumberQuestion {
	//add 1 without +
	public int add(int num){
		int y=1;
		while(y!=0){
			int c=(num&y);
			num^=y;
			y=(c<<1);
		}
		return num;
	}
	public static void main(String[] args){
		NumberQuestion test=new NumberQuestion();
		System.out.println(test.add(25));
		
	}
}
