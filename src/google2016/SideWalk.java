package google2016;

import java.util.Random;

public class SideWalk {
	Interval1[] sids=new Interval1[100];
	
	public int count(){
		for(int i=0;i<100;i++){
			sids[i]=new Interval1();
		}
		int count=0;
		while(count<100){
			double p=Math.random();
			int index=(int)(p*100);
			if(sids[index].right>sids[index].left){
				double r=0.01-(p-index*0.01);
				sids[index].right=Math.min(sids[index].right, 0.01-r);
				if(sids[index].right<=sids[index].left){
					count++;
				}
			}
			if(index<99&&sids[index+1].right>sids[index+1].left){
				double l=p-index*0.01;
				sids[index+1].left=Math.max(sids[index+1].left,l);
				if(sids[index+1].right<=sids[index].left){
					count++;
				}
				
			}
			
			
		}
		return count;
		
		
	}
	public static void main(String[] args){
		SideWalk test=new SideWalk();
		System.out.println(test.count());
	}

	
	
	
	
	
	
	

}


	class Interval1{
		double left;
		double right;
		Interval1(){
			left=0;
			right=0.01;
		}
	}
	

