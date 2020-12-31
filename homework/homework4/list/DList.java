/* DList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    head= newNode(Integer.MIN_VALUE,null,null);
    head.next=head;
    head.prev=head;
    size=0;
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
		  head.next=newNode(item,head,head.next);
		  head.next.next.prev=head.next;
		  size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
    head.prev=newNode(item,head.prev,head);
    head.prev.prev.next=head.prev;
    size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
    if(size==0) {
    	return null;
    }else {
    	return head.next;
    }
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
    if(size==0) {
    	return null;
    }else {
    	return head.prev;
    }
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    if(node==null||node.next==head) {
    	return null;
    }else {
    	return node.next;
    }
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
	  if(node==null||node.prev==head) {
	    	return null;
	    }else {
	    	return node.prev;
	    }
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
    if(node!=null) {
    	node.next=newNode(item,node,node.next);
    	node.next.next.prev=node.next;
    	size++;
    }
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
	  if(node!=null) {
	    	node.prev=newNode(item,node.prev,node);
	    	node.prev.prev.next=node.prev;
	    	size++;
	    }
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
	  if(node!=null) {
	    	node.prev.next=node.next;
	    	node.next.prev=node.prev;
	    	size--;
	    }
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }
 public static void main(String[] args) {
	  DList d1=new DList();
	  System.out.println("return front and back of the list d1 should be both null: "+d1.front()+","+d1.back());
	  d1.insertFront(2);
	  d1.insertBack(4);
	  System.out.println("return front and back of the list d1 should be 2 and 4 respectively: "+d1.front()+","+d1.back());
	  d1.insertAfter(3, d1.front());
	  System.out.println("return  list d1 should be 2 3 4 respectively: "+d1);
	  d1.insertBefore(5, d1.back());
	  System.out.println("return  list d1 should be 2 3 5 4 respectively: "+d1);
	  System.out.println("return  prev in d1.front should be null: "+d1.prev(d1.front()));
	  System.out.println("return  next in d1.back should be null: "+d1.next(d1.back()));
	  d1.remove(d1.front());
	  System.out.println("return  list d1 should be 3 5 4 respectively: "+d1);
	  d1.remove(d1.back());
	  System.out.println("return  list d1 should be 3 5 respectively: "+d1);
	  d1.insertBack(7);
	  d1.remove(d1.front().next);
	  System.out.println("return  list d1 should be 3 7 respectively: "+d1);
 }
}
