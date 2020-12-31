package list;

public class LockDListNode extends DListNode{
	private boolean lockState;
	LockDListNode(Object i, DListNode p, DListNode n){
		super(i, p, n);
		lockState=false;
	}
	public void setLockState(boolean f) {
		lockState=f;
	}
	public boolean getLockState() {
		return lockState;
	}
}
