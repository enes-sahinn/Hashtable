# Hashtable

## Table of Contents

* [About the Project](#about-the-project)
* [Scenario of the Project](#scenario-of-the-project)
* [Information About Algorithms](#information-about-algorithms)
* [Built With](#built-with)
* [Contact](#contact)

## About The Project
This project aims to index words of a document named as ‘story.txt’ to hash table according to rules given below. 

## Hash Function
I implemented two different hash functions including polynomial accumulation function and my own hash function. 
In my hash function, I used hypotenuse and pow function with ascii values.

## Collision Handling Approach
You are expected to implement a collision resolution technique based on open addressing. The insertion algorithm is as follows:
* Calculate the hash value and initial index of the entry to be inserted.
* Then search the position linearly.
* While searching, the distance from initial index is kept which is called DIB (Distance from Initial Bucket).
* If we can find the empty bucket, we can insert the new entry with the DIB value in here.
* If we encounter an entry which has less DIB than the candidate entry, swap them.

## Performance Monitoring

![alt text](https://github.com/enes-sahinn/Calculator/blob/master/game_screen.png)

The table shows that increasing of the load factor decreases the number of collisions we achieve. 
But increases indexing time at the same time. Decreasing of the load factor increases the number of collisions we achieve. 
But decreases indexing time at the same time.

## Built With
* Java

## Contact
Mail: enessah200@gmail.com\
LinkedIn: [linkedin.com/in/enes-sahinn](https://www.linkedin.com/in/enes-sahinn/)\
Project Link: [Hashtable](https://github.com/enes-sahinn/Hashtable)