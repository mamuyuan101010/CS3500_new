import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SmartCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SmartCalculatorTest {
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
  Calculator step9;
  Calculator step10;
  Calculator step11;
  Calculator step12;
  Calculator step13;
  Calculator step14;
  Calculator step15;
  Calculator step16;
  Calculator step17;
  Calculator step18;
  Calculator step19;
  Calculator step20;

  String errorMsg;

  // setUp method assign default values to calculators that will be used
  // to test input later;
  @Before
  public void setUp() {
    c1 = new SmartCalculator();
    c2a = new SmartCalculator();
    c2b = new SmartCalculator();
    c3 = new SmartCalculator();
    c4 = new SmartCalculator();
    c5 = new SmartCalculator();
    c6 = new SmartCalculator();
    c7 = new SmartCalculator();
    c8 = new SmartCalculator();
    c9 = new SmartCalculator();
    c10 = new SmartCalculator();

  }

  // this test representing the SmartCalculator Constructor
  // with valid argument inputs
  @Test
  public void testValidSmartCalculatorConstructor() throws Exception {
    // this declaration simulate a calculator the time just after = sign is input,
    // and first operand is 5, second operand is 5, plus is used, and result is 10
    Calculator validCal1 = new SmartCalculator("10", new int[]{10,
            10, -1, 3, 1});
    // if + is input, it will auto make 10 be first operand and expect a second operand
    step1 = validCal1.input('+');
    assertEquals("10+", step1.getResult());
    step1 = step1.input('4').input('=');
    // calculation is expected to be 14
    assertEquals("14", step1.getResult());

    // if 4 is input, it will start a brand-new calculation by itself
    step2 = validCal1.input('4');
    assertEquals("4", step2.getResult());

    // this declaration simulate a calculator the time when second operand is expected to input,
    // and first operand is 5, second operand is 5, plus is used, and result is 10
    Calculator validCal2 = new SmartCalculator("10", new int[]{10,
            5, 5, 2, 1});
    step3 = validCal2.input('4');
    assertEquals("104", step3.getResult());

    // build a constructor that has empty string
    Calculator validCal3 = new SmartCalculator("", new int[]{10,
            5, 5, 3, 1});

    // build a constructor that has operator and number string
    Calculator validCal4 = new SmartCalculator("4+", new int[]{10,
            5, 5, 3, 1});
    Calculator validCal5 = new SmartCalculator("7*", new int[]{10,
            5, 5, 3, 1});
    Calculator validCal6 = new SmartCalculator("8-2", new int[]{10,
            5, 5, 3, 1});
  }

  // test for invalid input in SimpleVariable constructor
  @Test
  public void testInvalidSimpleVariableConstructor() {
    // input some string that contain alphabetic characters
    try {
      Calculator invalidCal1 = new SmartCalculator("illegalresultInt",
              new int[]{0, 2, 2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal currentResult string input", errorMsg);

    // empty space should also be disallowed
    try {
      Calculator invalidCal1 = new SmartCalculator(" ",
              new int[]{0, 2, 2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal currentResult string input", errorMsg);

    // = sign is disallowed
    try {
      Calculator invalidCal1 = new SmartCalculator("8+8=",
              new int[]{0, 2, 2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal currentResult string input", errorMsg);

    // divide sign is not allowed
    try {
      Calculator invalidCal1 = new SmartCalculator("8/8",
              new int[]{0, 2, 2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal currentResult string input", errorMsg);

    // now try invalid operand2 values
    try {
      Calculator invalidCal1 = new SmartCalculator("6+3",
              new int[]{0, 2, -2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("cannot handle negative operand", errorMsg);

    // now testing status not in good range
    try {
      Calculator invalidCal1 = new SmartCalculator("6+3",
              new int[]{0, 2, 2, 7, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal status number input", errorMsg);

    // now testing operator not in good range
    try {
      Calculator invalidCal1 = new SmartCalculator("6+3",
              new int[]{0, 2, 2, 3, 9});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal operator number input", errorMsg);
  }

  // test for the getResult method in the start
  @Test
  public void resultAtStart() {
    assertEquals("", c1.getResult());
  }

  // test the case that we put a 0 in the first digit
  @Test
  public void zeroInFront() throws Exception {
    assertEquals("02+", c3.input('0').input('2').input('+').getResult());
    assertEquals("4+", c3.input('0').input('2')
            .input('+').input('0')
            .input('2').input('+').getResult());
  }

  // test 32 + 23 = 54
  @Test
  public void testSmartCalculator1() throws Exception {
    assertEquals("56", c1.input('3').input('2').input('+')
            .input('2').input('4').input('=').getResult());
  }

  // inputting operator after equal sign
  @Test
  public void testSmartCalculator2() throws Exception {
    // test 32 + 24 - 10 = , answer should be 46
    // test every step in sequence
    assertEquals("", c2a.getResult());
    step1 = c2a.input('3');
    assertEquals("3", step1.getResult());
    step2 = step1.input('2');
    assertEquals("32", step2.getResult());
    step3 = step2.input('+');
    assertEquals("32+", step3.getResult());
    step4 = step3.input('2');
    assertEquals("32+2", step4.getResult());
    step5 = step4.input('4');
    assertEquals("32+24", step5.getResult());
    step6 = step5.input('-');
    assertEquals("56-", step6.getResult());
    step7 = step6.input('1');
    assertEquals("56-1", step7.getResult());
    step8 = step7.input('0');
    assertEquals("56-10", step8.getResult());
    step9 = step8.input('=');
    assertEquals("46", step9.getResult());

    // test 32 + 24 = - 10 =, answer should still be 46
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
  public void testSmartCalculator3() throws Exception {
    assertEquals("46", c3.input('3').input('2')
            .input('+').input('2').input('4')
            .input('-').input('1').input('0')
            .input('=').getResult());

  }

  // test for input a C to return Calculator back to default
  @Test
  public void testInputC() throws Exception {
    step1 = c5.input('3')
            .input('2')
            .input('+')
            .input('2')
            .input('4')
            .input('=')
            .input('-')
            .input('1');
    assertEquals("56-1", step1.getResult());
    step2 = step1.input('C');
    assertEquals("", step2.getResult());
  }

  // test the result of input 3 2 + 2 4 =
  @Test
  public void testSmartCalculator7() throws Exception {
    step1 = c7.input('3');
    assertEquals("3", step1.getResult());
    step2 = step1.input('2');
    assertEquals("32", step2.getResult());
    step3 = step2.input('+');
    assertEquals("32+", step3.getResult());
    step4 = step3.input('2');
    assertEquals("32+2", step4.getResult());
    step5 = step4.input('4');
    assertEquals("32+24", step5.getResult());
    step6 = step5.input('=');
    assertEquals("56", step6.getResult());

  }

  // test for invalid negative input values in the operands
  @Test
  public void testNegativeInput() throws Exception {
    // negative in first operand
    try {
      c8.input('-')
              .input('1')
              .input('0')
              .input('+')
              .input('5')
              .input('=');
      fail("should fail");
    } catch (Exception e) {
      errorMsg = e.getMessage();
      assertEquals("cannot handle invalid character:-", errorMsg);
    }

    // negative in the second operand
    c10 = c9.input('1')
            .input('0')
            .input('+')
            .input('-')
            .input('5')
            .input('=');
    assertEquals("5", c10.getResult());
  }

  // test for all operands are positive, but result is negative, and should be
  // valid
  @Test
  public void testValidNegativeResult() throws Exception {
    step1 = c8.input('1')
            .input('-')
            .input('8')
            .input('=');
    assertEquals("-7", step1.getResult());
    step2 = step1.input('*').input('9').input('=');
    assertEquals("-63", step2.getResult());
  }

  // test invalid input that type alphabetical characters
  @Test
  public void testInvalidInput() {
    try {
      c9.input('1').input('g');
      fail("should fail");
    } catch (Exception e) {
      errorMsg = e.getMessage();
      assertEquals("cannot handle invalid character:g", errorMsg);
    }

  }

  // test to put a number after = sign, then put an operator
  @Test
  public void testOperatorAfterEquals() throws Exception {
    // test whether 1 + 8 1 = 9 will only return 9
    assertEquals("9", c10.input('1').input('+').input('8').input('1')
            .input('=').input('9').getResult());

    // test whether 1 + 8 1 = 9 + will return 9+
    assertEquals("9+", c10.input('1').input('+').input('8').input('1')
            .input('=').input('9').input('+').getResult());
    // check c10 is not mutated after called by input
    assertEquals("", c10.getResult());
  }

  // test whether an overflow will happen if the result value is lower than
  // the lowest value of 32-bit integer. should return a 0
  @Test
  public void testMinusOverflow() throws Exception {
    step1 = c10.input('0');
    assertEquals("0", step1.getResult());
    step2 = step1.input('-');
    assertEquals("0-", step2.getResult());
    step3 = step2.input('1');
    assertEquals("0-1", step3.getResult());
    step4 = step3.input('-');
    assertEquals("-1-", step4.getResult());
    step5 = step4.input('2').input('1').input('4');
    assertEquals("-1-214", step5.getResult());
    step6 = step5.input('7').input('4').input('8');
    assertEquals("-1-214748", step6.getResult());
    step7 = step6.input('3').input('6');
    assertEquals("-1-21474836", step7.getResult());
    step8 = step7.input('4');
    assertEquals("-1-214748364", step8.getResult());
    step9 = step8.input('7');
    assertEquals("-1-2147483647", step9.getResult());
    step10 = step9.input('=');
    assertEquals("-2147483648", step10.getResult());
    Calculator stepMinus1 = step10.input('-').input('1');
    assertEquals("-2147483648-1", stepMinus1.getResult());
    Calculator stepOverFlow = stepMinus1.input('=');
    assertEquals("0", stepOverFlow.getResult());

  }

  // test process calculation in sequence no matter + - or *
  // in this test, input sequence is 5 + 4 * 3, so first add 5 and 4 which is 9,
  // then 9 * 3 becomes 27;
  @Test
  public void testInSequence() throws Exception {
    step1 = c10.input('5');
    assertEquals("5", step1.getResult());

    step2 = step1.input('+');
    assertEquals("5+", step2.getResult());

    step3 = step2.input('4');
    assertEquals("5+4", step3.getResult());

    step4 = step3.input('*');
    assertEquals("9*", step4.getResult());

    step5 = step4.input('3');
    assertEquals("9*3", step5.getResult());

    step6 = step5.input('=');
    assertEquals("27", step6.getResult());
  }

  // in the following test I make test to what getResult output in every important
  // step we input values
  @Test
  public void testAddAndMinus() throws Exception {
    step1 = c10.input('1').input('1')
            .input('1').input('+').input('1')
            .input('1').input('1').input('=')
            .input('+').input('1')
            .input('+').input('1').input('=')
            .input('=');
    assertEquals("225", step1.getResult());
    assertEquals("", c10.getResult());

    step2 = step1.input('=');
    assertEquals("226", step2.getResult());
    assertEquals("225", step1.getResult());
    assertEquals("", c10.getResult());

    step3 = step2.input('-')
            .input('4')
            .input('3').input('1');
    assertEquals("226-431", step3.getResult());
    assertEquals("226", step2.getResult());
    assertEquals("225", step1.getResult());
    assertEquals("", c10.getResult());

    step4 = step3.input('+');
    assertEquals("-205+", step4.getResult());
    assertEquals("226-431", step3.getResult());
    assertEquals("226", step2.getResult());
    assertEquals("225", step1.getResult());
    assertEquals("", c10.getResult());

    step5 = step4.input('9');
    assertEquals("-205+9", step5.getResult());
    assertEquals("-205+", step4.getResult());
    assertEquals("226-431", step3.getResult());
    assertEquals("226", step2.getResult());
    assertEquals("225", step1.getResult());
    assertEquals("", c10.getResult());

    step6 = step5.input('+');
    assertEquals("-196+", step6.getResult());
    assertEquals("-205+9", step5.getResult());
    assertEquals("-205+", step4.getResult());
    assertEquals("226-431", step3.getResult());
    assertEquals("226", step2.getResult());
    assertEquals("225", step1.getResult());
    assertEquals("", c10.getResult());
  }

  // test whether * work in the correct way
  @Test
  public void multiplication() throws Exception {
    Calculator step1 = c10.input('8').input('8').input('8').input('+')
            .input('2').input('2').input('2').input('*')
            .input('5').input('2').input('0');
    assertEquals("1110*520", step1.getResult());
    assertEquals("", c10.getResult());

    Calculator step2 = step1.input('+');
    assertEquals("577200+", step2.getResult());
  }

  // test for overflow error when the result is over the max number of 32-bits integer
  @Test
  public void testOverFlow() throws Exception {
    // test the case where result is overflow, causing result to be zero
    // a + b - 1 0 = should result in -10 if a+b will overflow
    step1 = c4.input('5').input('+').input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4').input('7').input('-')
            .input('1').input('0').input('=');

    assertEquals("-10", step1.getResult());

    // test that the operand 1 is overflow, causing an illegal argument error
    step1 = c5.input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4');
    assertEquals("214748364", step1.getResult());
    try {
      step2 = step1.input('8');
      fail("should fail");
    } catch (IllegalArgumentException e) {
      // check operand1 remain the same even input cause an overflow
      assertEquals("214748364", step1.getResult());
      errorMsg = e.getMessage();
    }
    // check the error message
    assertEquals("your input integer is over 32 bits", errorMsg);

  }

  // test that the result is over 32 bits
  @Test
  public void testVeryBigNumberAddition() throws Exception {
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

  // test for operand2 remain the same as operand2 is overFlow
  @Test
  public void testOperand2OverFlow() throws Exception {
    // test that the operand 2 is overflow, causing an illegal argument error
    step1 = c6.input('4').input('+');
    assertEquals("4+", step1.getResult());
    step2 = step1.input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4');
    assertEquals("4+214748364", step2.getResult());
    try {
      step3 = step2.input('8');
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
      // test that the previous step causing exception doesn't change in operand2
      assertEquals("4+214748364", step2.getResult());
    }
    // check the error message
    assertEquals("your input integer is over 32 bits", errorMsg);
  }

  // test for immutable property of input method,
  // the input method should not change the calling object
  @Test
  public void testImmutable() throws Exception {
    step1 = c10.input('1');
    // sept1 is the calling object here, so expect only step2's value change
    // and step1's value remain the same
    // plus
    step2 = step1.input('+').input('1').input('=');
    assertEquals("1", step1.getResult());
    assertEquals("2", step2.getResult());
    // minus
    step3 = c9.input('1');
    step4 = step3.input('-').input('1').input('=');
    assertEquals("1", step3.getResult());
    assertEquals("0", step4.getResult());
    assertEquals("1", step3.getResult());
    // times
    step5 = step3.input('*').input('2').input('=');
    assertEquals("1", step3.getResult());
    assertEquals("2", step5.getResult());
    assertEquals("1", step3.getResult());
  }

  // test for inputting multiple = signs
  @Test
  public void testMultipleEquals() throws Exception {
    assertEquals("", c1.getResult());
    step1 = c1.input('3');
    assertEquals("3", step1.getResult());
    step2 = step1.input('2');
    assertEquals("32", step2.getResult());
    step3 = step2.input('+');
    assertEquals("32+", step3.getResult());
    step4 = step3.input('2');
    assertEquals("32+2", step4.getResult());
    step5 = step4.input('4');
    assertEquals("32+24", step5.getResult());
    step6 = step5.input('=');
    assertEquals("56", step6.getResult());
    step7 = step6.input('=');
    assertEquals("80", step7.getResult());
    step8 = step7.input('=');
    assertEquals("104", step8.getResult());
  }

  @Test
  public void testSkipOperand2() throws Exception {
    assertEquals("", c1.getResult());
    step1 = c1.input('3');
    assertEquals("3", step1.getResult());
    step2 = step1.input('2');
    assertEquals("32", step2.getResult());
    step3 = step2.input('+');
    assertEquals("32+", step3.getResult());
    step4 = step3.input('=');
    assertEquals("64", step4.getResult());
    step5 = step4.input('=');
    assertEquals("96", step5.getResult());
  }

  @Test
  public void debug() throws Exception {
  }

  @Test
  public void testFirstOpertorIgnore() throws Exception {
    step1 = c1.input('3');
    assertEquals("3", step1.getResult());
    step2 = step1.input('2');
    assertEquals("32", step2.getResult());
    step3 = step2.input('+');
    assertEquals("32+", step3.getResult());
    step4 = step3.input('-');
    assertEquals("32-", step4.getResult());
    step5 = step4.input('2');
    assertEquals("32-2", step5.getResult());
    step6 = step5.input('4');
    assertEquals("32-24", step6.getResult());
    step7 = step6.input('=');
    assertEquals("8", step7.getResult());
  }

  // Begin with operator
  @Test
  public void testBeginWithOperator() throws Exception {
    assertEquals("", c1.getResult());
    step1 = c1.input('+');
    assertEquals("", step1.getResult());
    step2 = step1.input('3');
    assertEquals("3", step2.getResult());
    step3 = step2.input('2');
    assertEquals("32", step3.getResult());
    step4 = step3.input('-');
    assertEquals("32-", step4.getResult());
    step5 = step4.input('2');
    assertEquals("32-2", step5.getResult());
    step6 = step5.input('4');
    assertEquals("32-24", step6.getResult());
    step7 = step6.input('=');
    assertEquals("8", step7.getResult());
  }

}