import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * SimpleCalculatorTest class is the class for testing SimpleCalculator class.
 */
public class SimpleCalculatorTest {
  // fields to pre-declared
  Calculator simpleCalculator;

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

  String errorMsg;

  // setUp method assign default values to calculators that will be used
  // to test input later;
  @Before
  public void setUp() {
    simpleCalculator = new SimpleCalculator();
  }

  // this test representing the SimpleCalculator Constructor
  // with valid argument inputs
  @Test
  public void testValidSimpleCalculatorConstructor() {
    // this declaration simulate a calculator the time just after = sign is input,
    // and first operand is 5, second operand is 5, plus is used, and result is 10
    try {
      Calculator validCal1 = new SimpleCalculator("10", new int[]{10, 5, 5, 3, 1});
    } catch (Exception e) {
      fail(e.getMessage()); // should not fail
    }

    // this declaration simulate a calculator the time when second operand is expected to input,
    // and first operand is 5, second operand is 5, plus is used, and result is 10
    try {
      Calculator validCal2 = new SimpleCalculator("10", new int[]{10, 5, 5, 2, 1});
    } catch (Exception e) {
      fail(e.getMessage()); // should not fail
    }

    // build a constructor that has empty string
    try {
      Calculator validCal3 = new SimpleCalculator("", new int[]{10, 5, 5, 3, 1});
    } catch (Exception e) {
      fail(e.getMessage()); // should not fail
    }

    // build a constructor that has operator + and number string
    try {
      Calculator validCal4 = new SimpleCalculator("4+", new int[]{10, 5, 5, 3, 1});
    } catch (Exception e) {
      fail(e.getMessage()); // should not fail
    }

    // build a constructor that has operator * and number string
    try {
      Calculator validCal5 = new SimpleCalculator("7*", new int[]{10, 5, 5, 3, 1});
    } catch (Exception e) {
      fail(e.getMessage()); // should not fail
    }

    // build a constructor that has one operator and 2 integers string
    try {
      Calculator validCal6 = new SimpleCalculator("8-2", new int[]{10, 5, 5, 3, 1});
    } catch (Exception e) {
      fail(e.getMessage()); // should not fail
    }
  }

  // test for invalid input in SimpleVariable constructor
  @Test
  public void testInvalidSimpleVariableConstructor() {
    // input some string that contain alphabetic characters
    try {
      Calculator invalidCal1 = new SimpleCalculator("illegalresultInt",
              new int[]{0, 2, 2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal currentResult string input", errorMsg);

    // empty space should also be disallowed
    try {
      Calculator invalidCal1 = new SimpleCalculator(" ",
              new int[]{0, 2, 2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal currentResult string input", errorMsg);

    // = sign is disallowed
    try {
      Calculator invalidCal1 = new SimpleCalculator("8+8=",
              new int[]{0, 2, 2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal currentResult string input", errorMsg);

    // divide sign is not allowed
    try {
      Calculator invalidCal1 = new SimpleCalculator("8/8",
              new int[]{0, 2, 2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal currentResult string input", errorMsg);

    // now try invalid operand2 values
    try {
      Calculator invalidCal1 = new SimpleCalculator("6+3",
              new int[]{0, 2, -2, 2, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("cannot handle negative operand", errorMsg);

    // now testing status not in good range
    try {
      Calculator invalidCal1 = new SimpleCalculator("6+3",
              new int[]{0, 2, 2, 7, 2});
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("illegal status number input", errorMsg);

    // now testing operator not in good range
    try {
      Calculator invalidCal1 = new SimpleCalculator("6+3",
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
    assertEquals("", simpleCalculator.getResult());
  }

  // test for invalid second operator input
  @Test
  public void invalidSecondOperator() {
    // test for input a duplicate operator
    step1 = simpleCalculator.input('2');
    assertEquals("2", step1.getResult());
    step2 = step1.input('+');
    assertEquals("2+", step2.getResult());
    // try to put + sign
    try {
      step3 = step2.input('+');
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("cannot handle invalid character:+", errorMsg);

    // try to put - sign
    try {
      step3 = step2.input('-');
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("cannot handle negative operand", errorMsg);

    // try to put - sign
    try {
      step3 = step2.input('*');
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("cannot handle invalid character:*", errorMsg);
  }

  // test for input an unauthorized character
  @Test
  public void unauthorizedCharacterInput() {
    step1 = simpleCalculator.input('2');
    assertEquals("2", step1.getResult());
    step2 = step1.input('+');
    assertEquals("2+", step2.getResult());
    try {
      step3 = step2.input('L');
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("cannot handle invalid character:L", errorMsg);
  }

  // test the case to put equal sign before call the operator
  @Test
  public void equalSignBeforeOperator() {
    step1 = simpleCalculator.input('2');
    assertEquals("2", step1.getResult());
    step2 = step1.input('1');
    assertEquals("21", step2.getResult());
    try {
      step3 = step2.input('=');
      fail("should fail");
    } catch (IllegalArgumentException e) {
      errorMsg = e.getMessage();
    }
    assertEquals("cannot handle invalid character:=", errorMsg);
  }

  // test for input /
  @Test
  public void divideInput() {
    step1 = simpleCalculator.input('2');
    assertEquals("2", step1.getResult());
    step2 = step1.input('1');
    assertEquals("21", step2.getResult());
    try {
      step3 = step2.input('/');
    } catch (IllegalArgumentException e) {
      assertEquals("cannot handle invalid character:/", e.getMessage());
    }
  }

  // test the case that we put a 0 in the first digit,
  // zero should remain in the front until second operator is input
  @Test
  public void zeroInFront() {
    step1 = simpleCalculator.input('0');
    assertEquals("0", step1.getResult());
    step2 = step1.input('2');
    assertEquals("02", step2.getResult());
    step3 = step2.input('+');
    assertEquals("02+", step3.getResult());
    step4 = step3.input('0');
    assertEquals("02+0", step4.getResult());
    step5 = step4.input('2');
    assertEquals("02+02", step5.getResult());
    step6 = step5.input('*');
    assertEquals("4*", step6.getResult());
  }

  // test 32 + 24 = 56
  @Test
  public void testSimpleCalculator1() {
    step1 = simpleCalculator.input('3');
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

  // inputting operator after equal sign
  @Test
  public void testSimpleCalculator2() {
    // test 32 + 24 - 10 = , answer should be 46
    // test every step in sequence
    assertEquals("", simpleCalculator.getResult());
    step1 = simpleCalculator.input('3');
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
    step1 = simpleCalculator.input('3');
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
    step7 = step6.input('-');
    assertEquals("56-", step7.getResult());
    step8 = step7.input('1');
    assertEquals("56-1", step8.getResult());
    step9 = step8.input('0');
    assertEquals("56-10", step9.getResult());
    step10 = step9.input('=');
    assertEquals("46", step10.getResult());

  }

  // test for input a C to return Calculator back to default
  @Test
  public void testInputC() {
    // first input 3 2 + 2 4 = - 1
    step1 = simpleCalculator.input('3');
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
    step7 = step6.input('-');
    assertEquals("56-", step7.getResult());
    step8 = step7.input('1');
    assertEquals("56-1", step8.getResult());
    // now input C
    step9 = step8.input('C');
    assertEquals("", step9.getResult());
  }

  // test invalid inputs = sign is input before second operand
  @Test
  public void equalBeforeSecondOperand() {
    step1 = simpleCalculator.input('3');
    assertEquals("3", step1.getResult());
    step2 = step1.input('2');
    assertEquals("32", step2.getResult());
    step3 = step2.input('+');
    assertEquals("32+", step3.getResult());
    try {
      step4 = step3.input('=');
      fail("should fail");
    } catch (IllegalArgumentException e) {
      assertEquals("cannot handle invalid character:=", e.getMessage());
    }
  }

  // test to put + sign in the front
  @Test
  public void equalSignInFront() {
    try {
      simpleCalculator.input('+');
      fail("should fail");
    } catch (Exception e) {
      assertEquals("cannot handle invalid character:+", e.getMessage());
    }
  }

  // check putting multiple = will get the same answer
  @Test
  public void testMultipleEqu() {
    step1 = simpleCalculator.input('3');
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
    assertEquals("56", step7.getResult()); // remain the same
    step8 = step7.input('=');
    assertEquals("56", step8.getResult()); // remain the same

  }

  // test for invalid negative input values in the operands
  @Test
  public void testNegativeInput() {
    // negative in first operand
    try {
      simpleCalculator.input('-');
      fail("should fail");
    } catch (Exception e) {
      errorMsg = e.getMessage();
      assertEquals("cannot handle negative operand", errorMsg);
    }

    // negative in the second operand
    simpleCalculator = new SimpleCalculator();
    step1 = simpleCalculator.input('1');
    assertEquals("1", step1.getResult());
    step2 = step1.input('0');
    assertEquals("10", step2.getResult());
    step3 = step2.input('+');
    assertEquals("10+", step3.getResult());
    try {
      step4 = step3.input('-');
      fail("should fail");
    } catch (Exception e) {
      errorMsg = e.getMessage();
      assertEquals("cannot handle negative operand", errorMsg);
    }
  }

  // test for all operands are positive, but result is negative, and should be
  // valid
  @Test
  public void testValidNegativeResult() {
    step1 = simpleCalculator.input('1');
    assertEquals("1", step1.getResult());
    step2 = step1.input('-');
    assertEquals("1-", step2.getResult());
    step3 = step2.input('8');
    assertEquals("1-8", step3.getResult());
    step4 = step3.input('=');
    assertEquals("-7", step4.getResult());
    step5 = step4.input('*');
    assertEquals("-7*", step5.getResult());
    step6 = step5.input('9');
    assertEquals("-7*9", step6.getResult());
    step7 = step6.input('=');
    assertEquals("-63", step7.getResult());
  }

  // test invalid input that type alphabetical characters
  @Test
  public void testInvalidInput() {
    step1 = simpleCalculator.input('1');
    try {
      step2 = step1.input('g');
      fail("should fail");
    } catch (Exception e) {
      errorMsg = e.getMessage();
      assertEquals("cannot handle invalid character:g", errorMsg);
    }
  }

  // test to put a number after = sign, then put an operator
  @Test
  public void testOperatorAfterEquals() {
    // test whether 1 + 8 1 = 9 will only return 9
    step1 = simpleCalculator.input('1');
    assertEquals("1", step1.getResult());
    step2 = step1.input('+');
    assertEquals("1+", step2.getResult());
    step3 = step2.input('8');
    assertEquals("1+8", step3.getResult());
    step4 = step3.input('1');
    assertEquals("1+81", step4.getResult());
    step5 = step4.input('=');
    assertEquals("82", step5.getResult());
    step6 = step5.input('9');
    assertEquals("9", step6.getResult());

    // following by inputting + will return 9+
    step7 = step6.input('+');
    assertEquals("9+", step7.getResult());
  }

  // test whether an overflow will happen if the result value is lower than
  // the lowest value of 32-bit integer. should return a 0
  @Test
  public void testMinusOverflow() {
    step1 = simpleCalculator.input('0');
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
  public void testInSequence() {
    step1 = simpleCalculator.input('5');
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
  public void testAddAndMinus() {
    step1 = simpleCalculator.input('1').input('1')
            .input('1').input('+').input('1')
            .input('1').input('1').input('=')
            .input('+').input('1')
            .input('+').input('1').input('=')
            .input('=');
    assertEquals("224", step1.getResult());

    step2 = step1.input('=');
    assertEquals("224", step2.getResult());

    step3 = step2.input('-')
            .input('4')
            .input('3').input('1');
    assertEquals("224-431", step3.getResult());

    step4 = step3.input('+');
    assertEquals("-207+", step4.getResult());

    step5 = step4.input('9');
    assertEquals("-207+9", step5.getResult());

    step6 = step5.input('+');
    assertEquals("-198+", step6.getResult());

    // now test step2's getResult doesn't change
    assertEquals("224", step2.getResult());
  }

  // test whether * work in the correct way
  @Test
  public void multiplication() {
    step1 = simpleCalculator.input('8').input('8').input('8').input('+')
            .input('2').input('2').input('2');
    assertEquals("888+222", step1.getResult());
    step2 = step1.input('*');
    assertEquals("1110*", step2.getResult());
    step3 = step2.input('5').input('2').input('0');
    assertEquals("1110*520", step3.getResult());
    step4 = step3.input('+');
    assertEquals("577200+", step4.getResult());
  }

  // test the case where result is overflow because of too large, causing result to be zero
  @Test
  public void testResultOverFlowTooLarge() {
    // a + b - 1 0 = should result in -10 if a+b will overflow
    step1 = simpleCalculator.input('5').input('+').input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4').input('7');
    assertEquals("5+2147483647", step1.getResult());
    step2 = step1.input('-');
    assertEquals("0-", step2.getResult());
    step3 = step2.input('1').input('0').input('=');
    assertEquals("-10", step3.getResult());
  }

  // test that the operand 1 is overflow because of too large,
  // causing an illegal argument error
  @Test
  public void testOperand1OverFlow() {
    step1 = simpleCalculator.input('2').input('1').input('4')
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
    step1 = simpleCalculator.input('4').input('2').input('9').input('4').input('9')
            .input('6').input('7').input('2').input('9').input('+').input('4')
            .input('2').input('9').input('4').input('9').input('6').input('7')
            .input('2').input('9').input('+').input('4').input('2').input('9')
            .input('4').input('9').input('6').input('7').input('2').input('9')
            .input('+').input('4').input('2').input('9').input('4').input('9')
            .input('6').input('7').input('2').input('9').input('+').input('4')
            .input('2').input('9').input('4').input('9').input('6').input('7')
            .input('2').input('9').input('+');
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
    step1 = simpleCalculator.input('4').input('+');
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
    step1 = simpleCalculator.input('1');
    // sept1 is the calling object here, so expect only step2's value change
    // and step1's value remain the same
    // plus
    step2 = step1.input('+').input('1').input('=');
    assertEquals("1", step1.getResult());
    assertEquals("2", step2.getResult());
    // minus
    step3 = simpleCalculator.input('1');
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
}