package lab5;

public class Y extends X{
	protected Object y;
	public Y() {
		y=null;
	}
	public Y(Object obj) {
		y=obj;
	}
	public static void main(String[] args) {
		X[] xa=new X[10];
		Y[] ya=new Y[10];
		xa=ya;
		System.out.println("it works!");
	}
	
}
