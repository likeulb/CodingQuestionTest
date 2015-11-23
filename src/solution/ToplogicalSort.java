package solution;

import java.util.ArrayList;
import java.util.List;

public class ToplogicalSort {
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] numAdjs = new int[numCourses];
        List<List<Integer>> adjs = new ArrayList<List<Integer>>(numCourses);
        for(int i=0;i<numCourses;i++){
            adjs.add(new ArrayList<Integer>());
        }
        for(int[] e:prerequisites){
            adjs.get(e[0]).add(e[1]);
            numAdjs[e[0]]++;
        }
        return dfs(adjs);
    }
    public int[] dfs(List<List<Integer>> adjs){
        int n = adjs.size();
        boolean[] checking  = new boolean[n];
        boolean[] checked = new boolean[n];
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            if(!checked[i]){
                dfshelp(adjs, i, checking, checked,result);
            }
        }
        int[] output = new int[result.size()];
        int k=0;
        for(int i=result.size()-1;i>=0;i--){
                output[k++]=result.get(i);
        }
        return output;
    }
    public void dfshelp(List<List<Integer>> adjs, int i, boolean[] checking, boolean[] checked, ArrayList<Integer> result){
        if(checking[i]) return;
        if(!checked[i]){
            checking[i]=true;
            for(int j:adjs.get(i)){
                dfshelp(adjs, j, checking, checked, result);
            }
            checking[i]=false;
            checked[i]=true;
            result.add(i);
        }
    }
    public static void main(String[] args){
    	int[][] input = {{1,0}};
    	ToplogicalSort t = new ToplogicalSort();
    	int[] out = t.findOrder(2,input);
    	System.out.println(out[0]);
    	System.out.println(out[1]);
    }

}
