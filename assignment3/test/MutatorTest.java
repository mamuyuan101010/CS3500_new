import org.junit.Test;
import org.junit.Before;

import calculator.Mutator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * MutatorTest class is the class for testing Mutator object.
 */
public class MutatorTest {
  // make a mutator with default value
  Mutator m;
  Mutator m1;

  // setUp method that set up the value for later use
  @Before
  public void setUp() {
    m = new Mutator(-1, -1, 0, 0, 0, "");
    m1 = new Mutator(-1, -1, 0, 0, 0, "");
  }

  // test for make all argument valid to build a constructor
  @Test
  public void testValidConstructor() {
    Mutator mutator = new Mutator(1,
            2, 1, 2, 0, "12+3");

    assertEquals("12+3", mutator.buildCalculator().getResult());

    // case that operand2 has not assigned a value yet
    Mutator mutator2 = new Mutator(1, -1, 0,
            1, 5, "12+");
    assertEquals("12+", mutator2.buildCalculator().getResult());
  }

  // test for make argument invalid to build a constructor
  @Test
  public void testInvalidConstructor() {
    // illegal status
    try {
      new Mutator(1, 2, 0, 4, 5, "12+3");
      fail("should fail");
    } catch (IllegalArgumentException e) {
      assertEquals("illegal status number input", e.getMessage());
    }

    // test invalid operator
    try {
      new Mutator(1, 2, 4, 1, 5, "12+3");
      fail("fail");
    } catch (IllegalArgumentException e) {
      assertEquals("illegal operator number input", e.getMessage());
    }

    // Invalid stringResult
    try {
      new Mutator(1, 2, 0, 1, 5, "string");
      fail("should fail");
    } catch (IllegalArgumentException e) {
      assertEquals("illegal currentResult string input", e.getMessage());
    }

    // Invalid operand2
    try {
      new Mutator(1, -2, 0, 1, 5, "12+3");
      fail("should fail");
    } catch (IllegalArgumentException e) {
      assertEquals("negative operand2 input", e.getMessage());
    }


  }

  // test for input method
  @Test
  public void testInput() {
    // first just a simple test of plus, minus, times
    m.input('1');
    assertEquals("1", m.buildCalculator().getResult());
    m.input('+');
    assertEquals("1+", m.buildCalculator().getResult());
    m.input('1');
    assertEquals("1+1", m.buildCalculator().getResult());
    m.input('=');
    assertEquals("2", m.buildCalculator().getResult());
    m.input('*');
    assertEquals("2*", m.buildCalculator().getResult());
    m.input('2');

    // now test the overflow in operand input will not cause operand change
    // meaning if an input method is called that make operand overflow,
    // an illegal argument exception will be thrown, and operand will remain the same
    // before input is called.

    int max = Integer.MAX_VALUE;
    char[] maxCharacters = String.valueOf(max).toCharArray();
    for (char c : maxCharacters) {
      m1.input(c);
    }
    try {
      m1.input('1');
    } catch (IllegalArgumentException e) {
      assertEquals("cannot work with whole numbers longer than 32 bits",
              e.getMessage());
      m1.input('-');
      m1.input('1');
      assertEquals("2147483647-1", m1.buildCalculator().getResult());
    }
  }

  @Test
  public void buildCalculator() {
    Mutator mu = new Mutator(2,3,2,2,0, "2-3");
    mu.input('=');
    assertEquals("-1", mu.buildCalculator().getResult());

    Mutator mu1 = new Mutator(2,3,1,2,0, "2+3");
    mu1.input('=');
    assertEquals("5", mu1.buildCalculator().getResult());

    Mutator mu2 = new Mutator(2,3,3,2,0, "2*3");
    mu2.input('=');
    assertEquals("6", mu2.buildCalculator().getResult());
  }
}