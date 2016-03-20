package solution;

public class PaintHouse {
public int minCostII(int[][] costs) {
        
        int index0=-1, index1=-1;
        
        int min0=0, min1=0;
        for(int i=0;i<costs.length;i++){
            int tmp0=0, tmp1=0;
            int cur0=-1,cur1=-1;
            for(int j=0;j<costs[0].length;j++){
                int c=0;
                if(i==0){
                    c=costs[i][j];
                }
                else{
                    c = (index0==j?min1:min0)+costs[i][j];
                    
                }
                
                if(cur0==-1||c<tmp0){
                    cur1=cur0;
                    tmp1=tmp0;
                    cur0=j;
                    tmp0=c;
                }
                else if(cur1==-1||c<tmp1){
                    cur1=j;
                    tmp1=c;
                }
            }
            min0=tmp0;
            min1=tmp1;
            index0=cur0;
            index1=cur1;
        }
        return min0;
        
    }
	public static void main(String[] args){
		PaintHouse test=new PaintHouse();
		int[][] a = {{1,5,3},{2,9,4}};
		test.minCostII(a);
	}
}
