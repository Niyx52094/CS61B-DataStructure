
public class Wall {
		int horiz;
		int vert;
		String statement;
public Wall(int horiz, int vert,String statement) {
		this.horiz=horiz;
		this.vert=vert;
		this.statement=statement;
	}

public String toString() {
		String result="";
		result+="("+horiz+","+vert+","+statement+")";
		return result;
}
}
