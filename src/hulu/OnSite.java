package hulu;

public class OnSite {
	
	//find the smallest k that through +/- at most k for each element
	public int smallestK(int[] a){
		if(a==null||a.length<=1) return 0;
		int diff=0;
		int max=a[0];
		for(int i=1;i<a.length;i++){
			max=Math.max(max,a[i]);
			diff=Math.max(diff,max-a[i]);
		}
		return diff/2+1;
		
	}
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1=nums1.length, len2=nums2.length;
        int l=len1+len2;
        if(l%2==0){
            return (findKth(nums1,0,len1-1,nums2,0,len2-1,l/2)+findKth(nums1,0,len1-1,nums2,0,len2-1,l/2+1))/2.0;
        }
        else return findKth(nums1,0,len1-1,nums2,0,len2-1,l/2+1);
    }
	public int findKth(int[] a, int[] b, int k){
		if(a==null||b==null) return -1;
		int len1=a.length, len2=b.length;
		if(k>len1+len2) return -1;
		if(a==null||a.length==0) return b[k-1];
		if(b==null||b.length==0) return a[k-1];
		return findKth(a,0,len1-1,b,0,len2-1,k);
	}
    public int findKth(int[] nums1,int l1,int r1,int[] nums2, int l2,int r2, int k){
        int len1=r1-l1+1;
        int len2=r2-l2+1;
        if(len1>len2) return findKth(nums2,l2,r2,nums1,l1,r1,k);
        if(len1<=0) return nums2[l2+k-1];
        if(k==1) return Math.min(nums1[l1],nums2[l2]);
        
        int part1=Math.min(len1,k/2);
        int part2=k-part1;
        
        int index1=l1+part1-1;
        int index2=l2+part2-1;
        
        if(nums1[index1]==nums2[index2]) return nums1[index1];
        else if(nums1[index1]>nums2[index2]) return findKth(nums1,l1,r1,nums2,index2+1,r2,k-part2);
        else return findKth(nums1,index1+1,r1,nums2,l2,r2,k-part1);
    }
    //给n个slots，让插入一个广告x，保证x之间的gap大于一个值k，一共有多少种????
    public int insertAds(int gap, int k){
    	if(gap<=k) return 0;
    	int[] dp = new int[gap+1];
    	dp[0]=1;
    	for(int i=1;i<=gap;i++){
    		if(i<=k){
    			dp[i]=i+1;
    		}
    		else{
    			dp[i]=dp[i-1]+dp[i-k-1];
    		}
    	}
    	return dp[gap];
    }
    //quicksort linked list ???????????????????????????
    public ListNode quickSort(ListNode head){
    	if(head==null||head.next==null) return head;
    	ListNode p=head;
    	while(p.next!=null){
    		p=p.next;
    	}
    	return quickSort(head,p);
    }
    public ListNode quickSort(ListNode start, ListNode end){
    	if(start==end) return start;
    	ListNode p =partition(start,end);
    	if(start!=p){
    		ListNode tmp=start;
    		while(tmp.next!=p){
    			tmp=tmp.next;
    		}
    		tmp.next=null;
    		quickSort(start,tmp);
    		tmp.next=p;
    	}
    	System.out.println(p.val);
    	p.next=quickSort(p.next,end);
    	return start;
    }
    public ListNode partition(ListNode start, ListNode end){
    	if(start==null||start==end) return start;
    	int target = end.val;
    	ListNode p=start;
    	ListNode n=end;
    	while(p!=end){
    		if(p.val>=target){
    			ListNode nxt=p.next;
    			n.next=p;
    			n=n.next;
    			p=nxt;
    		}
    		else
    			p=p.next;
    	}
    	end=n;
    	return p;
    }
    
    class ListNode{
    	int val;
    	ListNode next;
    	ListNode(int v){
    		this.val=v;
    	}
    }
    
    public static void main(String[] args){
    	OnSite s = new OnSite();
    	int[] a={1,2}, b={2,2,9,9};
    	ListNode node1=s.new ListNode(3);
    	ListNode node2=s.new ListNode(1);
    	node1.next=node2;
    	s.quickSort(node1);
    	s.findMedianSortedArrays(a, b);
    	System.out.println(s.smallestK(a));
    	System.out.println(s.insertAds(4,2));
    }
}
