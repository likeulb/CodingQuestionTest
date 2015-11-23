package googleOnSite;

import java.util.Random;

public class RandomTest {
	public boolean HalfTo70Percent(){
		Random rand = new Random();
		boolean result;
		while(true){
			int x = 8*rand.nextInt(2)+4*rand.nextInt(2)+2*rand.nextInt(2)+rand.nextInt(2);
			if(x>=10) continue;
			else if(x<7){
				result=true;
				break;
			}
			else{
				result=false;
				break;
			}
		}
		return result;
	}
	public int HalfTo60Num(){
		Random rand = new Random();
		int result=-1;
		while(true){
			result = 4*rand.nextInt(2)+2*rand.nextInt(2)+rand.nextInt(2);
			if(result<=5) break;
		}
		return result;
	}
	public static void main(String[] args){
		RandomTest test = new RandomTest();
		System.out.println(test.HalfTo60Num());
	}
}
