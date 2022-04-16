package assignment1;

public class HashEntry<K,V> {
	private K key;
	private V value;
	int count;
	int DIB;
	
	public HashEntry(K key, V value) {
		this.key = key;
		this.value = value;
		count = 1;
		DIB = 0;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public int getCount() {
		return count;
	}

	public int getDIB() {
		return DIB;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setDIB(int dIB) {
		DIB = dIB;
	}	
}
