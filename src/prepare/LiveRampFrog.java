package prepare;

public class LiveRampFrog {
	public int frogCanJump(int[] A, int D, int x){
		if(A==null||A.length==0) return 0;
		if(x<=D) return 0;
		int numBin =(x-1)/D+1;
		int num=numBin;
		Range[] rangs = new Range[numBin];
		boolean[] checked = new boolean[x+1];
		checked[0]=true;
		checked[x]=true;
		rangs[numBin-1]=new Range(x,x);
		//rangs[numBin-1].acessed=true;
		for(int i=0;i<A.length;i++){
			int cur = A[i];
			if(cur>=x) continue;
			if(checked[cur]) continue;
			int bin = (cur-1)/D;
			if(rangs[bin]==null){
				rangs[bin]=new Range(cur, cur);
			}
			else{
				rangs[bin].left=Math.min(rangs[bin].left, cur);
				rangs[bin].right=Math.max(rangs[bin].right, cur);
			}
			
			if(bin-1>=0&&rangs[bin-1]!=null){
				if(rangs[bin].left-rangs[bin-1].right<=D){
					if(!rangs[bin].acessed){
						num--;
						rangs[bin].acessed=true;
					}
					if(!rangs[bin-1].acessed){
						num--;
						rangs[bin-1].acessed=true;
					}
				}
			}
			if(bin+1<numBin&&rangs[bin+1]!=null){
				if(rangs[bin+1].left-rangs[bin].right<=D){
					if(!rangs[bin].acessed){
						num--;
						rangs[bin].acessed=true;
					}
					if(!rangs[bin+1].acessed){
						num--;
						rangs[bin+1].acessed=true;
					}
				}
			}
			checked[cur]=true;
			if(num==0) return i;
			
		}
		return -1;
		
	}
	class Range{
		int left;
		int right;
		boolean acessed;
		Range(int l, int r){
			this.left=l;
			this.right=r;
			this.acessed=false;
		}
		
	}
	public static void main(String[] args){
		LiveRampFrog test = new LiveRampFrog();
		int[] A={3,4,6,1};
		int[] B={1,3,1,4,2,5};
		System.out.println(test.frogCanJump(A, 4, 11));
	}
}
