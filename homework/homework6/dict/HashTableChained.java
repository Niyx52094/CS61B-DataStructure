/* HashTableChained.java */

package dict;

import java.util.*;
import list.*;
/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	private EntryList[] bucket;
	private double loadFactor;
	private int size;
	private int capacity;
	private int collisions;
	
	public int collisions() {
		return collisions;
	}
  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
	  loadFactor=0.5;
	  capacity=(int)(sizeEstimate/loadFactor);
	  bucket=new EntryList[capacity];
	  size=0;
	  collisions=0;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    this(50);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
	  int p=109345121;
	  Random rand =new Random();
	  int a=rand.nextInt(p-1);
	  int b=rand.nextInt(p-1);
	  int compressor=((a*code+b)%p)%capacity;
	  if(compressor<0) {
		  compressor+=capacity;
	  }
    return compressor;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return size==0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
	  Entry newEntry=new Entry();
	  newEntry.key=key;
	  newEntry.value=value;
	  int comp=compFunction(key.hashCode());
	  if(bucket[comp]==null) {
		  EntryList newList=new EntryList();
		  bucket[comp]=newList;
	  }else {
		  collisions++;
	  }
	  bucket[comp].insertBack(newEntry);
	  size++;
    // Replace the following line with your solution.
    return newEntry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
	  int comp=compFunction(key.hashCode());
	  if(bucket[comp]==null) {
		  return null;
	  }else {
		  return bucket[comp].front();
	  }
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
	  int comp=compFunction(key.hashCode());
	  if(bucket[comp]==null) {
		  return null;
	  }else {
		  EntryListNode e=bucket[comp].front();
		  try {
			  bucket[comp].front().remove(); 
			  size--;
		  }catch(InvalidNodeException ex) {
			  System.err.println("can no remove node on an invalid node!!");
		  }
		  return e;
	  }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
	  int i=0;
	  while(size!=0) {
		  if(bucket[i]==null) {
			  i++;
			  continue;
		  }else {
			  while(bucket[i].front()!=null) {
				  try {
				  bucket[i].front().remove();
				  size--;
				  }catch(InvalidNodeException ex){
					  System.err.println("can no remove node on an invalid node!!");
				  }
			  }
			  bucket[i]=null;
			  i++;
		  }
	  }
		  
  }
  public String toString() {
	  String re="";
	  for(int i=0;i<capacity;i++) {
		  if(bucket[i]==null) {
			  continue;
		  }else {
			  re+="table["+i+"]->"+bucket[i].toString()+";\n";
		  }
	  }
	  return re;
  }
  

}
