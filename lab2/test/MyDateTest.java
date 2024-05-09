import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * this is the class for all testing code.
 */
public class MyDateTest {
  // pre-build variables
  private MyDate testDate1;
  private MyDate testDate2;
  private MyDate testDate3;
  private MyDate testDate4;
  private MyDate testDate5;
  private MyDate testDate6;

  // setup up function
  @Before
  public void setUp() {
    testDate1 = new MyDate(8, 5, 2024);
    testDate2 = new MyDate(1, 1, 2024);
    testDate3 = new MyDate(8, 5, 2024);
    testDate4 = new MyDate(31, 12, 2024);
    testDate5 = new MyDate(2, 1, 0);
    testDate6 = new MyDate(30, 11, 9999);

  }

  // test for all invalid dates being put into the constructor
  @Test
  public void testInvalidDates() {
    MyDate yearForTest;
    // test for a valid date for non-leap year
    try {
      yearForTest = new MyDate(28, 2, 2003);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
    }
    // test for another valid date in a non-leap year
    try {
      yearForTest = new MyDate(31, 3, 2003);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
    }
    // test for another valid date in a non-leap year
    try {
      yearForTest = new MyDate(30, 6, 2003);
    } catch (Exception e) {
      fail("An exception should not have been thrown");
    }
    // test for a valid date for leap year
    try {
      yearForTest = new MyDate(29, 2, 2004);
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
      // there should be an exception over here
    }
  }

  // test function for checking the invalid advance method
  // which are those do advance over Dec 9999
  @Test
  public void checkInvalidAdvance() {
    try {
      testDate6.advance(40);
      fail("Year Cannot over 9999");
    } catch (Exception e) {
      // there should be an exception over here
    }
  }

  // test function for advance method
  @Test
  public void testAdvance() {
    testDate2.advance(1);
    assertEquals(2024, testDate2.getYear());
    assertEquals(1, testDate2.getMonth());
    assertEquals(2, testDate2.getDay());
    testDate2.advance(365);
    assertEquals(2025, testDate2.getYear());
    assertEquals(1, testDate2.getMonth());
    assertEquals(1, testDate2.getDay());
    testDate1.advance(365);
    assertEquals(2025, testDate1.getYear());
    assertEquals(5, testDate1.getMonth());
    assertEquals(8, testDate1.getDay());
    testDate3.advance(-35);
    assertEquals(3, testDate3.getDay());
    assertEquals(4, testDate3.getMonth());
    assertEquals(2024, testDate3.getYear());
    testDate4.advance(-366);
    assertEquals(31, testDate4.getDay());
    assertEquals(12, testDate4.getMonth());
    assertEquals(2023, testDate4.getYear());
    testDate5.advance(-5);
    assertEquals(1, testDate5.getDay());
    assertEquals(1, testDate5.getMonth());
    assertEquals(0, testDate5.getYear());
  }

  // test function for toString method
  @Test
  public void testToString() {
    assertEquals("2024-01-01", testDate2.toString());
    testDate2.advance(731);
    assertEquals("2026-01-01", testDate2.toString());
    testDate2.advance(730);
    assertEquals("2028-01-01", testDate2.toString());
    testDate2.advance(365);
    assertEquals("2028-12-31", testDate2.toString());
    testDate4.advance(-367);
    assertEquals("2023-12-30", testDate4.toString());
    testDate5.advance(-5);
    assertEquals("0000-01-01", testDate5.toString());
  }


  // test function for getDay
  @Test
  public void getDay() {
    assertEquals(1, testDate2.getDay());
  }

  // test function for getMonth
  @Test
  public void getMonth() {
    assertEquals(1, testDate2.getMonth());
  }

  // test function for testYear
  @Test
  public void getYear() {
    assertEquals(2024, testDate2.getYear());
  }
}