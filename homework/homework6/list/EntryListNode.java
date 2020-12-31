package list;
import dict.Entry;
public class EntryListNode extends Entry{
	
	protected EntryListNode next;
	protected EntryList myList;
	
	EntryListNode(){
		this(null,null,null,null);
	}
	
	EntryListNode(Object key,Object value,EntryList l, EntryListNode n){
		this.key=key;
		this.value=value;
		myList=l;
		next=n;
	}
	
	public boolean isValidNode() {
		return myList!=null;
	}

	  /**
	   *  next() returns the node following this node.  If this node is invalid,
	   *  throws an exception.
	   *
	   *  @return the node following this node.
	   *  @exception InvalidNodeException if this node is not valid.
	   *
	   *  Performance:  runs in O(1) time.
	   */
	  public EntryListNode next() throws InvalidNodeException {
	    if (!isValidNode()) {
	      throw new InvalidNodeException("next() called on invalid node");
	    }
	    if (next == null) {
	      // Create an invalid node.
	    	EntryListNode node = myList.newNode(null, null);
	      node.myList = null;
	      return node;
	    } else {
	      return next;
	    }
	  }

	  /**
	   *  prev() returns the node preceding this node.  If this node is invalid,
	   *  throws an exception.
	   *
	   *  @param node the node whose predecessor is sought.
	   *  @return the node preceding this node.
	   *  @exception InvalidNodeException if this node is not valid.
	   *
	   *  Performance:  runs in O(this.size) time.
	   */
	  public EntryListNode prev() throws InvalidNodeException {
	    if (!isValidNode()) {
	      throw new InvalidNodeException("prev() called on invalid node");
	    }
	    EntryListNode prev = myList.head;
	    if (prev.key() == this.key()&&prev.value()==this.value()) {
	      // Create an invalid node.
	      prev = ((EntryList) myList).newNode(null, null);
	      prev.myList = null;
	    } else {
	      while (prev.next.key() != this.key()&&prev.next.value() != this.value()) {
	        prev = prev.next;
	      }
	    }
	    return prev;
	  }

	  /**
	   *  insertAfter() inserts an item immediately following this node.  If this
	   *  node is invalid, throws an exception.
	   *
	   *  @param item the item to be inserted.
	   *  @exception InvalidNodeException if this node is not valid.
	   *
	   *  Performance:  runs in O(1) time.
	   */
	  public void insertAfter(Entry item) throws InvalidNodeException {
	    if (!isValidNode()) {
	      throw new InvalidNodeException("insertAfter() called on invalid node");
	    }
	    EntryListNode newNode = ((EntryList) myList).newNode(item, next);
	    if (next == null) {
	      ((EntryList) myList).tail = newNode;
	    }
	    next = newNode;
	    myList.size++;
	  }

	  /**
	   *  insertBefore() inserts an item immediately preceding this node.  If this
	   *  node is invalid, throws an exception.
	   *
	   *  @param item the item to be inserted.
	   *  @exception InvalidNodeException if this node is not valid.
	   *
	   *  Performance:  runs in O(this.size) time.
	   */
	  public void insertBefore(Entry item) throws InvalidNodeException {
	    if (!isValidNode()) {
	      throw new InvalidNodeException("insertBefore() called on invalid node");
	    }
	    EntryListNode newNode = ((EntryList) myList).newNode(item, this);
	    if (this.key()==myList.head.key()&&this.value()==myList.head.value()) {
	      ((EntryList) myList).head = newNode;
	    } else {
	    	EntryListNode prev = (EntryListNode) prev();
	      prev.next = newNode;
	    }
	    myList.size++;
	  }

	  /**
	   *  remove() removes this node from its SList.  If this node is invalid,
	   *  throws an exception.
	   *
	   *  @exception InvalidNodeException if this node is not valid.
	   *
	   *  Performance:  runs in O(this.size) time.
	   */
	  public void remove() throws InvalidNodeException {
	    if (!isValidNode()) {
	      throw new InvalidNodeException("remove() called on invalid node");
	    }
	    if (this.key()==myList.head.key()&&this.value()==myList.head.value() ) {
	      ((EntryList) myList).head = next;
	      if (next == null) {
	        ((EntryList) myList).tail = null;
	      }
	    } else {
	    	EntryListNode prev = (EntryListNode) prev();
	      prev.next = next;
	      if (next == null) {
	        ((EntryList) myList).tail = prev;
	      }
	    }
	    myList.size--;

	    // Make this node an invalid node, so it cannot be used to corrupt myList.
	    myList = null;
	    // Set other reference to null to improve garbage collection.
	    next = null;
	  }
	  public String toString() {
		  String re="("+this.key()+","+this.value()+")";
		  return re;
	  }
	
}
