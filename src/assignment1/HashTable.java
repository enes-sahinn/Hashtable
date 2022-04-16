package assignment1;

public class HashTable<K,V> {
	private HashEntry<K,V>[] table;
	private HashEntry<K,V> DEFUNCT = new HashEntry<K,V>(null, null); // no need to use it but I wanted to use it.
	private int capacity = 997; // capacity of array
	private int n = 0; // number of entries
	int collisionCount = 0;
	double loadFactor;
	
	public HashTable() {
		table = new HashEntry[capacity];
		for (int i = 0; i < capacity; i++)
            table[i] = null;
	}
	
	public int hashCode(String word) {  // PAF
		int result = 0;
		char ch;
		int prime = 31;
		for (int i = 0; i < word.length(); i++) {
			ch = word.charAt(i);
			int alphabeticalOrder = ch % 32;
			result += alphabeticalOrder * Math.pow(prime, word.length() - i - 1);
		}
		return result;
	}
	
	public int hashValue(int hashCode) { // Compressin funciton of PAF
		return (int) (Math.abs(((11 * hashCode + 17) / 5) % capacity));
	}
	
	public int myHashCode(String word) { // YHF (I used hypotenuse and pow function with ascii values)
		int result = 0;
		char ch;
		int prime = 37;
		for (int i = 0; i < word.length(); i++) {
			ch = word.charAt(i);
			int asciiValue = ch;
			result += asciiValue * Math.hypot((Math.pow(prime, word.length() - i)), prime);
		}
		return result;
	}
	
	public int myHashValue(int myHashCode) { // Compression function of YHF
		return (int) (Math.abs((Math.pow(13 * myHashCode, 31) + 19) / 5 % capacity)); 
	}
	
	private boolean isAvailable(int key) {
		return (table[key] == null || table[key] == DEFUNCT);
	}
	
	private int findPrimeNumber(int capacity) {   // Find prime number less than capacity
		int counter;
		int primeNumber = 0;
		for (int i = capacity - 1; i > 0; i--) {
			counter = 0;
			for (int j = 1; j <= i; j++) {
				if (i % j == 0) {
					counter++;
				}
			}
			if (counter == 2) {
				primeNumber = i;
				break;
			}
		} return primeNumber;
	}

	private int findSlot(int h, HashEntry<K,V> newEntry) { // linear probing and collusion handling part
		int index = h;
		do { 
		    if (isAvailable(index)) {      // Check initial index
				return index;
			}
		    else if (table[index].getValue().equals(newEntry.getValue())) {   // Check equality of values
		    	table[index].count++;
				return -1; 
			}
			else if (table[index].getDIB() < newEntry.getDIB()) {	 // Check DIB value
				do {												// Loop for swapping process
					if (table[index].getDIB() < newEntry.getDIB()) {
						HashEntry<K,V> swappedIndex = table[index];
						table[index] = newEntry;
						newEntry = swappedIndex;	
						h = index - newEntry.getDIB();  
					}		
					index = (index + 1) % capacity;
					newEntry.setDIB(Math.abs(h - index));
					if (isAvailable(index)) {
						table[index] = newEntry;
						n++;
						return -1; // after swapping process, break the loop
					}
				} while (true);		
			}
			index = (index + 1) % capacity;
			newEntry.setDIB(Math.abs(h - index));
		} while (index != h);
		return -1;
	}
	 
	public void put(V value) {
		int key = hashValue(hashCode((String)value));
		if (hashCode((String)value) != 0){
			if (isAvailable(key)) { 
				table[(int)key] = new HashEntry(key, value);
				n++; // Increase the number of entries by one.
			}
			else {
				HashEntry<K,V> newEntry = new HashEntry(key,value);
				int index = findSlot(key, newEntry); // index after find appropriate slot
				if (index != -1) {
					table[index] = newEntry;
					n++;
				}
				if (index != -1 && index != key) {
					collisionCount++;  // Increase the collusion count if index is different from key
				}
			}
			
			if (n > capacity * loadFactor ) {
				resize(findPrimeNumber(2 * capacity));
			}
		}
	}
	
	public HashEntry<K,V> get(V value) {
		int key = hashValue(hashCode((String)value));
		HashEntry<K,V> searchedEntry = new HashEntry(key, value);
		int index = key;
		
		do {
			if (isAvailable(index) || table[index].getDIB() < searchedEntry.getDIB()) {  // Check DIB values
				return null;
			}
			else if (table[index].getValue().equals(value)) {      // Check equality of values
				return table[index];
			}
			index = (index + 1) % capacity;
			searchedEntry.setDIB(Math.abs(key - index));	
		} while (index != key);
		return null;
	}
	
	private void resize(int newCapacity) {  
		HashEntry<K,V>[] array = new HashEntry[capacity];
		for (int i = 0; i < capacity; i++) {             // Transfer all elements to array
			array[i] = table[i];
		}
		capacity = newCapacity;
		table = new HashEntry[capacity];  // Construct a new hash table with new capacity
		n = 0;                            // Reset number of elements
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {      // Transfer non-null entries only
				int savedCount = array[i].getCount();  // Save old word count
				int key = hashValue(hashCode((String)array[i].getValue()));
				put(array[i].getValue());
				get(array[i].getValue()).setCount(savedCount);		
			}
		}
	}

	public HashEntry<K, V>[] getTable() {
		return table;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getN() {
		return n;
	}

	public void setTable(HashEntry<K, V>[] table) {
		this.table = table;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getCollisionCount() {
		return collisionCount;
	}

	public void setCollisionCount(int collisionCount) {
		this.collisionCount = collisionCount;
	}

	public double getLoadFactor() {
		return loadFactor;
	}

	public void setLoadFactor(double loadFactor) {
		this.loadFactor = loadFactor;
	}
}
