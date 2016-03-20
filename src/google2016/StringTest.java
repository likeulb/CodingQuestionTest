package google2016;

import java.util.*;

public class StringTest {
	/*第一题：给两个长度相等的string，如果两个string对应位置的字母不相同记为一个distance，
	如果现在能够交换一次其中一个string中任意位置的两个char，
	返回能够将distance缩到最小的两个char的index（如果有多个最优解只返回一个）*/
	//用hashMap<char, HashMap<Character, Integer>> 看s2是不是有character在s1匹配不上的
	//还要注意如果c2不在map的key里，还要看c1是不是在map的value里
	public int[] swapChar(String s1, String s2){
		int index1=-1;
		int index2=-1;
		HashMap<Character,HashMap<Character,Integer>> map=new HashMap<>();
		
		for(int i=0;i<s1.length();i++){
			if(s1.charAt(i)!=s2.charAt(i)){
				char c1=s1.charAt(i);
				char c2=s2.charAt(i);
				if(map.containsKey(c2)){
					if(map.get(c2).containsKey(c1)){
						return new int[]{map.get(c2).get(c1),i};
					}
					else{
						if(index1<0&&map.get(c2).size()>0){
							for(char t:map.get(c2).keySet()){
								index1=map.get(c2).get(t);
								break;
							}
							index2=i;
						}
					}
				}
				else{
					for(char key:map.keySet()){
						if(index1<0&&map.get(key).containsKey(c1)){
							index1=map.get(key).get(c1);
							index2=i;
							break;
						}
						
					}
					
				}
				if(!map.containsKey(c1)){
					map.put(c1, new HashMap<Character,Integer>());
				}
				map.get(c1).put(c2, i);
			}
		}
		return new int[]{index1, index2};
		
	}
	//https://www.careercup.com/question?id=5721860970905600
	//csv parssr
	/*a, "b", c ==> 
	a 
	b 
	c 


	a, "b, x" , d ==> 
	a 
	b,x 
	d 

	a, "b, x,""", d ==> 'a' 'b,x"' 'd' 
	a 
	b,x," 
	d*/
	public List<String> csvParser(String s){
		List<String> result=new ArrayList<>();
		int flag=-1;
		String cur="";
		int i=0;
		while(i<s.length()){
			char c=s.charAt(i);
			if(c==','){
				if(flag<0){
					result.add(cur);
					cur="";
				}
				else{
					cur+=c;
				}
				i++;
			}
			else if(c==' '){
				i++;
			}
			else if(c=='"'){
				if(flag<0){
					flag=i;
					i++;
				}
				else{
					int count=0;
					while(i<s.length()&&s.charAt(i)=='"'){
						count++;
						i++;
					}
					
					int dup=(count-1)/2;
					for(int k=0;k<dup;k++){
						cur+='"';
					}
					if((count-1)%2==1) cur+='"';
					flag=-1;
				}
			}
			else{
				cur+=c;
				i++;
			}
		}
		result.add(cur);
		return result;
	}
	//“bananaxxbb”，“can” ，参照“can”的顺序返回"aaannabxxbb"，不在“can”里面的保持不变。
	public String sortChars(String s1, String s2){
		final HashMap<Character, Integer> map=new HashMap<>();
		for(int i=0;i<s2.length();i++){
			char c=s2.charAt(i);
			if(!map.containsKey(c)){
				map.put(c, i);
			}
		}
		
		Character[] c1=new Character[s1.length()];
		for(int i=0;i<s1.length();i++){
			c1[i]=new Character(s1.charAt(i));
		}
				
		Arrays.sort(c1,new Comparator<Character>(){
			public int compare(Character c1, Character c2){
				if(map.containsKey(c1)&&map.containsKey(c2)){
					return map.get(c1)-map.get(c2);
				}
				else{
					return map.containsKey(c1)?-1:1;
				}
			}
		});
		StringBuilder sb=new StringBuilder();
		for(char c:c1){
			sb.append(c);
		}
		return sb.toString();
	}
	//输出一个string所有可能的compress strings, compress之后必须比原string短
	//比如 abcdef => [a4f, ab3f, abc2f, a2def, a3ef]
	
	public List<String> compress(String s){
		List<String> result=new ArrayList<>();
		if(s.length()<=3){
			result.add(s);
			return result;
		}
		
		for(int i=2;i<s.length()-1;i++){
			for(int j=1;j<i;j++){
				result.add(s.substring(0,j)+(i-j+1)+s.substring(i+1));
			}
		}
		
	/*	for(int i=2;i<=s.length()-2;i++){
			for(int j=1;i+j<s.length();j++){
				result.add(s.substring(0,j)+i+s.substring(i+j));
			}
		}*/
		return result;
		
	}
	public static void main(String[] args){
		StringTest test=new StringTest();
		
		List<String> compress=test.compress("abcdef");
		for(String s:compress){
			System.out.print(s+" ");
		}
		
		/*
		System.out.println(test.sortChars("bananaxxbb", "can"));
		
		List<String> t=test.csvParser("a, \"b, x, \"\"\", c");
		for(String s:t){
			System.out.println(s);
		}
		int[] a=test.swapChar("acef", "bafe");
		for(int i:a){
			System.out.println(i);
		}*/
	}
}
