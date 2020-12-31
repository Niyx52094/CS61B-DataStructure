/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
	private List mySet;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    mySet=new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return mySet.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
	  if(cardinality()==0) {
		  mySet.insertFront(c);
	  }else {
		  ListNode mySetElement=mySet.front();
		  try {
			  while(mySetElement.isValidNode()) {
				  if(c.compareTo(mySetElement.item())>0) {
					  mySetElement=mySetElement.next();
					  if(!mySetElement.isValidNode()) {
						  mySet.insertBack(c);
					  }
				  }else if(c.compareTo(mySetElement.item())<0) {
					  mySetElement.insertBefore(c);
					  break;
				  }else {
					  break;
				  }

			  }
		  }	catch(InvalidNodeException e) {
			  System.err.print("can't insert a invalidNode");
		  	}
	  }
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
	 ListNode Spointer=s.mySet.front();
	 ListNode thisPointer=this.mySet.front();
	 try {
	  while(Spointer.isValidNode()) {
		  while(thisPointer.isValidNode()) {
			  if(((Comparable)Spointer.item()).compareTo(thisPointer.item())>0) {
				  thisPointer=thisPointer.next();
			  }else if(((Comparable)Spointer.item()).compareTo(thisPointer.item())<0) {
				  thisPointer.insertBefore(Spointer.item());
				  break;
			  }else {
				  break;
			  }
		  }
		  if(!thisPointer.isValidNode()) {
			  this.mySet.insertBack(Spointer.item());
		  }
		  Spointer=Spointer.next();
	  }
	 }catch(InvalidNodeException e) {
		 System.err.println();
	 }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
	  ListNode Spointer=s.mySet.front();
		 ListNode thisPointer=this.mySet.front();
		 try {
		  while(thisPointer.isValidNode()&&Spointer.isValidNode()) {
				  if(((Comparable)Spointer.item()).compareTo(thisPointer.item())<0) {
					  Spointer=Spointer.next();
				  }else if(((Comparable)Spointer.item()).compareTo(thisPointer.item())>0) {
					  if(thisPointer.next().isValidNode()) {
						  thisPointer=thisPointer.next();
						  thisPointer.prev().remove();
				  		}else {
				  			thisPointer.remove();
				  		}
				  }else {
					  Spointer=Spointer.next();
					  thisPointer=thisPointer.next();
				  }
		  }
			while(thisPointer.isValidNode()) {
				if(thisPointer.prev().isValidNode()) {
				  thisPointer=thisPointer.prev();
				  thisPointer.next().remove();
				  thisPointer=thisPointer.next();
				}else {
					if(thisPointer.next().isValidNode()) {
						  thisPointer=thisPointer.next();
						  thisPointer.prev().remove();
					}else {
						thisPointer.remove();
					}
				}
			}
			  
		 }catch(InvalidNodeException e) {
			 System.err.println();
		 }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
	  String result = "{  ";
	    ListNode current = mySet.front();
	    while (current.isValidNode()) {
		    try {
	      result = result + current.item() + "  ";
	      current = current.next();
		    }
		    catch(InvalidNodeException e) {
		    		System.err.println("Invalid Node!!");
	    }
	    }
	    return result + "}";
    
  }

  /*public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
  }
}
*/
  public static void main(String[] argv) {
	    System.out.println("Testing insert()");
	    Set s = new Set();
	    s.insert(new Integer(3));
	    s.insert(new Integer(4));
	    s.insert(new Integer(3));
	    System.out.println("Set s should be { 3 4 }: " + s);

	    Set s2 = new Set();
	    s2.insert(new Integer(4));
	    s2.insert(new Integer(5));
	    s2.insert(new Integer(5));
	    System.out.println("Set s2 should be { 4 5 }: " + s2);

	    Set s3 = new Set();
	    s3.insert(new Integer(5));
	    s3.insert(new Integer(3));
	    s3.insert(new Integer(8));
	    System.out.println("Set s3 should be { 3 5 8 }: " + s3);

	    System.out.println();
	    System.out.println("Tesing union()");
	    s.union(s2);
	    System.out.println("After s.union(s2), s should be { 3 4 5 }: " + s);
	    s2.union(s3);
	    System.out.println("After s2.union(s3), s2 should be { 3 4 5 8 }: " + s2);
	    Set s4 = new Set();
	    System.out.println("Empty set s4 = " + s4);
	    s.union(s4);
	    System.out.println("After s.union(s4), s should be { 3 4 5 }: " + s);
	    s4.union(s);
	    System.out.println("After s4.union(s), s4 should be { 3 4 5 }: " + s4);

	    System.out.println();
	    System.out.println("Tesing intersect()");
	    Set s5 = new Set();
	    Set s6 = new Set();
	    s6.insert(new Integer(1));
	    s5.intersect(s6);
	    System.out.println("{}.intersect({1}) should be { }: " + s5);
	    s6.intersect(s5);
	    System.out.println("{1}.intersect({}) should be { }: " + s6);
	    s6.insert(new Integer(1));
	    Set s7 = new Set();
	    s7.insert(new Integer(1));
	    s7.insert(new Integer(2));
	    s6.intersect(s7);
	    System.out.println("{1}.intersect({1 2}) should be { 1 }: " + s6);
	    s6.insert(new Integer(2));
	    s6.insert(new Integer(3));
	    s6.intersect(s7);
	    System.out.println("{1 2 3}.intersect({1 2}) should be { 1 2 }: " + s6);
	    s6.insert(new Integer(3));
	    s6.insert(new Integer(5));
	    s7.insert(new Integer(4));
	    s7.insert(new Integer(7));
	    s7.intersect(s6);
	    System.out.println("{1 2 4 7}.intersect({1 2 3 5}) should be { 1 2 }: " + s7);

	    System.out.println();
	    System.out.println("Tesing cardinality()");
	    System.out.println("s.cardinality() should be 3: " + s.cardinality());
	    System.out.println("s4.cardinality() should be 3: " + s4.cardinality());
	    System.out.println("s5.cardinality() should be 0: " + s5.cardinality());
	    System.out.println("s6.cardinality() should be 4: " + s6.cardinality());
	    System.out.println("s7.cardinality() should be 2: " + s7.cardinality());
	  }
}