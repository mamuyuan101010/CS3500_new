import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyDateTest {
  private MyDate testDate1;
<<<<<<< HEAD
  private MyDate testDate2;
  @Before
  public void setUp() {
    testDate2 = new MyDate(8, 5, 2024);
=======
  @Before
  public void setUp() {
>>>>>>> origin/main

  }

  @Test
  public void testInvalidYear() {
    MyDate yearForTest;
    // test for a valid date for non-leap year
    try {
      yearForTest = new MyDate(28, 2, 2003);
    }
    catch (Exception e) {
      fail("An exception should not have been thrown");
    }
    // test for another valid date in a non-leap year
    try {
      yearForTest = new MyDate(31, 3, 2003);
    } catch (Exception e){
      fail("An exception should not have been thrown");
    }
    // test for another valid date in a non-leap year
    try {
      yearForTest = new MyDate(30, 6, 2003);
    } catch (Exception e){
      fail("An exception should not have been thrown");
    }
    // test for a valid date for leap year
    try {
      yearForTest = new MyDate(29, 2, 2004);
    } catch (Exception e){
      fail("An exception should not have been thrown");
    }
    // test for an invalid year
    try {
      yearForTest = new MyDate(1, 1, -1);
      fail("Year must be positive");
    } catch (IllegalArgumentException e) {
      // there should be an exception over here.
    }
    // test for an invalid month
    try {
      yearForTest = new MyDate(1, 13, 2004);
      fail("month must within 1 to 12");
    } catch (IllegalArgumentException e) {
      // there should be an exception over here.
    }
    // test for invalid month
    try {
      yearForTest = new MyDate(28, 0, 2004);
      fail("month must within 1 to 12");
    } catch (IllegalArgumentException e) {
      // there should be an exception over here
    }
    // test for invalid date that doesn't have 31st
    try {
      yearForTest = new MyDate(31, 4, 2004);
      fail("April does not have 31st");
    }
    catch (Exception e) {
      // there should be an exception over here
    }
    // test for another invalid date that over the range
    try {
      yearForTest = new MyDate(32, 5, 2004);
      fail("May does not have 32nd");
    } catch (IllegalArgumentException e) {
      // there should be an exception over here
    }
    // test for another invalid date that over the range
    try {
      yearForTest = new MyDate(-32, 5, 2004);
      fail("day cannot be negative");
    } catch (IllegalArgumentException e) {
      // there should be an exception over here
    }
    // test for another invalid date in a non-leap year that does not have a 29th
    try {
      yearForTest = new MyDate(29, 2, 2007);
      fail("In 2007, February does not have 29th");
    } catch (Exception e){
      // there should be an exception over here
    }
  }

<<<<<<< HEAD

  @Test
  public void advance() {
    testDate2.advance(28);
    assertEquals(testDate2.getYear(), 2024);
    assertEquals(testDate2.getMonth(), 6);
    assertEquals(testDate2.getDay(), 5);
  }

  @Test
  public void testToString() {
    testDate2.advance(24);
    assertEquals(testDate2.toString(), "2024-06-05");
=======
  @org.junit.jupiter.api.Test
  void advance() {
  }

  @org.junit.jupiter.api.Test
  void testToString() {
>>>>>>> origin/main
  }
}