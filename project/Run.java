
public class Run {
	public Pixel value;
	public int count;
	public Run next;
	public Run prev;
	public Run() {
		value=new Pixel((short)0,(short)0,(short)0);
		count=1;
		prev=null;
		next=null;
	}
	public Run(int red, int green, int blue,int length) {
		value=new Pixel((short)red,(short)green,(short)blue);
		this.count=length;
		prev=null;
		next=null;
	}
	
	public void addCount() {
		count+=1;
	}
	public void addCount(Run run) {
		count+=run.count;
	}
	public void addCount(int i) {
		count+=i;
	}
	public String toString() {
		String result="";
		result+=value.red+","+value.green+","+value.blue+"/"+count;
		return result;
	}
}
