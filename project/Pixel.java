
public class Pixel {
	public short red;
	public short green;
	public short blue;
	public Pixel() {
		red=0;
		green=0;
		blue=0;
	}
	public Pixel(short red, short green, short blue) {
		this.red=red;
		this.green=green;
		this.blue=blue;
	}
	public Pixel(Pixel pixel) {
		red=pixel.red;
		green=pixel.green;
		blue=pixel.blue;
	}
	/*public void setRGBvalue(short red,short green, short blue) {
		this.red=red;
		this.green=green;
		this.blue=blue;
	}
	public void setRGBvalue(short x) {
		red=x;
		blue=x;
		green=x;
	}*/
	public void setRGBvalue(Pixel pixel) {
		red=pixel.red;
		green=pixel.green;
		blue=pixel.blue;
	}
	public short getRed() {
		return this.red;
	}
	public short getBlue() {
		return this.blue;
	}
	public short getGreen() {
		return this.green;
	}
	public Pixel changeValue(int i) {
		
		short Red=(short)(i*red);
		short Green=(short)(i*green);
		short Blue=(short)(i*blue);
		return (new Pixel(Red,Green,Blue));
	}

	public Pixel addValue(Pixel pixel,int i) {
		Pixel pixel2=pixel.changeValue(i);
		red+=pixel2.red;
		green+=pixel2.green;
		blue+=pixel2.blue;
		Pixel pixel3=new Pixel(red,green,blue);
		return pixel3;
	}
	public Pixel addPixelValue(Pixel pixel,int i) {
		Pixel pixel2=pixel.changeValue(i);
		red+=pixel2.red;
		green+=pixel2.green;
		blue+=pixel2.blue;
		Pixel pixel3=new Pixel(red,green,blue);
		return pixel3;
	}
	public String toString() {
		String result="";
		result+=red+","+green+","+blue;
		return result;
	}
	public boolean equals(Pixel item) {
		return red==item.red&&green==item.green&&blue==item.blue;
	}
	
	public static void main(String[] args) {
		Pixel p6=new Pixel((short)1,(short)2,(short)3);
		System.out.println("RGB value of the pixel is(should be red 1,green 2,blue 3):"+p6.red+" "+p6.green+" "+p6.blue);
		Pixel pi=p6.changeValue(2);
		System.out.println("RGB value of the pixel is(should be red 2,green 4,blue 6):"+pi.red+" "+pi.green+" "+pi.blue);
		Pixel p2=new Pixel((short)2,(short)4,(short)5);
		System.out.println("P2 RGB value of the pixel is(should be red 2,green 4,blue 5):"+p2.red+" "+p2.green+" "+p2.blue);
		Pixel p4=new Pixel((short)4,(short)2,(short)5);
		Pixel p5=new Pixel(pi);
		Pixel p3=p5.addPixelValue(p2, 3).addPixelValue(p4, 2);
		System.out.println("P3 RGB value of the pixel is(should be red 8,green 16,blue 21):"+p3.red+" "+p3.green+" "+p3.blue);
		System.out.println("RGB value of the pixel is(should be red 2,green 4,blue 6):"+pi.red+" "+pi.green+" "+pi.blue);
		System.out.println("P2 RGB value of the pixel is(should be red 2,green 4,blue 5):"+p2.red+" "+p2.green+" "+p2.blue);
	}
}
