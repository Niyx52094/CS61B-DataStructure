/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.awt.Image;
import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
	private long length;
	private int width;
	private int height;
	private Run head;
	private Run tail;

	
	public RunLengthEncoding() {
		length=0;
		width=0;
		height=0;
		head=null;
		tail=null;
	}
  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {
	  this();
	  Run run=new Run(0,0,0,width*height);
	  head=run;
	  tail=run;
	  this.width=width;
	  this.height=width;
	  length++;
  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
	  this();
	  this.width=width;
	  this.height=width;
	  for(int i=0;i<red.length;i++) {
		  Run run=new Run(red[i],green[i],blue[i],runLengths[i]);
		  if(head==null) {
			  head=run;
			  tail=run;
		  }else {
			  tail.next=run;
			  run.prev=tail;
			  tail=run; 
		  }
		  length++;
	  }
  }

  
  
  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    return width;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    return height;
  }
  public long getLength() {
	  return length;
  }
  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this RunLengthEncoding.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
    RunIterator i=new RunIterator(this.head);
    return i;
    // You'll want to construct a new RunIterator, but first you'll need to
    // write a constructor in the RunIterator class.
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
    // Replace the following line with your solution.
	  PixImage image=new PixImage(width,height);
	  RunIterator i=this.iterator();
	  int[] runArray=null;
	 for(int y=0;y<height;y++) {
		 for(int x=0;x<width;) {
			 if(runArray==null||runArray[0]==0) {
					 runArray= i.next();
			}
					 while(runArray[0]>0) {
						 image.setPixel(x, y, (short)runArray[1], (short)runArray[2], (short)runArray[3]);
						 runArray[0]--;
						 x++;
						 if(x==width) {
							 break;
						 }
					 }

		 }
	 }
	 
    return image;
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
    // Replace the following line with your solution.
	  String result="[(";
	  RunIterator i=this.iterator();
	  int[] runArray=null;
	  int x=this.getWidth();
	  for(int j=0;j<this.getLength();j++) {
			 runArray=i.next();
				  if(j%x!=2) {
					  if(j!=this.getLength()-1) {
						  result+=runArray[1]+","+runArray[2]+","+runArray[3]+")"+runArray[0]+"];[(";
					  }
					  else {
						  result+=runArray[1]+","+runArray[2]+","+runArray[3]+")"+runArray[0]+"]\n"; 
					  }
				  }else {
					  if(j!=this.getLength()-1) {
						  result+=runArray[1]+","+runArray[2]+","+runArray[3]+")"+runArray[0]+"]\n[(";
					  }
					  else {
							
						  result+=runArray[1]+","+runArray[2]+","+runArray[3]+")"+runArray[0]+"]\n"; 
					  }	
				  
				  }
	  }
    return result;
  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
    // Your solution here, but you should probably leave the following line
    // at the end.
	  this();
	  this.height=image.getHeight();
	  this.width=image.getWidth();
	SListRun ListRun=new SListRun(image);
	ListRun.squish();
	for(int i=0;i<ListRun.length();i++) {
		Run run=new Run(ListRun.nth(i+1).red,ListRun.nth(i+1).green,ListRun.nth(i+1).blue,ListRun.runLength(i));
		 if(head==null) {
			  head=run;
			  tail=run;
		  }else {
			  tail.next=run;
			  run.prev=tail;
			  tail=run; 
		  }
		  length++;
	}
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check() {
	  Run pointer=head;
	  int[] count=new int[1];
	  count[0]=pointer.count;
	  while(pointer!=tail) {
		 if(pointer.value.red==pointer.next.value.red
				 ||pointer.value.green==pointer.next.value.green
				 ||pointer.value.blue==pointer.next.value.blue) {
			 System.err.println("Wrong Length Encoding!!!the Run has "
			 		+ "identical consecutive RGB intensities");
		 }
		 if(pointer.count<1) {
			 System.err.println("Invalid Run Length!!");
		 }
		 count[0]+=pointer.next.count;
		 pointer=pointer.next;
	  }
	  	if(count[0]!=width*height) {
	  		System.err.println("Wrong RunLengthEncoding Length!!");
	  	}
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
	  int i=0;
	  int Count=0;
	  Run pointRun=new Run();
	   while(y>0) {
		   i+=this.getWidth();
		   y--;
	   }
	   i+=(x+1);
	   RunIterator point=this.iterator();
	   while(point.hasNext()) {//如果RunEnCoding有多个Run，并且点（x,y）不再最后一个Run里，使用此method。
		   int[] runArray=point.next();
		   Count+=runArray[0];
		   if(Count<i) {
			   continue;
		   } else {
			   pointRun=point.currentRun().prev;
			   break;
		   }
	   }
	   if(!point.hasNext()&&Count<i) {//如果只有一个Run或者点(x,y)在最后一个Run里，使用这个method
		   pointRun=point.currentRun();
	   }	  
	   Run insertRun=new Run(red,green,blue,1);
	   if(!insertRun.value.equals(pointRun.value)) {
		   if(i==Count+1||i-(Count-pointRun.count)==1) {//判断点（x，y）在Run里的位置。头，和尾部只需要分裂两个Run，中间则需要分裂三个Run。该if判断是否在pointRun头部
			   pointRun.addCount(-1);
			   if(pointRun.count!=0) {
				   if(pointRun==head) {
					   pointRun.prev=insertRun;
					   insertRun.next=pointRun;
					   head=insertRun;
				   }else {
					   pointRun.prev.next=insertRun;
					   insertRun.prev=pointRun.prev;
					   insertRun.next=pointRun;
					   pointRun.prev=insertRun;
			   }
			   }else {
				   if(pointRun==tail) {
					   pointRun.prev.next=insertRun;
					   insertRun.prev=pointRun.prev;
					   tail=insertRun;
				   }else if(pointRun==head){
					   pointRun.next.prev=insertRun;
					   insertRun.next=pointRun.next;
					   head=insertRun;
				   }else {
				   pointRun.prev.next=insertRun;
				   insertRun.prev=pointRun.prev;
				   insertRun.next=pointRun.next;
				   pointRun.next.prev=insertRun;
				   }
			   }
		   }else if(i==Count||(pointRun.next==null&&i==Count+pointRun.count)) {//插入pointRun的尾部
			   pointRun.addCount(-1);
			   
			   if(pointRun.count!=0) {
				   if(pointRun==tail) {
					   pointRun.next=insertRun;
					   insertRun.prev=pointRun;
					   tail=insertRun;
				   }else {
					   pointRun.next.prev=insertRun;
					   insertRun.next=pointRun.next;
					   insertRun.prev=pointRun;
					   pointRun.next=insertRun;
				   }
			   }
			   else {
				   if(pointRun.next==null) {
					   pointRun.prev.next=insertRun;
					   insertRun.prev=pointRun.prev;
					   tail=insertRun;
				   }else if(pointRun.prev==null){
					   pointRun.next.prev=insertRun;
					   insertRun.next=pointRun.next;   
					   head=insertRun;
				   }else {
					   pointRun.prev.next=insertRun;
					   insertRun.prev=pointRun.prev;
					   insertRun.next=pointRun.next;
					   pointRun.next.prev=insertRun; 
				   }
			   }
		   }else {
			   Run runFront=new Run(pointRun.value.red,pointRun.value.green,pointRun.value.blue,i-(Count-pointRun.count)-1);
			   Run runBack=new Run(pointRun.value.red,pointRun.value.green,pointRun.value.blue,Count-i);
			   if(pointRun==head) {
				   runBack.next=head.next;
				   head.next.prev=runBack;
				   runFront.next=insertRun;
				   insertRun.prev=runFront;
				   insertRun.next=runBack;
				   runBack.prev=insertRun;
				   head=runFront;
			   }else if(pointRun==tail) {
				   runFront.prev=tail.prev;
				   tail.prev.next=runFront;
				   runFront.next=insertRun;
				   insertRun.prev=runFront;
				   insertRun.next=runBack;
				   runBack.prev=insertRun;
				   tail=runBack;
			   } 
				   else {
				   pointRun.prev.next=runFront;
				   runFront.prev=pointRun.prev;
				   pointRun.next.prev=runBack;
				   runBack.next=pointRun.next;
				   runFront.next=insertRun;
				   insertRun.prev=runFront;
				   insertRun.next=runBack;
				   runBack.prev=insertRun;
			   }
		   }
	   }
	   if(insertRun==head) {
		   if(insertRun.value.equals(insertRun.next.value)) {
			   insertRun.next.addCount(insertRun);
			   head=insertRun.next;
			   head.prev=null;
			   length--;
		   }
	   }else if(insertRun==tail) {
		   if(insertRun.value.equals(insertRun.prev.value)) {
			   insertRun.prev.addCount(insertRun);
			   tail=insertRun.prev;
			   tail.next=null;
			   length--;
		   }
	   }else {
		   if(insertRun.value.equals(insertRun.next.value)) {
			   insertRun.addCount(insertRun.next);
			   insertRun.next=insertRun.next.next;
			   if(insertRun.next==null) {
				   tail=insertRun;
			   }else {
			   insertRun.next.prev=insertRun;
			   }
				  length-- ;
		   }
		   if(insertRun.value.equals(insertRun.prev.value)){
			   insertRun.addCount(insertRun.prev);
			   insertRun.prev=insertRun.prev.prev;
			   if(insertRun.prev==null) {
				   head=insertRun;
			   }else {
			   insertRun.prev.next=insertRun;
			   } 
				  length-- ;
		   }

	   }
    check();
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
    rle.setPixel(x, y,
                 (short) intensity, (short) intensity, (short) intensity);
    rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                                                   { 1, 4, 7 },
                                                   { 2, 5, 8 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
    rle1.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
           "RLE1 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 42);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "Setting RLE1[0][0] = 42 fails.");
    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails.");
    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");
    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");


    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");


    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 1);
    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");
  }
}
