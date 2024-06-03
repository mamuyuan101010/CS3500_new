import org.junit.Before;
import org.junit.Test;

import spreadsheet.bulkAssignMacro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class bulkAssignMacroTest {
  @Before
  public void setUp() throws Exception {
    bulkAssignMacro testMacro1;
  }

  @Test
  public void validityTest() {
    // test valid
    try {
      bulkAssignMacro testMacro1 = new bulkAssignMacro(4, 4, 2,
              2, 0.5);
    } catch (IllegalArgumentException e) {
      fail("should not fail here");
    }

    // now test invalid
    try {
      bulkAssignMacro testMacro1 = new bulkAssignMacro(-4, 4,
              2, 2, 0.5);
      fail("should fail here");
    } catch (IllegalArgumentException e) {
      assertEquals("at least one of the given value is both negative",
              e.getMessage());
    }

    try {
      bulkAssignMacro testMacro1 = new bulkAssignMacro(4, -4,
              2, 2, 0.5);
      fail("should fail here");
    } catch (IllegalArgumentException e) {
      assertEquals("at least one of the given value is both negative",
              e.getMessage());
    }

    try {
      bulkAssignMacro testMacro1 = new bulkAssignMacro(3, 4,
              -2, 2, 0.5);
      fail("should fail here");
    } catch (IllegalArgumentException e) {
      assertEquals("at least one of the given value is both negative",
              e.getMessage());
    }

    try {
      bulkAssignMacro testMacro1 = new bulkAssignMacro(4, 4,
              2, -2, 0.5);
      fail("should fail here");
    } catch (IllegalArgumentException e) {
      assertEquals("at least one of the given value is both negative",
              e.getMessage());
    }
  }

  // now test overRange
  @Test
  public void validityTest2() {
    try {
      bulkAssignMacro testMacro1 = new bulkAssignMacro(1, 1,
              2, 1, 0.5);
      fail("should fail here");
    } catch (IllegalArgumentException e) {
      assertEquals("either height or width out of range", e.getMessage());
    }

    try {
      bulkAssignMacro testMacro1 = new bulkAssignMacro(1, 3,
              2, 1, 0.5);
      fail("should fail here");
    } catch (IllegalArgumentException e) {
      assertEquals("either height or width out of range", e.getMessage());
    }
  }
}