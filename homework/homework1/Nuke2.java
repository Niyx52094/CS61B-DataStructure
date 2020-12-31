import java.io.*;
public class Nuke2  {
	public static void main(String[] args) throws Exception{
	BufferedReader ktbd=new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Please input a String with at least two characters:");
	String line=ktbd.readLine();
	String omittedLine=removeSecondChar(line);
	System.out.println(omittedLine);
	}
	
	public static String removeSecondChar(String str) {
		char[] cha=new char[str.length()];
		for(int i=0;i<str.length();i++) {
			cha[i]=str.charAt(i);
		}
		String result=""+cha[0];
		for(int j=2;j<str.length();j++) {
			result+=cha[j];
		}
		return result;
	}
}
