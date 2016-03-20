package solution;

import java.util.*;

public class CountOfLeftSmaller {
	List<Integer> result;
    public List<Integer> countSmaller(int[] nums) {
        if(nums==null||nums.length==0) return new ArrayList<Integer>();
        int left = 0, right = nums.length-1;
        result = new ArrayList<>();
        List<Entry> list = new ArrayList<Entry>();
        for(int i=0;i<nums.length;i++){
            list.add(new Entry(nums[i],i));
            result.add(0);
        }
        divide(list, 0, list.size()-1);
        return result;
    }
    public List<Entry> divide(List<Entry> list, int left, int right){
        List<Entry> res = new ArrayList<>();
        if(left==right){
            res.add(list.get(left));
            return res;
        }
        int mid = left+(right-left)/2;
        List<Entry> l = divide(list,left,mid);
        List<Entry> r = divide(list,mid+1,right);
        return merge(l,r);
    }
    public List<Entry> merge(List<Entry> left, List<Entry> right){
        int i=0, j=0;
        List<Entry> res = new ArrayList<>();
        while(i<left.size()&&j<right.size()){
            if(left.get(i).val>right.get(j).val){
                int c = result.get(left.get(i).index)+(right.size()-j);
                System.out.println(right.size()-j);
                result.set(left.get(i).index,c);
                res.add(left.get(i++));
            }
            else{
                res.add(right.get(j++));
            }
        }
        while(i<left.size()){
            res.add(left.get(i++));
        }
        while(j<right.size()){
            res.add(right.get(j++));
        }
        return res;
    }
    class Entry{
        int val;
        int index;
        Entry(int v, int i){
            val=v;
            index=i;
        }
    }
    public static void main(String[] args){
    	int[] a  = {5,2,6,1};
    	CountOfLeftSmaller s = new CountOfLeftSmaller();
    	List<Integer> res = s.countSmaller(a);
    	for(int i:res){
    		System.out.println(i);
    	}
    }
}
