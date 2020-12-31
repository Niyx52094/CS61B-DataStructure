/* SList.java */

/**
 *  The SList class is a singly-linked implementation of the linked list
 *  abstraction.  SLists are mutable data structures, which can grow at either
 *  end.
 *
 *  @author Kathy Yelick and Jonathan Shewchuk
 **/

public class SListRun {

  private SListNode head;
  private int[] count;
  private int size;

  /**
   *  SList() constructs an empty list.
   **/

  public SListRun() {
    size = 0;
    count=null;
    head = null;
  }
  /**to create a SList of all pixels in an image.
   * 
   * @param pix means a Piximage contains(width*height)pixels.
   */
public SListRun(PixImage pix) {
	this();
	
		for(int y=pix.getHeight()-1;y>-1;y--) {
			for(int x=pix.getWidth()-1;x>-1;x--) {
			SListNode point= new SListNode(pix.getRed(x, y),pix.getGreen(x, y),pix.getBlue(x, y));
			if(head==null) {
				head=point;
			}else {
				point.next=head;
				head=point;
			}
			size++;
		}
	}
}
  /**
   *  isEmpty() indicates whether the list is empty.
   *  @return true if the list is empty, false otherwise.
   **/

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   *  length() returns the length of this list.
   *  @return the length of this list.
   **/

  public int length() {
    return size;
  }
  
  /**helper method: to store runLength in a int array.
   * @param i means the position in the count.
   * @return the runLength.
   */
  public int runLength(int i) {
	  return count[i];
  }
  /**
   *  nth() returns the item at the specified position.  If position < 1 or
   *  position > this.length(), null is returned.  Otherwise, the item at
   *  position "position" is returned.  The list does not change.
   *  @param position the desired position, from 1 to length(), in the list.
   *  @return the item at the given position in the list.
   **/

  public Pixel nth(int position) {
    SListNode currentNode;

    if ((position < 1) || (head == null)) {
      return null;
    } else {
      currentNode = head;
      while (position > 1) {
        currentNode = currentNode.next;
        if (currentNode == null) {
          return null;
        }
        position--;
      }
      return currentNode.item;
    }
  }

  /**
   *  squish() takes this list and, wherever two or more consecutive items are
   *  equals(), it removes duplicate nodes so that only one consecutive copy
   *  remains.  Hence, no two consecutive items in this list are equals() upon
   *  completion of the procedure.
   *
   *  After squish() executes, the list may well be shorter than when squish()
   *  began.  No extra items are added to make up for those removed.
   *
   *  For example, if the input list is [ 0 0 0 0 1 1 0 0 0 3 3 3 1 1 0 ], the
   *  output list is [ 0 1 0 3 1 0 ].
   *
   *  IMPORTANT:  Be sure you use the equals() method, and not the "=="
   *  operator, to compare items.
   **/

  public void squish() {
	 SListNode s=head;
	 SListNode t=head;
	 int i=0;
	 if(head!=null) {
		 while(s.next!=null) {
			 if(count==null) {
				 count=new int[this.length()];
			 }
			 if(count[i]==0) {
				 count[i]=1;
			 }
			 if(s.item.equals(s.next.item)) {
				 size--;
				 count[i]++;
			 }else {
				 t.next=s.next;
				 t=t.next;
				 i++;
				 count[i]=1;
			 }
			 s=s.next;
		 }
		 t.next=null;
	 }
  }

 

  /**
   *  toString() converts the list to a String.
   *  @return a String representation of the list.
   **/

  public String toString() {
    int i;
    Pixel obj;
    String result = "[  ";

    SListNode cur = head;

    while (cur != null) {
      obj = cur.item;
      result = result + obj.toString() + "  ";
      cur = cur.next;
    }
    result = result + "]";
    return result;
  }
}