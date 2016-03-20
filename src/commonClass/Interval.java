package commonClass;

public class Interval {
	public int start;
	public int end;
	public int profit;
	public Interval(int s, int e, int p){
		this.start=s;
		this.end=e;
		this.profit=p;
	}
	public Interval(int s, int e){
		this.start=s;
		this.end=e;
	}
}
