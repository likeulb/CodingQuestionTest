package solution;

public class BitProblem {
	public static int updateBits(int n, int m, int i, int j) {
        // write your code here
        
        long allOnes = ~0;
        long left = allOnes<<(j+1);
        System.out.println(Long.toBinaryString(left));
        int right = ((1<<i)-1);
        System.out.println(Long.toBinaryString(right));
        long mask = left|right;
        System.out.println(Long.toBinaryString(mask));
        n&=mask;
        System.out.println(Integer.toBinaryString(n));
        return n|(m<<i);
    }
	public static String binaryRepresentation(String n) {
        // write your code here
		double num = Double.parseDouble(n);
        if(n.equals("0"))
            return "0";
        int left = (int)num;
        String result = Integer.toBinaryString(left);
        num-=left;
        if(num==0)
            return result;
        StringBuilder sb = new StringBuilder();
        while(num!=0){
            if(sb.length()==32)
                return "ERROR";
            double tmp = num*2;
            if(tmp>=1){
                sb.append(1);
                num = tmp-1;
            }
            else{
                sb.append(0);
                num=tmp;
            }
        }
        return result+"."+sb.toString();
    }
	
	
	
	
	    
	
	public static void main(String[] args){
		int[] a = {2,2,3,2};
		BitProblem test = new BitProblem();
		
		System.out.println(Integer.toBinaryString(-512));
		binaryRepresentation("28187281.128121212121");
		
	}
}
