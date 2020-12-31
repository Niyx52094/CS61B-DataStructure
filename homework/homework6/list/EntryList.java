package list;
import dict.Entry;
public class EntryList{
	/**
	   *  (inherited)  size is the number of items in the list.
	   *  head references the first node.
	   *  tail references the last node.
	   **/

	  protected EntryListNode head;
	  protected EntryListNode tail;
	  protected int size;

	  /**
	   *  newNode() calls the EntryListNode constructor.  Use this method to allocate
	   *  new entryListNodes rather than calling the EntryListNode constructor directly.
	   *  That way, only this method need be overridden if a subclass of EntryList
	   *  wants to use a different kind of node.
	   *
	   *  @param item the item to store in the node.
	   *  @param next the node following this node.
	   **/
	  protected EntryListNode newNode(Entry item, EntryListNode next) {
	    return new EntryListNode(item.key(),item.value(),this, next);
	  }

	  /**
	   *  SList() constructs for an empty SList.
	   **/
	  public EntryList() {
	    head = null;
	    tail = null;
	    size = 0;
	  }

	  /**
	   *  insertFront() inserts an item at the front of this SList.
	   *
	   *  @param item is the item to be inserted.
	   *
	   *  Performance:  runs in O(1) time.
	   **/
	  public void insertFront(Entry item) {
	    head = newNode(item,head);
	    if (size == 0) {
	      tail = head;
	    }
	    size++;
	  }

	  /**
	   *  insertBack() inserts an item at the back of this SList.
	   *
	   *  @param item is the item to be inserted.
	   *
	   *  Performance:  runs in O(1) time.
	   **/
	  public void insertBack(Entry item) {
	    if (head == null) {
	      head = newNode(item, null);
	      tail = head;
	    } else {
	      tail.next = newNode(item, null);
	      tail = tail.next;
	    }
	    size++;
	  }

	  /**
	   *  front() returns the node at the front of this SList.  If the SList is
	   *  empty, return an "invalid" node--a node with the property that any
	   *  attempt to use it will cause an exception.
	   *
	   *  @return a ListNode at the front of this SList.
	   *
	   *  Performance:  runs in O(1) time.
	   */
	  public EntryListNode front() {
	    if (head == null) {
	      // Create an invalid node.
	      EntryListNode node = newNode(null, null);
	      node.myList = null;
	      return node;
	    } else {
	      return head;
	    }
	  }

	  /**
	   *  back() returns the node at the back of this SList.  If the SList is
	   *  empty, return an "invalid" node--a node with the property that any
	   *  attempt to use it will cause an exception.
	   *
	   *  @return a ListNode at the back of this SList.
	   *
	   *  Performance:  runs in O(1) time.
	   */
	  public EntryListNode back() {
	    if (tail == null) {
	      // Create an invalid node.
	      EntryListNode node = newNode(null, null);
	      node.myList = null;
	      return node;
	    } else {
	      return tail;
	    }
	  }

	  /**
	   *  toString() returns a String representation of this SList.
	   *
	   *  @return a String representation of this SList.
	   *
	   *  Performance:  runs in O(n) time, where n is the length of the list.
	   */
	  public String toString() {
	    String result = "[  ";
	    EntryListNode current = head;
	    while (current != null) {
	      result = result + "("+current.key() + ","+current.value()+") ";
	      current = current.next;
	    }
	    return result + "]";
	  }
}
