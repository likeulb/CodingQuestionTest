package solution;

import java.util.*;

public class ReconstructItinerary {
	List<String> result;
    public List<String> findItinerary(String[][] tickets) {
        result = new ArrayList<>();
        if(tickets==null||tickets.length==0||tickets[0].length==0) return result;
        HashMap<String,List<String>> map = new HashMap<>();
        int n=tickets.length;
        
        for(String[] s:tickets){
            if(!map.containsKey(s[0])){
                map.put(s[0],new ArrayList<String>());
            }
            map.get(s[0]).add(s[1]);
        }
        for(String k:map.keySet()){
            if(map.get(k).size()>1)
                Collections.sort(map.get(k));
        }
        
        dfs(map,"JFK",n,0,new ArrayList<String>());
        return result;
    }
    public void dfs(HashMap<String,List<String>> map, String s, int n, int num,List<String> cur){
        cur.add(s);
        if(num==n){
            result=new ArrayList<String>(cur);
            return;
        }
        if(map.containsKey(s)){
            List<String> nxt = map.get(s);
            for(int i=0;i<nxt.size();i++){
                String tmp=nxt.get(i);
                if(tmp.length()>0){
                    nxt.set(i,"");
                    dfs(map,tmp,n,num+1,cur);
                    nxt.set(i,tmp);
                }
            }
            
        }
        cur.remove(cur.size()-1);
    }
    public static void main(String[] args){
    	ReconstructItinerary s=new ReconstructItinerary();
    	String[][] t={{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
    	s.findItinerary(t);
    }
}
