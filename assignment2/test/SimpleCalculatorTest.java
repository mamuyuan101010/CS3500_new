import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.*;

public class SimpleCalculatorTest {
  Calculator c1;
  Calculator c2a;
  Calculator c2b;
  Calculator c3;
  Calculator c4;
  Calculator c5;
  Calculator c6;
  Calculator c7;
  Calculator c8;
  Calculator c9;
  Calculator c10;

  @Before
  public void setUp() {
    c1 = new SimpleCalculator();
    c2a = new SimpleCalculator();
    c2b = new SimpleCalculator();
    c3 = new SimpleCalculator();
    c4 = new SimpleCalculator();
    c5 = new SimpleCalculator();
    c6 = new SimpleCalculator();
    c7 = new SimpleCalculator();
    c8 = new SimpleCalculator();
    c9 = new SimpleCalculator();
    c10 = new SimpleCalculator();
  }

  @Test
  public void resultAtStart() {
    assertEquals("", c1.getResult());
  }

  @Test
  public void invalidThirdInput() {
    try {
      c3.input('2').input('+').input('+');
    } catch (IllegalArgumentException e) {
      assertEquals("cannot handle character + other " +
              "than number in the first position of the operand 2", e.getMessage());
    }
    try {
      c3.input('C');
      c3.input('2').input('+').input('L');
    } catch (IllegalArgumentException e) {
      assertEquals("cannot handle character L other " +
              "than number in the first position of the operand 2", e.getMessage());
    }
    try {
      c3.input('C');
      assertEquals("", c3.getResult());
      c3.input('2').input('1').input('=');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "should not type any character " +
              "other than number or operator here");
    }
  }

  @Test
  public void zeroInFront() {
    c3.input('0').input('2').input('+');
    assertEquals("02+", c3.getResult());
    c3.input('0').input('2').input('+');
    assertEquals("4+", c3.getResult());
  }

  @Test
  public void testSimpleCalculator1() {
    assertEquals("56", c1.input('3').input('2').input('+')
            .input('2').input('4').input('=').getResult());
  }

  @Test
  public void testSimpleCalculator2() {
    assertEquals("46", c2a.input('3').input('2').input('+').input('2')
            .input('4').input('-').input('1').input('0').input('=')
            .getResult());
    assertEquals("46", c2b.input('3')
            .input('2')
            .input('+')
            .input('2')
            .input('4')
            .input('=')
            .input('-')
            .input('1')
            .input('0')
            .input('=')
            .getResult()
    );
  }

  @Test
  public void testSimpleCalculator3() {
    c3.input('3');
    c3.input('2');
    c3.input('+');
    c3.input('2');
    c3.input('4');
    c3.input('-');
    c3.input('1');
    c3.input('0');
    c3.input('=');
    assertEquals(c3.getResult(),"46");

  }

  @Test
  public void testSimpleCalculator5() {
    c5.input('3')
            .input('2')
            .input('+')
            .input('2')
            .input('4')
            .input('=')
            .input('-')
            .input('1').input('C');
    System.out.println(c5.getResult());
  }

  @Test
  public void testSimpleCalculator6IllegalArg() {
    try {
      c6.input('3').input('2').input('+').input('=');
    } catch (Exception e) {
      assertEquals("cannot handle character = other than" +
              " number in the first position of the operand 2", e.getMessage());
    }
    c6 = new SimpleCalculator();
    try {
      c6.input('+').input('1').input('2').input('+').input('3');
    } catch (Exception e) {
      assertEquals("cannot handle character + " +
              "other than number in the first position of the operand 1", e.getMessage());
    }
  }

  @Test
  public void testSimpleCalculator7() {
    c7.input('3')
            .input('2')
            .input('+')
            .input('2')
            .input('4')
            .input('=')
            .input('=')
            .input('=');
    assertEquals("56", c7.getResult());

  }

  @Test
  public void testSimpleCalculator8() {
    try {
      c8.input('-')
              .input('1')
              .input('0')
              .input('+')
              .input('5')
              .input('=');
    } catch (Exception e) {
      assertEquals("cannot handle negative numbers", e.getMessage());
    }

    c8 = new SimpleCalculator();
    try {
      c8.input('1')
              .input('0')
              .input('+')
              .input('-')
              .input('5')
              .input('=');
    } catch (Exception e) {
      assertEquals("cannot handle negative numbers", e.getMessage());
    }
  }

  @Test
  public void testInvalidInput() {
    try {
      c9.input('1').input('g');
    } catch (Exception e) {
      assertEquals("should not type any " +
              "character other than number or operator here", e.getMessage());
    }

  }

  @Test
  public void testNumberAfterEquals() {
    c10.input('1').input('+').input('8').input('1').input('=');
    c10.input('9');
    assertEquals("9", c10.getResult());
    c10.input('+');
    assertEquals("9+", c10.getResult());
  }

  @Test
  public void testMinusOverflow() {
    c10.input('0').input('-').input('2').input('-')
            .input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4').input('7').input('=');
    assertEquals("0", c10.getResult());
  }

  @Test
  public void testAddAndMinus() {
    c10.input('1').input('1').input('1').input('+').input('1')
            .input('1').input('1').input('=').input('+').input('1')
            .input('+').input('1').input('=');
    c10.input('=');
    assertEquals("224", c10.getResult());
    c10.input('=');
    assertEquals("224", c10.getResult());
    c10.input('-').input('4').input('3').input('1');
    assertEquals("224-431", c10.getResult());
    c10.input('+');
    assertEquals("-207+", c10.getResult());
    c10.input('9');
    assertEquals("-207+9", c10.getResult());
    c10.input('+');
    assertEquals("-198+", c10.getResult());
    c10.input('9').input('9').input('9');
    assertEquals("-198+999", c10.getResult());
    c10.input('=');
    assertEquals("801", c10.getResult());
  }

  @Test
  public void multiplication() {
    c10.input('8').input('8').input('8').input('+')
            .input('2').input('2').input('2').input('*')
            .input('5').input('2').input('0');
            assertEquals("1110*520", c10.getResult());
            c10.input('+');
    assertEquals("577200+", c10.getResult());
  }

  @Test
  public void testOverFlow() {
    c4.input('5').input('+').input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4').input('7').input('-')
            .input('1').input('0').input('=');
    assertEquals("-10", c4.getResult());
    c4 = new SimpleCalculator();
    try {
      c4.input('2').input('1').input('4')
              .input('7').input('4').input('8').input('3')
              .input('6').input('4').input('8');
    } catch (Exception e) {
      assertEquals("cannot work with whole numbers longer than 32 bits", e.getMessage());
    }
  }

  @Test
  public void testVeryBigNumberAddition() {
    assertEquals(c4.input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('+')
            .input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('+')
            .input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('+')
            .input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('+')
            .input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('=').getResult(), "2147483645");

  }
}