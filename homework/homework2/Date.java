/* Date.java */

import java.io.*;

class Date {

  private  int day;
  private int month;
  private int year;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
	  this(""+month+"/"+day+"/"+year); 
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
	  String[] sp =s.split("/");
	  if(sp.length<3) {
		  System.exit(0);
	  }
	  if(!this.isDigits(sp[0])) {
		  System.exit(0);
	  }else if(!this.isDigits(sp[1])) {
		  System.exit(0);
	  }else if(!this.isDigits(sp[2])) {
		  System.exit(0);
	  }
	  int month=Integer.parseInt(sp[0]);
	  int day=Integer.parseInt(sp[1]);
	  int year= Integer.parseInt(sp[2]);
	  if(Date.isValidDate(month,day,year)) {
		  this.month=month;
		  this.day=day;
		  this.year=year;
		  
	  }else {
		  System.exit(0);
	  }
  }
  
  
  /** Helper method:check whether month, day and year are 
   *  digits or not.
   * @param str could be month,day or year, the type of them
   * are String.
   * @return true if there are all digits.
   */
  private boolean isDigits(String str) {
	 for(int i=0;i<str.length();i++) {
		 if(!Character.isDigit(str.charAt(i))) {
			 return false;
		 }
	 }
	 return true;
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
	  if(year%400==0) {
		  return true;  
	  }else if(year%100==0) {
		  return false;
	  }else {
		  return year%4==0;
	  }
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
	  switch(month){
		  case 4:
		  case 6:
		  case 9:
		  case 11:
			  return 30;
		  case 2:
			  if(Date.isLeapYear(year)) {
				  return 29;
			  }else {
				  return 28;
			  }
		 default:
			return 31;
	  }                       
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
    if(year>9999||year<1) {
    	return false;
    }else if(month>12||month<1) {
    	return false;
    }else if(day<1) {
    	return false;
    }else return !(day>Date.daysInMonth(month,year));
	                         
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
	  String result = "";
	  result+=this.month+"/"+this.day+"/"+this.year;

    return result;                     // replace this line with your solution
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
	  if(this.year<d.year) {
		  return true;
	  }else {
		  if(this.month<d.month&&this.year==d.year) {
			  return true;
		  }else {
			  if(this.day<d.day&&this.month==d.month&&this.year==d.year) {
				  return true;
			  }else {
				  return false;
			  }
		  }
	  }
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
	 return(!this.isBefore(d)&&!(this.day==d.day&&this.month==d.month&&this.year==d.year));
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
	  int sum=0;
	  for(int i=1;i<this.month;i++) {
		  sum+=Date.daysInMonth(i,this.year);
	  }
	  return sum+this.day;
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
	 if(this.isBefore(d)) {
		 return -Date.calculate(this,d);
	 }else {
		 return Date.calculate(d,this);
		 
	 }
  }
 
  /**Helper method: calculate the difference between two dates,check
   * whether the date is in leap year or not,If it's
   * in leap year.The day in Feb. should be 29.
   * @param c means the date which is previous compared to date d.
   * @param d means the date which is after  date c.
   * @return the total difference between those two dates.
   */
  private static int calculate(Date c,Date d) {
	  int sum=0;
	  for(int i =c.year;i<d.year;i++) {
		 if(Date.isLeapYear(i)) {
			 sum+=366;
		 }else{
			 sum+=365;
		 }
	  }
	  return sum+d.dayInYear()-c.dayInYear();

  }

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    System.out.println("\nTesting isLeapYearMethod");
    Date d6=new Date("2/28/1002");
    System.out.println("Date shoud be false:"+Date.isLeapYear(d6.year));
    

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
