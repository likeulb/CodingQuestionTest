package solution;

import java.util.*;

public class RepeatedDNA {
	public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if(s==null||s.length()<9) return result;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A',0);
        map.put('C',1);
        map.put('G',2);
        map.put('T',3);
        HashSet<Integer> set = new HashSet<>();
        HashSet<String> res=new HashSet<>();
        int num=0;
        for(int i=0;i<s.length();i++){
            if(i<10){
                num<<=2;
                num+=map.get(s.charAt(i));
            }
            else{
                if(set.contains(num)){
                    res.add(s.substring(i-10,i));
                }
                else{
                    set.add(num);
                }
                num<<=2;
                num&=((1<<23)-1);
                num+=map.get(s.charAt(i));
            }
        }
        if(set.contains(num)) res.add(s.substring(s.length()-10, s.length()));
        result.addAll(res);
        return result;
    }
	public static void main(String[] args){
		RepeatedDNA test = new RepeatedDNA();
		test.findRepeatedDnaSequences("AAGATCCGTCCCCCCAAGATCCGTC");
	}
}
