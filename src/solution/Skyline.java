package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Skyline {
    public List<int[]> getSkyline(int[][] buildings) {
        if(buildings==null||buildings.length==0||buildings[0].length!=3)
            return Collections.emptyList();
        Building[] arr = new Building[buildings.length];
        for(int i=0;i<buildings.length;i++){
            arr[i]=new Building(buildings[i][0],buildings[i][1],buildings[i][2]);
        }
        return divide(arr, 0, arr.length-1);
        
    }
    
    public List<int[]> divide(Building[] arr, int l, int r){
         List<int[]> result = new ArrayList<int[]>();
         if(l==r){
             int[] a = {arr[l].left, arr[l].height};
             int[] b = {arr[l].right, 0};
             result.add(a);
             result.add(b);
             return result;
         }
         int m=(l+r)/2;
         List<int[]> left = divide(arr, l, m);
         List<int[]> right = divide(arr, m+1, r);
         return merge(left, right);
    }
    
    public List<int[]> merge(List<int[]> left, List<int[]> right){
        int i=0,j=0;
        int hleft = 0;
        int hright = 0;
        List<int[]> result = new ArrayList<int[]>();
        while(i<left.size()&&j<right.size()){
            if(left.get(i)[0]<right.get(j)[0]){
                hleft = left.get(i)[1];
                int maxh = Math.max(hleft, hright);
                left.get(i)[1]=maxh;
                add(result, left.get(i));
                i++;
            }
            else{
                hright = right.get(j)[1];
                int maxh = Math.max(hleft,hright);
                right.get(j)[1]=maxh;
                add(result, right.get(j));
                j++;
            }
        }
        if(i==left.size()){
            while(j<right.size()){
                add(result, right.get(j));
                j++;
            }
        }
        if(j==right.size()){
            while(i<left.size()){
                add(result,left.get(i));
                i++;
            }
        }
        return result;
            
    }
    
    public void add(List<int[]> result, int[] added){
        
        if(result.size()==0){
            result.add(added);
            System.out.println("added: "+ added[0]+ " and "+added[1]);
            return;
        }
       
        int lastH = result.get(result.size()-1)[1];
        int lastX = result.get(result.size()-1)[0];
        if(lastH==added[1])
            return;
        if(lastX==added[0]){
            result.get(result.size()-1)[1]=Math.max(lastH, added[1]);
            return;
        }
        result.add(added);
        System.out.println("added: "+ added[0]+ " and "+added[1]);
    }
public List<int[]> getSkyline2(int[][] buildings) {
        
        List<int[]> edges = new ArrayList<>();
        if(buildings==null||buildings.length==0||buildings[0].length!=3) return new ArrayList<int[]>();
        for(int[] b:buildings){
            edges.add(new int[]{b[0],b[2]});
            edges.add(new int[]{b[1],0});
        }
        return divide(edges, 0, edges.size()-1);
    }
    public List<int[]> divide(List<int[]> edges, int leftindex, int rightindex){
        if(leftindex>rightindex){
            return new ArrayList<>();
        }
        List<int[]> result = new ArrayList<>();
        if(leftindex==rightindex){
            Add(result, new int[]{edges.get(leftindex)[0], edges.get(leftindex)[1]});
            return result;
        }
        int mid = (leftindex+rightindex)/2;
        List<int[]> left = divide(edges, leftindex, mid);
        List<int[]> right = divide(edges, mid+1, rightindex);
        
        int lefth=0, righth=0;
        int i=0, j=0;
        while(i<left.size()&&j<right.size()){
            if(left.get(i)[0]<right.get(j)[0]){
                lefth=left.get(i)[1];
                left.get(i)[1]=Math.max(righth, lefth);
                Add(result, left.get(i));
                i++;
            }
            else{
                righth=right.get(j)[1];
                right.get(j)[1]=Math.max(righth, lefth);
                Add(result, right.get(j));
                j++;
            }
        }
        if(i<left.size()){
            for(int k=i;k<left.size();k++){
                Add(result, left.get(k));
            }
        }
        if(j<right.size()){
            for(int k=j;k<right.size();k++){
                Add(result, right.get(k));
            }
        }
        return result;
    }
    public void Add(List<int[]> result, int[] added){
        if(result.size()==0||result.get(result.size()-1)[1]!=added[1])
            result.add(added);
    }
    public class Building{
        int left;
        int right;
        int height;
        Building(int a, int b, int c){
            this.left = a;
            this.right = b;
            this.height = c;
        }
    }

    public static void main(String[] args){
    	int[][] build = {{1,2,1},{1,2,2},{1,2,3}};
    	Skyline a = new Skyline();
    	a.getSkyline2(build);
    }
}