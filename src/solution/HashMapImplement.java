package solution;

/**
 * implement HashMap with a list of Entry nodes
 */
public class HashMapImplement<K,V>{
	Entry<K,V>[] arr;
	int size;
	
	/**
     * Constructor
     * @param size  number of Entry array size
     */  	
	HashMapImplement(int size){
		this.size=size;
		arr=new Entry[size];
	}
	
	/**
	 * Add a key value pair
	 * If the key already exists, the add function will not change the related value and return false 
	 * @param key input key
	 * @param value input value
	 * @return true if the key value pair successfully added, false if there already exists the key
	 */
	public boolean add(K key, V value){
		int hashcode = key.hashCode();
		int index=hashcode%size;
		if(arr[index]==null){
			arr[index]=new Entry<K, V>(key, value);
			return true;
		}
		else{
			Entry<K,V> head = arr[index];
			return insertNewEntry(head, key, value);
		}	
	}
	
	/**
	 * Get the value relates to the input key
	 * @param key input key
	 * @return The value object if the key exists. Null, otherwise
	 */
	public V get(K key){
		Entry<K,V> entry = getEntry(key);
		if(entry==null) return null;
		return entry.value;
	}
	
	/**
	 * Update the key value pair
	 * @param key input key
	 * @param value input value
	 * @return true if the value is updated, false if there is no such key
	 */
	public boolean update(K key, V value){
		Entry<K,V> entry = getEntry(key);
		if(entry==null) return false;
		entry.value=value;
		return true;
	}
	
	/**
	 * Delete the key value pair
	 * @param key input key
	 * @return true if the key value pair has been deleted, false if there is no such key
	 */
	public boolean delete(K key){
		int hashcode = key.hashCode();
		int index=hashcode%size;
		Entry<K,V> p= arr[index];
		if(p==null) return false;
		if(p.key.equals(key)){
			arr[index]=p.next;
			return true;
		}
		else{
			while(p.next!=null){
				if(p.next.key.equals(key)){
					p.next=p.next.next;
					return true;
				}
				p=p.next;
			}
		}
		return false;
	}
	
	/**
	 * Get the Entry node of the input key
	 * @param key input key
	 * @return the Entry node
	 */
	public Entry<K,V> getEntry(K key){
		int hashcode = key.hashCode();
		int index=hashcode%size;
		Entry<K,V> p= arr[index];
		if(p==null) return null;
		while(p!=null){
			if(p.key.equals(key)){
				return p;
			}
			p=p.next;
		}
		return null;
	}
	
	/**
	 * Insert new Entry node
	 * @param Entry<K,V> the head of the Entry node list
	 * @param key input key
	 * @param value input value
	 * @return true if a new Entry node is created and added to the list, false if there is already an Entry node with the input key
	 */
	public boolean insertNewEntry(Entry<K,V> head, K key, V value){
		Entry<K,V> p=head;
		if(p.key.equals(key)) return false;
		while(p.next!=null){
			if(p.next.key.equals(key))
				return false;
			p=p.next;
		}
		p.next=new Entry<K, V>(key,value);
		return true;
	}
	
	
	/**
	 * Entry node class with key, value and next pointer
	 */
	public class Entry<K,V>{
		K key;
		V value;
		Entry<K,V> next;
		
		/**
		 * Constructor
		 * @param key input key
		 * @param value input value
		 */
		Entry(K key, V value){
			this.key=key;
			this.value=value;
			next=null;
		}
		
	}
	public static void main(String[] arr){
		HashMapImplement<String, Integer> map = new HashMapImplement<>(10);
		System.out.println(map.add("test1",1));
		System.out.println(map.add("abc",34));
		System.out.println(map.get("abc"));
		System.out.println(map.add("abc",4));
		System.out.println(map.get("abc"));
		System.out.println(map.add("abm",5));
		System.out.println(map.get("abm"));
		String key="abc";
		int value=10;
		map.add(key, value);
		map.update(key,value);
		System.out.println(map.get(key));
		map.delete(key);
		System.out.println(map.get(key));
		
		
	}
}
