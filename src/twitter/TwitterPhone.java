package twitter;

import java.util.*;

public class TwitterPhone {
	//basic calculator
	static int calculator(String s){
		int result=0;
		char lastope='+';
		int lastNum=0;
		int curNum=0;
		for(int i=0;i<s.length();i++){
			if(Character.isDigit(s.charAt(i))){
				curNum=curNum*10+s.charAt(i)-'0';
				
			}
			else{
				if(s.charAt(i)=='+'||s.charAt(i)=='-'){
					result=calSum(result,lastope,lastNum,curNum);
					lastNum=0;
					
				}
				else{
					lastNum=calNum(lastope,lastNum,curNum);
					
				}
				curNum=0;
				lastope=s.charAt(i);
			}
		}
		if(lastNum!=0||curNum!=0){
			result=calSum(result,lastope,lastNum,curNum);
		}
		return result;
	}
	static int calSum(int result,char lastope, int lastNum,int curNum){
		switch(lastope){
		case '+': result+=lastNum+curNum;
			break;
		case '-': result+=lastNum-curNum;
			break;
		case '*': result+=lastNum*curNum;
			break;
		case '/': result+=lastNum/curNum;
			break;
		}
		return result;
	}
	static int calNum(char lastope,int lastNum, int curNum){
		switch(lastope){
		case '+': lastNum=curNum;
			break;
		case '-': lastNum=-curNum;
			break;
		case '*': lastNum*=curNum;
			break;
		case '/': lastNum/=curNum;
			break;
		}
		return lastNum;
	}
//	input: [[1, 5], [7, 9], [8, 12], [15, 20]]
//			output: [[0, 1], [5, 7], [12, 15]]

	static List<Interval> missing(List<Interval> list){
		
		List<Interval> result=new ArrayList<>(); //假设list按start时间排好了
		for(int i=1;i<list.size();i++){
			if(list.get(i).start>list.get(i-1).end){
				result.add(new Interval(list.get(i-1).end,list.get(i).start));
			}
		}
		return result;
	}
	static class Interval{
		int start;
		int end;
		Interval(int s, int e){
			this.start=s;
			this.end=e;
		}
	}
	//3 * 3 的一个board，上面有1-8 八个数，0表示一个空格，其他的数可以移到这个位置。
	//每个状态可以编码成一个string，
	static String moveBoard(String s, int direction){
		int p=-1;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='0'){
				p=i;
				break;
			}
		}
		char[] c=s.toCharArray();
		switch(direction){
		case 0: //move up
			if(p>=0&&p<3) return "";
			else swap(c,p-3,p);
			break;
		case 1: //move right
			if(p%3==2) return "";
			else swap(c,p+1,p);
			break;
		case 2: //move down
			if(p>=6&&p<9) return "";
			else swap(c,p+3,p);
			break;
		case 3: //move left
			if(p%3==0) return "";
			else swap(c,p-1,p);
			break;
		}
		return new String(c);
	}
	static void swap(char[] c, int i , int j){
		char t=c[i];
		c[i]=c[j];
		c[j]=t;
	}
	static int fewestStep(String s, String t){
		Queue<String> que=new LinkedList<>();
		que.add(s);
		int step=0;
		HashSet<String> visited=new HashSet<>();
		while(!que.isEmpty()){
			int size=que.size();
			HashSet<String> cur=new HashSet<>();
			for(int i=0;i<size;i++){
				String tmp=que.poll();
				for(int j=0;j<4;j++){
					String nxt=moveBoard(tmp,j);
					if(nxt.equals(t)) return step+1;
					if(nxt.length()>0&&!visited.contains(nxt)&&!cur.contains(nxt)){
						cur.add(nxt);
						que.add(nxt);
					}
				}
				
			}
			step++;
			visited.addAll(cur);
		}
		return step;
	}
	
	static double prob(int w, int r){
		if(r==0) return 1;
		if(w==0) return 0;
		return w/(r+w)*prob(r,w-1)+r/(r+w)*(w/(r+w)*prob(r,w-1)+r/(r+w)*prob(r-1,w));
	}
	
	
	public static void main(String[] args){
		System.out.println(TwitterPhone.calculator("1/5-90+2*8*9/3"));
		System.out.println(TwitterPhone.moveBoard("123456780", 0));
	}
}
