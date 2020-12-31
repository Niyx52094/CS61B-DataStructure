/* SListNode.java */

/**
 *  SListNode is a class used internally by the SList class.  An SList object
 *  is a singly-linked list, and an SListNode is a node of a singly-linked
 *  list.  Each SListNode has two references:  one to an object, and one to
 *  the next node in the list.
 *
 *  @author Kathy Yelick and Jonathan Shewchuk
 */

class SListNode {
  Pixel item;
  SListNode next;


  /**
   *  SListNode() (with two parameters) constructs a list node referencing the
   *  item "obj", whose next list node is to be "next".
   */

  SListNode(Pixel obj, SListNode next) {
    item = obj;
    this.next = next;
  }

  /**
   *  SListNode() (with one parameter) constructs a list node referencing the
   *  item "obj".
   */

  SListNode(Pixel obj) {
    this(obj, null);
  }
  SListNode(short red,short green, short blue){
	 item=new Pixel(red,green,blue);
	 next=null;
  }
  
  public static void main(String[] args) {
	  SListNode node1=new SListNode((short)1,(short)2,(short)3);
	System.out.println("node1 should be 1 2 3: "+node1.item.red+node1.item.green+node1.item.blue);
  }
}