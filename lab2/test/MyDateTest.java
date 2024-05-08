import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyDateTest {
  private MyDate testDate1;
  private MyDate testDate2;
  @Before
  public void setUp() {
    testDate1 = new MyDate(8,5,2024);
    testDate2 = new MyDate(1, 1, 2024);
  }

  @Test
  public void testInvalidDates() {
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

  @Test
  public void advance() {
    testDate2.advance(1);
    assertEquals(testDate2.getYear(), 2024);
    assertEquals(testDate2.getMonth(), 1);
    assertEquals(testDate2.getDay(), 2);
    testDate2.advance(365);
    assertEquals(testDate2.getYear(), 2025);
    assertEquals(testDate2.getMonth(), 1);
    assertEquals(testDate2.getDay(), 1);
    testDate1.advance(365);
    assertEquals(testDate1.getYear(), 2025);
    assertEquals(testDate1.getMonth(), 5);
    assertEquals(testDate1.getDay(), 8);
  }

  @Test
  public void testToString() {
    assertEquals("2024-01-01", testDate2.toString());
    testDate2.advance(731);
    assertEquals("2026-01-01", testDate2.toString());
    testDate2.advance(730);
    assertEquals("2028-01-01", testDate2.toString());
    testDate2.advance(365);
    assertEquals("2028-12-31", testDate2.toString());
  }


  @Test
  public void getDay() {
    assertEquals(1, testDate2.getDay());
  }

  @Test
  public void getMonth() {
    assertEquals(1, testDate2.getMonth());
  }

  @Test
  public void getYear() {
    assertEquals(2024, testDate2.getYear());
  }
}