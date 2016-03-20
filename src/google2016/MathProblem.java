package google2016;
import java.util.*;

import commonClass.*;
public class MathProblem {
	//给一堆点，找一条垂直于x轴的线使左右两边的点对称
	//先求x坐标的meam, 按x坐标排序，对x坐标>=mean的点建一个hashmap<x,hashmap<y,count>>的map
	public double findX(Point[] arr){
		double x=0;
		Arrays.sort(arr,new Comparator<Point>(){
			public int compare(Point p1,Point p2){
				return p1.x-p2.x;
			}
		});
		for(int i=0;i<arr.length;i++){
			x+=arr[i].x;
		}
		double mid=x/arr.length;
		HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
		for(int i=arr.length-1;i>=0;i--){
			Point p =arr[i];
			if(p.x>=mid){
				if(!map.containsKey(p.x)){
					map.put(p.x, new HashMap<Integer,Integer>());
				}
				HashMap<Integer,Integer> cur=map.get(p.x);
				if(!cur.containsKey(p.y)){
					cur.put(p.y, 1);
				}
				else{
					cur.put(p.y, cur.get(p.y)+1);
				}
			}
			else break;
		}
		for(int i=0;i<arr.length;i++){
			Point p=arr[i];
			if(p.x<mid){
				int target=(int)(2*mid-p.x);
				if(!map.containsKey(target)){
					return Double.MAX_VALUE; //no such target
				}
				else{
					HashMap<Integer,Integer> cur=map.get(p.x);
					if(cur.containsKey(p.y)) return Double.MAX_VALUE;
					else{
						if(cur.get(p.y)<=0) return Double.MAX_VALUE;
						else cur.put(cur.get(p.y), cur.get(p.y)-1);
					}
				}
			}
		}
		if(map.containsKey(mid)){
			for(int y:map.get(mid).keySet()){
				if(y%2==1) return Double.MAX_VALUE;
			}
		}
		return mid;
		
	}
	//就是y=a*x^2+b*x+c，给一个sorted list of x， return sorted y
	public double[] calculation(int a, int b, int c, double[] x){
		double[] y=new double[x.length];
		if(a==0){
			for(int i=0;i<x.length;i++){
				if(b>0){
					y[i]=b*x[i]+c;
				}
				else{
					y[y.length-i-1]=b*x[i]+c;
				}
			}
			return y;
		}
		else{
			double mid=-(double)b/2*a;
			List<Double> l1=new ArrayList<>();
			List<Double> l2=new ArrayList<>();
			for(int i=0;i<x.length;i++){
				if(x[i]<=mid){
					l1.add(a*x[i]*x[i]+b*x[i]+c);
				}
				else{
					l2.add(a*x[i]*x[i]+b*x[i]+c);
				}
			}
			if(a<0){
				Collections.reverse(l2);
			}
			else{
				Collections.reverse(l1);
			}
			return merge(l1,l2);
		}
	}
	public double[] merge(List<Double> l1, List<Double> l2){
		double[] result=new double[l1.size()+l2.size()];
		int i=0, j=0;
		int k=0;
		while(i<l1.size()&&j<l2.size()){
			if(l1.get(i)<l2.get(j)){
				result[k++]=l1.get(i);
				i++;
			}
			else{
				result[k++]=l2.get(j);
				j++;
			}
		}
		while(i<l1.size()){
			result[k++]=l1.get(i);
			i++;
		}
		while(j<l2.size()){
			result[k++]=l2.get(j);
			j++;
		}
		return result;
	}

}
