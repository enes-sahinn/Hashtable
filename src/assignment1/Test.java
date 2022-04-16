package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		HashTable<Integer,String> ht = new HashTable<>();
		
		Scanner scn3 = new Scanner(System.in);
		System.out.print("Please enter the load factor between 0.0 and 1.0: ");
		String load_Factor = "";
		Double loadFactor = 0.0;
		while (true) {
			try {
				load_Factor = scn3.next();
				loadFactor = Double.valueOf(load_Factor);
				if (loadFactor < 0.1 || loadFactor > 0.9) { // To keep the loadfactor between 0.0 and 1.0
					System.out.print("Please enter double value between 0.0 and 1.0! -> ");
					continue;
				}else {
					break;       // if no exceptions breaks out of loop
				}
			} catch (Exception e) {  // if an exception appears prints message below
				System.out.print("Please enter double value between 0.0 and 1.0! " + e.getMessage() + "-> ");
				continue;    // continues to loop if exception is found
			}
		}
		ht.setLoadFactor(loadFactor);
		
		long startTime;
		long endTime;
		long estimatedTime;
		double seconds;
		try {
			File file = new File("story.txt");
			Scanner scn = new Scanner(file); 
			startTime = System.nanoTime();
			while (scn.hasNextLine()) {
				String[] currentLine = scn.nextLine().split(" ");
				for (int i = 0; i < currentLine.length; i++) {							
					ht.put(currentLine[i].toLowerCase().replace('ý', 'i'));
				}
			} scn.close();
			endTime = System.nanoTime();
			estimatedTime = endTime - startTime;
			seconds = (double)estimatedTime / 1000000000;  // convert nanosecond to second
			//System.out.println("total indexing time is: " + seconds);
        } catch (FileNotFoundException e) {
        	System.out.println("File not found! " + e.getMessage());
        } 
		/*
		double averageTime = 0;
		double totalTime = 0;
		double maxTime = 0;
		double minTime = 999;
		int wordCount = 0;		
		try {
			File file2 = new File("search.txt");
			Scanner scn2 = new Scanner(file2);
			while (scn2.hasNextLine()) {
				String wordToSearch = scn2.nextLine();
				startTime = System.nanoTime();
				ht.get(wordToSearch);
				endTime = System.nanoTime();
				estimatedTime = endTime - startTime;
				seconds = (double)estimatedTime / 1000000000;
				if (seconds > maxTime) {
					maxTime = seconds;
				}
				if (seconds < minTime) {
					minTime = seconds;
				}
				totalTime += seconds;
				wordCount++;
			} scn2.close();
        } catch (FileNotFoundException e) {
        	System.out.println("File not found! " + e.getMessage());
        }	
		averageTime = totalTime / wordCount;
		System.out.println("The min search time is: " + minTime);
		System.out.println("The max search time is: " + maxTime);
		System.out.println("The average search time is: " + averageTime);
		*/
		scn3 = new Scanner(System.in);
		System.out.println("---> Number of Entries: " + ht.getN());
		System.out.println("---> Capacity of Array: " + ht.getCapacity());
		System.out.println("---> Collusion Count  : " + ht.getCollisionCount() + "\n");
		
		while (true) {
			System.out.print("Search: ");
			String wordToSearched = scn3.next().toLowerCase().replace('ý', 'i');
			int key = ht.hashCode(wordToSearched);
			
			if (ht.get(wordToSearched) != null) {
				System.out.println("Key: " + key);
				System.out.println("Count: " + ht.get(wordToSearched).getCount());
				System.out.println("Index: " + (ht.hashValue(key) + ht.get(wordToSearched).getDIB()));
			} 
			else {
				System.out.println("NOT FOUND");
			}
			System.out.println();
		}	
	}
}
