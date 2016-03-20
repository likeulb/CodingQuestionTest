package solution;

import java.util.Comparator;

public class NutsAndBolts {
	
	public void sortNutsAndBolts(String[] nuts, String[] bolts, Comparator<String> compare) {
        // write your code here
        if(nuts==null||nuts.length==0||bolts==null||bolts.length==0||nuts.length!=bolts.length) return;
        quickSort(nuts,0,nuts.length-1,bolts, compare);
    }
    public void quickSort(String[] nuts, int left, int right, String[] bolts, Comparator<String> compare){
        if(left>=right) return;
        int p = partition(nuts,left,right,bolts[left],compare);
        partition(bolts,left,right,nuts[p],compare);
        quickSort(nuts,left,p-1, bolts,compare);
        quickSort(nuts,p+1,right,bolts,compare);
    }
    public int partition(String[] s, int left, int right, String pivot, Comparator<String> compare){
        int p=left;
        for(int i=left+1;i<=right;i++){
            if(compare.compare(s[i],pivot)<0){
                p++;
                swap(s,p,i);
            }
            else if(compare.compare(s[i],pivot)==0){
                swap(s,i,left);
                i--;
            }
        }
        
        swap(s,p,left);
        return p;
    }
    public void swap(String[] s, int i, int j){
        String tmp = s[i];
        s[i]=s[j];
        s[j]=tmp;
    }
    public static void main(String[] args){
    	String[] s1={"ab","bc","dd","gg"};
    	String[] s2={"AB","GG","DD","BC"};
    	NutsAndBolts test=new NutsAndBolts();
    	Comparator<String> com = new Comparator<String>(){
    		public int compare(String s1, String s2){
    			return s1.toLowerCase().compareTo(s2.toLowerCase());
    		}
    	};
    	test.sortNutsAndBolts(s1, s2, com);
    	
    	
    }
    

}
