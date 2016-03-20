package ms;

import java.util.*;


public class DataStructure {
	//图像压缩，每个像素点的值是binary的，如何encoding。
	static List<Integer> encode(char[][] arr){
		List<Integer> result=new ArrayList<>();
		int count=0;
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				if(result.size()%2==0&&arr[i][j]=='0'||result.size()%2==1&&arr[i][j]=='1'){
					if(count==Integer.MAX_VALUE){
						result.add(count);
						result.add(0);
						count=0;
					}
					count++;
				}
				else if(result.size()%2==0&&arr[i][j]=='1'||result.size()%2==1&&arr[i][j]=='0'){
					result.add(count);
					count=1;
				}
			}
		}
		return result;
	}
	
}
