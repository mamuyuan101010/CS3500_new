import org.junit.Before;
import org.junit.Test;
import calculator.Calculator;
import calculator.SimpleCalculator;
import static org.junit.Assert.assertEquals;


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

  Calculator step1;
  Calculator step2;
  Calculator step3;
  Calculator step4;
  Calculator step5;
  Calculator step6;
  Calculator step7;
  Calculator step8;

  String errorMsg;

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

  // test the case that we put a 0 in the first digit
  @Test
  public void zeroInFront() {
    assertEquals("02+", c3.input('0').input('2').input('+').getResult());
    assertEquals("4+", c3.input('0').input('2')
            .input('+').input('0')
            .input('2').input('+').getResult());
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
    assertEquals("46", c3.input('3').input('2')
            .input('+').input('2').input('4')
            .input('-').input('1').input('0')
            .input('=').getResult());

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
    assertEquals("56", c7.input('3')
            .input('2')
            .input('+')
            .input('2')
            .input('4').input('=').getResult());

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
    assertEquals("9", c10.input('1').input('+').input('8').input('1')
            .input('=').input('9').getResult());

    assertEquals("9+", c10.input('1').input('+').input('8').input('1')
            .input('=').input('9').input('+').getResult());
    // check c10 is not mutated after called by input
    assertEquals("", c10.getResult());
  }

  @Test
  public void testMinusOverflow() {
    assertEquals("0", c10.input('0').input('-').input('2').input('-')
            .input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4').input('7').input('=')
            .getResult());
  }

  // in the following test I make test to what getResult output in every step we
  // input values
  @Test
  public void testAddAndMinus() {
    step1 = c10.input('1').input('1')
            .input('1').input('+').input('1')
            .input('1').input('1').input('=')
            .input('+').input('1')
            .input('+').input('1').input('=')
            .input('=');
    assertEquals("224", step1.getResult());
    assertEquals("", c10.getResult());

    step2 = step1.input('=');
    assertEquals("224", step2.getResult());
    assertEquals("224", step1.getResult());
    assertEquals("", c10.getResult());

    step3 = step2.input('-')
            .input('4')
            .input('3').input('1');
    assertEquals("224-431", step3.getResult());
    assertEquals("224", step2.getResult());
    assertEquals("224", step1.getResult());
    assertEquals("", c10.getResult());

    step4 = step3.input('+');
    assertEquals("-207+", step4.getResult());
    assertEquals("224-431", step3.getResult());
    assertEquals("224", step2.getResult());
    assertEquals("224", step1.getResult());
    assertEquals("", c10.getResult());

    step5 = step4.input('9');
    assertEquals("-207+9", step5.getResult());
    assertEquals("-207+", step4.getResult());
    assertEquals("224-431", step3.getResult());
    assertEquals("224", step2.getResult());
    assertEquals("224", step1.getResult());
    assertEquals("", c10.getResult());

    step6 = step5.input('+');
    assertEquals("-198+", step6.getResult());
    assertEquals("-207+9", step5.getResult());
    assertEquals("-207+", step4.getResult());
    assertEquals("224-431", step3.getResult());
    assertEquals("224", step2.getResult());
    assertEquals("224", step1.getResult());
    assertEquals("", c10.getResult());
  }

  @Test
  public void multiplication() {
    Calculator step1 = c10.input('8').input('8').input('8').input('+')
            .input('2').input('2').input('2').input('*')
            .input('5').input('2').input('0');
    assertEquals("1110*520", step1.getResult());
    assertEquals("", c10.getResult());

    Calculator step2 = step1.input('+');
    assertEquals("577200+", step2.getResult());
  }

  @Test
  public void testOverFlow() {
    step1 = c4.input('5').input('+').input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4').input('7').input('-')
            .input('1').input('0').input('=');

    assertEquals("-10", step1.getResult());

    step1 = c5.input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4');
    assertEquals("214748364", step1.getResult());
    try {
      step2 = step1.input('8');
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("cannot work with whole numbers longer than 32 bits", errorMsg);
  }

  // test that the result is over 32 bits
  @Test
  public void testVeryBigNumberAddition() {
    // this step only add to a very large number, but not over 32 bits.
    step1 = c10.input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('+')
            .input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('+')
            .input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('+')
            .input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('+')
            .input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9')
            .input('+');
    assertEquals("2147483645+", step1.getResult());
    // this step will cause the result over 32 bits, and over 32 bits.
    step2 = step1
            .input('4').input('2').input('9').input('4').input('9').input('6')
            .input('7').input('2').input('9').input('=');

    assertEquals("0", step2.getResult());

  }
}