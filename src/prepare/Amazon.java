package prepare;

import java.util.*;

public class Amazon {
	//you are given an integer array, where all numbers except TWO  appear even number of times
	//find out the two numbers which appear odd number of times
	//1.use bit, O(n)time O(1)space
	public static void findTwoOddNumber(int[] arr){
		int xor=0;
		for(int i:arr){
			xor^=i;
		}
		int y = xor & (-xor);
		int num1=0;
		int num0=0;
		for(int i:arr){
			if((y&i)==y){
				num1^=i;
			}
			else{
				num0^=i;
			}
		}
		System.out.println(num0);
		System.out.println(num1);
	}
	//2. use hashset, O(n) time and space
	public static void findTwoOddNumber2(int[] arr){
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i:arr){
			if(set.contains(i)){
				set.remove(i);
			}
			else{
				set.add(i);
			}
		}
		for(int i:set){
			System.out.println(i);
		}
	}
	
	public static int[][] swap(int[][] arr, boolean flag){
		int n=arr.length;
		int m=arr[0].length;
		int[][] result = new int[m][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(flag){//turn right
					result[j][n-1-i]=arr[i][j];
				}
				else{
					result[m-1-j][i]=arr[i][j];
				}
			}
		}
		return result;
	}
	public static void printArray2(int[][] a){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				System.out.print(a[i][j]);
			}
			System.out.println("");
		}
	}
	
	public static int shortestJobFirst(int[] request, int[] duration){
		int[] end = new int[request.length];
		int curTime=0;
		for(int i=0;i<request.length;i++){
			int minDuration = Integer.MAX_VALUE;
			int curMin = -1;
			for(int j=0;j<request.length;j++){
				if(end[j]!=0) continue;
				if(request[j]<=curTime){
					if(duration[j]<minDuration){
						minDuration = duration[j];
						curMin = j;
					}
				}
				else
					break;
			}
			if(curMin==-1){
				curMin = i;
				curTime = request[curMin];
			}
			curTime+=duration[curMin];
			end[curMin]=curTime;
		}
		int waitTime=0;
		for(int i=0;i<request.length;i++){
			waitTime+=end[i]-request[i]-duration[i];
		}
		return waitTime;
	}
	public static int SJF(int[] request, int[] duration){
		HashSet<Integer> hasRun = new HashSet<>();
		int curTime = 0;
		int waitTime = 0;
		for(int i=0;i<request.length;i++){
			int minDuration = Integer.MAX_VALUE;
			int minIndex = -1;
			for(int j=0;j<request.length;j++){
				if(hasRun.contains(j))
					continue;
				if(request[j]<=curTime){
					if(duration[j]<minDuration){
						minDuration = duration[j];
						minIndex = j; 
					}
				}
				else
					break;
			}
			if(minIndex==-1){
				minIndex=i;
				curTime = request[minIndex];
			}
			hasRun.add(minIndex);
			waitTime+=curTime-request[minIndex];
			curTime+=duration[minIndex];
			
		}
		return waitTime;
	}
	
	public static int roundRobin(int[] arrival, int[] run, int q){
		Queue<String> que = new LinkedList<String>();
		que.add(0+" "+0);
		int n=arrival.length;
		int time = 0;
		int i=1;
		int wait=0;
		int cur_index;
		int arr;
		int m;
		while(!que.isEmpty()){
			String[] s = que.poll().split(" ");
			cur_index = Integer.parseInt(s[0]);
			arr = Integer.parseInt(s[1]);
			System.out.println("poll from queue "+cur_index+" "+" "+arr+"at: "+time);
			wait+=Math.max(time-arr,0);
			int r = Math.min(q, run[cur_index]);
			time+=r;
			run[cur_index]-=r;
			while(i<n&&arrival[i]<time&&arrival[i]>time-r){
				que.add(i+" "+arrival[i]);
				System.out.println("new add to queue: "+ i+" "+arrival[i]);
				i++;
			}
			if(run[cur_index]>0){
				que.add(cur_index+" "+time);
				System.out.println("nufinished: "+ cur_index+" "+time);
			}
			if(que.isEmpty()){
				if(i>=n) return wait;
				que.add(i+" "+arrival[i]);
				time=arrival[i];
				i++;
			}
			
			
		}
		return wait;
	}
	
	public int roundRobinNew(int[] arr, int[] exe, int q){
		Queue<job> que = new LinkedList<>();
		int curTime = 0;
		int waitTime = 0;
		int index = 0;
		while(!que.isEmpty()||index<arr.length){
			if(!que.isEmpty()){
				job cur = que.poll();
				waitTime+=curTime-cur.arrival;
				curTime+=Math.min(q,cur.runTime);
				for(;index<arr.length;index++){
					if(arr[index]<curTime){
						que.offer(new job(arr[index],exe[index]));
					}else{
						break;
					}
				}
				if(cur.runTime>q){
					que.offer(new job(curTime, cur.runTime-q));
				}
			}
			else{
				que.offer(new job(arr[index],exe[index]));
				curTime = arr[index++];
			}
		}
		return waitTime;
	}
	public class job{
		int arrival;
		int runTime;
		public job(int a, int r){
			this.arrival=a;
			this.runTime=r;
		}
		
	}
	public  class Pair implements Comparable{
        int sum;
        int index;
        public Pair(int s, int i){
            this.sum=s;
            this.index=i;
        }
        public int compareTo(Pair s){
            return this.sum-s.sum;
        }
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}
        
    }
	
	public static void main(String[] args){
	/*	int[] a = {3,3,5,5,2,4,2,4,5,3};
		Amazon.findTwoOddNumber(a);
		int[][] b={{1,2,3},{4,5,6}};
		Amazon.printArray2(b);
		int[][] c=Amazon.swap(b, false);
		Amazon.printArray2(c);*/
		
		int[] aa = {0,50,130,190,210,350};
		int[] run = {250,170,75,100,130,50};
		int[] run1 = {250,170,75,100,130,50};
		System.out.println(Amazon.roundRobin(aa, run, 100));
		Amazon test = new Amazon();
		System.out.println(test.roundRobinNew(aa,run1, 100));
		int[] request = {0,0,5,6,20,20,24};
		int[] duration = {3,1,4,50,40,30,10};
		System.out.println(Amazon.shortestJobFirst(request, duration));
		System.out.println(Amazon.SJF(request, duration));
		
	}
}

