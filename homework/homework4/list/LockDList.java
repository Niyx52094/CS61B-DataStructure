package list;

public class LockDList extends DList{
	
	/** this method aims to lock the node .
	 * 
	 * @param node is the node that was chosen to be locked,
	 * and can not to be removed.
	 */
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item,prev,next);
	}
	public void lockNode(DListNode node) {
		((LockDListNode)node).setLockState(true);
		}
	 public void remove(DListNode node) {
		 if(((LockDListNode)node).getLockState()!=true) {
		 super.remove(node); 
		 }
	 }
	public static void main(String[] args) {
		LockDList ld1=new LockDList();
		ld1.insertFront(2);
		 System.out.println("return  list d1 should be 2 respectively: "+ld1);
		 ld1.lockNode(ld1.front());
		 ld1.insertFront(1);
		 System.out.println("return  list d1 should be 1 2 respectively: "+ld1);
		 ld1.remove(ld1.back());
		 System.out.println("remove can not work,  list d1 should still be 1 2 : "+ld1);
		 ld1.remove(ld1.front());
		 System.out.println("return  list d1 should be 2 respectively: "+ld1);
	}
}
